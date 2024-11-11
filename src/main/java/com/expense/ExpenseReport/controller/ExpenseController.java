package com.expense.ExpenseReport.controller;
import com.expense.ExpenseReport.entity.Expense;
import com.expense.ExpenseReport.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        log.info("Received request to create expense: {}", expense);
        return ResponseEntity.ok(expenseService.createExpense(expense));
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        log.info("Received request to fetch all expenses");
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        log.info("Received request to fetch expense by ID: {}", id);
        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense expenseDetails) {
        log.info("Received request to update expense with ID: {}", id);
        return ResponseEntity.ok(expenseService.updateExpense(id, expenseDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        log.info("Received request to delete expense with ID: {}", id);
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}
