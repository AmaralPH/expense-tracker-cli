package org.example.commands;

import org.example.repositories.ExpenseRepository;
import picocli.CommandLine;

@CommandLine.Command(
        name = "summary",
        description = "Retorna o somatorio despesas.",
        mixinStandardHelpOptions = true)
public class SummaryExpenseCommand implements Runnable {
    ExpenseRepository repository = new ExpenseRepository();

    @CommandLine.Option(names = {"-m", "--month"}, required = false, description = "Mês buscado")
    int month = 0;


    public void run() {
        double sum = month != 0 ? repository.summary(month) : repository.summary();
        System.out.println("Total expense: $" + sum);
    }
}
