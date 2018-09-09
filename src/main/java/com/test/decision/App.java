package com.test.decision;

/**
 * Класс являющийся точкой входа в программу
 */
public class App {
    public static void main(String[] args) {

        if (args == null) return;
        String[] comands = args;

        switch (comands[0]) {

            case "list":
                System.out.println();
                for (Commands command : Commands.values()) {
                    System.out.println(command);
                }
                break;

            case "readDrives":
                Drives.readDrives();
                break;

            case "search":
                if (comands.length > 2) {
                    FilesUtil.getNewFiliUtil().searchFiles(comands[1], comands[2], false);
                }
                break;

            case "modified":
                if (comands.length > 2) {
                    FilesUtil.getNewFiliUtil().modifiedFile(comands[1], comands[2]);
                }
                break;

            default:
                System.out.println();
                for (Commands command : Commands.values()) {
                    System.out.println(command);
                }
                break;
        }
    }
}

