package pet.project.expenseswebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pet.project.expenseswebapp.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
