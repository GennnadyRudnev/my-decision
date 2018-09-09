package com.test.decision;


import java.util.Scanner;

/**
 * Класс являющийся точкой входа в программу
 */
public class App {
    public static void main(String[] args) {

            System.out.println("Введите команду и параметры(если имеются), например: search(команда) absolutePathToFile nameFile");
            System.out.println("Для вывода списка команд введите: list");
            System.out.println("");

            String[] comands = args;

            switch (comands[1]) {

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
                        FilesUtil.getNewFiliUtil().searchFiles(comands[2], comands[3], false);
                    }
                    break;

                case "modified":
                    if (comands.length > 2) {
                        FilesUtil.getNewFiliUtil().modifiedFile(comands[2], comands[3]);
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

