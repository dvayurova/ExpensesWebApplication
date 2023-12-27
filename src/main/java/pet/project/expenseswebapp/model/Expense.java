package pet.project.expenseswebapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Please provide a date")
    private  LocalDate date;
    @Positive(message = "Amount must be a positive number")
    private  float amount;
    @NotBlank(message = "Please enter a description")
    private  String description;
    @NotBlank(message = "Category cannot be blank")
    private  String category;

    public Expense() {
    }

    public Expense(Long id, LocalDate date, float amount, String description, String category) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.category = category;
    }
}
