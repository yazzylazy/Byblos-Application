package com.example.byblosapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class CreateForm extends AppCompatActivity {
    TextView  firstLast, dateOfBirth, address, email, licenseType, carType,returnDate, pickupDate, maxKilometers,truckArea,movingStart,movingEnd,numberMovers,numberBoxes;
    EditText firstLastTxt, dateOfBirthTxt, addressTxt, emailTxt, licenseTypeTxt, carTypeTxt,returnDateTxt, pickupDateTxt, maxKilometersTxt,truckAreaTxt,movingStartTxt,movingEndTxt,numberMoversTxt,numberBoxesTxt;
    Button btnSubmitForm;

    // regex patterns for form validation

    public static final Pattern validAddressPattern =   Pattern.compile("\\d+(\\s+[a-z]+)+",Pattern.CASE_INSENSITIVE);
    public static final Pattern validEmailPattern = Pattern.compile("^[a-z0-9_.]+@[a-z0-9_]+\\.[a-z0-9_]+(\\.[a-z0-9_]+)*$", Pattern.CASE_INSENSITIVE);
    public static final Pattern validNamePattern = Pattern.compile("^[a-z ,.'-]+$", Pattern.CASE_INSENSITIVE);
    public static final Pattern validLicenseType = Pattern.compile("\"[G]$|[G]+[1,2]", Pattern.CASE_INSENSITIVE);
    public static final Pattern validDatePattern = Pattern.compile("^\\d{4}[-/\\s]?((((0[13578])|(1[02]))[-/\\s]?(([0-2][0-9])|(3[01])))|(((0[469])|(11))[-/\\s]?(([0-2][0-9])|(30)))|(02[-/\\s]?[0-2][0-9]))$", Pattern.CASE_INSENSITIVE);
    public static final Pattern validNumberPattern = Pattern.compile("^[1-9]\\d*$", Pattern.CASE_INSENSITIVE);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_form);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String customerName = extras.getString("CUSTOMER");

        FirebaseDatabase database = FirebaseDatabase.getInstance();



        firstLast = findViewById(R.id.FirstLastTextView);
        firstLastTxt = findViewById(R.id.txtFirstLast);
        dateOfBirth = findViewById(R.id.TextViewDateOfBirth);
        dateOfBirthTxt = findViewById(R.id.txtDateOfBirth);
        address = findViewById(R.id.AddressTextView);
        addressTxt = findViewById(R.id.txtAddress);
        email = findViewById(R.id.TextViewEmail);
        emailTxt = findViewById(R.id.txtEmail);
        licenseType = findViewById(R.id.textViewLicenseType);
        licenseTypeTxt = findViewById(R.id.txtLicenseType);
        carType = findViewById(R.id.TextVCarType);
        carTypeTxt = findViewById(R.id.txtCarType);
        returnDate = findViewById(R.id.textViewReturnDate);
        returnDateTxt = findViewById(R.id.txtReturnDate);
        pickupDate = findViewById(R.id.textViewPickUpDate);
        pickupDateTxt = findViewById(R.id.txtPickUpDate);
        maxKilometers = findViewById(R.id.TextViewMaxKilometers);
        maxKilometersTxt = findViewById(R.id.txtMaxKilometers);
        truckArea = findViewById(R.id.textViewTruckArea);
        truckAreaTxt = findViewById(R.id.txtAreaTruck);
        movingStart = findViewById(R.id.textViewMovingStart);
        movingStartTxt = findViewById(R.id.txtMovingStart);
        movingEnd = findViewById(R.id.textViewViewMovingEnd);
        movingEndTxt = findViewById(R.id.txtMovingEnd);
        numberMovers = findViewById(R.id.TextViewNumberOfMovers);
        numberMoversTxt = findViewById(R.id.txtNumberOfMovers);
        numberBoxes = findViewById(R.id.textViewNumberBoxes);
        numberBoxesTxt = findViewById(R.id.txtNumberOfBoxes);

        // only make the needed fields visible on the form
        if(!extras.getBoolean("FIRST_LAST")) {firstLast.setVisibility(TextView.GONE); firstLastTxt.setVisibility(TextView.GONE);}
        if(!extras.getBoolean("DATEOFBIRTH")) {dateOfBirth.setVisibility(TextView.GONE); dateOfBirthTxt.setVisibility(TextView.GONE); }
        if(!extras.getBoolean("ADDRESS")){address.setVisibility(TextView.GONE); addressTxt.setVisibility(TextView.GONE);}
        if(!extras.getBoolean("EMAILSTRING")) {email.setVisibility(TextView.GONE); emailTxt.setVisibility(TextView.GONE);}
        if(!extras.getBoolean("CARTYPE")) {carType.setVisibility(TextView.GONE); carTypeTxt.setVisibility(TextView.GONE);}
        if(!extras.getBoolean("LICENSETYPE")) {licenseType.setVisibility(TextView.GONE); licenseTypeTxt.setVisibility(TextView.GONE);}
        if(!extras.getBoolean("PICKUPDATE")) {pickupDate.setVisibility(TextView.GONE); pickupDateTxt.setVisibility(TextView.GONE);}
        if(!extras.getBoolean("RETURNDATE")) {returnDate.setVisibility(TextView.GONE); returnDateTxt.setVisibility(TextView.GONE);}
        if(!extras.getBoolean("MAXKILOMETERS")) {maxKilometers.setVisibility(TextView.GONE); maxKilometersTxt.setVisibility(TextView.GONE);}
        if(!extras.getBoolean("TRUCKAREA")) {truckArea.setVisibility(TextView.GONE); truckAreaTxt.setVisibility(TextView.GONE);}
        if(!extras.getBoolean("MOVINGSTART")) {movingStart.setVisibility(TextView.GONE); movingStartTxt.setVisibility(TextView.GONE);}
        if(!extras.getBoolean("MOVINGEND")) {movingEnd.setVisibility(TextView.GONE); movingEndTxt.setVisibility(TextView.GONE);}
        if(!extras.getBoolean("NUMBERMOVERS")) {numberMovers.setVisibility(TextView.GONE); numberMoversTxt.setVisibility(TextView.GONE);}
        if(!extras.getBoolean("NUMBERSBOXES")) {numberBoxes.setVisibility(TextView.GONE); numberBoxesTxt.setVisibility(TextView.GONE);}


        btnSubmitForm = findViewById(R.id.btnSubmitForm);
        btnSubmitForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstLast = firstLastTxt.getText().toString();
                String dateOfBirth = dateOfBirthTxt.getText().toString();
                String address = addressTxt.getText().toString();
                String email = emailTxt.getText().toString();
                String carType = carTypeTxt.getText().toString();
                String licenseType = licenseTypeTxt.getText().toString();
                String pickupDate = pickupDateTxt.getText().toString();
                String returnDate = returnDateTxt.getText().toString();
                String maxKilometers = maxKilometersTxt.getText().toString();
                String truckArea = truckAreaTxt.getText().toString();
                String movingStart = movingStartTxt.getText().toString();
                String movingEnd = movingEndTxt.getText().toString();
                String numberMovers = numberMoversTxt.getText().toString();
                String numberBoxes = numberBoxesTxt.getText().toString();

                if(firstLast.isEmpty() && extras.getBoolean("FIRST_LAST")) { firstLastTxt.setError("First and last name is required"); return;}
                else if(dateOfBirth.isEmpty() && extras.getBoolean("DATEOFBIRTH")) {dateOfBirthTxt.setError("Date of Birth is required"); return;}
                else if(address.isEmpty() && extras.getBoolean("ADDRESS")) {addressTxt.setError("Address is required"); return;}
                else if(email.isEmpty() && extras.getBoolean("EMAILSTRING")) {emailTxt.setError("Email is required"); return;}
                else if(carType.isEmpty() && extras.getBoolean("CARTYPE")) {carTypeTxt.setError("Car type is required"); return;}
                else if(licenseType.isEmpty() && extras.getBoolean("LICENSETYPE")) {licenseTypeTxt.setError("License type is required"); return;}
                else if(pickupDate.isEmpty() && extras.getBoolean("PICKUPDATE")) {pickupDateTxt.setError("Pick up date is required"); return;}
                else if(returnDate.isEmpty() && extras.getBoolean("RETURNDATE")) {returnDateTxt.setError("Return date is required"); return;}
                else if(maxKilometers.isEmpty() && extras.getBoolean("MAXKILOMETERS")) {maxKilometersTxt.setError("Max kilometers is required"); return;}
                else if(truckArea.isEmpty() && extras.getBoolean("TRUCKAREA")) {truckAreaTxt.setError("Truck area is required"); return;}
                else if(movingStart.isEmpty() && extras.getBoolean("MOVINGSTART")) {movingStartTxt.setError("Moving start is required"); return;}
                else if(movingEnd.isEmpty() && extras.getBoolean("MOVINGEND")) {movingEndTxt.setError("Moving end is required"); return;}
                else if(numberMovers.isEmpty() && extras.getBoolean("NUMBERMOVERS")) {numberMoversTxt.setError("Number of movers is required"); return;}
                else if(numberBoxes.isEmpty() && extras.getBoolean("NUMBERSBOXES")) {numberBoxesTxt.setError("Number of boxes is required"); return;}


                else if(!validAddressPattern.matcher(address.trim()).find() && extras.getBoolean("ADDRESS")) { Toast.makeText(getApplicationContext(), "Please Enter A valid Address", Toast.LENGTH_SHORT).show(); return;}
                else if(!validEmailPattern.matcher(email.trim()).find() && extras.getBoolean("EMAILSTRING")) { Toast.makeText(getApplicationContext(), "Please enter a valid email", Toast.LENGTH_SHORT).show(); return;}
                else if(!validNamePattern.matcher(firstLast.trim()).find() && extras.getBoolean("FIRST_LAST")) { Toast.makeText(getApplicationContext(), "Please enter a real, full name", Toast.LENGTH_SHORT).show(); return;}
                else if(!validNamePattern.matcher(carType.trim()).find() && extras.getBoolean("CARTYPE")) {Toast.makeText(getApplicationContext(), "Please enter a car type, excluding the year", Toast.LENGTH_SHORT).show(); return;}
                else if(!validLicenseType.matcher(licenseType.trim()).find() && extras.getBoolean("LICENSETYPE") ) {Toast.makeText(getApplicationContext(), "Please enter a valid License type (G, G1, G2)", Toast.LENGTH_SHORT).show(); return;}
                else if(!validAddressPattern.matcher(movingStart.trim()).find() && extras.getBoolean("MOVINGSTART")) {Toast.makeText(getApplicationContext(),"Please enter a valid address for Start Location", Toast.LENGTH_SHORT).show(); return;}
                else if(!validAddressPattern.matcher(movingEnd.trim()).find()&& extras.getBoolean("MOVINGEND")) {Toast.makeText(getApplicationContext(), "Please enter a valid address for End Location", Toast.LENGTH_SHORT).show(); return;}
                else if(!validNamePattern.matcher(truckArea.trim()).find() && extras.getBoolean("TRUCKAREA")) {Toast.makeText(getApplicationContext(), "Please enter a valid name of area of operation", Toast.LENGTH_SHORT).show(); return;}
                else if(!validDatePattern.matcher(dateOfBirth.trim()).find() && extras.getBoolean("DATEOFBIRTH")) {Toast.makeText(getApplicationContext(), "Please enter date in format: YYYY-MM-DD sss", Toast.LENGTH_SHORT).show(); return;}
                else if(!validDatePattern.matcher(pickupDate.trim()).find() && extras.getBoolean("PICKUPDATE")) {Toast.makeText(getApplicationContext(), "Please enter date in format: YYYY-MM-DD aaa", Toast.LENGTH_SHORT).show(); return;}
                else if(!validDatePattern.matcher(returnDate.trim()).find() && extras.getBoolean("RETURNDATE")) {Toast.makeText(getApplicationContext(), "Please enter date in format: YYYY-MM-DD ll", Toast.LENGTH_SHORT).show(); return;}
                else if(!validNumberPattern.matcher(maxKilometers.trim()).find() && extras.getBoolean("MAXKILOMETERS")) {Toast.makeText(getApplicationContext(), "Please enter a valid number of kilometers", Toast.LENGTH_SHORT).show(); return;}
                else if(!validNumberPattern.matcher(numberBoxes.trim()).find()&& extras.getBoolean("NUMBERSBOXES")) {Toast.makeText(getApplicationContext(), "Please enter a valid number of boxes", Toast.LENGTH_SHORT).show(); return;}
                else if(!validNumberPattern.matcher(numberMovers.trim()).find() && extras.getBoolean("NUMBERMOVERS")) {Toast.makeText(getApplicationContext(), "Please enter a valid number of movers", Toast.LENGTH_SHORT).show(); return;}

                String name = extras.getString("NAME");
                DatabaseReference ref = database.getReference("Branch/"+name+"/ServiceRequests/"+ extras.getString("SERVICE")+"/accepted");
                DatabaseReference ref1 = database.getReference("Branch/"+name+"/ServiceRequests/"+extras.getString("SERVICE")+"/username");
                DatabaseReference ref2 = database.getReference("Branch/"+name+"/ServiceRequests/"+extras.getString("SERVICE")+"/firstLast");
                DatabaseReference ref3 = database.getReference("Branch/"+name+"/ServiceRequests/"+extras.getString("SERVICE")+"/address");
                DatabaseReference ref4 = database.getReference("Branch/"+name+"/ServiceRequests/"+extras.getString("SERVICE")+"/dateOfBirth");
                DatabaseReference ref5 = database.getReference("Branch/"+name+"/ServiceRequests/"+extras.getString("SERVICE")+"/licenseType");
                DatabaseReference ref6 = database.getReference("Branch/"+name+"/ServiceRequests/"+extras.getString("SERVICE")+"/carType");
                DatabaseReference ref7 = database.getReference("Branch/"+name+"/ServiceRequests/"+extras.getString("SERVICE")+"/returnDate");
                DatabaseReference ref8 = database.getReference("Branch/"+name+"/ServiceRequests/"+extras.getString("SERVICE")+"/pickUpDate");
                DatabaseReference ref9 = database.getReference("Branch/"+name+"/ServiceRequests/"+extras.getString("SERVICE")+"/maxKilometers");
                DatabaseReference ref10 = database.getReference("Branch/"+name+"/ServiceRequests/"+extras.getString("SERVICE")+"/truckArea");
                DatabaseReference ref11 = database.getReference("Branch/"+name+"/ServiceRequests/"+extras.getString("SERVICE")+"/movingStart");
                DatabaseReference ref12 = database.getReference("Branch/"+name+"/ServiceRequests/"+extras.getString("SERVICE")+"/movingEnd");
                DatabaseReference ref13 = database.getReference("Branch/"+name+"/ServiceRequests/"+extras.getString("SERVICE")+"/numberMovers");
                DatabaseReference ref14 = database.getReference("Branch/"+name+"/ServiceRequests/"+extras.getString("SERVICE")+"/numbersBoxes");
                DatabaseReference ref15 = database.getReference("Branch/"+name+"/ServiceRequests/"+extras.getString("SERVICE")+"/emailString");
                DatabaseReference ref16 = database.getReference("Branch/"+name+"/ServiceRequests/"+extras.getString("SERVICE")+"/serviceName");

                DatabaseReference refF = database.getReference("Forms/"+extras.getString("CUSTOMER")+"/"+extras.getString("SERVICE")+"/accepted");
                DatabaseReference ref1F = database.getReference("Forms/"+extras.getString("CUSTOMER")+"/"+extras.getString("SERVICE")+"/username");
                DatabaseReference ref2F = database.getReference("Forms/"+extras.getString("CUSTOMER")+"/"+extras.getString("SERVICE")+"/firstLast");
                DatabaseReference ref3F = database.getReference("Forms/"+extras.getString("CUSTOMER")+"/"+extras.getString("SERVICE")+"/address");
                DatabaseReference ref4F = database.getReference("Forms/"+extras.getString("CUSTOMER")+"/"+extras.getString("SERVICE")+"/dateOfBirth");
                DatabaseReference ref5F = database.getReference("Forms/"+extras.getString("CUSTOMER")+"/"+extras.getString("SERVICE")+"/licenseType");
                DatabaseReference ref6F = database.getReference("Forms/"+extras.getString("CUSTOMER")+"/"+extras.getString("SERVICE")+"/carType");
                DatabaseReference ref7F = database.getReference("Forms/"+extras.getString("CUSTOMER")+"/"+extras.getString("SERVICE")+"/returnDate");
                DatabaseReference ref8F = database.getReference("Forms/"+extras.getString("CUSTOMER")+"/"+extras.getString("SERVICE")+"/pickUpDate");
                DatabaseReference ref9F = database.getReference("Forms/"+extras.getString("CUSTOMER")+"/"+extras.getString("SERVICE")+"/maxKilometers");
                DatabaseReference ref10F = database.getReference("Forms/"+extras.getString("CUSTOMER")+"/"+extras.getString("SERVICE")+"/truckArea");
                DatabaseReference ref11F = database.getReference("Forms/"+extras.getString("CUSTOMER")+"/"+extras.getString("SERVICE")+"/movingStart");
                DatabaseReference ref12F = database.getReference("Forms/"+extras.getString("CUSTOMER")+"/"+extras.getString("SERVICE")+"/movingEnd");
                DatabaseReference ref13F = database.getReference("Forms/"+extras.getString("CUSTOMER")+"/"+extras.getString("SERVICE")+"/numberMovers");
                DatabaseReference ref14F = database.getReference("Forms/"+extras.getString("CUSTOMER")+"/"+extras.getString("SERVICE")+"/numbersBoxes");
                DatabaseReference ref15F = database.getReference("Forms/"+extras.getString("CUSTOMER")+"/"+extras.getString("SERVICE")+"/emailString");
                DatabaseReference ref16F = database.getReference("Forms/"+extras.getString("CUSTOMER")+"/"+extras.getString("SERVICE")+"/serviceName");

                ref.setValue(false);
                ref1.setValue(extras.getString("CUSTOMER"));
                ref2.setValue(firstLast);
                ref3.setValue(address);
                ref4.setValue(dateOfBirth);
                ref5.setValue(licenseType);
                ref6.setValue(carType);
                ref7.setValue(returnDate);
                ref8.setValue(pickupDate);
                ref9.setValue(maxKilometers);
                ref10.setValue(truckArea);
                ref11.setValue(movingStart);
                ref12.setValue(movingEnd);
                ref13.setValue(numberMovers);
                ref14.setValue(numberBoxes);
                ref15.setValue(email);
                ref16.setValue(extras.getString("SERVICE"));

                refF.setValue(false);
                ref1F.setValue(extras.getString("CUSTOMER"));
                ref2F.setValue(firstLast);
                ref3F.setValue(address);
                ref4F.setValue(dateOfBirth);
                ref5F.setValue(licenseType);
                ref6F.setValue(carType);
                ref7F.setValue(returnDate);
                ref8F.setValue(pickupDate);
                ref9F.setValue(maxKilometers);
                ref10F.setValue(truckArea);
                ref11F.setValue(movingStart);
                ref12F.setValue(movingEnd);
                ref13F.setValue(numberMovers);
                ref14F.setValue(numberBoxes);
                ref15F.setValue(email);
                ref16F.setValue(extras.getString("SERVICE"));

                Intent intent = new Intent(view.getContext(), CustomerFeatures.class);
                Bundle extras = new Bundle();
                extras.putString("NAME", name);
                intent.putExtras(extras);
                startActivity(intent);


            }



        });
    }
}