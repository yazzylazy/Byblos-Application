package com.example.byblosapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewAccountDetails extends AppCompatActivity {
    Button back;
    TextView viewUsernameInput,ViewEmailInput,ViewPasswordInput,ViewAddressInput,ViewPhoneInput,ViewCityInput,ViewProvinceInput,ViewCountryInput,ViewZIPINPUT;
    DatabaseReference data ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_account_details);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String type = extras.getString("TYPE");
        String name = extras.getString("NAME");
        System.out.println(type);


        back = findViewById(R.id.backBtn);


        viewUsernameInput = findViewById(R.id.viewUsernameInput);
        ViewEmailInput = findViewById(R.id.ViewEmailInput);
        ViewPasswordInput = findViewById(R.id.ViewPasswordInput);
        ViewAddressInput = findViewById(R.id.ViewAddressInput);
        ViewPhoneInput = findViewById(R.id.ViewPhoneInput);
        ViewCityInput = findViewById(R.id.ViewCityInput);
        ViewProvinceInput = findViewById(R.id.ViewProvinceInput);
        ViewCountryInput = findViewById(R.id.ViewCountryInput);
        ViewZIPINPUT = findViewById(R.id.ViewZIPINPUT);

        data = FirebaseDatabase.getInstance().getReference("users");


        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for(DataSnapshot snap:snapshot.getChildren()){

                    Users user = snap.getValue(Users.class);
                    if(user.getUsername().equals(extras.getString("NAME"))){

                        viewUsernameInput.setText(user.getUsername());
                        ViewEmailInput.setText(user.getEmail());
                        ViewPasswordInput.setText(user.getPassword());
                        ViewAddressInput.setText(user.getAddress());
                        ViewPhoneInput.setText(user.getPhone());
                        ViewCityInput.setText(user.getCity());
                        ViewProvinceInput.setText(user.getProvince());
                        ViewCountryInput.setText(user.getCountry());
                        ViewZIPINPUT.setText(user.getZIP());


                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type.equals("Customer")){
                    Intent intent = new Intent(v.getContext(), CustomerFeatures.class);
                    Bundle extras = new Bundle();
                    extras.putString("NAME",name);
                    intent.putExtras(extras);
                    startActivity(intent);
                }
                else if(type.equals("Employee")){
                    Intent intent = new Intent(v.getContext(), EmployeeFeatures.class);
                    Bundle extras = new Bundle();
                    extras.putString("NAME", name);
                    intent.putExtras(extras);
                    startActivity(intent);
                }
            }
        });
    }




}