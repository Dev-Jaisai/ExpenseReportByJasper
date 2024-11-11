package com.expense.ExpenseReport.service;
import com.expense.ExpenseReport.entity.Expense;
import com.expense.ExpenseReport.repo.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public Expense createExpense(Expense expense) {
        log.info("Creating a new expense: {}", expense);
        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        log.info("Fetching all expenses");
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(Long id) {
        log.info("Fetching expense by ID: {}", id);
        return expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with ID: " + id));
    }

    public Expense updateExpense(Long id, Expense expenseDetails) {
        log.info("Updating expense with ID: {}", id);
        Expense expense = getExpenseById(id);
        expense.setCategory(expenseDetails.getCategory());
        expense.setAmount(expenseDetails.getAmount());
        expense.setDate(expenseDetails.getDate());
        expense.setDescription(expenseDetails.getDescription());
        return expenseRepository.save(expense);
    }

    public void deleteExpense(Long id) {
        log.info("Deleting expense with ID: {}", id);
        Expense expense = getExpenseById(id);
        expenseRepository.delete(expense);
    }
}
