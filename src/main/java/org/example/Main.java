package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("encryption.txt")));
        content = content.replaceAll("[^A-Za-z ]", " ").toLowerCase(Locale.ROOT);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть число 'a' (число % 26): ");
        int a = scanner.nextInt();

        System.out.print("Введіть число 'b' (число % 26): ");
        int b = scanner.nextInt();

        if (gcd(a, 26) != 1) {
            System.out.println("Помилка: 'a' і 26 не є взаємно простими.");
            return;
        }

        String encryptedText = encrypt(content, a, b);
        BufferedWriter writer = new BufferedWriter(new FileWriter("encrypted_output.txt"));
        writer.write(encryptedText);
        writer.close();
        System.out.println("Зашифрований текст: " + encryptedText);
    }

    public static String encrypt(String text, int a, int b) {
        StringBuilder encryptedText = new StringBuilder();
        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                int x = character - 'A';
                x = (a * x + b) % 26;
                encryptedText.append((char) (x + 'A'));
            } else {
                encryptedText.append(character);
            }
        }
        return encryptedText.toString();
    }

    public static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }
}
