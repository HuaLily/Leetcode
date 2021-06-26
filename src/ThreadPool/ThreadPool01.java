package ThreadPool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ：wenli.hua
 * @description：TODO
 * @date ：2021/5/2 16:24
 */

public class ThreadPool01 {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {

            //submit 是先生成一个Future对象结果，需要返回这个Future对象
            // 通过execute()执行任务
            Future future = executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("current Thread name = "+Thread.currentThread().getName());
                    Object o = null;
                    System.out.println(o.toString());

                    //1.处理线程池中出现异常的方法1：
                    //直接在代码中try catch
//                    try {
//                        Object o = null;
//                        System.out.println(o.toString());
//                    }catch (Exception e){
//                        System.out.println(e.getClass());
//                    }
                }
            });


//            try {
//                //2.方法二通过submit传递出的Future对象的get方法接受抛出的异常
//                future.get();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
            final ThreadLocal<Object> objectThreadLocal = new ThreadLocal<>();


        }

    }
}
