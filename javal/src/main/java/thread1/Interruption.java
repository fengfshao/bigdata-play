package thread1;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Interruption {
    public static void main(String[] args) throws InterruptedException, IOException {
        IODemo3();
    }

    static void IODemo() throws InterruptedException {
        //IO阻塞时被打断自动抛异常,但需要io本身是可打断的
        //明显inputStream无法打断
        Thread t = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        });
        t.start();
        Thread.sleep(1000 * 1);
        t.interrupt();
    }

    static void IODemo2() throws InterruptedException {
        //IO阻塞时被打断自动抛异常,但需要io本身是可打断的
        //可打断的io被打断时跑出的是InterruptedIOException
        //InputStream IO可被打断的特性好像早就被jdk移除了
        Thread t = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }

        });
        t.start();
        Thread.sleep(1000 * 1);
        t.interrupt();
    }

    static void IODemo3() throws InterruptedException {
        //测试InputStream是否能打断
        //IO阻塞时被打断自动抛异常,但需要io本身是可打断的
        //可打断的io被打断时跑出的是InterruptedIOException
        //InputStream IO可被打断的特性好像早就被jdk移除了
        Thread t = new Thread(() -> {
            InputStream inputStream=System.in;
            try {
                int b= inputStream.read();
                System.out.println("read:"+b);
                System.out.println("isInterrupted:" + Thread.currentThread().isInterrupted());
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        t.start();
        Thread.sleep(1000 * 1);
        t.interrupt();
    }

    static void sleepDemo() throws InterruptedException {
        //sleeping时被打断自动抛异常
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(1000 * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        });
        t.start();
        Thread.sleep(1000 * 1);
        System.out.println(t.getState());
        t.interrupt();
        System.out.println(t.isInterrupted());
    }
}
