package ThreadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author ：wenli.hua
 * @description：TODO
 * @date ：2021/5/9 16:31
 */

public class ThreadPool03 {
    public static void main(String[] args) {
        //3.第三种捕获线程池中异常的方式：为工作者线程设置UncatchExceptionHandler处理异常
        ExecutorService executorPool =Executors.newFixedThreadPool(1, new ThreadFactory(){
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread t, Throwable e) {
                        System.out.println(t.getName()+"线程抛出的异常： "+e);
                    }
                });
                return t ;
            }
        });

        executorPool.execute(new Runnable() {
            @Override
            public void run() {
                Object o = null;
                System.out.println("result == "+ o.toString());
            }
        });


    }
}
