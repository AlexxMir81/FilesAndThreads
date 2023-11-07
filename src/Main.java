import java.io.File;
import java.io.FileWriter;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

class MyThread extends Thread{
    static int count = 0;
    public void run(){
        String path = "";
        count++;
        File folder = new File("test"+count);
        if (!folder.exists()){
            folder.mkdir();
            path+=folder.getAbsolutePath();
            for (var i=1; i<=20; i++){
                File file = new File(path+"\\Test"+ i + ".txt");
                StringBuilder strbuilder= new StringBuilder();
                try (FileWriter writer = new FileWriter(file)) {
                    for (var j = 1; j<=1_000_000; j++){
                        strbuilder.append(j+"\n");
                    }
                    writer.write(strbuilder.toString());
                   // System.out.println(path);
                } catch (Exception e) {
                    e.getMessage();
                }
            }

        }else {
            System.out.println("Файл уже создан!");
        }
    }

}
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Начало создания файлов...");

        long startTime = System.nanoTime();
        for (var i = 0; i<20;i++){
            MyThread thread = new MyThread();
            thread.start();
            thread.join();
        }

        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        System.out.println("Файлы созданы.\n" +
                "Общее время выполнения:  "
                +TimeUnit.NANOSECONDS.toSeconds(timeElapsed) + "сек.");
    }
}