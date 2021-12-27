package com.example.byblosapplication;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EmployeeFeatures extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    Button btnCreateEmployee, btnCreateCustomer, btnSignOutEmployee, btnAddToBranch, createRequest , btnListServiceRequests , btnRemoveService,btnAccountDetails;
    TextView textStartTime,textEndTime,textRating;
    Boolean lastSelectedTime = true;
    FirebaseDatabase database;
    String name;

    WorkingHours WorkHours;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_features);

        Button goToCreateEmployee = (Button) findViewById(R.id.btnCreateEmployee);
        Button setStartTime = (Button) findViewById(R.id.btnStartTime);
        Button setEndTime = (Button) findViewById(R.id.btnEndTime);
        Button goToLogIn = (Button) findViewById(R.id.btnSignOutEmployee);
        Button addToBranch = (Button) findViewById(R.id.btnAddToBranch);
        Button btnListServiceRequests = (Button) findViewById(R.id.btnListServiceRequests);
        btnRemoveService = (Button) findViewById(R.id.btnRemoveService);
        //createRequest = (Button) findViewById(R.id.btnCreateServiceRequest);
        textStartTime = (TextView) findViewById(R.id.txtStartTime);
        textEndTime = (TextView) findViewById(R.id.txtEndTime);
        textRating = (TextView) findViewById(R.id.TextViewRATING);
        btnAccountDetails = findViewById(R.id.btnAccountDetails);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String name = extras.getString("NAME");


        // update the start and end times as soon as the activity is created
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference refServices = database.getReference("Branch/"+name+"/WorkingHours");

        refServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                WorkingHours workinghours = snapshot.getValue(WorkingHours.class);
                WorkHours = workinghours;
                textStartTime.setText("Hour: " + workinghours.getStartHour() + " Minute: " + workinghours.getStartMinute());
                textEndTime.setText("Hour: " + workinghours.getEndHour() + " Minute: " + workinghours.getEndMinute());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        FirebaseDatabase data = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Ratings/"+name);
        final double[] counter = {0};
        final double[] sum = {0};
        System.out.println("counter should be 1");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap: snapshot.getChildren()){
                    sum[0] +=snap.getValue(Double.class);
                    counter[0]++;
                    textRating.setText(Double.toString(sum[0]/ counter[0]));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });








        /*
        TextView showStartTime , showEndTime ;
        showStartTime = (TextView) findViewById(R.id.txtStartTime);
        showEndTime = (TextView) findViewById(R.id.txtEndTime);


         DatabaseReference startHour =  database.getReference("Users/"+name+"/startHour");
        DatabaseReference startMin =  database.getReference("Users/"+name+"/startMin");
        DatabaseReference endHour =  database.getReference("Users/"+name+"/endHour");
        DatabaseReference endMin =  database.getReference("Users/"+name+"/endMin");
        */




        // set start time

        setStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastSelectedTime = true;
                DialogFragment timePicker = new TimeSelector();
                timePicker.show(getSupportFragmentManager(), "Set start time");
            }
        });

        // set end time

        setEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastSelectedTime = false;
                DialogFragment timePicker = new TimeSelector();
                timePicker.show(getSupportFragmentManager(), "Set end time");
            }
        });

        //send you to list of service requests
        btnListServiceRequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ServiceRequests.class);
                Bundle extras = new Bundle();
                extras.putString("NAME", name);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        // go to ServicesList if button is pressed

        addToBranch.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ListActiveServices.class);
                Bundle extras = new Bundle();
                extras.putString("TYPE", "Add To Branch");
                extras.putString("NAME", name);
                intent.putExtras(extras);
                startActivity(intent);
           }
       });

        // sends to list of services offered by branch
        btnRemoveService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BranchServices.class);
                Bundle extras = new Bundle();
                extras.putString("NAME",name);
                extras.putString("type","remove");
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        // sends you to account details
        btnAccountDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewAccountDetails.class);
                Bundle extras = new Bundle();
                extras.putString("TYPE","Employee");
                extras.putString("NAME", name);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });


        // go to createEmployee activity if button is pressed

        goToCreateEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CreateBranchAccount.class);
                Bundle extras = new Bundle();
                extras.putString("ROLE", "Employee");
                extras.putString("NAME", name);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        // Sign out and go back to login page

        goToLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LogInPage.class);
                startActivity(intent);
            }
        });
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView showTime;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String name = extras.getString("NAME");

        if (lastSelectedTime) {
            showTime = (TextView) findViewById(R.id.txtStartTime);
            DatabaseReference startHour =  database.getReference("Branch/"+name+"/WorkingHours/startHour");
            DatabaseReference startMin =  database.getReference("Branch/"+name+"/WorkingHours/startMinute");

            // makes sure you can't set end time after
            if(!validStartHours(WorkHours, hourOfDay, minute)) {
                Toast.makeText(getApplicationContext(), "Can not set start time after end time", Toast.LENGTH_SHORT).show();
                return;
            }
            /*
            if(WorkHours[0].getEndHour()<hourOfDay){
                Toast.makeText(getApplicationContext(), "Can not set start time after end time", Toast.LENGTH_SHORT).show();
                return;
            }
            // makes sure you can't set end time after start time given the hours are the same?
            if(WorkHours[0].getEndHour()==hourOfDay && WorkHours[0].getEndMinute()< minute){
                Toast.makeText(getApplicationContext(), "Can not set start time after end time", Toast.LENGTH_SHORT).show();
                return;
            }
            */
            startHour.setValue(hourOfDay);
            startMin.setValue(minute);

        }
        else {
            showTime = (TextView) findViewById(R.id.txtEndTime);
            DatabaseReference endHour =  database.getReference("Branch/"+name+"/WorkingHours/endHour");
            DatabaseReference endMin =  database.getReference("Branch/"+name+"/WorkingHours/endMinute");

            if(!validEndHours(WorkHours, hourOfDay, minute)){
                Toast.makeText(getApplicationContext(), "Can not set end time before start time", Toast.LENGTH_SHORT).show();
                return;
            }
            /*
            // makes sure you can't set start time after end time
            if(time.getStartHour()>hour){
                Toast.makeText(getApplicationContext(), "Can not set end time before start time", Toast.LENGTH_SHORT).show();
                return;
            }
            // makes sure you can't set start time after end time given the hours are the same?
            if(WorkHours[0].getStartHour()==hourOfDay && WorkHours[0].getStartHour()> minute){
                Toast.makeText(getApplicationContext(), "Can not set end time before start time", Toast.LENGTH_SHORT).show();
                return;
            }
            */
            endHour.setValue(hourOfDay);
            endMin.setValue(minute);
        }
        showTime.setText("Hour: " + hourOfDay + " Minute: " + minute);
    }

    public static boolean validStartHours(WorkingHours time, int hour, int minute) {
        if(time.getEndHour()<hour) {
            return false;
        }
        if(time.getEndHour()==hour && time.getEndMinute()< minute) {
            return false;
        }
        return true;
    }

    public static boolean validEndHours(WorkingHours time, int hour, int minute) {
        if(time.getStartHour()>hour) {
            return false;
        }
        if(time.getStartHour()==hour && time.getStartHour()> minute) {
            return false;
        }
        return true;

    }


}