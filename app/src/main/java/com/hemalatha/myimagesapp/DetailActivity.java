package com.hemalatha.myimagesapp;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class DetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    String url;
    RecyclerView rv;
    //ProgressDialog pd;
    ArrayList<ImageModel> listModels;
    Bundle b;
    String name;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        rv = findViewById(R.id.recyclerView_image);
        progressBar=findViewById(R.id.progress);
        name = getIntent().getStringExtra("names");
        url="https://pixabay.com/api/?key=11576801-c95da5cfd68c0f7180afeccf0&q="+name;
        Log.i("imageurls",name);
        /*b = new Bundle();
        b.putString("key1", name);*/
        listModels = new ArrayList<>();
        getSupportLoaderManager().initLoader(1, null, this);

        rv.setAdapter(new MyImageAdapter(this, listModels));
        rv.setLayoutManager(new GridLayoutManager(this, 2));
    }

    /*@NonNull
    @Override
    public Loader onCreateLoader(int i, @Nullable Bundle bundle) {
        *//*pd=new ProgressDialog(this);
        pd.setMessage("please Wait");
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.show();*//*
        return new ImageLoader(this, url + bundle.getString("key1"));

    }*/

    @Override
    public void onLoadFinished(@NonNull Loader loader, String s) {
        //Toast.makeText(this, "" + loader, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "" + s, Toast.LENGTH_SHORT).show();
       // pd.dismiss();
    progressBar.setVisibility(View.GONE);
        try {
            JSONObject object = new JSONObject(s);
            //String totalHints = object.getString("totalHits");
            JSONArray array = object.getJSONArray("hits");
            //String total = object.optString("total");
            if(array!=null)
            for (int i = 0; i < array.length(); i++) {
                JSONObject object1 = array.getJSONObject(i);
                String image = object1.getString("largeImageURL");

                //Toast.makeText(this, "ding :"+imgModel, Toast.LENGTH_LONG).show();
                listModels.add(new ImageModel(image));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //rv.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onLoaderReset(@NonNull Loader loader) {

    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new AsyncTaskLoader<String>(this) {
            @Nullable
            @Override
            public String loadInBackground() {
                try {
                    URL myUrl=new URL(url);
                    Log.i("imageurls",url.toString());
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
                progressBar.setVisibility(View.VISIBLE);
                forceLoad();
            }
        };
    }
}
