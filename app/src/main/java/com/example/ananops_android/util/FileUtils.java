package com.example.ananops_android.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class FileUtils {
    public static FileUtils instance;
    public static FileUtils getInstance() {
        if (null == instance) {
            instance = new FileUtils();
        }
        return instance;
    }

    private FileUtils() {
    }

    //获取文件大小
    /**
     * 获取指定文件大小
     */
    public long getFileSize(File file) {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                size = fis.available();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("获取文件大小", "文件不存在!");
        }
        Log.d("文件大小为", String.valueOf(size));
        return size;
    }

    //压缩文件
    /**
     * 质量压缩
     *
     * @param srcPath 原图路径
     * @param context
     * @param max 要压缩到多大以内（单位：kb）
     * @return
     */
    public String compressReSave(String srcPath, Context context, int max) {
        String filePath = "";
        BitmapFactory.Options newOpts = new BitmapFactory.Options();//1
        Bitmap image = BitmapFactory.decodeFile(srcPath);
        newOpts.inJustDecodeBounds = true;//1
        newOpts.inJustDecodeBounds = false;//1
        int w = newOpts.outWidth;//1
        int h = newOpts.outHeight;//1
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;// 这里设置高度为800f
        float ww = 480f;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        image = BitmapFactory.decodeFile(srcPath, newOpts);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 90;
        while (baos.toByteArray().length / 1024 > max) { // 循环判断如果压缩后图片是否大于maxkb,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            if (options > 10){
                options -= 10;// 每次都减少10
            } else {
                options -= 5;
            }
            if (options == 5){//这里最多压缩到5，options不能小于0
                break;
            }
        }
        FileOutputStream outStream = null;
        filePath = createFile(context, "myImg");
        try {
            outStream = new FileOutputStream(filePath);
            // 把数据写入文件
            outStream.write(baos.toByteArray());
            // 记得要关闭流！
            outStream.close();
            Log.v("压缩路径",  filePath);
            return filePath;
        } catch (Exception e) {
            e.printStackTrace();
            Log.v("压缩失败",  filePath);
            return null;
        } finally {
            try {
                if (outStream != null) {
                    // 记得要关闭流！
                    outStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static String createFile(Context context, String pathName) {
        String path = "";
        File file = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            file = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + pathName);
        } else {
            file = new File(context.getFilesDir().getPath() + File.separator + pathName);
        }
        if (file != null) {
            if (!file.exists()) {
                file.mkdir();
            }
            File output = new File(file, System.currentTimeMillis() + ".png");
            try {
                if (output.exists()) {
                    output.delete();
                } else {
                    output.createNewFile();
                }
                path = output.getAbsolutePath();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return path;
    }
    /**
     * 清除缓存文件
     */
    public void deleteCacheFile(File file){
     //   File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"myImg");
        RecursionDeleteFile(file);
        Log.d("删除文件", "删除");
    }
    /**
     * 递归删除
     */
    public  void RecursionDeleteFile(File file){
        if(file.isFile()){
            file.delete();
            return;
        }
        if(file.isDirectory()){
            File[] childFile = file.listFiles();
            if(childFile == null || childFile.length == 0){
                file.delete();
                return;
            }
            for(File f : childFile){
                RecursionDeleteFile(f);
            }
            file.delete();
        }
    }

}
