package com.expense.expenseReport.controller;

import com.expense.expenseReport.entity.Expense;
import com.expense.expenseReport.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    // Service to handle business logic for Expense entities
    private final ExpenseService expenseService;

    // Endpoint to create a new expense
    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        try {
            // Log the request for debugging purposes
            log.info("Received request to create expense: {}", expense);
            // Call the service layer to create the expense and return the created expense
            return ResponseEntity.ok(expenseService.createExpense(expense));
        } catch (Exception e) {
            log.error("Error while creating expense: {}", expense, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to create expense", e);
        }
    }

    // Endpoint to fetch all expenses
    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        try {
            // Log the request for debugging purposes
            log.info("Received request to fetch all expenses");
            // Call the service layer to fetch all expenses and return them
            return ResponseEntity.ok(expenseService.getAllExpenses());
        } catch (Exception e) {
            log.error("Error while fetching all expenses", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to fetch expenses", e);
        }
    }

    // Endpoint to fetch a specific expense by ID
    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        try {
            // Log the request for debugging purposes
            log.info("Received request to fetch expense by ID: {}", id);
            // Call the service layer to fetch the expense by ID and return it
            return ResponseEntity.ok(expenseService.getExpenseById(id));
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error while fetching expense by ID: {}", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to fetch expense", e);
        }
    }

    // Endpoint to update an existing expense by ID
    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense expenseDetails) {
        try {
            // Log the request for debugging purposes
            log.info("Received request to update expense with ID: {}", id);
            // Call the service layer to update the expense and return the updated expense
            return ResponseEntity.ok(expenseService.updateExpense(id, expenseDetails));
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error while updating expense with ID: {}", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to update expense", e);
        }
    }

    // Endpoint to delete an existing expense by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        try {
            // Log the request for debugging purposes
            log.info("Received request to delete expense with ID: {}", id);
            // Call the service layer to delete the expense
            expenseService.deleteExpense(id);
            // Return a response indicating that the deletion was successful with no content
            return ResponseEntity.noContent().build();
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error while deleting expense with ID: {}", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to delete expense", e);
        }
    }
}
