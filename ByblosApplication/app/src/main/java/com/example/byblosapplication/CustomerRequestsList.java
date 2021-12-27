package com.example.byblosapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import android.util.Pair;

import java.util.List;


public class CustomerRequestsList extends ArrayAdapter<Pair<Boolean,String>> {
    private Activity context;
    List<Pair<Boolean,String>> serviceRequests;


    public CustomerRequestsList(Activity context,   List<Pair<Boolean,String>> serviceRequests) {
        super(context, R.layout.customer_request_list, serviceRequests);
        this.context = context;
        this.serviceRequests = serviceRequests;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewUsers = inflater.inflate(R.layout.customer_request_list, null, true);

        TextView textViewName = (TextView) listViewUsers.findViewById(R.id.Branch_Name);
        TextView textViewAcceptedOrRefused = (TextView) listViewUsers.findViewById(R.id.AcceptedOrRefused);

        Pair<Boolean,String> pair = serviceRequests.get(position);

        textViewName.setText(pair.second);
        if(pair.first){
            textViewAcceptedOrRefused.setText("Accepted");
        }
        else{
            textViewAcceptedOrRefused.setText("Refused");
        }


        return listViewUsers;
    }
}