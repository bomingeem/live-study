멀티쓰레드를 알기 전에 프로세스에 대한 이해부터 알아보고자 합니다.

<h3>프로세스</h3>
- 프로세스란, 프로그램이 실행되어 프로세스라는 과정으로 메모리에 할당됩니다.
- 간단하게 실행 중인 프로그램으로 생각하면 됩니다.
- 프로세스는 자원과 스레드로 구성되어 있습니다.


<h3>스레드</h3>
- 스레드란 프로세스 내에서 실제 작업을 수행하며 모든 프로세스는 하나 이상의 스레드를 가지고 있습니다.
- 스레드를 비유로 들자면 공장의 일꾼이라고 생각하시면 됩니다.
- 싱글 스레드 프로세스 = 자원 + 스레드
- 멀티 스레드 프로세스 = 자원 + 스레드 + 스레드 + ... + 스레드


<h3>스레드의 구현과 실행</h3>
스레드 구현은 2가지의 방법이 있습니다.
1. Thread 클래스 상속
- Thread 클래스를 상속한 경우, start()를 실행하는 기점에 run() 메소드를 사용합니다.
- 이 말은 즉, start() 메소드는 새로운 스레드가 작업을 실행하는데 필요한 Call Stack이라는 공간을 생성한 다음 run() 메소드를 호출해서 Stack 내부에 run()이 저장됩니다.

  ![캡처1](https://user-images.githubusercontent.com/47099798/105260983-2495f800-5bd2-11eb-80c6-a840f6a10e48.JPG)
  

2. Runnable 인터페이스 구현
 - Runnable 인터페이스를 구현한 경우, Thread 객체 안에 스레드를 사용하려는 객체를 넣은 후 객체화 하여 사용합니다.
 
   ![캡처2](https://user-images.githubusercontent.com/47099798/105260990-2b246f80-5bd2-11eb-9898-51b8dc2c16bc.JPG)
  
두가지 중 어느 쪽을 사용하던지 크게 차이는 없지만 Java에서는 다중상속을 허용하지 않기 때문에 <br>
Thread 클래스를 상속받을 경우, 다른 클래스를 사용할 수 없습니다. <br>
더 객체지향적인 방법을 사용하고자 한다면 Runnable 인터페이스를 구현하는 것이 일반적입니다.


<h3>멀티스레드 실행결과</h3>
아래의 예시 출력결과 2개의 스레드가 순차적으로 실행되지 않고 끝나는 시간도 각각이며 매번 실행할 때마다 실행결과는 항상 동일하지 않습니다.

![캡처3](https://user-images.githubusercontent.com/47099798/105260992-2bbd0600-5bd2-11eb-8565-b7dce60bfea0.JPG)

결과 <br>

  ![thread](https://user-images.githubusercontent.com/47099798/105131104-9f580800-5b2b-11eb-8bd8-c727277020b7.jpg)


<h3>스레드의 상태</h3>
간혹 스레드를 제어해야 할 필요성이 있는데 스레드의 상태를 알아보고자 합니다. <br>
효율적으로 보기위해 그림으로 그려보았습니다. <br>

  ![thread2](https://user-images.githubusercontent.com/47099798/105141153-11384d80-5b3c-11eb-93a7-8c937edd9a24.jpg)
  
① 스레드가 생성(NEW)되고 start() 호출 시 실행대기열에 저장되어 실행중 또는 실행 가능한 상태 <br>
② 자기 차례가 되면 실행 상태 <br>
③ 주어진 실행 시간이 다 되거나 yield() 실행 시 다음 차례의 스레드가 되는 실행상태 <br>
④ 실행 중 suspend(), sleep(), wait(), join() 호출에 의해 작업이 종료되지는 않았으나 실행가능하지 않은 일시정지 상태 <br>
⑤ 지정된 일시정지 시간이 다 되거나(time-out), notify(), resume(), interrup()가 호출되면 일시정지를 벗어나 다시 실행대기열에 상태 <br>
⑥ stop()이 호출되거나 스레드의 작업이 종료된 상태 <br>


<h3>스레드의 우선순위</h3>
 - 작업의 중요도에 따라 스레드의 우선순위를 다르게 하여 특정 스레드가 더 많은 작업시간을 갖도록 할 수 있습니다.
 - 우선순위는 setPriority() 메소드를 이용하여 스레드의 우선순위를 지정한 값으로 변경하고 getPriority() 메소드를 이용하여 스레드의 우선순위를 반환합니다.
 - 우선순위를 지정하는 방법은 ①숫자로 지정하는 방법이 있고, ②Thread 클래스의 상수를 쓸 수도 있습니다.
 - 숫자로 지정하는 방법의 경우 우선순위는 1~10까지 있으며 1이 우선순위가 가장 낮으며 별도의 설정을 하지 않는경우 5로 설정됩니다.
저는 아래와 같이 상수를 이용하여 예제를 작성하였습니다.

![캡처4](https://user-images.githubusercontent.com/47099798/105260995-2c559c80-5bd2-11eb-9c02-6cad5aee4110.JPG)

위에 대한 예시 코드에 대한 출력 결과입니다.   

![ex1](https://user-images.githubusercontent.com/47099798/105260997-2eb7f680-5bd2-11eb-950a-ee0fb524a309.JPG)

<h3>Main 스레드</h3>
 - 모든 자바 application은 반드시 하나의 메인 스레드를 가지고 있습니다.
 - main() 메소드를 실행하면서 시작하며, main() 메소드의 마지막 코드를 실행하거나 return 문을 만나면 종료합니다.
 - 메인 스레드는 여러개의 작업 스레드를 생성(즉, 멀티 스레드)하여 병렬로 코드를 실행할 수 있습니다. 그래서 멀티 스레드 어플리케이션은 메인 스레드가 종료되더라도 아직 실행중인 다른 스레드가 하나라도 존재한다면 프로세스를 종료하지 않습니다.
 
   ![캡처5](https://user-images.githubusercontent.com/47099798/105260996-2d86c980-5bd2-11eb-9d60-ddce3664b83f.JPG)
   
<h3>스레드의 동기화</h3>
- 한번에 하나의 스레드만 객체에 접근할 수 있도록 객체에 락(lock)을 걸어서 데이터의 일관성을 유지하는 것입니다.
- 스레드를 동기화 하는 방법은 synchronized를 이용하여 임계영역을 설정하는 방법이 있습니다.

① 메서드 전체를 임계영역으로 설정하는 방법

![올려1](https://user-images.githubusercontent.com/47099798/105437661-d00f7d00-5ca4-11eb-8ac8-76cf157785fe.JPG)

② 특정한 영역을 임계영역으로 설정하는 방법

![올려2](https://user-images.githubusercontent.com/47099798/105437675-d56cc780-5ca4-11eb-92fc-38d2d622f13a.JPG)

동기화를 사용하지 않을 경우 발생되는 문제에 대하여 아래와 같이 예제를 작성하였습니다.

![올려3](https://user-images.githubusercontent.com/47099798/105437769-077e2980-5ca5-11eb-848c-1281756b0051.JPG)

출력 결과입니다.

![image (1)](https://user-images.githubusercontent.com/47099798/105437828-2381cb00-5ca5-11eb-8ad4-1c5085b6dc7a.png)

잔고가 음수로 나온 이유는 스레드 하나가 조건문을 통과하며 balance 검사 후 순서가 넘어갔는데 <br>
그 사이 또 다른 스레드가 실행하였기 때문에 실제 balance가 조건문을 통과할 때 검사한 balance보다 적어져서 결국 음수가 출력됩니다. <br>
해결하기 위하여 출금 로직에 동기화를 설정하여 하나의 스레드가 해당 로직을 실행하고 있으면 다른 스레드가 해당 블록을 타지 않도록 처리해야 합니다.



위 코드에서 Account Class의 withdraw 메소드의 synchronized를 추가하였습니다.

![올려4](https://user-images.githubusercontent.com/47099798/105437905-49a76b00-5ca5-11eb-8b1d-4b2b115f303f.JPG)

출력 결과 정상적으로 나왔습니다.

![image (2)](https://user-images.githubusercontent.com/47099798/105437929-5926b400-5ca5-11eb-9825-58b273c3d421.png)


<h3>데드락</h3> 
- 두가지 이상의 작업이 하나의 자원을 가지고 있으면서 상대방이 가진 자원을 서로 필요로 하고 있는 상태입니다. <br>
- 어떠한 작업도 실행되지 못하고 상대방의 작업이 끝나기만 바라는 무한정 대기하는 비정상적인 상태입니다. <br>
- 데드락 상태를 완전히 피할 수는 없지만 특정 규칙을 따르면 교착상태가 발생하는 기회를 최소화 할 수 있습니다.


<h3>데드락의 발생 조건</h3>
- 상호배제 : 한번에 한 프로세스만이 자원을 사용 <br>
- 점유대기 : 하나 이상의 자원을 소지한 프로세스가 다른 프로세스가 가진 자원을 추가로 얻기위해 대기 <br>
- 비선점 : 자원을 강제로 뺏을 수 없고, 사용중인 프로세스가 종료된 후 반환되어야 사용 가능 <br>
- 환형대기 : A는 B의 자원을 대기하고 B는 C의 자원을 대기하고... N은 A의 자원을 대기 <br>
- 이 조건을 모두 만족할 경우 교착상태가 발생 <br>
- 각각의 조건은 서로 완전히 독립적이지 않고 연관되어 있습니다. 환형대기의 경우 상호배제, 점유대기, 비선점 조건을 만족해야 됩니다.


<h3>교착상태 해결방법</h3>
<h5>데드락 예방</h5>
- 교착상태 발생조건 중 하나를 제거함으로써 교착상태가 일어나지 않도록 합니다.

<h5>데드락 회피</h5>
- 데드락 예방의 경우 자원의 비효율적인 상태를 초래할 수 있기 때문에 발생 시 피해가는 방법이 있습니다. <br>
- 회피의 예시로 프로세스의 시작 중단, 은행가 알고리즘 등이 있습니다.

<h5>데드락 회복</h5>
- 교착상태를 일으킨 프로세스를 종료하거나, 할당된 자원을 해제함으로써 회복합니다.
