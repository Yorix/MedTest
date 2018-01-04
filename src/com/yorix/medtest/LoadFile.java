package com.yorix.medtest;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class LoadFile {

    File file() {
        JFileChooser chooser = new JFileChooser("example");
        chooser.setFileFilter(new FileNameExtensionFilter("Только текстовый файл", "txt"));
        int returnVal = chooser.showOpenDialog(new JFrame());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        }
        System.exit(0);
        return null;
    }

    String[] read(File file) throws FileNotFoundException {
        Scanner fileReader = new Scanner(file);         // Создание сканнера файла
        ArrayList<String> lines = new ArrayList<>();    // Создать объект списка для хранения строк из файла
        int x = 0;
        while (fileReader.hasNextLine()) {              // Пока файл не кончился
            lines.add(fileReader.nextLine().trim());    // Назначить следующему элементу списка следующую строку, обрезав пробелы

            // Удаляем пустые строки
            if (lines.get(x).equals(""))
                lines.remove(x--);
            x++;
        }
        return lines.toArray(new String[lines.size()]);
    }
}
