import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) {
        String fileName = "text"; // Имя твоего файла

        try {
            // Чтение всего текста из файла
            String content = Files.readString(Path.of(fileName));

            // Регулярное выражение для слов (поддержка кириллицы и латиницы)
            Pattern pattern = Pattern.compile("\\b[\\p{L}\\p{Nd}]+\\b", Pattern.UNICODE_CHARACTER_CLASS);
            Matcher matcher = pattern.matcher(content.toLowerCase());

            // Сбор всех слов
            List<String> words = new ArrayList<>();
            while (matcher.find()) {
                words.add(matcher.group());
            }

            // Сортировка
            Collections.sort(words);
            System.out.println("Отсортированные слова:");
            System.out.println(words);
            System.out.println();

            // Подсчет частоты
            Map<String, Integer> freqMap = new LinkedHashMap<>();
            for (String word : words) {
                freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
            }

            System.out.println("Частота слов:");
            for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            // Нахождение максимальной частоты
            int maxFreq = Collections.max(freqMap.values());

            List<String> mostFrequentWords = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
                if (entry.getValue() == maxFreq) {
                    mostFrequentWords.add(entry.getKey());
                }
            }

            System.out.println("\nСлова с максимальной частотой (" + maxFreq + "):");
            for (String word : mostFrequentWords) {
                System.out.println(word);
            }

        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}