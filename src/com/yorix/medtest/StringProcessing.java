package com.yorix.medtest;

import java.util.ArrayList;

class StringProcessing {

    String[][] allQuestion(String[] lines, int numberOfAnswers) {

        ArrayList<ArrayList<String>> allQuestions = new ArrayList<>();
        int questionNumber = 0;

        allQuestions.add(new ArrayList<>());
        allQuestions.get(0).add("");
        for (int i = 0, length = lines.length; i < length; i++) {               // Счётчик от 0 до последней строки текста

            // Далее присваиваем строкам места в списке в зависимости от первого символа строки.
            // Проверяем каждую строку на правильность расположения, так как строки с вариантами ответов
            // могут быть разорваны в файле. В этом случае, склеиваем их.

            switch (lines[i].substring(0, 2).toLowerCase()) {
                case "a.":
                case "+a":
                    allQuestions.get(questionNumber).add(1, lines[i]);
                    if (!lines[i + 1].toLowerCase().startsWith("b.") && !lines[i + 1].toLowerCase().startsWith("+b."))   // Если следующая строка не начинается с B
                        allQuestions.get(questionNumber).set(1, lines[i] + " " + lines[++i]);// добавить след. строку к текущей, пропустить счётчик
                    break;
                case "b.":
                case "+b":
                    allQuestions.get(questionNumber).add(2, lines[i]);
                    if (!lines[i + 1].toLowerCase().startsWith("c.") && !lines[i + 1].toLowerCase().startsWith("+c."))
                        allQuestions.get(questionNumber).set(2, lines[i] + " " + lines[++i]);
                    break;
                case "c.":
                case "+c":
                    allQuestions.get(questionNumber).add(3, lines[i]);
                    if (!lines[i + 1].toLowerCase().startsWith("d.") && !lines[i + 1].toLowerCase().startsWith("+d."))
                        allQuestions.get(questionNumber).set(3, lines[i] + " " + lines[++i]);
                    break;
                case "d.":
                case "+d":
                    allQuestions.get(questionNumber).add(4, lines[i]);
                    if (!lines[i + 1].toLowerCase().startsWith("e.") && !lines[i + 1].toLowerCase().startsWith("+e."))
                        allQuestions.get(questionNumber).set(4, lines[i] + " " + lines[++i]);
                    break;
                case "e.":
                case "+e":
                    allQuestions.get(questionNumber).add(5, lines[i]);
                    questionNumber++;                                               // увеличить номер вопроса
                    allQuestions.add(new ArrayList<>());
                    allQuestions.get(questionNumber).add("");
                    break;
                default:
                    allQuestions.get(questionNumber).set(0, allQuestions.get(questionNumber).get(0) + " " + lines[i]);
                    break;
            }
        }

        allQuestions.remove(allQuestions.size() - 1);
        String[][] ques = new String[allQuestions.size()][numberOfAnswers + 1];

        for (int i = 0, size = allQuestions.size(); i < size; i++) {
            ques[i] = allQuestions.get(i).toArray(new String[numberOfAnswers + 1]);
        }
        return ques;
    }


    // Проверка на наличие в текстве "+" в качестве отметки правильных ответов
    boolean checkTextType(String[] question) {
        for (int i = 1, length = question.length - 1; i < length; i++) {
            if (question[i].startsWith("+"))
                return true;
        }
        return false;
    }
}
