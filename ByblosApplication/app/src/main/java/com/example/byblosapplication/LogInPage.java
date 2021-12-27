package com.example.byblosapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class LogInPage extends AppCompatActivity {
    Button loginBtn;
    EditText loginUsername , loginPassword ;
    public static final Pattern validCountryPattern =   Pattern.compile("[a-z]+",Pattern.CASE_INSENSITIVE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);

        // create account textView that functions as a button
        TextView gotoCreateAccount = (TextView) findViewById(R.id.gotoCreateAccount);
        gotoCreateAccount.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             // changes the activity to the customer create account page
            Intent intent = new Intent(view.getContext(), CreateBranchAccount.class);
            Bundle extras = new Bundle();
            extras.putString("ROLE", "Customer");
            intent.putExtras(extras);
            startActivity(intent);
         }
        });

        // login button
        loginBtn = findViewById(R.id.btnSignIn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // stores the username and password as Strings
                loginUsername = findViewById(R.id.username);
                loginPassword = findViewById(R.id.Password);
                String username = loginUsername.getText().toString();
                String Password = loginPassword.getText().toString();

                // Checks for an empty username and password
                if(username.isEmpty()){
                    loginUsername.setError("Username is required");
                    return;
                }
                else if(Password.isEmpty()) {
                    loginPassword.setError("Email is required");
                    return;
                }
                else if(username.contains("@")||username.contains(".")||username.contains("#")||username.contains("$")||username.contains("[")||username.contains("]")){
                    Toast.makeText(getApplicationContext(), "wrong username or password", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(!validCountryPattern.matcher(username.trim()).find()){
                    Toast.makeText(getApplicationContext(), "wrong username or password", Toast.LENGTH_SHORT).show();
                    return;
                }
                // calls the database for info about the username
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference users = database.getReference("users/"+username);
                //final ArrayList<Users> list = new ArrayList<>();

                ValueEventListener usersListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                        boolean WrongPassword = true;
                        for(DataSnapshot snapsh : datasnapshot.getChildren()){

                            Users user = datasnapshot.getValue(Users.class);
                            if(validAccount(username, Password, user)) {
                            //if(Password.equals(user.getPassword()) && username.equals(user.getUsername())){
                                Intent intent = new Intent(view.getContext(), LogInSuccessful.class);
                                Bundle extras = new Bundle();
                                extras.putString("USERNAME", username);
                                extras.putString("ROLE", user.getRole());
                                intent.putExtras(extras);
                                WrongPassword = false;
                                startActivity(intent);
                                finish();
                            }
                        }

                        if(WrongPassword){
                            Toast.makeText(getApplicationContext(),"Wrong username or password",Toast.LENGTH_SHORT).show();
                        }




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                };
                users.addValueEventListener(usersListener);

            }
        });


    }

    public static boolean validAccount(String username, String password, Users user) {
        if(username.equals(user.getUsername()) && password.equals(user.getPassword())) return true;
        else return false;
    }

}
