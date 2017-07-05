package txt;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hbowang on 2/16/17.
 */
public class TxtDemo {
    public static void main(String[] args) throws IOException, ParseException {
//        String str = "hellolfdafaf";
//        ByteOutputStream byteOutputStream =new ByteOutputStream();
//        byteOutputStream.write(str.getBytes());
//        FileOutputStream fileOutputStream = new FileOutputStream(new File("test.txt"));
//        fileOutputStream.write(str.getBytes());
//        fileOutputStream.flush();
//        fileOutputStream.close();
        Date date = new SimpleDateFormat("yyyyMMdd").parse("20170217");
        System.out.println(date.toLocaleString());
        Date date2 = new SimpleDateFormat("yyyyMMdd").parse("20170224");
        System.out.println(date2.toLocaleString());
    }
}
