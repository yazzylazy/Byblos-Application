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

public class FormList extends ArrayAdapter<Forms> {

    private Activity context;
    List<Forms> forms;

    public FormList(Activity context, List<Forms> forms) {
        super(context, R.layout.form_list,forms);
        this.context = context;
        this.forms = forms;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewForms = inflater.inflate(R.layout.form_list, null, true);

        TextView username = (TextView) listViewForms.findViewById(R.id.Fusername);
        TextView firstLast = (TextView)listViewForms.findViewById(R.id.FfirstLast);
        TextView dateOfBirth = (TextView)listViewForms.findViewById(R.id.FdateOfBirth);
        TextView address = (TextView)listViewForms.findViewById(R.id.Faddress);
        TextView email =(TextView) listViewForms.findViewById(R.id.Femail);
        TextView licenseType = (TextView)listViewForms.findViewById(R.id.FlicenseType);
        TextView carType = (TextView)listViewForms.findViewById(R.id.FcarType);
        TextView returnDate = (TextView)listViewForms.findViewById(R.id.FreturnDate);
        TextView pickupDate = (TextView)listViewForms.findViewById(R.id.FpickUpDate);
        TextView maxKilometers = (TextView)listViewForms.findViewById(R.id.FmaxKilometers);
        TextView truckArea = (TextView)listViewForms.findViewById(R.id.FtruckArea);
        TextView movingStart = (TextView)listViewForms.findViewById(R.id.FmovingStart);
        TextView movingEnd = (TextView)listViewForms.findViewById(R.id.FmovingEnd);
        TextView numberMovers = (TextView)listViewForms.findViewById(R.id.FNumbersMovers);
        TextView numberBoxes = (TextView)listViewForms.findViewById(R.id.FNumberBoxes);

        Forms form = forms.get(position);
        if(form.getUsername().isEmpty()){ username.setVisibility(View.GONE); } else{username.setText(form.getUsername());}
        if(form.getFirstLast().isEmpty()){ firstLast.setVisibility(View.GONE); } else{ firstLast.setText("First and Last Name: "+form.getFirstLast());}
        if(form.dateOfBirth.isEmpty()){ dateOfBirth.setVisibility(View.GONE); } else{dateOfBirth.setText("Date of Birth: "+form.getDateOfBirth()); }
        if(form.address.isEmpty()){ address.setVisibility(View.GONE); } else{ address.setText("Address: "+form.getAddress());}
        if(form.emailString.isEmpty()){ email.setVisibility(View.GONE); } else{  email.setText("Email: "+form.getEmailString());}
        if(form.licenseType.isEmpty()){ licenseType.setVisibility(View.GONE); } else{  licenseType.setText("License Type: "+form.getLicenseType());}
        if(form.getCarType().isEmpty()){ carType.setVisibility(View.GONE); } else{  carType.setText("Type of Car: "+form.getCarType());}
        if(form.getReturnDate().isEmpty()){ returnDate.setVisibility(View.GONE); } else{returnDate.setText("Return date: "+form.getReturnDate()); }
        if(form.getPickUpDate().isEmpty()){ pickupDate.setVisibility(View.GONE); } else{ pickupDate.setText("Pick up date: "+form.getPickUpDate());}
        if(form.getMaxKilometers().isEmpty()){ maxKilometers.setVisibility(View.GONE); } else{ maxKilometers.setText("Maximum number of kilometers: "+form.getMaxKilometers()); }
        if(form.getTruckArea().isEmpty()){ truckArea.setVisibility(View.GONE); } else{ truckArea.setText("Area truck will be driven: "+form.getTruckArea());}
        if(form.getMovingStart().isEmpty()){ movingStart.setVisibility(View.GONE); } else{movingStart.setText("Moving Start Location: "+form.getMovingEnd()); }
        if(form.getMovingEnd().isEmpty()){ movingEnd.setVisibility(View.GONE); } else{ movingEnd.setText("Moving End Location: "+form.getMovingEnd()); }
        if(form.getNumberMovers().isEmpty()){ numberMovers.setVisibility(View.GONE); } else{ numberMovers.setText("Number of Movers: "+form.getNumberMovers()); }
        if(form.getNumberBoxes()==null || form.getNumberBoxes().isEmpty()  ){ numberBoxes.setVisibility(View.GONE); } else{  numberBoxes.setText("Number of boxes: "+form.getNumberBoxes());}









        return listViewForms;
    }
}
