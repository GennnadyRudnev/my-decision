package com.test.decision;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Данный класс предоставляет статические методы для
 * получения ифнормации о логических дисках в системе windows.
 */
public  final class Drives {
    private Drives(){}

    /**
     * Выводит в консоль список логических дисков (Локальные диски, CD/DWD-ROM, USB FLASH) в системе,
     * не включая сетевые диски.
     */
    public static void readDrives() {
        File[] paths;
        FileSystemView fsv = FileSystemView.getFileSystemView();

        // возвращает имена путей для файлов и каталогов
        paths = File.listRoots();

        for(File path:paths)
        {
            String driveLetter = path.getAbsolutePath().substring(0,1);

            if (isNetworkDrive(driveLetter)) {
                continue;
            }

            // печатает пути к файлам и каталогам
            System.out.println("Drive Name: "+path);
            System.out.println("Description: "+fsv.getSystemTypeDescription(path));
        }
    }

    /**
     * Проверяет является ли логический диск сетевым
     * @param driveLetter
     *        Буквенное обозначение диска
     * @return возвращает true если диск ялвяется сетевым или false если не является
     * @throws IllegalStateException
     *         если не удалось запустить 'net use' на логическом диске
     */
    private static boolean isNetworkDrive(String driveLetter) {
        List<String> cmd = Arrays.asList("cmd", "/c", "net", "use", driveLetter + ":");

        try {
            Process process = new ProcessBuilder(cmd)
                    .redirectErrorStream(true)
                    .start();

            process.getOutputStream().close();

            int result = process.waitFor();

            return result == 0;

        } catch(Exception e) {
            throw new IllegalStateException("Не удалось запустить 'net use' на " + driveLetter, e);
        }
    }

}
