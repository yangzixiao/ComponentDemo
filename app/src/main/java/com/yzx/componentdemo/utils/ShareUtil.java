//////////////////////////////////////////////////////////////////////
//
// ShareUtil.java: implementation of the ShareUtil class.
//
///////////////////////////////////////////////////////////////////////////////

package com.yzx.componentdemo.utils;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.yzx.componentdemo.R;

import java.io.File;

/**
 * @author Ethan.White
 * @date 2018-11-07 12:01
 * @organize ZBG
 * @describe
 * @update
 */
public class ShareUtil {

    //过滤能够分享到的应用
    private static String[] sShareAppEntries;
    private static String[] sShareAppActivities;

    public static void showImgShare(Activity context, String imgPath) {
        Intent imageIntent = new Intent(Intent.ACTION_SEND);
        imageIntent.setType("image/*");
        imageIntent.putExtra(Intent.EXTRA_STREAM, getUri(context, imgPath));
        context.startActivity(Intent.createChooser(imageIntent, context.getString(R.string.txt_share)));
    }

    /**
     * 获取本地图片的Uri
     *
     * @param context
     * @param filePath
     * @return
     */
    private static Uri getUri(Context context, String filePath) {
        Uri resultUri = null;
        if (context != null && !TextUtils.isEmpty(filePath)) {
            File file = new File(filePath);
            if (file.exists()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    resultUri = getImageContentUri(context, file);
                } else {
                    resultUri = Uri.fromFile(file);
                }
            }
        }
        return resultUri;
    }

    private static Uri getImageContentUri(Context context, File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[] { MediaStore.Images.Media._ID }, MediaStore.Images.Media.DATA + "=? ",
                new String[] { filePath }, null);
        Uri uri = null;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
                Uri baseUri = Uri.parse("content://media/external/images/media");
                uri = Uri.withAppendedPath(baseUri, "" + id);
            }

            cursor.close();
        }

        if (uri == null) {
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.DATA, filePath);
            uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        }

        return uri;
    }

}
