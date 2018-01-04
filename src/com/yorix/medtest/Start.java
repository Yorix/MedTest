package com.yorix.medtest;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Start {
    public static void main(String[] args) throws FileNotFoundException {
        LoadFile loadFile;                  // Объект класса LoadFile
        File file;                          // Файл
        MakeTest makeTest;                  // Объект класса, формирующего тестирование
        Gui window;                         // Объект ГЮИ
        String fileName;                    // Имя файла
        String[] lines;                     // Строки из файла
        StringProcessing stringProcessing;  // Объект класса обработки строк
        String[][] allQuestions;            // Массив готовых вопросов
        int numberOfQuestions;              // Количество вопросов в текущем тесте
        int numberOfAnswers = 5;            // Количество вариантов ответа
        boolean textType;                   // Определение типа текста (+ или A)
        String[][] question;                // Массив вопросов для текущего теста
        int[] trueAnswer;                   // Массив верных ответов

        loadFile = new LoadFile();          // Создание объекта файла
        file = loadFile.file();             // Загрузка файла
        fileName = file.getName();          // Получение имени файла из файла

        // Вызов метода объекта, возвращающего список строк из файла. Присвоение перменной lines значений списка.
        lines = loadFile.read(file);


        stringProcessing = new StringProcessing(); // создание объекта класса генерирования вопросов-ответов.
        allQuestions = stringProcessing.allQuestion(lines, numberOfAnswers);    // Создание массива вопросов
        textType = stringProcessing.checkTextType(allQuestions[0]); // Определение типа обозначения верного ответа (+ или A)

        JFrame.setDefaultLookAndFeelDecorated(true);
        window = new Gui(fileName);                 // Объект ГЮИ
        numberOfQuestions = window.numberOfQuestions(); // Получение числа вопросов текущего тестирования
        makeTest = new MakeTest();             // Объект класса, формирующего тестирование

        question = makeTest.test(allQuestions, numberOfQuestions, numberOfAnswers, textType);  // Сформировать массив вопросов тестирования
        trueAnswer = makeTest.getTrueAnswer();                                          // Получить массив верных ответов

        window.BuildWindow(fileName, question, trueAnswer, numberOfAnswers);   // Вызов окна тестирования
    }
}
