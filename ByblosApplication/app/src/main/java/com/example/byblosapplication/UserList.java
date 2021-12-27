package com.example.byblosapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;


public class UserList extends ArrayAdapter<Users> {
    private Activity context;
    List<Users> users;

    public UserList(Activity context, List<Users> users) {
        super(context, R.layout.user_list, users);
        this.context = context;
        this.users = users;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewUsers = inflater.inflate(R.layout.user_list, null, true);

        TextView textViewName = (TextView) listViewUsers.findViewById(R.id.textViewName);

        Users user = users.get(position);
        textViewName.setText(user.getUsername());

        return listViewUsers;
    }
}