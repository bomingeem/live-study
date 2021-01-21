package study.multiThread;

public class MultiThread extends Thread {

    public MultiThread(String name) {
        super(name);
    }
    public void run() {
        for (int i=0; i<100; i++) {
            int x = 100;
            x += i;
            for (int j=0; j<100; j++) {
                x += j;
            }
        }
        System.out.println(this.getName() + "ThreadStart");
    }
}
