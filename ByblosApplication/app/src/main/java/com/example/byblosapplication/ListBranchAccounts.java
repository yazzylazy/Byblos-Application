package com.example.byblosapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class ListBranchAccounts extends AppCompatActivity {
    ListView listview;
    DatabaseReference data ;
    SearchView searchView;
    //Button createRequest;
    ArrayList<Users> branchAccounts = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_branch_accounts);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String customerName = extras.getString("NAME");

        searchView = findViewById(R.id.searchView);
        listview = (ListView) findViewById(R.id.branchListview);

        data = FirebaseDatabase.getInstance().getReference("users");


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



                UserList usersAdapter = new UserList(ListBranchAccounts.this, branchAccounts);

                listview.setAdapter(usersAdapter);




            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Users> newUsers = new ArrayList<>();

                for(Users user : branchAccounts){

                    if(user.getUsername().toLowerCase(Locale.ROOT).contains(newText.toLowerCase(Locale.ROOT))||user.getAddress().toLowerCase(Locale.ROOT).contains(newText.toLowerCase())){
                        newUsers.add(user);
                        System.out.println("added user:"+user.getUsername());
                    }
                }
                UserList userAdapter = new UserList(ListBranchAccounts.this,newUsers);


                listview.setAdapter(userAdapter);




                return true;
            }
        });



        // sends you to Branch Services
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if(extras.getString("style").equals("customerRequest")){
                    Intent intent = new Intent(view.getContext(), BranchServices.class);
                    Bundle extras = new Bundle();
                    extras.putString("NAME",branchAccounts.get(position).getUsername().toString());
                    extras.putString("type","request");
                    extras.putString("CUSTOMER", customerName);
                    intent.putExtras(extras);
                    startActivity(intent);
                }
                else if(extras.getString("style").equals("rate")){

                    LayoutInflater inflater = getLayoutInflater();
                    View dialogView = inflater.inflate(R.layout.rating_dialogue, null);

                    // create the popup window
                    int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                    int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    boolean focusable = true; // lets taps outside the popup also dismiss it
                    final PopupWindow b = new PopupWindow(dialogView,width, height, focusable);


                    final Button btnSetRating = (Button) dialogView.findViewById(R.id.btnSetRating);
                    final RatingBar Rating = (RatingBar) dialogView.findViewById(R.id.simpleRatingBar);



                    b.showAtLocation(view, Gravity.CENTER,0,0);

                    btnSetRating.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Ratings/"+branchAccounts.get(position).getUsername().toString()+"/"+customerName);
                            ref.setValue(Rating.getRating());
                            Toast.makeText(getApplicationContext(),"You have given "+ branchAccounts.get(position).getUsername().toString()+" a "+Rating.getRating() +" star rating",Toast.LENGTH_SHORT).show();
                            b.dismiss();
                        }
                    });
                    return true;
                }

                return true;
            }
        });

    }
    private void search()
    {
        searchView = findViewById(R.id.searchView);


    }
}