package com.example.javier.imagenesweb;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Javier on 28/11/2015.
 */
public class DescargaWeb extends AsyncTask<String, Integer, ArrayList<String>> {

    private ListView lv;
    private Context c;

    public DescargaWeb(Context c, ListView lv) {
        this.lv = lv;
        this.c = c;
    }

    @Override
    protected ArrayList<String> doInBackground(String... params) {
        ArrayList<String> enlaces = new ArrayList<>();

        URL url = null;
        try {
            url = new URL(params[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(url.openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String ln, out = "";
        try {
            while((ln = in.readLine()) != null){
                ln = ln.trim();
                out += ln+"\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String re1="(<)";	// Any Single Character 1
        String re2="(img)";	// Word 1
        String re3="( )";	// White Space 1
        String re4="(src)";	// Word 2
        String re5="(=)";	// Any Single Character 2
        String re6="(\")";	// Any Single Character 3
        String re7="((?:http|https)(?::\\/{2}[\\w]+)(?:[\\/|\\.]?)(?:[^\\s\"]*))";	// HTTP URL 1
        String re8="(\")";	// Any Single Character 4
        String re9=".*?";	// Non-greedy match on filler
        String re10="(>)";	// Any Single Character 5

        Pattern p = Pattern.compile(re1+re2+re3+re4+re5+re6+re7+re8+re9+re10,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(out);
        while (m.find()) {
            String httpurl1=m.group(7);
            System.out.println(httpurl1);;
            enlaces.add(httpurl1);
        }
        return enlaces;
    }

    @Override
    protected void onPostExecute(ArrayList<String> strings) {
        ModeloEnlace adaptador = new ModeloEnlace(c, R.layout.item_enlace, strings);
        lv.setAdapter(adaptador);
    }
}
