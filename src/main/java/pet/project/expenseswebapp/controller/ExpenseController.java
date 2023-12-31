package pet.project.expenseswebapp.controller;


import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pet.project.expenseswebapp.model.Expense;
import pet.project.expenseswebapp.service.ExpenseService;

import java.util.List;
import java.util.Map;

@Controller
class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/expenses")
    public String findAll(Model model){
        List<Expense> expenses = expenseService.findAll();
        model.addAttribute("expenses", expenses);
        return "expense-list";
    }

    @GetMapping("/expense-create")
    public String createExpenseForm(Expense expense){
        return "expense-create";
    }

    @PostMapping("expense-create")
    public String createExpense(@Valid Expense expense, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("errors", result.getAllErrors());
            return "expense-create";
        }
        expenseService.saveExpense(expense);
        return "redirect:/expenses";
    }

    @GetMapping("/expenses-by-category")
    public String expensesByCategory(Model model) {
        Map<String, Double> expensesByCategory = expenseService.calculateTotalExpensesByCategory();
        model.addAttribute("expensesByCategory", expensesByCategory);
        return "expenses-by-category";
    }

    @GetMapping("/expenses-by-month")
    public String expensesByMonth(Model model){
        Map<String, Double> expensesByMonth = expenseService.calculateTotalExpensesByMonth();
        model.addAttribute("expensesByMonth", expensesByMonth);
        return  ("expenses-by-month");
    }

    @GetMapping("/expense-delete/{id}")
    public String deleteExpense(@PathVariable("id") Long id){
        expenseService.deleteById(id);
        return "redirect:/expenses";
    }

    @GetMapping("/expense-update/{id}")
    public String updateExpenseForm(@PathVariable("id") Long id, Model model){
        Expense expense = expenseService.findById(id);
        model.addAttribute("expense", expense);
        return "expense-update";
    }

    @PostMapping("/expense-update")
    public String updateExpense(@Valid @ModelAttribute("expense") Expense expense, BindingResult result){
        if(result.hasErrors()){
            return "/expense-update";
        }
        expenseService.saveExpense(expense);
        return "redirect:/expenses";
    }
}
