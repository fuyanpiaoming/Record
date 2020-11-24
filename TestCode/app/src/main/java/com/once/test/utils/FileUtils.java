package com.once.test.utils;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileUtils {
    private static final String TAG = FileUtils.class.getSimpleName();

    public static final String SDCARD_PATH = Environment.getExternalStorageDirectory().getPath();
    public static final String SDCARD_PATH_TEST = Environment.getExternalStorageDirectory().getPath() + "/test/";

    public static boolean createFile(String dirName, String fileName) throws IOException {
        Log.i(TAG,"[createFile]SDCARD_PATH=" + SDCARD_PATH);
        File file = new File(SDCARD_PATH + dirName + File.separator + fileName);
        Log.i(TAG,"[createFile]file=" + file.getAbsolutePath());
        return file.createNewFile();
    }

    public static void writeData(String fileName,String content) throws IOException {
        File file = new File(SDCARD_PATH_TEST + fileName);
        if (!file.exists()){
            File dir = new File(file.getParent());
            if (dir.mkdirs()){
                if (file.createNewFile()){
                    Log.i(TAG,"[writeData]create ok");
                }
            }
        }else{
            File file1 = new File(SDCARD_PATH_TEST);
            String[] fileNames = file1.list();
            for(String name: fileNames){
                Log.i(TAG,"[writeData]name=" + name);
            }
        }
        RandomAccessFile accessFile = new RandomAccessFile(file,"rwd");
        byte[] bytes = content.getBytes();
        accessFile.write(bytes);
        accessFile.close();
    }

    public static void writeData2(String fileName,String content){
        try{
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                File file = new File(fileName);
                FileOutputStream fileOutputStream = new FileOutputStream(file,false);
                fileOutputStream.write(content.getBytes("UTF-8"));
                fileOutputStream.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readData(String fileName) throws IOException {
        File file = new File(SDCARD_PATH_TEST + fileName);
        if (!file.exists()){
            return null;
        }
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder stringBuilder = new StringBuilder();
        String value;
        while((value = bufferedReader.readLine()) != null){
            stringBuilder.append(value);
        }
        return stringBuilder.toString();
    }

    public static boolean deleteFile(String path){
        File file = new File(path);
        if (file.exists() && file.isFile()){
            return file.delete();
        }
        return false;
    }

}
