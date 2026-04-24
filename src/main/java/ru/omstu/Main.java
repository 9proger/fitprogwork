package ru.omstu;

import ru.omstu.interfaces.DataParser;
import ru.omstu.interfaces.ParserFactory;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Введите формат (json/xml) или 'exit': ");
            String format = scanner.nextLine().toLowerCase().trim();
            if (format.equals("exit")) break;

            try {
                DataParser parser = ParserFactory.getParser(format);

                System.out.print("Имя файла (example.json): ");
                String file = scanner.nextLine().trim();

                System.out.print("Путь (/relation/[1]/name): ");
                String path = scanner.nextLine().trim();

                System.out.println("Результат: " + parser.parse(file, path));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("---");
        }
    }
}
