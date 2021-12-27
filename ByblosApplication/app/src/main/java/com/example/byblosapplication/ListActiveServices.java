package com.example.byblosapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListActiveServices extends AppCompatActivity {

    ArrayList<Services> servicesList;
    ListView ServicelistView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_active_services);
        ServicelistView = findViewById(R.id.serviceListView);

        servicesList = new ArrayList<>();
        // when we edit an activity pass bundle edit as well as as the service name

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String type = extras.getString("TYPE");
        //String visibility = extras.getString("VISIBILITY");

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference refServices = database.getReference("Services");

        refServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 servicesList.clear();
                 for(DataSnapshot datasnap: snapshot.getChildren()){
                     Services service = datasnap.getValue(Services.class);
                     servicesList.add(service);
                 }
                ServiceList usersAdapter = new ServiceList(ListActiveServices.this, servicesList);

                ServicelistView.setAdapter(usersAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

       if(type.equals("edit")){
           ServicelistView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
               @Override
               public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                   Intent intent = new Intent(view.getContext(),CreateService.class);
                   Bundle extras = new Bundle();
                   extras.putString("TYPE","edit");
                   extras.putString("SERVICE", servicesList.get(position).getServiceName());
                   extras.putDouble("HRATE", servicesList.get(position).getHourlyRate());
                   extras.putBoolean("FIRST_LAST", servicesList.get(position).isFirstLastV());
                   extras.putBoolean("ADDRESS", servicesList.get(position).isAddressV());
                   extras.putBoolean("DATEOFBIRTH", servicesList.get(position).isDateOfBirthV());
                   extras.putBoolean("EMAILSTRING", servicesList.get(position).isEmailStringV());
                   extras.putBoolean("LICENSETYPE", servicesList.get(position).isLicenseTypeV());
                   extras.putBoolean("CARTYPE", servicesList.get(position).isCarTypeV());
                   extras.putBoolean("RETURNDATE", servicesList.get(position).isReturnDateV());
                   extras.putBoolean("PICKUPDATE", servicesList.get(position).isPickUpDateV());
                   extras.putBoolean("MAXKILOMETERS", servicesList.get(position).isMaxKilometersV());
                   extras.putBoolean("TRUCKAREA", servicesList.get(position).isTruckAreaV());
                   extras.putBoolean("MOVINGSTART", servicesList.get(position).isMovingStartV());
                   extras.putBoolean("MOVINGEND", servicesList.get(position).isMovingEndV());
                   extras.putBoolean("NUMBERMOVERS", servicesList.get(position).isNumberMoversV());
                   extras.putBoolean("NUMBERBOXES", servicesList.get(position).isNumberBoxesV());
                   intent.putExtras(extras);
                   startActivity(intent);
                   return true;
               }
           });


       }
       else if(type.equals("Add To Branch")) {
           ServicelistView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
              @Override
              public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                  FirebaseDatabase databaseSolid = FirebaseDatabase.getInstance();
                  DatabaseReference ref = databaseSolid.getReference("Branch/"+extras.getString("NAME")+"/Services/"+servicesList.get(i).getServiceName());
                  ref.setValue(servicesList.get(i));
                  Toast.makeText(getApplicationContext(), "Added Service!", Toast.LENGTH_SHORT).show();
                  return true;
              }
          });

       }
       else if(type.equals("delete")) {
           ServicelistView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
               @Override
               public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                   FirebaseDatabase database = FirebaseDatabase.getInstance();

                   if(servicesList.size()==3){
                       Toast.makeText(getApplicationContext(), "Have to have 3 services at all times", Toast.LENGTH_SHORT).show();
                   }
                   else{

                       String twoDelete = servicesList.get(position).getServiceName();
                       DatabaseReference services = database.getReference("Services/" + servicesList.get(position).getServiceName());
                       services.setValue(null);
                       Toast.makeText(getApplicationContext(), servicesList.get(position).getServiceName() + " has been deleted", Toast.LENGTH_SHORT).show();


                       FirebaseDatabase DATA = FirebaseDatabase.getInstance();

                       DatabaseReference refServices = DATA.getReference("Services");

                       refServices.addValueEventListener(new ValueEventListener() {
                           @Override
                           public void onDataChange(@NonNull DataSnapshot snapshot) {
                               servicesList.clear();
                               for(DataSnapshot datasnap: snapshot.getChildren()){
                                   Services service = datasnap.getValue(Services.class);
                                   servicesList.add(service);
                               }
                               ServiceList usersAdapter = new ServiceList(ListActiveServices.this, servicesList);

                               ServicelistView.setAdapter(usersAdapter);
                           }

                           @Override
                           public void onCancelled(@NonNull DatabaseError error) {

                           }

                       });


                       ArrayList<Users> branchAccounts = new ArrayList<>();

                       DatabaseReference data = FirebaseDatabase.getInstance().getReference("users");

                       data.addValueEventListener(new ValueEventListener() {
                           @Override

                           public void onDataChange(@NonNull DataSnapshot snapshot) {
                               branchAccounts.clear();

                               for(DataSnapshot dataS:snapshot.getChildren()){

                                   Users user = dataS.getValue(Users.class);
                                   if(user.getRole().equals("Employee")){
                                       branchAccounts.add(user);
                                   }

                               }
                               //UserList usersAdapter = new UserList(ListBranchAccounts.this, branchAccounts);

                               System.out.println("something");
                               for(int i=0;i<branchAccounts.size();i++){
                                   DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Branch/"+branchAccounts.get(i).getUsername()+"/Services");

                                   int finalI1 = i;
                                   ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                       @Override
                                       public void onDataChange(@NonNull DataSnapshot snapshot) {
                                           //System.out.println("nothing");
                                           for(DataSnapshot snap: snapshot.getChildren()){
                                               Services service = snap.getValue(Services.class);
                                               System.out.println("Service snap " + service.getServiceName());
                                               //System.out.println("Service name list" + servicesList.get(position-1).getServiceName());
                                               if(service.getServiceName().equals(twoDelete)){

                                                   DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Branch/"+branchAccounts.get(finalI1).getUsername()+"/Services/"+service.getServiceName());
                                                   //System.out.println(servicesList.get(position).getServiceName());
                                                   ref.setValue(null);
                                               }
                                           }
                                       }

                                       @Override
                                       public void onCancelled(@NonNull DatabaseError error) {

                                       }
                                   });


                               }


                           }



                           @Override
                           public void onCancelled(@NonNull DatabaseError error) {

                           }
                       });



                   }

                   return true;
               }


           });
           refServices.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                   servicesList.clear();
                   for (DataSnapshot datasnap : snapshot.getChildren()) {
                       Services service = datasnap.getValue(Services.class);
                       servicesList.add(service);
                   }
                   ServiceList usersAdapter = new ServiceList(ListActiveServices.this, servicesList);

                   ServicelistView.setAdapter(usersAdapter);
               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {

               }
           });
       }




    }
}