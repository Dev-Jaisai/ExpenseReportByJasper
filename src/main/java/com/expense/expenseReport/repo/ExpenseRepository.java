package com.expense.expenseReport.repo;
import com.expense.expenseReport.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
