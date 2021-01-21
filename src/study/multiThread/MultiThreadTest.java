package study.multiThread;

public class MultiThreadTest {
    public static void main(String[] args) {
        for (int i=0; i<5; i++) {
            Thread thread = new MultiThread("Thread = " + i);
            if (i == 4) {
                thread.setPriority(Thread.MAX_PRIORITY);
            } else {
                thread.setPriority(Thread.MIN_PRIORITY);
            }
            thread.start();
        }
    }
}
