package com.example.lpd.imageloadertest.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by LPD on 2018/4/10.
 */

public class MyUtils {
    public static void close(Closeable closeable){
        try {
            if(closeable != null){
                closeable.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
