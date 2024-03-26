package csc2620_thread_time_example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stuetzlec
 */
public class TimeThread extends Thread {

    @Override
    public void run() {
        while (true) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            System.out.println(dtf.format(now));
            
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {

            }
        }
    }

}
