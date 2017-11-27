class MakeTest {

    private int[] trueAnswer;

    String[][] test(String[][] allQuestions, int numberOfQuestions, int numberOfAnswers, boolean textType) {
        trueAnswer = new int[numberOfQuestions];        // Массив верных ответов
        String[][] testQuestion = new String[numberOfQuestions][numberOfAnswers + 1]; // Массив выбранных для теста вопросов

        for (int i = 0; i < numberOfQuestions; i++) {
            int rnd = (int) (Math.random() * allQuestions.length); // Присвоить переменной для номера вопроса случайное число от 0 до ...
            testQuestion[i] = allQuestions[rnd].clone();


            // Перемешать ответы:
            for (int j = 1; j <= numberOfAnswers; j++) {                       // 5 раз
                int r = (int) Math.ceil(Math.random() * numberOfAnswers);     // Присвоить переменной r случайное число от 1 до 5
                String tmp = testQuestion[i][j];               // Присвоить переменной tmp значение текущей строки
                testQuestion[i][j] = testQuestion[i][r];      // Присвоить текущей строке значение строки под номером r (от 1 до 5)
                testQuestion[i][r] = tmp;                      // Присвоить строке под номером r (от 1 до 5) значение tmp
            }


            if (textType) {
                for (int j = 1; j < numberOfAnswers + 1; j++) {
                    if (testQuestion[i][j].charAt(0) == '+') {  // Если первый символ строки '+'
                        trueAnswer[i] = j;                      // присвоить правильному ответу номер текущей строки
                        testQuestion[i][j] = testQuestion[i][j].substring(3);
                    } else
                        testQuestion[i][j] = testQuestion[i][j].substring(2);
                }
            } else {
                for (int j = 1; j < numberOfAnswers + 1; j++) {
                    if (testQuestion[i][j].substring(0, 1).toLowerCase().equals("a"))   // Если первый символ строки 'A'
                        trueAnswer[i] = j;                          // присвоить правильному ответу номер текущей строки
                    testQuestion[i][j] = testQuestion[i][j].substring(2);
                }
            }
        }

        return testQuestion;
    }

    int[] getTrueAnswer() {
        return trueAnswer;
    }
}
