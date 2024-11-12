package com.expense.expenseReport.controller;

import com.expense.expenseReport.entity.Expense;
import com.expense.expenseReport.service.ExpenseService;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ReportGencController {

    private static final Logger logger = LoggerFactory.getLogger(ReportGencController.class);

    @Autowired
    ExpenseService expenseService;

    @GetMapping("/generateReport")
    public void generateReport(HttpServletResponse response) {
        try {
            // Log: Start generating report
            logger.info("Starting report generation process");

            // Your data source
            List<Expense> expenses = expenseService.getAllExpenses();
            logger.debug("Retrieved expenses from service: {} records", expenses.size());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(expenses);

            // Load the report template
            InputStream reportStream = getClass().getResourceAsStream("/reports/practice.jrxml");
            if (reportStream == null) {
                logger.error("Report template not found at /reports/practice.jrxml");
                throw new RuntimeException("Report template not found");
            }
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
            logger.info("Report template compiled successfully");

            // Add parameters to the report
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("firstName", "John");
            parameters.put("age", 30);

            // Convert string date to java.util.Date
            Date date;
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                date = dateFormat.parse("05/06/1996");
            } catch (Exception e) {
                logger.error("Error parsing date: ", e);
                throw new RuntimeException("Invalid date format");
            }
            parameters.put("dob", date);
            logger.debug("Parameters added: firstName=John, age=30, dob={}", date);

            // Add your data set parameter (assuming it is needed for the table data)
            parameters.put("ExpenseDataSet", dataSource);
            logger.debug("Data source parameter added to report");

            // Fill the report
            JasperPrint jasperPrint;
            try {
                jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
                logger.info("Report filled successfully");
            } catch (JRException e) {
                logger.error("Error filling the report: ", e);
                throw new RuntimeException("Error filling the report");
            }

            // Set response headers for PDF output
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=ExpenseReport.pdf");
            logger.debug("Response headers set for PDF output");

            // Write the PDF to the response output
            try {
                JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
                logger.info("Report successfully written to response output");
            } catch (Exception e) {
                logger.error("Error writing the report to response output: ", e);
                throw new RuntimeException("Error writing the report to response output");
            }
        } catch (Exception e) {
            logger.error("Unexpected error during report generation: ", e);
        }
    }
}
