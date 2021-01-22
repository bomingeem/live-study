package study.multiThread;

public class Account {
    int balance = 1000;

    public int getBalance() {
        return balance;
    }

    public synchronized void withdraw(int money) {
        // 잔고가 출금액보다 클때만 출금을 실시하므로 잔고가 음수가 되는 일은 없어야함
        if (balance >= money) {
            try {
                // 문제 상황을 만들기 위해 고의로 쓰레드를 일시정지
                Thread.sleep(1000);
            } catch (InterruptedException e) {}

            balance -= money;
        }
    }
}
