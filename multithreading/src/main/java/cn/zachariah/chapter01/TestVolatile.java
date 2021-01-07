package cn.zachariah.chapter01;

/**
 * @ClassName TestVolatile
 * @Description TODO valatile 关键学习
 * @Author zachariah
 * @Date 2021/1/7 23:17
 */
public class TestVolatile {
    public static void main(String[] args) {
        Thread1 td1 = new Thread1();
        Thread2 td2 = new Thread2(td1);
        td1.start();
        td2.start();
    }
}


class Thread1 extends Thread{
    private volatile boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
            flag = true;
            System.out.println("当前flag值为"+ flag);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

class Thread2 extends Thread{
    Thread1 td;
    public Thread2(Thread1 td) {
        this.td = td;
    }

    @Override
    public void run() {
        while (true){
            if (td.isFlag()){
                System.out.println("当前flag值为:"+td.isFlag()+",Thread2结束运行");
                break;
            }
        }
    }
}