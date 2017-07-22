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
     * 生成存储路径
     * @param name
     * @return
     */
    public static File generateFilePath(String name){
        File appDir = new File(Environment.getExternalStorageDirectory(), "igank");
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        String fileName = name.replace("/", "-") + "-girl.jpg";
        File file = new File(appDir, fileName);
        return file;
    }

    /**
     * 检查文件是否已经存在
     * @param file
     * @return
     */
    public static boolean checkIsSaved(File file){
        return file.exists();
    }

    /**
     * 保存Bitmap到SD卡
     * @param bitmap
     * @param name
     * @return
     */
    public static Uri saveBitmapToSDCard(Bitmap bitmap, String name) {
        File file = generateFilePath(name);
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(file);
            assert bitmap != null;
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return Uri.fromFile(file);
    }
}
