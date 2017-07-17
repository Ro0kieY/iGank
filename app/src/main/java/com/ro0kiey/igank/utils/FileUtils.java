package com.ro0kiey.igank.utils;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.view.View;

import com.ro0kiey.igank.R;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Ro0kieY on 2017/7/17.
 */

public class FileUtils {

    private FileUtils() {}

    public static boolean isSDCardEnable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static File generateFile(String path, String name){
        File appDir = new File(Environment.getExternalStorageDirectory(), path);
        if (!appDir.exists()){
            appDir.mkdirs();
        }
        String fileName = name.replace("/", "-") + ".jpg";
        File file = new File(appDir, fileName);
        return file;
    }

    public static boolean checkFileExisted(File file){
        return file.exists();
    }

    public static Uri saveBitmapToSDCard(File file, Bitmap bitmap, String name) {
            try {
                FileOutputStream os = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
                os.flush();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        return Uri.fromFile(file);
    }
}
