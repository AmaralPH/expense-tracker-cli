package org.example.repositories;

import org.example.models.Expense;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseRepository {
    private static final String CSV_FILE_PATH = "data/expenses.csv";
    private static final String HEADER = "ID,DATE,DESCRIPTION,AMOUNT\n";
    private List<Expense> expenses = new ArrayList<>();

    public ExpenseRepository() {
        ensureDataDirectoryExists();
        refreshLocalList();
    }

    private void refreshLocalList() {
        try {
            this.expenses = findAll();
        } catch (Exception e) {
            this.expenses = new ArrayList<>();
        }
    }

    public Expense save(Expense expense) throws Exception {
        refreshLocalList();

        if (expenses.isEmpty()) {
            expense.setId(1);
        } else {
            int lastId = expenses.get(expenses.size() - 1).getId();
            expense.setId(lastId + 1);
        }

        expense.setDate(LocalDate.now());
        expenses.add(expense);

        Path path = Paths.get(CSV_FILE_PATH);
        boolean isNewFile = !Files.exists(path) || Files.size(path) == 0;

        String csvLine = String.format("%d,%s,%s,%s\n",
                expense.getId(),
                expense.getDate(),
                expense.getDescription(),
                expense.getAmount());

        if (isNewFile) {
            Files.writeString(path, HEADER + csvLine, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } else {
            Files.writeString(path, csvLine, StandardOpenOption.APPEND);
        }
        return expense;
    }

    public List<Expense> findAll() throws Exception {
        Path path = Paths.get(CSV_FILE_PATH);
        List<Expense> loadedExpenses = new ArrayList<>();

        if (!Files.exists(path) || Files.size(path) == 0) {
            return loadedExpenses;
        }

        List<String> lines = Files.readAllLines(path);

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);

            if (line.trim().isEmpty()) continue;

            String[] columns = line.split(",");

            if (columns.length == 4) {
                Expense exp = new Expense();
                exp.setId(Integer.parseInt(columns[0].trim()));
                exp.setDate(LocalDate.parse(columns[1].trim()));
                exp.setDescription(columns[2].trim());
                exp.setAmount(Double.parseDouble(columns[3].trim()));

                loadedExpenses.add(exp);
            }
        }

        return loadedExpenses;
    }

    public boolean delete(int id) throws Exception {
        refreshLocalList();

        boolean isRemoved = expenses.removeIf(expense -> expense.getId() == id);

        if (isRemoved) {
            Path path = Paths.get(CSV_FILE_PATH);
            StringBuilder newFileContent = new StringBuilder(HEADER);

            for (Expense exp : expenses) {
                newFileContent.append(String.format("%d,%s,%s,%s\n",
                        exp.getId(),
                        exp.getDate(),
                        exp.getDescription(),
                        exp.getAmount()));
            }

            Files.writeString(path, newFileContent.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }

        return isRemoved;
    }

    public double summary() {
        refreshLocalList();
        double sum = 0;

        for (Expense expense : expenses) {
            sum += expense.getAmount();
        }

        return sum;
    }

    public double summary(int month) {
        refreshLocalList();
        double sum = 0;

        for (Expense expense : expenses) {
            if (expense.getDate().getMonthValue() == month) {
                sum += expense.getAmount();
            }
        }

        return sum;
    }

    private void ensureDataDirectoryExists() {
        try {
            Path path = Paths.get("data");
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            System.err.println("Erro ao criar diretório de dados: " + e.getMessage());
        }
    }
}