package pet.project.expenseswebapp.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import pet.project.expenseswebapp.model.Expense;
import pet.project.expenseswebapp.service.ExpenseService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExpenseControllerTest {

    @InjectMocks
    private ExpenseController expenseController;
    @Mock
    private ExpenseService expenseService;

    @Test
    public void testFindAll() {
        Model model = mock(Model.class);
        when(expenseService.findAll()).thenReturn(List.of(new Expense(1L, LocalDate.now(), 50, "milk", "Supermarket")));
        String viewName = expenseController.findAll(model);
        Assertions.assertEquals("expense-list", viewName);
        verify(model).addAttribute("expenses", List.of(new Expense(1L, LocalDate.now(), 50, "milk", "Supermarket")));
        verify(expenseService, times(1)).findAll();
    }

    @Test
    public void testCreateExpenseForm() {
        String viewName = expenseController.createExpenseForm(new Expense());
        Assertions.assertEquals("expense-create", viewName);
    }

    @Test
    public void testCreateExpenseValid() {
        Expense validExpense = new Expense(1L, LocalDate.now(), 35.4f, "a soap", "Beauty and health");
        String viewName = expenseController.createExpense(validExpense, mock(BindingResult.class), mock(Model.class));
        Assertions.assertEquals("redirect:/expenses", viewName);
        verify(expenseService, times(1)).saveExpense(validExpense);
    }

    @Test
    public void testCreateExpenseInvalid() {
        Model model = mock(Model.class);
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);
        String viewName = expenseController.createExpense(new Expense(), result, model);
        Assertions.assertEquals("expense-create", viewName);
        verify(model).addAttribute("errors", result.getAllErrors());
        verify(expenseService, never()).saveExpense(new Expense());
    }

    @Test
    public void testExpensesByCategory(){
        Model model = mock(Model.class);
        Map<String, Double> map = Map.of("Supermarket", 200.0);
        when(expenseService.calculateTotalExpensesByCategory()).thenReturn(map);
        String viewName = expenseController.expensesByCategory(model);
        Assertions.assertEquals("expenses-by-category", viewName);
        verify(model).addAttribute("expensesByCategory", map);
    }

    @Test
    public void testExpensesByMonth(){
        Model model = mock(Model.class);
        Map<String, Double> map = Map.of("Supermarket", 200.0);
        when(expenseService.calculateTotalExpensesByMonth()).thenReturn(map);
        String viewName = expenseController.expensesByMonth(model);
        Assertions.assertEquals("expenses-by-month", viewName);
        verify(model).addAttribute("expensesByMonth", map);
    }

    @Test
    public void testUpdateExpenseForm(){
        Model model = mock(Model.class);
        Expense expense = new Expense();
        when(expenseService.findById(2L)).thenReturn(expense);
        String viewName = expenseController.updateExpenseForm(2L, model);
        Assertions.assertEquals("expense-update", viewName);
        verify(model).addAttribute("expense", expense);
    }

    @Test
    public void testUpdateExpenseValid() {
        Expense validExpense = new Expense(1L, LocalDate.now(), 35.4f, "a soap", "Beauty and health");
        String viewName = expenseController.updateExpense(validExpense, mock(BindingResult.class));
        Assertions.assertEquals("redirect:/expenses", viewName);
        verify(expenseService, times(1)).saveExpense(validExpense);
    }

    @Test
    public void testUpdateExpenseInvalid() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);
        String viewName = expenseController.updateExpense(new Expense(), result);
        Assertions.assertEquals("/expense-update", viewName);
        verify(expenseService, never()).saveExpense(new Expense());
    }
}

