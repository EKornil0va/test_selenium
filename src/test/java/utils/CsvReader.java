package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    public static Object[][] getData(String fileName) {
        List<Object[]> dataList = new ArrayList<>();
        InputStream inputStream = CsvReader.class.getClassLoader().getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new RuntimeException("Файл не найден: " + fileName);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                if (line.trim().isEmpty()) continue;

                String[] values = line.split(",");
                if (values.length >= 4) {
                    boolean shouldSucceed = Boolean.parseBoolean(values[2].trim());
                    dataList.add(new Object[]{
                            values[0].trim(),
                            values[1].trim(),
                            shouldSucceed,
                            values[3].trim()
                    });
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении CSV файла: " + fileName, e);
        }
        return dataList.toArray(new Object[0][]);
    }
}