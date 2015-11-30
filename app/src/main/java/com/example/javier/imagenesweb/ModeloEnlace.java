package com.example.javier.imagenesweb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Javier on 28/11/2015.
 */
public class ModeloEnlace extends ArrayAdapter<String>{
    private Context contexto;
    private int recurso;
    private LayoutInflater layoutInflater;
    private ArrayList<String> enlaces;

    static class ViewHolder{
        public TextView tvEnlace;
    }

    public ModeloEnlace(Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);
        this.contexto = context;
        this.recurso = resource;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.enlaces = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder v = new ViewHolder();
        String enlace = enlaces.get(position);
        if(convertView==null){
            convertView = layoutInflater.inflate(recurso, null);
            v.tvEnlace = (TextView) convertView.findViewById(R.id.tvEnlace);
            convertView.setTag(v);
        }else{
            v = (ViewHolder) convertView.getTag();
        }
        v.tvEnlace.setText(enlace);

        return convertView;
    }
}
