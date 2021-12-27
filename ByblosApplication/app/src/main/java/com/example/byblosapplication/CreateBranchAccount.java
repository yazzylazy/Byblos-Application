package com.example.byblosapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


// This is going to be the new 1 and only account creation page
// depending on how you got here, the role will be passed thu
// from main page -> click create custy account -> goes to this page and passes thru Role = Customer
// from an Employee account logged in -> create an employee account -> goes to this page and passes thru Role = Employee

public class CreateBranchAccount extends AppCompatActivity {

    // instance variables
    EditText creatAccountUsername,creatAccountEmail,creatAccountPassword,createAccountAddress,createAccountPhoneNumber,createZIP,createCity,createProvince,createCountry;
    Button CreateAccountBtn,BackButton;


    public static final Pattern validPhoneNumberPattern =   Pattern.compile("^\\(?([0-9]{3})\\)?[-.●]?([0-9]{3})[-.●]?([0-9]{4})$",Pattern.CASE_INSENSITIVE);
    public static final Pattern validAddressPattern =   Pattern.compile("\\d+(\\s+[a-z]+)+",Pattern.CASE_INSENSITIVE);
    public static final Pattern validZIPPattern =   Pattern.compile("^[ABCEGHJ-NPRSTVXY]\\d[ABCEGHJ-NPRSTV-Z][ -]?\\d[ABCEGHJ-NPRSTV-Z]\\d$",Pattern.CASE_INSENSITIVE);
    public static final Pattern validCityPattern =   Pattern.compile("[a-z]+",Pattern.CASE_INSENSITIVE);
    public static final Pattern validProvincePattern =   Pattern.compile("[a-z]+",Pattern.CASE_INSENSITIVE);
    public static final Pattern validCountryPattern =   Pattern.compile("[a-z]+$",Pattern.CASE_INSENSITIVE);
    public static final Pattern validEmailPattern = Pattern.compile("^[a-z0-9_.]+@[a-z0-9_]+\\.[a-z0-9_]+(\\.[a-z0-9_]+)*$", Pattern.CASE_INSENSITIVE);


