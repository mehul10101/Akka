import java.util.ArrayList;
import java.util.List;

class PrimeNumbersOptimised {
    
    public static void main(String[] args) throws InterruptedException {

        long a = System.currentTimeMillis();
        Result result = new Result();

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Runnable task = new PrimeNumbers(i * 10000000, i * 10000000 + 10000000, result);
            Thread t = new Thread(task);
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        long b = System.currentTimeMillis();
        System.out.println(b - a + " millis");

        System.out.println(result.getNumberOfPrimes());
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
        if (num < 2) {
            return false;
        }
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

    public static class Result {
        private int numberOfPrimes = 0;

        public synchronized int getNumberOfPrimes() {
            return numberOfPrimes;
        }

        public void addNumberOfPrimes(int numOfPrimes) {
            synchronized (this) {
                this.numberOfPrimes += numOfPrimes;
            }
        }
    }
    
    public static class PrimeNumbers implements Runnable {

        int st, end, k, numOfPrimes = 0;
        Result result;
        
        public PrimeNumbers(int st, int end, Result result) {
            this.st = st;
            this.end = end;
            this.result = result;
        }
        
        @Override
        public void run() {
            for (k = st; k < end; k++) {
                numOfPrimes = isPrimeOptimised(k) ? numOfPrimes + 1 : numOfPrimes;
            }
            result.addNumberOfPrimes(numOfPrimes);
        }
    }
}
