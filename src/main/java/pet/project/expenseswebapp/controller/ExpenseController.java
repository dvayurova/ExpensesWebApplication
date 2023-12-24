package pet.project.expenseswebapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pet.project.expenseswebapp.model.Expense;
import pet.project.expenseswebapp.service.ExpenseService;

import java.util.List;

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
    public String createExpense(Expense expense){
        expenseService.saveExpense(expense);
        return "redirect:/expenses";
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
    public String updateExpense(@ModelAttribute("expense") Expense expense){
        expenseService.saveExpense(expense);
        return "redirect:/expenses";
    }
}
