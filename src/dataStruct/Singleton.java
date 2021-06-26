package dataStruct;

/**
 * @author ：wenli.hua
 * @description：TODO
 * @date ：2021/6/8 20:42
 */

public class Singleton {
    private volatile static Singleton instance;   //static 必须要有，这样子才可以点getInstance
    //volatile 两个作用，防止指令重排序和保证线程可见性，
    //线程可见性，是因为每个线程有自己的工作内存，将共享内存的数据创建了副本，这样子会导致线程对共享变量值的修改，不能及时的被其他线程看到。
    //生成对象分为三部，1.分配内存空间2.初始化对象3.将引用指向对象
    //这边主要是防止线程A，已经完成1，3，但是尚未初始化
    //这时线程B发现instance！= null，直接返回地址，但是这时候的实例还没有初始化

    private Singleton(){};
    public static Singleton getInstance(){
        if(instance == null){  //不用再创建对象，防止创建多个对象
            synchronized (Singleton.class){
                if(instance == null){
                    //如果没有这个判空，两个线程同时通过第一个判空了，
                    // 其中一个A拿到锁对象，另一个B没有拿到，
                    //等A生成就释放锁，B会再生成实例
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }



}
