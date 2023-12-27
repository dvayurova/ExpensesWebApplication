package pet.project.expenseswebapp.service;

import org.springframework.stereotype.Service;
import pet.project.expenseswebapp.model.Expense;
import pet.project.expenseswebapp.repository.ExpenseRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Expense findById(Long id){
        return expenseRepository.getReferenceById(id);
    }

    public List<Expense> findAll(){
        return expenseRepository.findAll();
    }

    public Expense saveExpense(Expense expense){
        return expenseRepository.save(expense);
    }

    public void deleteById(Long id){
        expenseRepository.deleteById(id);
    }

    public Map<String, Double> calculateTotalExpensesByCategory() {
        List<Expense> expenses = expenseRepository.findAll();
        Map<String, Double> expensesByCategory = new HashMap<>();

        for (Expense expense : expenses) {
            String category = expense.getCategory();
            double amount = expense.getAmount();

            expensesByCategory.put(category, expensesByCategory.getOrDefault(category, 0.0) + amount);
        }

        return expensesByCategory;
    }


    public Map<String, Double> calculateTotalExpensesByMonth(){
        List<Expense> expenses = expenseRepository.findAll();
        Map<String, Double> expensesByMonth = new HashMap<>();

        for(Expense expense : expenses) {
            String month = expense.getDate().getMonth() + " " + expense.getDate().getYear();
            double amount = expense.getAmount();

            expensesByMonth.put(month, expensesByMonth.getOrDefault(month, 0.0) + amount);
        }

        return expensesByMonth;
    }
}
