package com.example.android.newsfeed;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by user on 16-06-2017.
 */

public class NewsAdapter extends ArrayAdapter {
    public NewsAdapter(@NonNull Context context, ArrayList<News> news) {
        super(context,0,news);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View list_item_view = convertView;
        if (list_item_view == null){
            list_item_view = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        News news = (News) getItem(position);
        TextView head_text = (TextView) list_item_view.findViewById(R.id.title);
        head_text.setText(news.getHeadline());
        TextView desc_text = (TextView) list_item_view.findViewById(R.id.description);
        desc_text.setText(news.getDescription());
        TextView author = (TextView) list_item_view.findViewById(R.id.author);
        author.setText("By "+news.getAuthor());
        TextView time = (TextView) list_item_view.findViewById(R.id.time);
        time.setText("On "+news.getTime());
        ImageView image = (ImageView) list_item_view.findViewById(R.id.thumbnail);
        Picasso.with(getContext()).load(news.getImageResource()).into(image);
        return list_item_view;
    }
}
