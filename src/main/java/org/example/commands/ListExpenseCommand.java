package org.example.commands;

import org.example.models.Expense;
import org.example.repositories.ExpenseRepository;
import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(
        name = "list",
        description = "Lista todas as despesas do ficheiro CSV.",
        mixinStandardHelpOptions = true)
public class ListExpenseCommand implements Runnable {
    private ExpenseRepository repository = new ExpenseRepository();
    private List<Expense> expenses;

    public void run() {
        try {
           expenses = repository.findAll();
            System.out.println("ID   Date           Description    Amount");
            expenses.forEach(expense -> System.out.println(expense));
        } catch (Exception e) {
            System.out.println("An error occured: " + e.getMessage());
        }
    }
}
