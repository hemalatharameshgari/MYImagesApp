package com.hemalatha.myimagesapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
/*import com.squareup.picasso.Picasso;*/

import java.util.ArrayList;
import java.util.List;

public class MyImageAdapter extends RecyclerView.Adapter<MyImageAdapter.ImageViewHolder> {
    Context context;
  //  String url="https://pixabay.com/get/ea34b50f2cfd033ed1584d05fb1d4797e670e4dd19b00c4090f4c870a1e9bcbdd0_1280.jpg";
    List<ImageModel> list;
    public MyImageAdapter(DetailActivity detailActivity,List<ImageModel> listModels) {
        context=detailActivity;
        list=listModels;
    }

    @NonNull
    @Override
    public MyImageAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view= LayoutInflater.from(context).inflate(R.layout.row_image,viewGroup,false);
       return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyImageAdapter.ImageViewHolder imageViewHolder, int i) {

        //Glide.with(context).load(model.getImages()).into(imageViewHolder.image);
        //Picasso.load(model.getImages()).into(imageViewHolder.image);
     Picasso.with(context).load(list.get(i).getImages())
             .into(imageViewHolder.image);
        Log.i("imageurls",list.get(i).getImages());
        //imageViewHolder.image.setImageResource([i]);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.iv);
        }
    }
}
