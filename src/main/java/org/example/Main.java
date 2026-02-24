package org.example;

import org.example.commands.MainCommand;
import picocli.CommandLine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MainCommand mainCommand = new MainCommand();
        CommandLine cmd = new CommandLine(mainCommand);

        if (args.length > 0) {
            int exitCode = cmd.execute(args);
            System.exit(exitCode);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Person CLI - Modo Interativo ===");


        while (true) {
            System.out.print("expense-tracker > ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
                break;
            }

            if (input.isBlank()) continue;

            String[] interactiveArgs = input.split("(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$) ");

            for (int i = 0; i < interactiveArgs.length; i++) {
                interactiveArgs[i] = interactiveArgs[i].replace("\"", "");
            }

            cmd.execute(interactiveArgs);
        }

        System.out.println("Saindo...");
    }
}