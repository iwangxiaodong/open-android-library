package com.openle.our.aos;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class IOCommon {

    //  todo - 仅兜底复制Uri暴漏的文件内容，还未处理直接获取FilePath等方式！ 缺陷 - 每次都要复制会耗费算力？
    public static String copyUriFileToAppDir(Context mContext, Uri uri, String newDirName) {
        Uri returnUri = uri;

        Cursor returnCursor = mContext.getContentResolver().query(returnUri, new String[]{
                OpenableColumns.DISPLAY_NAME, OpenableColumns.SIZE
        }, null, null, null);

        if (returnCursor != null) {
            int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            //int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
            returnCursor.moveToFirst();
            String name = (returnCursor.getString(nameIndex));
            //String size = (Long.toString(returnCursor.getLong(sizeIndex)));
            returnCursor.close();

            File output;
            if (!newDirName.isEmpty()) {
                File dir = new File(mContext.getFilesDir() + "/" + newDirName);
                if (!dir.exists()) {
                    System.out.println(dir.mkdir());
                }
                output = new File(mContext.getFilesDir() + "/" + newDirName + "/" + name);
            } else {
                output = new File(mContext.getFilesDir() + "/" + name);
            }
            try {

                InputStream inputStream = mContext.getContentResolver().openInputStream(uri);
                if (inputStream != null) {
                    FileOutputStream outputStream = new FileOutputStream(output);

                    int read = 0;
                    int bufferSize = 1024;
                    final byte[] buffers = new byte[bufferSize];
                    while ((read = inputStream.read(buffers)) != -1) {
                        outputStream.write(buffers, 0, read);
                    }

                    inputStream.close();
                    outputStream.close();
                }

            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
            return output.getPath();
        }
        return null;
    }
}
