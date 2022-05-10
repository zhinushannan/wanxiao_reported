package club.kwcoder.report.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamCloseUtil {

    public static void close(InputStream in, OutputStream out) {
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
