package com.example.byblosapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class CreateService extends AppCompatActivity {
    ArrayList<Services> servicesList;
    final Services[] SERVICE = {null};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_service);

        Switch firstLastSwitch, dateOfBirthSwitch, addressSwitch, emailSwitch, licenseTypeSwitch, carTypeSwitch,returnDateSwitch, pickupDateSwitch, maxKilometersSwitch,truckAreaSwitch,movingStartSwitch,movingEndSwitch,numberMoversSwitch,numberBoxesSwitch;
        EditText hourlyRateText,ServiceNAME;

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String edit = extras.getString("TYPE");
        String service = extras.getString("SERVICE");
        double HourlyRateExtra = extras.getDouble("HRATE");
        // add all the possible switches

        firstLastSwitch = (Switch) findViewById(R.id.carRentalNameSwitch);
        dateOfBirthSwitch = (Switch) findViewById(R.id.carRentalDOB);
        addressSwitch = (Switch) findViewById(R.id.carRentalAddress);
        emailSwitch = (Switch) findViewById(R.id.carRentalEmail);
        licenseTypeSwitch = (Switch) findViewById(R.id.carRentalLicenseType);
        carTypeSwitch = (Switch) findViewById(R.id.carRentalCarType);
        pickupDateSwitch = (Switch) findViewById(R.id.carRentalPickup);
        returnDateSwitch = (Switch) findViewById(R.id.carRentalReturn);
        maxKilometersSwitch = (Switch) findViewById(R.id.maxKilometers);
        truckAreaSwitch = (Switch) findViewById(R.id.truckArea);
        movingStartSwitch = (Switch) findViewById(R.id.movingStart);
        movingEndSwitch = (Switch) findViewById(R.id.movingEnd);
        numberMoversSwitch = (Switch) findViewById(R.id.numbersMovers);
        numberBoxesSwitch = (Switch) findViewById(R.id.numberBoxes);


        hourlyRateText = findViewById(R.id.carRentalHourlyPrice);
        ServiceNAME =  findViewById(R.id.serviceName);






        // if you edit the service and change the name you need to change the old name to the new one
        if(edit.equals("edit")){

            ServiceNAME.setText(service);
            hourlyRateText.setText(Double.toString(HourlyRateExtra));

            dateOfBirthSwitch.setChecked(extras.getBoolean("DATEOFBIRTH"));
            addressSwitch.setChecked(extras.getBoolean("ADDRESS"));
            firstLastSwitch.setChecked(extras.getBoolean("FIRST_LAST"));
            emailSwitch.setChecked(extras.getBoolean("EMAILSTRING"));
            carTypeSwitch.setChecked(extras.getBoolean("CARTYPE"));
            licenseTypeSwitch.setChecked(extras.getBoolean("LICENSETYPE"));
           pickupDateSwitch.setChecked(extras.getBoolean("PICKUPDATE"));
           returnDateSwitch.setChecked(extras.getBoolean("RETURNDATE"));
           maxKilometersSwitch.setChecked(extras.getBoolean("MAXKILOMETERS"));
           truckAreaSwitch.setChecked(extras.getBoolean("TRUCKAREA"));
           movingStartSwitch.setChecked(extras.getBoolean("MOVINGSTART"));
           movingEndSwitch.setChecked(extras.getBoolean("MOVINGEND"));
           numberMoversSwitch.setChecked(extras.getBoolean("NUMBERMOVERS"));
           numberBoxesSwitch.setChecked(extras.getBoolean("NUMBERSBOXES"));



        }

        Button submitChanges = findViewById(R.id.carRentalSubmit);
        submitChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Boolean[] firstLast = new Boolean[1];
                final Boolean[] dateOfBirth = new Boolean[1];
                final Boolean[] address = new Boolean[1];
                final Boolean[] email = new Boolean[1];
                final Boolean[] licenseType = new Boolean[1];
                final Boolean[] carType = new Boolean[1];
                final Boolean[] pickupDate = new Boolean[1];
                final Boolean[] returnDate = new Boolean[1];
                final Boolean[] maxKilometers = new Boolean[1];
                final Boolean[] truckArea = new Boolean[1];
                final Boolean[] movingStart = new Boolean[1];
                final Boolean[] movingEnd = new Boolean[1];
                final Boolean[] numberMovers = new Boolean[1];
                final Boolean[] numberBoxes = new Boolean[1];
                final double[] hourlyRate = new double[1];

                String serviceName;

                FirebaseDatabase database = FirebaseDatabase.getInstance();


                if(hourlyRateText.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Hourly Rate needs to be filled", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(ServiceNAME.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Service name need to be filled", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(edit.equals("edit")){
                    if(ServiceNAME.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Service name need to be filled", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(hourlyRateText.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Hourly Rate needs to be filled", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if(!hourlyRateText.getText().toString().isEmpty()){
                        for(int i=0;i<hourlyRateText.getText().toString().length();i++){
                            if(!Character.isDigit(hourlyRateText.getText().toString().charAt(i))){
                                if(hourlyRateText.getText().toString().charAt(i)=='.'){
                                    continue;
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Hourly Rate needs to be a float", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                            }
                        }

                    }

                    database.getReference("Services/"+service).removeValue();
                }


                serviceName = ServiceNAME.getText().toString();

                database.getReference("Services/"+serviceName+"/serviceName").setValue(serviceName);


                if(edit.equals("edit")){
                    // list that goes through ever employee
                    //
                    System.out.println("no way");
                    ArrayList<Users> branchAccounts = new ArrayList<>();

                    DatabaseReference data = FirebaseDatabase.getInstance().getReference("users");


                    data.addValueEventListener(new ValueEventListener() {
                        @Override

                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            branchAccounts.clear();

                            for(DataSnapshot dataS:snapshot.getChildren()){
                                System.out.println("whaat");
                                Users user = dataS.getValue(Users.class);
                                if(user.getRole().equals("Employee")){
                                    branchAccounts.add(user);
                                }

                            }
                            //UserList usersAdapter = new UserList(ListBranchAccounts.this, branchAccounts);

                            System.out.println("something");
                            for(int i=0;i<branchAccounts.size();i++){
                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Branch/"+branchAccounts.get(i).getUsername()+"/Services");

                                int finalI = i;
                                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        System.out.println("nothing");
                                        for(DataSnapshot snap: snapshot.getChildren()){
                                            Services service = snap.getValue(Services.class);
                                            if(service.getServiceName().equals(serviceName)){
                                                if (firstLastSwitch.isChecked()) { firstLast[0] = true; }
                                                else { firstLast[0] = false; }
                                                DatabaseReference mfirstLast =  database.getReference("Branch/"+branchAccounts.get(finalI).getUsername()+"/Services/"+serviceName+"/firstLastV");
                                                mfirstLast.setValue(firstLast[0]);

                                                if (dateOfBirthSwitch.isChecked()) { dateOfBirth[0] = true; }
                                                else { dateOfBirth[0] = false; }
                                                DatabaseReference mdateOfBirth =  database.getReference("Branch/"+branchAccounts.get(finalI).getUsername()+"/Services/"+serviceName+"/dateOfBirthV");
                                                mdateOfBirth.setValue(dateOfBirth[0]);

                                                if (addressSwitch.isChecked()) { address[0] = true; }
                                                else { address[0] = false; }
                                                DatabaseReference maddress = database.getReference("Branch/"+branchAccounts.get(finalI).getUsername()+"/Services/"+serviceName+"/addressV");
                                                maddress.setValue(address[0]);

                                                if (emailSwitch.isChecked()) { email[0] = true; }
                                                else { email[0] = false; }
                                                DatabaseReference memail = database.getReference("Branch/"+branchAccounts.get(finalI).getUsername()+"/Services/"+serviceName+"/emailV");
                                                memail.setValue(email[0]);

                                                if (licenseTypeSwitch.isChecked()) { licenseType[0] = true; }
                                                else { licenseType[0] = false; }
                                                DatabaseReference mlicenseType = database.getReference("Branch/"+branchAccounts.get(finalI).getUsername()+"/Services/"+serviceName+"/licenseTypeV");
                                                mlicenseType.setValue(licenseType[0]);

                                                if (carTypeSwitch.isChecked()) { carType[0] = true; }
                                                else { carType[0] = false; }
                                                DatabaseReference mcarType =database.getReference("Branch/"+branchAccounts.get(finalI).getUsername()+"/Services/"+serviceName+"/carTypeV");
                                                mcarType.setValue(carType[0]);

                                                if (pickupDateSwitch.isChecked()) { pickupDate[0] = true; }
                                                else { pickupDate[0] = false; }
                                                DatabaseReference mpickupDate = database.getReference("Branch/"+branchAccounts.get(finalI).getUsername()+"/Services/"+serviceName+"/pickUpDateV");
                                                mpickupDate.setValue(pickupDate[0]);

                                                if (returnDateSwitch.isChecked()) { returnDate[0] = true; }
                                                else { returnDate[0] = false; }
                                                DatabaseReference mreturnDate = database.getReference("Branch/"+branchAccounts.get(finalI).getUsername()+"/Services/"+serviceName+"/returnDateV");
                                                mreturnDate.setValue(returnDate[0]);

                                                if(maxKilometersSwitch.isChecked()) { maxKilometers[0] = true;}
                                                else { maxKilometers[0] = false; }
                                                DatabaseReference mmaxKilometers = database.getReference("Branch/"+branchAccounts.get(finalI).getUsername()+"/Services/"+serviceName+"/maxKilometersV");
                                                mmaxKilometers.setValue(maxKilometers[0]);

                                                if(truckAreaSwitch.isChecked()) { truckArea[0] = true; }
                                                else { truckArea[0] = false; }
                                                DatabaseReference mtruckArea = database.getReference("Branch/"+branchAccounts.get(finalI).getUsername()+"/Services/"+serviceName+"/truckAreaV");
                                                mtruckArea.setValue(truckArea[0]);

                                                if(movingStartSwitch.isChecked()) { movingStart[0] = true; }
                                                else { movingStart[0] = false; }
                                                DatabaseReference mmovingStart = database.getReference("Branch/"+branchAccounts.get(finalI).getUsername()+"/Services/"+serviceName+"/movingStartV");
                                                mmovingStart.setValue(movingStart[0]);

                                                if(movingEndSwitch.isChecked()) { movingEnd[0] = true; }
                                                else { movingEnd[0] = false; }
                                                DatabaseReference mmovingEnd = database.getReference("Branch/"+branchAccounts.get(finalI).getUsername()+"/Services/"+serviceName+"/movingEndV");
                                                mmovingEnd.setValue(movingEnd[0]);

                                                if(numberMoversSwitch.isChecked()) { numberMovers[0] = true; }
                                                else { numberMovers[0] = false; }
                                                DatabaseReference mnumberMovers = database.getReference("Branch/"+branchAccounts.get(finalI).getUsername()+"/Services/"+serviceName+"/numberMoversV");
                                                mnumberMovers.setValue(numberMovers[0]);

                                                if(numberBoxesSwitch.isChecked()) { numberBoxes[0] = true; }
                                                else { numberBoxes[0] = false; }
                                                DatabaseReference mnumberBoxes = database.getReference("Branch/"+branchAccounts.get(finalI).getUsername()+"/Services/"+serviceName+"/numberBoxesV");
                                                mnumberBoxes.setValue(numberBoxes[0]);

                                                if(hourlyRateText.getText().toString().length()>5){
                                                    Toast.makeText(getApplicationContext(), "Hourly Rate needs to be less then 5 digits", Toast.LENGTH_SHORT).show();
                                                    return;
                                                }
                                                hourlyRate[0] = Double.parseDouble((hourlyRateText.getText()).toString());
                                                DatabaseReference mhourlyRate = database.getReference("Branch/"+branchAccounts.get(finalI).getUsername()+"/Services/"+serviceName+"/hourlyRate");
                                                mhourlyRate.setValue(hourlyRate[0]);
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




                    Toast.makeText(getApplicationContext(), "Service Edited, Thank you!", Toast.LENGTH_SHORT).show();
                }



                    if (firstLastSwitch.isChecked()) { firstLast[0] = true; }
                    else { firstLast[0] = false; }
                    DatabaseReference mfirstLast =  database.getReference("Services/"+serviceName+"/firstLastV");
                    mfirstLast.setValue(firstLast[0]);

                    if (dateOfBirthSwitch.isChecked()) { dateOfBirth[0] = true; }
                    else { dateOfBirth[0] = false; }
                    DatabaseReference mdateOfBirth =  database.getReference("Services/"+serviceName+"/dateOfBirthV");
                    mdateOfBirth.setValue(dateOfBirth[0]);

                    if (addressSwitch.isChecked()) { address[0] = true; }
                    else { address[0] = false; }
                    DatabaseReference maddress = database.getReference("Services/"+serviceName+"/addressV");
                    maddress.setValue(address[0]);

                    if (emailSwitch.isChecked()) { email[0] = true; }
                    else { email[0] = false; }
                    DatabaseReference memail = database.getReference("Services/"+serviceName+"/emailV");
                    memail.setValue(email[0]);

                    if (licenseTypeSwitch.isChecked()) { licenseType[0] = true; }
                    else { licenseType[0] = false; }
                    DatabaseReference mlicenseType = database.getReference("Services/"+serviceName+"/licenseTypeV");
                    mlicenseType.setValue(licenseType[0]);

                    if (carTypeSwitch.isChecked()) { carType[0] = true; }
                    else { carType[0] = false; }
                    DatabaseReference mcarType =database.getReference("Services/"+serviceName+"/carTypeV");
                    mcarType.setValue(carType[0]);

                    if (pickupDateSwitch.isChecked()) { pickupDate[0] = true; }
                    else { pickupDate[0] = false; }
                    DatabaseReference mpickupDate = database.getReference("Services/"+serviceName+"/pickUpDateV");
                    mpickupDate.setValue(pickupDate[0]);

                    if (returnDateSwitch.isChecked()) { returnDate[0] = true; }
                    else { returnDate[0] = false; }
                    DatabaseReference mreturnDate = database.getReference("Services/"+serviceName+"/returnDateV");
                    mreturnDate.setValue(returnDate[0]);

                    if(maxKilometersSwitch.isChecked()) { maxKilometers[0] = true;}
                    else { maxKilometers[0] = false; }
                    DatabaseReference mmaxKilometers = database.getReference("Services/"+serviceName+"/maxKilometersV");
                    mmaxKilometers.setValue(maxKilometers[0]);

                    if(truckAreaSwitch.isChecked()) { truckArea[0] = true; }
                    else { truckArea[0] = false; }
                    DatabaseReference mtruckArea = database.getReference("Services/"+serviceName+"/truckAreaV");
                    mtruckArea.setValue(truckArea[0]);

                    if(movingStartSwitch.isChecked()) { movingStart[0] = true; }
                    else { movingStart[0] = false; }
                    DatabaseReference mmovingStart = database.getReference("Services/"+serviceName+"/movingStartV");
                    mmovingStart.setValue(movingStart[0]);

                    if(movingEndSwitch.isChecked()) { movingEnd[0] = true; }
                    else { movingEnd[0] = false; }
                    DatabaseReference mmovingEnd = database.getReference("Services/"+serviceName+"/movingEndV");
                    mmovingEnd.setValue(movingEnd[0]);

                    if(numberMoversSwitch.isChecked()) { numberMovers[0] = true; }
                    else { numberMovers[0] = false; }
                    DatabaseReference mnumberMovers = database.getReference("Services/"+serviceName+"/numberMoversV");
                    mnumberMovers.setValue(numberMovers[0]);

                    if(numberBoxesSwitch.isChecked()) { numberBoxes[0] = true; }
                    else { numberBoxes[0] = false; }
                    DatabaseReference mnumberBoxes = database.getReference("Services/"+serviceName+"/numberBoxesV");
                    mnumberBoxes.setValue(numberBoxes[0]);

                    if(hourlyRateText.getText().toString().length()>5){
                        Toast.makeText(getApplicationContext(), "Hourly Rate needs to be less then 5 digits", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    hourlyRate[0] = Double.parseDouble((hourlyRateText.getText()).toString());
                    DatabaseReference mhourlyRate = database.getReference("Services/"+serviceName+"/hourlyRate");
                    mhourlyRate.setValue(hourlyRate[0]);

                    Toast.makeText(getApplicationContext(), "Service Created, Thank you!", Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(getApplicationContext(), AdminFeatures.class);
                startActivity(intent);

            }
    });


    }
}
