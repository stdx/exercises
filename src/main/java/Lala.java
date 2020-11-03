import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Lala {

    public static void main(String[] args) {

        final Calculator calculator = new Calculator();

        // the test data -> firstNumber, secondNumber, expectedResultForAdd, expectedResultForMultiply
        int[][] testData = new int[][]{
                new int[]{0, 7, 7, 0},
                new int[]{1, 6, 7, 6},
                new int[]{2, 5, 7, 10},
                new int[]{3, 4, 7, 12},
                new int[]{4, 3, 7, 12},
                new int[]{5, 2, 7, 10},
                new int[]{6, 1, 7, 6},
                new int[]{7, 0, 7, 0}
        };
        final List<int[]> testResults = new Random()
                .ints(0, testData.length)
                .limit(10000)
                .mapToObj((i) -> {
                    calculator.setNumbers(testData[i][0], testData[i][1]);
                    return new int[]{i, calculator.add(), calculator.multiply()};
                })
                .collect(Collectors.toList());
        for (int[] testResult : testResults) {
            final int testDataIndex = testResult[0];

            final int expectedResultForAdd = testData[testDataIndex][2];
            final int expectedResultForMultiply = testData[testDataIndex][3];
            final int actualResultForAdd = testResult[1];
            final int actualResultForMultiply = testResult[2];

            if (expectedResultForAdd != actualResultForAdd) {
                throw new IllegalArgumentException(
                        String.format("%d + %d != %d (%d is wrong)\n", testData[testDataIndex][0], testData[testDataIndex][1], expectedResultForAdd, actualResultForAdd)
                );
            }
            System.out.printf("%d + %d == %d \u2713 \n", testData[testDataIndex][0], testData[testDataIndex][1], expectedResultForAdd);

            if (expectedResultForMultiply != actualResultForMultiply) {
                throw new IllegalArgumentException(
                        String.format("%d * %d != %d (%d is wrong)\n", testData[testDataIndex][0], testData[testDataIndex][1], expectedResultForMultiply, actualResultForMultiply)
                );
            }
            System.out.printf("%d * %d == %d \u2713 \n", testData[testDataIndex][0], testData[testDataIndex][1], expectedResultForMultiply);
        }
    }

    private static class Calculator {

        private int firstNumber;
        private int secondNumber;

        public void setNumbers(int firstNumber, int secondNumber) {
            this.firstNumber = firstNumber;
            this.secondNumber = secondNumber;
        }

        public int add() {
            return firstNumber + secondNumber;
        }

        public int multiply() {
            return firstNumber * secondNumber;
        }
    }
}
