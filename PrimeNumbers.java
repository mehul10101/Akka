class PrimeNumbers {
public static void main(String[] args) {
        int ans = 0;

        long a = System.currentTimeMillis();

        for (int i = 2; i <= 100000000; i++) {
            if (isPrime(i)) {
                ans++;
            }
        }

        long b = System.currentTimeMillis();
        System.out.println(b - a + " millis");

        System.out.println(ans);
    }

    private static boolean isPrime(int num) {
        if (num == 2) {
            return true;
        }
        for (int i = 3; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    private static boolean isPrimeOptimised(int num) {
        if (num == 2) {
            return true;
        }
        if (num % 2 == 0) {
            return false;
        }
        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}