    public CreateBranchAccount(){}
    public CreateBranchAccount(Context context) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_branch_account);

        // Bundled Role
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        creatAccountUsername = findViewById(R.id.newUsername);
        creatAccountEmail = findViewById(R.id.newUserEmail);
        creatAccountPassword = findViewById(R.id.newUserPassword);
        createAccountAddress = findViewById(R.id.newAddress);
        createAccountPhoneNumber = findViewById(R.id.newPhoneNumber);
        createZIP = findViewById(R.id.newZipCode);
        createCity = findViewById(R.id.newCity);
        createProvince = findViewById(R.id.newProvince);
        createCountry = findViewById(R.id.newCountry);

        String passedRole = extras.getString("ROLE");
        String NAME = extras.getString("NAME");

        // go back to log-in page
        BackButton = findViewById(R.id.BackButton);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // back button

                Intent intent;
                if(passedRole.equals("Employee")){
                    intent = new Intent(view.getContext(), EmployeeFeatures.class);
                    Bundle extras = new Bundle();
                    extras.putString("NAME", NAME);
                    intent.putExtras(extras);
                }
                else{
                    intent = new Intent(view.getContext(), LogInPage.class);
                }
                startActivity(intent);


            }
        });

        // Start Firebase Instance
        FirebaseAuth FireBaseAuthentication = FirebaseAuth.getInstance();

        CreateAccountBtn = findViewById(R.id.CreateAccountBtn);
        CreateAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // extract the username,email and password from the app
                String name = creatAccountUsername.getText().toString();
                String email = creatAccountEmail.getText().toString();
                String password = creatAccountPassword.getText().toString();
                String address = createAccountAddress.getText().toString();
                String phone = createAccountPhoneNumber.getText().toString();
                String ZIP = createZIP.getText().toString();
                String city = createCity.getText().toString();
                String province = createProvince.getText().toString();
                String country = createCountry.getText().toString();
                String role = passedRole;

                // checks to see if there were any inputs in the texts
                if (name.isEmpty()) {
                    creatAccountUsername.setError("Username is required");
                    return;
                } else if (email.isEmpty()) {
                    creatAccountEmail.setError("Email is required");
                    return;
                } else if (password.isEmpty()) {
                    creatAccountPassword.setError("Password is required");
                    return;
                }
                else if (address.isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Address is required", Toast.LENGTH_SHORT).show();
                    return;
                } else if (phone.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Phone Number is required", Toast.LENGTH_SHORT).show();
                    return;
                }else if (ZIP.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Postal Code is required", Toast.LENGTH_SHORT).show();
                    return;
                } else if (city.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "City is required", Toast.LENGTH_SHORT).show();
                    return;
                }else if (province.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Province is required", Toast.LENGTH_SHORT).show();
                    return;
                } else if (country.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Country is required", Toast.LENGTH_SHORT).show();
                    return;
                }

                else if(!validCountryPattern.matcher(name.trim()).find()){
                    Toast.makeText(getApplicationContext(), "Username can only be letters", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(!validEmailPattern.matcher(email.trim()).find()) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid email", Toast.LENGTH_SHORT).show();
                }
                else if(!validPhoneNumberPattern.matcher(phone.trim()).find()){
                    Toast.makeText(getApplicationContext(), "Phone format: 000-000-0000", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(!validAddressPattern.matcher(address.trim()).find()){
                    Toast.makeText(getApplicationContext(), "Address format: \"123\" address", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(!validZIPPattern.matcher(ZIP.trim()).find()){
                    Toast.makeText(getApplicationContext(), "ZIP format: A0A 0A0", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(!validCityPattern.matcher(city.trim()).find()){
                    Toast.makeText(getApplicationContext(), "Please enter a valid city", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(!validProvincePattern.matcher(province.trim()).find()){
                    Toast.makeText(getApplicationContext(), "Please enter a valid province", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(!validCountryPattern.matcher(country.trim()).find()){
                    Toast.makeText(getApplicationContext(), "Please enter a valid country", Toast.LENGTH_SHORT).show();
                    return;
                }



                // Firebase create account
                FireBaseAuthentication.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference newUsernameRef = database.getReference("users/" + name + "/username");
                        DatabaseReference newUserRoleRef = database.getReference("users/" + name + "/role");
                        DatabaseReference newEmailRoleRef = database.getReference("users/" + name + "/email");
                        DatabaseReference newPasswordRoleRef = database.getReference("users/" + name + "/password");
                        DatabaseReference newAddressRef = database.getReference("users/" + name + "/address");
                        DatabaseReference newPhoneRef = database.getReference("users/" + name + "/phone");
                        DatabaseReference newZIPRef = database.getReference("users/" + name + "/ZIP");
                        DatabaseReference newCityRef = database.getReference("users/" + name + "/city");
                        DatabaseReference newProvinceRef = database.getReference("users/" + name + "/province");
                        DatabaseReference newCountryRef = database.getReference("users/" + name + "/country");

                        if(role.equals("Employee")){
                            DatabaseReference StartHourRef = database.getReference("Branch/" +name+"/WorkingHours/startHour");
                            DatabaseReference StartMinuteRef = database.getReference("Branch/" +name+"/WorkingHours/startMinute");
                            DatabaseReference EndHourRef = database.getReference("Branch/" +name+"/WorkingHours/endHour");
                            DatabaseReference EndMinuteRef = database.getReference("Branch/" +name+"/WorkingHours/endMinute");
                            StartHourRef.setValue(9);
                            StartMinuteRef.setValue(0);
                            EndHourRef.setValue(5);
                            EndMinuteRef.setValue(0);
                        }

                        newUsernameRef.setValue(name);
                        newUserRoleRef.setValue(role);
                        newEmailRoleRef.setValue(email);
                        newPasswordRoleRef.setValue(password);
                        newAddressRef.setValue(address);
                        newPhoneRef.setValue(phone);
                        newZIPRef.setValue(ZIP);
                        newCityRef.setValue(city);
                        newProvinceRef.setValue(province);
                        newCountryRef.setValue(country);

                        Toast.makeText(getApplicationContext(), "Thank you!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), LogInPage.class);
                        startActivity(intent);
                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }



    public static boolean userEmailValidation(String email) {
        Matcher matcher = validEmailPattern.matcher(email.trim());
        return matcher.find();
    }
    public boolean usernameValidation(String name) {
        Matcher matcher = validCountryPattern.matcher(name.trim());
        return matcher.find();
    }
    public static boolean phoneValidation(String phone) {
        Matcher matcher = validPhoneNumberPattern.matcher(phone.trim());
        return matcher.find();
    }
    public boolean addressValidation(String address) {
        Matcher matcher = validAddressPattern.matcher(address.trim());
        return matcher.find();
    }
    public static boolean zipValidation(String zip) {
        Matcher matcher = validZIPPattern.matcher(zip.trim());
        return matcher.find();
    }
    public boolean cityValidation(String city) {
        Matcher matcher = validCityPattern.matcher(city.trim());
        return matcher.find();
    }
    public boolean provinceValidation(String province) {
        Matcher matcher = validProvincePattern.matcher(province.trim());
        return matcher.find();
    }
    public static boolean countryValidation(String country) {
        Matcher matcher = validCountryPattern.matcher(country.trim());
        return matcher.find();
    }

}