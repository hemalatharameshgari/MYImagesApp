package com.hemalatha.myimagesapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ImageLoader extends AsyncTaskLoader<String>{
    String url;
    public ImageLoader(@NonNull Context context, String key1) {
        super(context);
        url=key1;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        try {
            URL myUrl=new URL(url);
            HttpsURLConnection con= (HttpsURLConnection) myUrl.openConnection();
            InputStream inputStream=con.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder=new StringBuilder();
            String line=null;
            while ((line=br.readLine())!=null){
                builder.append(line+"\n");
            }
           return builder.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}