package tasks;

import java.math.BigInteger;
import java.util.stream.Stream;

public class Task1 implements Runnable {
    @Override
    public void run() {
         System.out.println("computed: " + computeFib(TaskUtil.askForInteger()));
//        0, 1, 1, 2, 3, 5, 8, 13, 21, 34,
//        test(0, 0);
//        test(1, 1);
//        test(2, 1);
//        test(3, 2);
//        test(4, 3);
//        test(5, 5);
//        test(6, 8);
//        test(7, 13);
//        test(27, 196418);
    }

    BigInteger computeFib(int until) {
//        long nMinus2 = 0, nMinus1 = 0, n = 1;
//        for (int i = 0; i < until; i++){
//            nMinus2 = nMinus1;
//            nMinus1 = n;
//            n = nMinus2 + nMinus1;
//        }
// Снизу примерно то же самое.
        // вроде норм считает до n = 3000
        return Stream
                .iterate(FibTriple.start(), (triple) -> FibTriple.create(triple.getCurrent(), triple.getNext()))
                .map((fibTriple) -> fibTriple.getCurrent())
                .skip(until)
                .findFirst()
                .get();
    }

    private static class FibTriple {
        private final BigInteger prev;
        private final BigInteger current;
        private final BigInteger next;

        private FibTriple(BigInteger prev, BigInteger current, BigInteger next) {
            this.prev = prev;
            this.current = current;
            this.next = next;
        }

        public static FibTriple start() {
            return create(new BigInteger("0"), new BigInteger("0"), new BigInteger("1"));
        }

        public static FibTriple create(BigInteger prev, BigInteger current) {
            return create(prev, current, prev.add(current));
        }

        public static FibTriple create(BigInteger prev, BigInteger current, BigInteger next) {
            return new FibTriple(prev, current, next);
        }

        public BigInteger getNext() {
            return next;
        }

        public BigInteger getCurrent() {
            return current;
        }

        public BigInteger getPrev() {
            return prev;
        }
    }

    private void test(int nth, long shouldBe) {
        System.out.println("fib: " + nth + " should be: " + shouldBe);
        BigInteger actual = computeFib(nth);
        if (!actual.equals(new BigInteger(Long.toString(shouldBe)))) {
            throw new RuntimeException(actual + " expected: " + shouldBe);
        } else {
            System.out.println("ok!");
        }
    }
}
