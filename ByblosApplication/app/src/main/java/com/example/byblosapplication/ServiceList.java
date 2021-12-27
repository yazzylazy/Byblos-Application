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

public class ServiceList extends ArrayAdapter<Services> {
    private Activity context;
    List<Services> services;

    public ServiceList(Activity context, List<Services> services) {
        super(context, R.layout.service_list, services);
        this.context = context;
        this.services = services;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewServices = inflater.inflate(R.layout.service_list, null, true);

        TextView textViewName = (TextView) listViewServices.findViewById(R.id.serviceViewName);

        Services service = services.get(position);
        textViewName.setText(service.getServiceName());

        return listViewServices;
    }

}
