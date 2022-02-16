package tasks;

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

    long computeFib(int until) {
//        long nMinus2 = 0, nMinus1 = 0, n = 1;
//        for (int i = 0; i < until; i++){
//            nMinus2 = nMinus1;
//            nMinus1 = n;
//            n = nMinus2 + nMinus1;
//        }
// Снизу примерно то же самое.

        return Stream
                .iterate(FibTriple.start(), (triple) -> FibTriple.create(triple.getCurrent(), triple.getNext()))
                .skip(until)
                .findFirst()
                .get()
                .getCurrent();
    }

    private static class FibTriple {
        private final long prev;
        private final long current;
        private final long next;

        private FibTriple(long prev, long current, long next) {
            this.prev = prev;
            this.current = current;
            this.next = next;
        }

        public static FibTriple start() {
            return create(0, 0, 1);
        }

        public static FibTriple create(long prev, long current) {
            return create(prev, current, prev + current);
        }

        public static FibTriple create(long prev, long current, long next) {
            return new FibTriple(prev, current, next);
        }

        public long getNext() {
            return next;
        }

        public long getCurrent() {
            return current;
        }

        public long getPrev() {
            return prev;
        }
    }

    private void test(int nth, long shouldBe) {
        System.out.println("fib: " + nth + " should be: " + shouldBe);
        long actual = computeFib(nth);
        if (actual != shouldBe) {
            throw new RuntimeException(actual + " expected: " + shouldBe);
        } else {
            System.out.println("ok!");
        }
    }
}
