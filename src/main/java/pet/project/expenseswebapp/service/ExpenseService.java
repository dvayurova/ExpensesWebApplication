package pet.project.expenseswebapp.service;

import org.springframework.stereotype.Service;
import pet.project.expenseswebapp.model.Expense;
import pet.project.expenseswebapp.repository.ExpenseRepository;

import java.util.List;

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
}
