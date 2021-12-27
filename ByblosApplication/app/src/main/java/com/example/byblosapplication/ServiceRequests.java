package com.example.byblosapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ServiceRequests extends AppCompatActivity {
    ListView ServiceRequestsListView;
    ArrayList<Forms> FormList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_requests);
        ServiceRequestsListView = findViewById(R.id.ServiceRequestsListView);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String name = extras.getString("NAME");
        FormList = new ArrayList<>();


        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference refServices = database.getReference("Branch/"+name+"/ServiceRequests");

        refServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                FormList.clear();
                for(DataSnapshot datasnap: snapshot.getChildren()){
                    Forms form = datasnap.getValue(Forms.class);
                    FormList.add(form);
                }
                FormList formAdapter = new FormList(ServiceRequests.this, FormList);

                ServiceRequestsListView.setAdapter(formAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });



        ServiceRequestsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(view.getContext());
                LayoutInflater inflater = getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.service_request_dialogue, null);
                dialogBuilder.setView(dialogView);


                final Button buttonAccept = (Button) dialogView.findViewById(R.id.btnAcceptRequest);
                final Button ButtonDeny = (Button) dialogView.findViewById(R.id.btnDenyRequest);


                final AlertDialog b = dialogBuilder.create();
                b.show();

                buttonAccept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatabaseReference Form = database.getReference("Branch/"+name+"/ServiceRequests/"+FormList.get(position).getServiceName());
                        Form.setValue(null);
                        Form = database.getReference("Forms/"+FormList.get(position).getUsername() +"/"+FormList.get(position).getServiceName()+"/accepted");
                        Form.setValue(true);
                        b.dismiss();
                    }
                });

                ButtonDeny.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatabaseReference Form = database.getReference("Branch/"+name+"/ServiceRequests/"+FormList.get(position).getServiceName());
                        Form.setValue(null);
                        Form = database.getReference("Forms/"+FormList.get(position).getUsername() +"/"+FormList.get(position).getServiceName()+"/accepted");
                        Form.setValue(false);
                        b.dismiss();
                    }
                });
                return true;
            }
        });



    }

}