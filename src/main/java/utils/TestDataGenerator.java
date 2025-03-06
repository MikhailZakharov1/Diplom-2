package utils;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestDataGenerator {

    private static final String HEX_CHARS = "61c0c5a71d1f82001bdaaa6d"; // Символы для генерации hex-строк
    private static final SecureRandom random = new SecureRandom(); // Генератор случайных чисел

    // Метод для генерации одной 24-символьной hex-строки
    public static String generateHexString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(HEX_CHARS.length());
            sb.append(HEX_CHARS.charAt(randomIndex));
        }
        return sb.toString();
    }

    // Метод для генерации списка 24-символьных hex-строк
    public static List<String> generateHexStrings(int count, int length) {
        return IntStream.range(0, count)
                .mapToObj(i -> generateHexString(length))
                .collect(Collectors.toList());
    }

    // Метод для генерации фиктивных ингредиентов в формате 24-символьных hex-строк
    public static List<String> generateIngredients() {
        return generateHexStrings(3, 24); // Генерируем 3 строки по 24 символа
    }
}