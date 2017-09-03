package com.hash.android.connekt;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by aditi on 03-Sep-17.
 */

public class ChatAdapter extends ArrayAdapter<User> {

    public ChatAdapter(Activity context, ArrayList<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // check if the current view is reused else inflate the view
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        //get the object located at position
        User user = getItem(position);

        //find the textview in list_item with id default_text_view
        TextView profileName = (TextView) listItemView.findViewById(R.id.name);
        //gets the default Translation and set it to the text of this textView
        profileName.setText(user.getName());


        //find the image view with id image
        ImageView profilePic = (ImageView) listItemView.findViewById(R.id.profilePic);

        Glide.with(profilePic.getContext())
                .load(user.getPhotoURL())
                .into(profilePic);

        return listItemView;
    }
}


