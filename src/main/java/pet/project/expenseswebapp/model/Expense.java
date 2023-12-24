package pet.project.expenseswebapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  LocalDate date;
    private  float amount;
    private  String description;

    public Expense() {
    }

    public Expense(Long id, LocalDate date, float amount, String description) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.description = description;
    }
}
