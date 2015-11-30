package com.example.javier.imagenesweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ActividadVisor extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_visor);
        init();
    }

    private void init(){
        iv = (ImageView)findViewById(R.id.ivFoto);
        String s = getIntent().getExtras().getString("url");
        DescargaImagen d = new DescargaImagen(iv);
        d.execute(new String[]{s});
    }
}
