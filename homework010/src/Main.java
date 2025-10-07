public class Main {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

        int[] evenNumbers = Sequence.filter(numbers, n -> n % 2 == 0);
        System.out.print("Чётные числа: ");
        printArray(evenNumbers);

        // Проверка на числа с чётной суммой цифр
        int[] digitSumEven = Sequence.filter(numbers, n -> {
            int sum = 0;
            int absN = Math.abs(n);
            while (absN > 0) {
                sum += absN % 10;
                absN /= 10;
            }
            return sum % 2 == 0;
        });
        System.out.print("Числа с чётной суммой цифр: ");
        printArray(digitSumEven);
    }

    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) System.out.print(", ");
            System.out.print(arr[i]);
        }
        System.out.println("]");
    }
}