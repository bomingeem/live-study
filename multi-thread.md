멀티쓰레드를 알기 전에 프로세스에 대한 이해부터 알아보고자 합니다.

프로세스
- 프로세스란, 프로그램이 실행되어 프로세스라는 과정으로 메모리에 할당됩니다.
- 간단하게 실행 중인 프로그램으로 생각하면 됩니다.
- 프로세스는 자원과 스레드로 구성되어 있습니다.


스레드
- 스레드란 프로세스 내에서 실제 작업을 수행하며 모든 프로세스는 하나 이상의 스레드를 가지고 있습니다.
- 스레드를 비유로 들자면 공장의 일꾼이라고 생각하시면 됩니다.
- 싱글 스레드 프로세스 = 자원 + 스레드
- 멀티 스레드 프로세스 = 자원 + 스레드 + 스레드 + ... + 스레드


스레드의 구현과 실행
스레드 구현은 2가지의 방법이 있습니다.
1. Thread 클래스 상속
- Thread 클래스를 상속한 경우, start()를 실행하는 기점에 run() 메소드를 사용합니다.
- 이 말은 즉, start() 메소드는 새로운 스레드가 작업을 실행하는데 필요한 Call Stack이라는 공간을 생성한 다음 run() 메소드를 호출해서 Stack 내부에 run()이 저장됩니다.

    public class Thread1 extends Thread { <br>
    }

public class ThreadTest { <br>
public static void main(String[] args) { <br>
Thread1 thread1 = new Thread1(); <br>
thread1.start(); <br>
} <br>
}

2. Runnable 인터페이스 구현
- Runnable 인터페이스를 구현한 경우, Thread 객체 안에 스레드를 사용하려는 객체를 넣은 후 객체화 하여 사용합니다.

public class Thread2 implements Runnable { <br>
@Override <br>
public void run() { <br>
//Runnable 인터페이스의 추상메서드 run()을 구현 <br>
} <br>
}

public class ThreadTest { <br>
public static void main(String[] args) { <br>
Thread thread = new Thread(new Thread2()); <br>
thread.start(); <br>
} <br>
} <br>
두가지 중 어느 쪽을 사용하던지 크게 차이는 없지만 Java에서는 다중상속을 허용하지 않기 때문에 <br>
Thread 클래스를 상속받을 경우, 다른 클래스를 사용할 수 없습니다. <br>
더 객체지향적인 방법을 사용하고자 한다면 Runnable 인터페이스를 구현하는 것이 일반적입니다.


멀티스레드 실행결과 <br>
아래의 예시 출력결과 2개의 스레드가 순차적으로 실행되지 않고 끝나는 시간도 각각이며 매번 실행할 때마다 실행결과는 항상 동일하지 않습니다.

public class Thread1 extends Thread { <br>
public void run() { <br>
for (int i=0; i<100; i++) { <br>
System.out.println("Thread-1"); <br>
} <br>
} <br>
}

public class Thread2 implements Runnable{ <br>
@Override <br>
public void run() { <br>
for (int i=0; i<100; i++) { <br>
System.out.println("Thread-2"); <br>
} <br>
} <br>
}

public class ThreadTest { <br>
public static void main(String[] args) { <br>
//멀티 스레드 <br>
Thread1 thread1 = new Thread1(); <br>
Thread thread2 = new Thread(new Thread2()); <br>
thread1.start(); <br>
thread2.start(); <br>
} <br>
} <br>
결과 <br>
![thread](https://user-images.githubusercontent.com/47099798/105131104-9f580800-5b2b-11eb-8bd8-c727277020b7.jpg)

스레드의 상태 <br>
간혹 스레드를 제어해야 할 필요성이 있는데 스레드의 상태를 알아보고자 합니다. <br>
효율적으로 보기위해 그림으로 그려보았습니다. <br>

![thread2](https://user-images.githubusercontent.com/47099798/105141153-11384d80-5b3c-11eb-93a7-8c937edd9a24.jpg)
① 스레드가 생성(NEW)되고 start() 호출 시 실행대기열에 저장되어 실행중 또는 실행 가능한 상태 <br>
② 자기 차례가 되면 실행 상태 <br>
③ 주어진 실행 시간이 다 되거나 yield() 실행 시 다음 차례의 스레드가 되는 실행상태 <br>
④ 실행 중 suspend(), sleep(), wait(), join() 호출에 의해 작업이 종료되지는 않았으나 실행가능하지 않은 일시정지 상태 <br>
⑤ 지정된 일시정지 시간이 다 되거나(time-out), notify(), resume(), interrup()가 호출되면 일시정지를 벗어나 다시 실행대기열에 상태 <br>
⑥ stop()이 호출되거나 스레드의 작업이 종료된 상태 <br>