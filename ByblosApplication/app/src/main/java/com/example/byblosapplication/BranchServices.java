package com.example.byblosapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BranchServices extends AppCompatActivity {
    ListView BranchServiceListView;
    ArrayList<Services> BranchServices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_services);
        BranchServiceListView = findViewById(R.id.BranchServicesListView);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String name = extras.getString("NAME");
        String type = extras.getString("type");
        String customer = extras.getString("CUSTOMER");
        BranchServices = new ArrayList<>();


        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference refServices = database.getReference("Branch/"+name+"/Services");

        refServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                BranchServices.clear();
                for(DataSnapshot datasnap: snapshot.getChildren()){
                    Services service = datasnap.getValue(Services.class);
                    BranchServices.add(service);
                }
                ServiceList usersAdapter = new ServiceList(BranchServices.this, BranchServices);

                BranchServiceListView.setAdapter(usersAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        if(type.equals("remove")){
            BranchServiceListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    FirebaseDatabase databaseSolid = FirebaseDatabase.getInstance();
                    DatabaseReference ref = databaseSolid.getReference("Branch/"+extras.getString("NAME")+"/Services/"+BranchServices.get(position).getServiceName());
                    ref.setValue(null);
                    Toast.makeText(getApplicationContext(), "Service has been removed", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }
        else if(type.equals("request")){
            BranchServiceListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(view.getContext(), CreateForm.class);
                    Bundle extras = new Bundle();
                    extras.putString("TYPE","edit");
                    extras.putString("NAME",name);
                    extras.putString("SERVICE", BranchServices.get(position).getServiceName());
                    extras.putDouble("HRATE", BranchServices.get(position).getHourlyRate());
                    extras.putBoolean("FIRST_LAST", BranchServices.get(position).isFirstLastV());
                    extras.putBoolean("ADDRESS", BranchServices.get(position).isAddressV());
                    extras.putBoolean("DATEOFBIRTH", BranchServices.get(position).isDateOfBirthV());
                    extras.putBoolean("EMAILSTRING", BranchServices.get(position).isEmailStringV());
                    extras.putBoolean("LICENSETYPE", BranchServices.get(position).isLicenseTypeV());
                    extras.putBoolean("CARTYPE", BranchServices.get(position).isCarTypeV());
                    extras.putBoolean("RETURNDATE", BranchServices.get(position).isReturnDateV());
                    extras.putBoolean("PICKUPDATE", BranchServices.get(position).isPickUpDateV());
                    extras.putBoolean("MAXKILOMETERS", BranchServices.get(position).isMaxKilometersV());
                    extras.putBoolean("TRUCKAREA", BranchServices.get(position).isTruckAreaV());
                    extras.putBoolean("MOVINGSTART", BranchServices.get(position).isMovingStartV());
                    extras.putBoolean("MOVINGEND", BranchServices.get(position).isMovingEndV());
                    extras.putBoolean("NUMBERMOVERS", BranchServices.get(position).isNumberMoversV());
                    extras.putBoolean("NUMBERBOXES", BranchServices.get(position).isNumberBoxesV());
                    extras.putString("CUSTOMER", customer);
                    intent.putExtras(extras);
                    startActivity(intent);
                    return true;
                }
            });
        }


    }

}