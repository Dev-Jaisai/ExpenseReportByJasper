package com.expense.ExpenseReport.repo;
import com.expense.ExpenseReport.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
