package com.example.android.booklist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by user on 13-06-2017.
 */

public class BookAdapter extends ArrayAdapter<Book> {
    public BookAdapter(Context context, ArrayList<Book> objects) {
        super(context,0,objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View list_item_view = convertView;
        if (list_item_view == null){
            list_item_view = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        Book currentBook = getItem(position);
        ImageView imageView = (ImageView) list_item_view.findViewById(R.id.image_view);
        Picasso.with(getContext())
                .load(currentBook.getImageResource())
                .into(imageView);
        TextView title_text = (TextView) list_item_view.findViewById(R.id.title);
        title_text.setText(currentBook.getTitleResource());
        TextView author_text = (TextView) list_item_view.findViewById(R.id.author);
        author_text.setText(currentBook.getAuthorResource());
        TextView price_text = (TextView) list_item_view.findViewById(R.id.price);
        price_text.setText(currentBook.getDate());
        return list_item_view;
    }
}
