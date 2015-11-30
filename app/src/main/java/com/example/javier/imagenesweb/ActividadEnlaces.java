package com.example.javier.imagenesweb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ActividadEnlaces extends AppCompatActivity {
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_enlaces);
        init();
    }

    private void init(){
        lv = (ListView)findViewById(R.id.listView);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ActividadEnlaces.this, ActividadVisor.class);
                Log.v("borrame", lv.getItemAtPosition(position).toString());
                intent.putExtra("url", lv.getItemAtPosition(position).toString());
                startActivity(intent);
            }
        });

        DescargaWeb descargaWeb = new DescargaWeb(this, lv);
        descargaWeb.execute(new String[]{getIntent().getExtras().getString("enlace")});
    }
}
