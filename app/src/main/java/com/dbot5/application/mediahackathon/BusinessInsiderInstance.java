package com.dbot5.application.mediahackathon;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by astrode5 on 24/3/18.
 */


public class BusinessInsiderInstance {
    private String author;
    private String title;
    private String description;
    private URL url;
    private URL urlToImage;
    private String publishedAt;
    public void displayAuthor( StringBuilder stringBuilder){
        stringBuilder.append(this.author);
    }
    public void displayPublishTime( StringBuilder stringBuilder){
        stringBuilder.append(this.publishedAt);
    }
    public void displayDescription( StringBuilder stringBuilder){
        stringBuilder.append(this.description);
    }
    /*public void displayImage(){
        Picasso.with(HomeActivity.this).load(urlToImage).into(news_image_id);
    }*/
    public void displayTitle(StringBuilder stringBuilder){
        stringBuilder.append(this.title);
    }

}
