package com.yorix.medtest;

import javax.swing.*;
import java.awt.*;

class Gui extends JFrame {

    private boolean[] change;
    private int score = 0;                                      // Переменная для подсчёта баллов

    Gui(String title) {
        super("MedTest: " + title.substring(0, title.length() - 4));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocation(200, 100);
    }

    int numberOfQuestions() {
        int numberOfQuestions = 0;
        do {
            String input = JOptionPane.showInputDialog("Введите необходимое количество вопросов:");
            if (input == null)
                System.exit(0);
            try {
                numberOfQuestions = Integer.parseInt(input);
            } catch (NumberFormatException ignored) {
            }
        }
        while (numberOfQuestions > 200 || numberOfQuestions < 1);
        return numberOfQuestions;
    }

    void BuildWindow(String title, String[][] question, int[] trueAnswer, int numberOfAnswers) {

        JPanel content = new JPanel(new BorderLayout());
        JPanel topFrame = new JPanel();
        JPanel botFrame = new JPanel(new GridBagLayout());
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel[] questionPanel = new JPanel[question.length];
        JRadioButton[][] radioButton = new JRadioButton[question.length][numberOfAnswers];
        ButtonGroup[] buttonGroup = new ButtonGroup[question.length];
        JButton back = new JButton("Назад");
        JButton next = new JButton("Далее");
        JButton end = new JButton("Завершить тестирование");


        GridBagConstraints c1 = new GridBagConstraints
                (0, 0, 1, 1, 1, 0,
                        GridBagConstraints.PAGE_START, GridBagConstraints.BOTH,
                        new Insets(5, 100, 5, 100), 0, 0);
        GridBagConstraints c2 = new GridBagConstraints
                (0, 1, 1, 1, 1, 0,
                        GridBagConstraints.LINE_END, GridBagConstraints.NONE,
                        new Insets(10, 10, 10, 10), 0, 0);


        topFrame.add(new JLabel(title.substring(0, title.length() - 4)));
        botFrame.add(back, c2);
        c2.gridx = 1;
        botFrame.add(next, c2);
        c2.gridx = 2;
        botFrame.add(end, c2);


        // Заполнение и добавление панели закладок.
        for (int i = 0, length = question.length; i < length; i++) {
            questionPanel[i] = new JPanel(new GridBagLayout());
            c1.gridy = 0;
            questionPanel[i].add(new JLabel("<html>" + question[i][0] + "</html>"), c1);
            buttonGroup[i] = new ButtonGroup();
            for (int j = 0; j < numberOfAnswers; j++) {
                radioButton[i][j] = new JRadioButton(question[i][j + 1]);
                c1.gridy++;
                questionPanel[i].add(radioButton[i][j], c1);
                buttonGroup[i].add(radioButton[i][j]);
            }
            tabbedPane.addTab("Вопрос №" + (i + 1), questionPanel[i]);
        }


        content.add(topFrame, BorderLayout.PAGE_START);
        content.add(tabbedPane, BorderLayout.CENTER);
        content.add(botFrame, BorderLayout.PAGE_END);


        change = new boolean[question.length];
        for (int i = 0, length = question.length; i < length; i++)
            for (int j = 0; j < numberOfAnswers; j++) {
                int finalI = i;
                int finalJ = j;
                radioButton[i][j].addItemListener(e -> {
                    // Присваивание элементу массива с верными ответами
                    // значения true, если этот элемент равен элементу списка,
                    // помеченному как верный ответ.
                    change[finalI] = e.getItemSelectable() == radioButton[finalI][trueAnswer[finalI] - 1];
                    if (radioButton[finalI][finalJ].isSelected())
                        tabbedPane.setBackgroundAt(finalI, new Color(255, 255, 160));
                });
            }

        back.addActionListener(e -> {
            if (tabbedPane.getSelectedIndex() > 0)
                tabbedPane.setSelectedIndex(tabbedPane.getSelectedIndex() - 1);
        });

        next.addActionListener(e -> {
            if (tabbedPane.getSelectedIndex() < question.length - 1)
                tabbedPane.setSelectedIndex(tabbedPane.getSelectedIndex() + 1);
        });

        end.addActionListener(e -> {
            score = 0;
            for (int i = 0; i < question.length; i++) {
                if (change[i]) {
                    score++;
                    tabbedPane.setBackgroundAt(i, new Color(160, 255, 160));
                } else
                    tabbedPane.setBackgroundAt(i, new Color(255, 160, 160));
            }
            end.setEnabled(false);
            c2.gridx = 0;
            c2.gridy = 0;
            botFrame.add(new JLabel("Результат: " + score + " из " + question.length), c2);
            botFrame.setVisible(false);
            botFrame.setVisible(true);
            JOptionPane.showMessageDialog(this,
                    "Результат: " + score + " из " + question.length + ".\n\nНажмите \"OK\" для просмотра верных ответов");

            for (int i = 0, length = question.length; i < length; i++) {
                for (int j = 0; j < numberOfAnswers; j++) {
                    radioButton[i][j].setEnabled(false);
                }
                questionPanel[i].getComponent(trueAnswer[i]).setBackground(new Color(160, 255, 160));
            }
        });


        setContentPane(content);
        setVisible(true);
    }
}
