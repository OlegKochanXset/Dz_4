import java.io.*;              // Импортируем классы для работы с вводом-выводом
import java.nio.file.*;         // Импортируем классы для работы с файлами и путями
import java.util.*;             // Импортируем коллекции: списки, карты и др.
import java.util.regex.*;       // Импортируем классы для работы с регулярными выражениями

public class Main {

    public static void main(String[] args) {
        String fileName = "text";                       // Имя файла для чтения

        try {
            String content = Files.readString(Path.of(fileName));   // Читаем весь текст из файла в одну строку

            Pattern pattern = Pattern.compile(                      // Создаем регулярное выражение для поиска слов
                    "\\b[\\p{L}\\p{Nd}]+\\b",                          // Поддержка букв (латиница, кириллица) и цифр
                    Pattern.UNICODE_CHARACTER_CLASS);

            Matcher matcher = pattern.matcher(content.toLowerCase());  // Ищем слова в тексте, приводя всё к нижнему регистру

            List<String> words = new ArrayList<>();                 // Создаем список для хранения найденных слов

            while (matcher.find()) words.add(matcher.group());      // Пока находим слова — добавляем их в список

            Collections.sort(words);                                 // Сортируем слова в алфавитном порядке

            System.out.println("Отсортированные слова:");          // Выводим заголовок
            System.out.println(words);                               // Выводим отсортированный список слов
            System.out.println();                                    // Пустая строка для разделения

            Map<String, Integer> freqMap = new LinkedHashMap<>();   // Создаем карту для подсчёта частоты слов

            for (String word : words)                                // Для каждого слова в списке
                freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);// Увеличиваем счётчик в карте на 1

            int totalWords = words.size();                           // Общее количество слов

            System.out.println("Частота слов (и процент от общего числа):");  // Заголовок
            for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
                String word = entry.getKey();                        // Слово
                int count = entry.getValue();                        // Кол-во раз, сколько встретилось
                double percent = (count * 100.0) / totalWords;       // Вычисляем процент
                System.out.printf("%s: %d (%.2f%%)%n", word, count, percent); // Выводим с форматированием
            }

            int maxFreq = Collections.max(freqMap.values());         // Находим максимальную частоту слов

            List<String> mostFrequentWords = new ArrayList<>();      // Список для слов с максимальной частотой

            for (Map.Entry<String, Integer> entry : freqMap.entrySet()) // Перебираем все слова и их частоты
                if (entry.getValue() == maxFreq)                      // Если частота равна максимальной
                    mostFrequentWords.add(entry.getKey());            // Добавляем слово в список

            System.out.println("\nСлова с максимальной частотой (" + maxFreq + "):"); // Заголовок для самых частых слов
            for (String word : mostFrequentWords)
                System.out.println(word);                             // Выводим каждое слово с максимальной частотой

        } catch (IOException e) {                                     // Обрабатываем исключение при ошибке чтения файла
            System.err.println("Ошибка при чтении файла: " + e.getMessage()); // Выводим сообщение об ошибке
        }
    }
}
