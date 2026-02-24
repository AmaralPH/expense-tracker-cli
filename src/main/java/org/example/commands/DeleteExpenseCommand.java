package org.example.commands;

import org.example.repositories.ExpenseRepository;
import picocli.CommandLine;

@CommandLine.Command(
        name = "delete",
        description = "Deleta uma despesa do ficheiro CSV.",
        mixinStandardHelpOptions = true)
public class DeleteExpenseCommand implements Runnable {
    ExpenseRepository repository = new ExpenseRepository();

    @CommandLine.Option(names = {"--id"}, required = true, description = "ID da despesa")
    private int id;

    public void run() {
        try {
            repository.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
