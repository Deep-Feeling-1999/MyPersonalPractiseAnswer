package MyThread.CAS.LongAdder;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author Deep-Feeling-1999
 * @create 2020/10/16
 *
 * 用来解决AtomicLong竞争同一个变量的自旋CAS操作浪费CPU资源问题
 * 内部维护多个Cell变量，在获取当前值时把所有cell累加再加上base返回
 */
public class LongAdderTest {
    private static LongAdder longAdder = new LongAdder();

    private static Integer[] arrayOne = new Integer[]{0, 1, 2, 0, 4, 5, 0, -9, 0, -8};
    private static Integer[] arrayTwo = new Integer[]{0, 1, 2, 0, 4, 0, 0, -9, 0, -8};

    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            int size = arrayOne.length;
            for (int i = 0; i < size; ++i) {
                if (arrayOne[i].intValue() == 0) {
                    longAdder.add(1);
                }
            }
        });
        Thread threadTwo = new Thread(() -> {
            for (int i = 0; i < arrayTwo.length; ++i) {
                if (arrayTwo[i].intValue() == 0) {
                    longAdder.add(1);
                }
            }
        });
        threadOne.start();
        threadTwo.start();
        threadOne.join();
        threadTwo.join();

        System.out.println("count 0: "+longAdder.sum());

    }
}
