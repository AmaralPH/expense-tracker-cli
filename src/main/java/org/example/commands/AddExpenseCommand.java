package org.example.commands;

import org.example.models.Expense;
import org.example.repositories.ExpenseRepository;
import picocli.CommandLine;

import java.time.LocalDateTime;

@CommandLine.Command(
        name = "add",
        description = "Adiciona uma nova despesa ao ficheiro CSV.",
        mixinStandardHelpOptions = true)
public class AddExpenseCommand implements Runnable {
    ExpenseRepository repository = new ExpenseRepository();
    @CommandLine.Option(names = {"-d", "--description"}, required = true, description = "Descrição da despesa")
    private String description;

    @CommandLine.Option(names = {"-a", "--amount"}, required = true, description = "Valor gasto na transação")
    private double amount;

    public void run() {
        Expense expense = new Expense(description, amount);
        try {
            expense = repository.save(expense);
            System.out.println("Expense added successfully (ID: " + expense.getId() + ")");
        } catch (Exception e) {
            System.out.println("An error occured: " + e.getMessage());
        }
    }
}