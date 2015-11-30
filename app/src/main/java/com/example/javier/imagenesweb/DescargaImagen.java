package com.example.javier.imagenesweb;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Javier on 28/11/2015.
 */
public class DescargaImagen extends AsyncTask<String, Integer, Bitmap> {
    private ImageView iv;

    public DescargaImagen(ImageView iv){
        this.iv = iv;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        URL imageUrl = null;
        HttpURLConnection conn = null;

        try {

            imageUrl = new URL(params[0]);
            conn = (HttpURLConnection) imageUrl.openConnection();
            conn.connect();

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 1;

            Bitmap imagen = BitmapFactory.decodeStream(conn.getInputStream(), new Rect(0, 0, 0, 0), options);

            return imagen;
        } catch (IOException e) {

            e.printStackTrace();

        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        iv.setImageBitmap(bitmap);

    }
}
