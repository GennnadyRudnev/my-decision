package com.test.decision;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Данный класс служит для работы с файлами
 */
public final class FilesUtil {
    private FilesUtil() {
    }

    /**
     * список для хранения имен файлов при поиске файла или при совпадении имен
     */
    private List<String> list;

    public static FilesUtil getNewFiliUtil() {
        return new FilesUtil();
    }

    /**
     * осуществляет поиск файла по его имени
     *
     * @param pathFile  абсолютный путь к файлу
     * @param nameFile  имя файла (полное или его часть)
     * @param cleanList флаг, если установлен в true, то по завершению метода, list
     *                  будет очищен
     */
    public void searchFiles(String pathFile, String nameFile, boolean cleanList) {

        String[] listFiles = null;
        List<String> fileNames = new ArrayList<>();

        // проверяем существует ли директория и не является ли nameFile директорией
        if (Files.exists(Paths.get(pathFile)) && !Files.isDirectory(Paths.get(pathFile, nameFile))) {

            // проверяем, что файл существует, при условии что nameFile это полное имя,
            // иначе просматриваем все файлы на совпадение имени
            if (Files.exists(Paths.get(pathFile, nameFile))) {

                System.out.println(nameFile);

                fileNames.add(nameFile);
            } else {

                listFiles = new File(pathFile).list();

                for (String file : listFiles) {

                    if (file.contains(nameFile)) {
                        fileNames.add(file);
                    }
                }

                if (fileNames.isEmpty()) {
                    System.out.println("Файл не найден или не существует.");
                }

                fileNames.stream()
                        .forEach(System.out::println);
            }

            list = fileNames;

            if (cleanList == true) {
                list.clear();
            }

        } else {
            System.out.println("Директория не найдена или не существует.");
            list = fileNames;
        }
    }

    /**
     * изменяет содержимое файла
     *
     * @param pathFile абсолютный путь к файлу
     * @param nameFile имя файла (полное или его часть)
     * @throws IOException
     *         если возникает ошибка ввода/вывода
     */
    public void modifiedFile(String pathFile, String nameFile) {
        searchFiles(pathFile, nameFile, false);

        if (list.size() == 0) {
            return;
        }

        List<String> stringList = null;

        try {
            for (int i = 0; i < list.size(); i++) {
                //проверка, является ли файл текстовым
                if (!(list.get(i).endsWith(".txt"))) {
                    continue;
                }

                Path oldFile = Paths.get(pathFile, list.get(i));
                Path newFile = Paths.get(pathFile, list.get(i) + " copy");

                stringList = Files.readAllLines(oldFile);

                try (BufferedWriter bufferedWriter = Files.newBufferedWriter(newFile)) {

                    bufferedWriter.write(String.valueOf(generateString(10) + "\n"));

                    for (String s : stringList) {
                        bufferedWriter.write(s);
                    }
                    bufferedWriter.flush();
                }

                Files.delete(oldFile);
                Files.move(newFile, oldFile);

            }

            list.clear();
        } catch (IOException e) {
            System.out.println("Произошла непредвиденная ошибка попробуйте еще раз.");
        }
    }

    /**
     * генерирует случайную строку указанной длины
     * @param length
     *        длина строки
     * @return
     *        возвращает случайно сгенерированную строку указанной длины
     */
    public static String generateString(int length)
    {
        String characters = "abcdefghijklmnopqrstuvwxyzABCEDFGHIJKLMNOPQRSTUVWXYZ1234567890";
        Random rng = new Random(characters.length()-1);

        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }
}
