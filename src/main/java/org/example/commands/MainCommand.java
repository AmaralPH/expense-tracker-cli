package org.example.commands;

import picocli.CommandLine;

@CommandLine.Command(
        name = "expense-tracker",
        mixinStandardHelpOptions = true,
        version = "Expense Tracker CLI 1.0",
        description = "Uma ferramenta simples para gerir suas despesas num ficheiro CSV.",
        subcommands = {
                AddExpenseCommand.class,
                ListExpenseCommand.class,
                DeleteExpenseCommand.class,
                SummaryExpenseCommand.class
        }
)
public class MainCommand implements Runnable {

    @Override
    public void run() {
        CommandLine.usage(this, System.out);
    }
}
