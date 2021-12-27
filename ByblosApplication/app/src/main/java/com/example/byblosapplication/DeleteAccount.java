package com.example.byblosapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DeleteAccount extends AppCompatActivity {
    ListView listview;
    EditText username;
    Button searchButton;
    DatabaseReference data ;
    ArrayList<Users> allUsers = new ArrayList<>();
   // ArrayList<String> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);
        username = findViewById(R.id.name);
        searchButton = findViewById(R.id.Search);
        listview = (ListView) findViewById(R.id.listview);

        data = FirebaseDatabase.getInstance().getReference("users");

        data.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {
                allUsers.clear();

                for(DataSnapshot dataS:snapshot.getChildren()){
                    Users user = dataS.getValue(Users.class);
                    allUsers.add(user);
                }
                UserList usersAdapter = new UserList(DeleteAccount.this, allUsers);

                listview.setAdapter(usersAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"ERROR: Input a name",Toast.LENGTH_SHORT).show();
                    return;
                }

                DatabaseReference adminData = FirebaseDatabase.getInstance().getReference("users/"+username.getText().toString()+"/role");
                System.out.println("admin role");
                System.out.println(adminData.toString());



                Users FoundUser=null;
                System.out.println("size:   ");
                System.out.println(allUsers.size());
                boolean USERSEXISTS = false;
                for(int i=0;i<allUsers.size();i++){
                    Users user = allUsers.get(i);
                    System.out.println(user);
                    if(user.getUsername().equals(username.getText().toString())){
                        FoundUser = user;
                        USERSEXISTS=true;
                    }
                }

                if(USERSEXISTS){
                    data = FirebaseDatabase.getInstance().getReference("users/"+username.getText().toString()+"/username");

                    if(FoundUser.getRole().equals("admin")){
                        Toast.makeText(getApplicationContext(),"Can't delete admin",Toast.LENGTH_SHORT).show();
                    }

                    else if(username.getText().toString().equals(FoundUser.getUsername())){
                        Toast.makeText(getApplicationContext(),"Deleted "+ username.getText(),Toast.LENGTH_SHORT).show();
                        data = FirebaseDatabase.getInstance().getReference("users/"+username.getText());
                        data.removeValue();
                    }
                }

                else{
                    Toast.makeText(getApplicationContext(),username.getText().toString()+" does not exist",Toast.LENGTH_SHORT).show();
                }
                data = FirebaseDatabase.getInstance().getReference("users");
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                data.addValueEventListener(new ValueEventListener() {
                    @Override

                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        allUsers.clear();
                        int i=0;
                        for(DataSnapshot dataS:snapshot.getChildren()){
                            System.out.println(i);
                            i++;
                            Users user = dataS.getValue(Users.class);
                            allUsers.add(user);
                        }
                        UserList usersAdapter = new UserList(DeleteAccount.this, allUsers);

                        listview.setAdapter(usersAdapter);
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });


        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();

                if(allUsers.get(position).getRole().equals("admin")){
                    Toast.makeText(getApplicationContext(),"Can't delete admin",Toast.LENGTH_SHORT).show();
                    return false;
                }
                DatabaseReference users = database.getReference("users/"+allUsers.get(position).getUsername());
                users.setValue(null);
                Toast.makeText(getApplicationContext(),allUsers.get(position).getUsername()+" has been deleted",Toast.LENGTH_SHORT).show();

                data = FirebaseDatabase.getInstance().getReference("users");

                data.addValueEventListener(new ValueEventListener() {
                    @Override

                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        allUsers.clear();
                        int i=0;
                        for(DataSnapshot dataS:snapshot.getChildren()){
                            System.out.println(i);
                            i++;
                            Users user = dataS.getValue(Users.class);
                            allUsers.add(user);
                        }
                        UserList usersAdapter = new UserList(DeleteAccount.this, allUsers);

                        listview.setAdapter(usersAdapter);
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                return true;
            }
        });



        /*
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(DeleteAccount.this,R.layout.user_list,arrayList);

        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(arrayAdapter);

        data = FirebaseDatabase.getInstance().getReference("users");

        data.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Users user = snapshot.getValue(Users.class);
                arrayList.add(user.getUsername());
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        */

    }


}