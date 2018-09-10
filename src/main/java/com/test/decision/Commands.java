package com.test.decision;

/**
 * Служит описанием команд
 */
enum Commands {
    READDRIVES("readDrives", "выводит список логических дисков (локальные, CD/DWD, USB FLASH), кроме сетевых"),

    SEARCH("search [absolutePathToFile] [nameFile]", "Поиск файла по его имени: полное совпадение, частичное совпадение \n" +
            "(имя файла содержит заданное сочетание символов). Вывод на экран в \n" +
            "консоль найденных файлов.\n" + "Параметр [absolutePathToFile] - абсолютный путь к файлу, указывается без ковычек.\n" +
            "Параметр [nameFile] - имя файла, указывается без ковычек"),

    MODIFIED("modified [параметр(absolutePathToFile)] [параметр(nameFile)]", "Изменение найденных файлов.\n" +
            "Параметр [absolutePathToFile] - абсолютный путь к файлу, указывается без ковычек.\n" +
            "Параметр [nameFile] - имя файла, указывается без ковычек");

    private String descriptionCommand;
    private String command;

    Commands(String command, String descriptionCommand) {
        this.command = command;
        this.descriptionCommand = descriptionCommand;

    }

    @Override
    public String toString() {
        return
                "команда = \'" + command + '\'' + "\n" +
                        "описание: '" + descriptionCommand + '\'' + "\n";
    }
}
