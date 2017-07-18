package com.ro0kiey.igank.utils;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 文件操作工具类
 * Created by Ro0kieY on 2017/7/17.
 */

public class FileUtils {

    private FileUtils() {}

    /**
     * 检查SD卡是否存在
     * @return
     */
    public static boolean isSDCardEnable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 生成文件存储路径
     * @param path
     * @param name
     * @return
     */
    public static File generateFile(String path, String name){
        File appDir = new File(Environment.getExternalStorageDirectory(), path);
        if (!appDir.exists()){
            appDir.mkdirs();
        }
        String fileName = name.replace("/", "-") + ".jpg";
        File file = new File(appDir, fileName);
        return file;
    }

    /**
     * 检测文件是否存在
     * @param file
     * @return
     */
    public static boolean checkFileExisted(File file){
        return file.exists();
    }

    /**
     * 保存Bitmap到SD卡
     * @param file
     * @param bitmap
     * @param name
     * @return
     */
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
