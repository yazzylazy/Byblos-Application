package com.example.byblosapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CustomerServiceRequestView extends AppCompatActivity {

    ListView ServiceRequest;
    ArrayList<Forms> forms;
    ArrayList<Pair<Boolean,String>> listPairs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_service_request_view);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String customerName = extras.getString("NAME");
        ServiceRequest = findViewById(R.id.CustomerServiceRequestList);


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Forms/"+customerName);

        forms = new ArrayList<>();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                forms.clear();
                for(DataSnapshot snap : snapshot.getChildren()){
                    forms.add(snap.getValue(Forms.class));
                }

                System.out.println("Size: "+forms.size());
                listPairs =  new  ArrayList<>();

                for(int i=0;i<forms.size();i++){
                    Pair<Boolean,String> pair = new Pair<Boolean,String>(forms.get(i).isAccepted(),forms.get(i).getServiceName());
                    System.out.println(pair.first+"  "+pair.second);
                    listPairs.add(pair);

                }

                CustomerRequestsList RequestAdapter = new CustomerRequestsList(CustomerServiceRequestView.this,listPairs);

                ServiceRequest.setAdapter(RequestAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}