package dataStruct;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ：wenli.hua
 * @description：TODO
 * @date ：2021/7/2 10:31
 */
//1>PriorityQueue是一种无界的，线程不安全的队列
//2>PriorityQueue是一种通过数组实现的，并拥有优先级的队列
//3>PriorityQueue存储的元素要求必须是可比较的对象， 如果不是就必须明确指定比较器


public class PriorityQueueTest {

    static class Student {
        private String name;  //名字
        private int score;    //分数

        public Student(String name,int score){
            this.name = name;
            this.score = score;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }


    public static void main(String[] args) {
        //通过改造器指定排序规则
        PriorityQueue<Student> q = new PriorityQueue<Student>(new Comparator<Student>() {
            public int compare(Student o1, Student o2) {
                //按照分数低到高，分数相等按名字
                if(o1.getScore() == o2.getScore()){
                    return o1.getName().compareTo(o2.getName());
                }
                //有个小技巧，你想用什么作为排序的依据，return什么
                return o1.getScore() - o2.getScore();
                //默认升序
                //即表示已经确定的 o1 < o2 --> o1 - o2 < 0
                //return o1 - o2 得知这个结果是 < 0 ， --> 表示对比的两个对象 o1 < o2 ，就是升序
                //return o2 - o1 得知这个结果是 > 0 , -- >表示对比的两个对象 o1 > o2 ，就是降序

                //

            }
        });
        //入列
        q.offer(new Student("dafei", 20));
        q.offer(new Student("will", 17));
        q.offer(new Student("setf", 30));
        q.offer(new Student("bunny", 20));

        //出列
        System.out.println(q.poll());  //Student{name='will', score=17}
        System.out.println(q.poll());  //Student{name='bunny', score=20}
        System.out.println(q.poll());  //Student{name='dafei', score=20}
        System.out.println(q.poll());  //Student{name='setf', score=30}



    }




}
