package com.expense.expenseReport.service;

import com.expense.expenseReport.entity.Expense;
import com.expense.expenseReport.repo.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExpenseService {

    // Repository to handle database operations for Expense entities
    private final ExpenseRepository expenseRepository;

    // Method to create a new Expense entry in the database
    public Expense createExpense(Expense expense) {
        try {
            // Log the creation request for debugging and auditing purposes
            log.info("Creating a new expense: {}", expense);
            // Save the expense entity to the database and return the saved instance
            return expenseRepository.save(expense);
        } catch (Exception e) {
            log.error("Error occurred while creating expense: {}", expense, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to create expense", e);
        }
    }

    // Method to retrieve all Expense entries from the database
    public List<Expense> getAllExpenses() {
        try {
            // Log the fetch request for debugging and auditing purposes
            log.info("Fetching all expenses");
            // Retrieve and return all expenses
            return expenseRepository.findAll();
        } catch (Exception e) {
            log.error("Error occurred while fetching all expenses", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to fetch expenses", e);
        }
    }

    // Method to retrieve a specific Expense by its ID
    public Expense getExpenseById(Long id) {
        try {
            // Log the fetch request for a specific ID for debugging and auditing purposes
            log.info("Fetching expense by ID: {}", id);
            // Retrieve the expense or throw an exception if not found
            return expenseRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Expense not found with ID: " + id));
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error occurred while fetching expense by ID: {}", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to fetch expense", e);
        }
    }

    // Method to update an existing Expense by its ID
    public Expense updateExpense(Long id, Expense expenseDetails) {
        try {
            // Log the update request for debugging and auditing purposes
            log.info("Updating expense with ID: {}", id);
            // Retrieve the existing expense by ID, or throw an exception if not found
            Expense expense = getExpenseById(id);
            // Update the fields of the existing expense with new values
            expense.setCategory(expenseDetails.getCategory());
            expense.setAmount(expenseDetails.getAmount());
            expense.setDate(expenseDetails.getDate());
            expense.setDescription(expenseDetails.getDescription());
            // Save the updated expense entity to the database and return the updated instance
            return expenseRepository.save(expense);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error occurred while updating expense with ID: {}", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to update expense", e);
        }
    }

    // Method to delete an existing Expense by its ID
    public void deleteExpense(Long id) {
        try {
            // Log the delete request for debugging and auditing purposes
            log.info("Deleting expense with ID: {}", id);
            // Delete the expense entity by ID
            expenseRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.error("Expense not found with ID: {}", id, e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Expense not found with ID: " + id, e);
        } catch (Exception e) {
            log.error("Error occurred while deleting expense with ID: {}", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to delete expense", e);
        }
    }
}
