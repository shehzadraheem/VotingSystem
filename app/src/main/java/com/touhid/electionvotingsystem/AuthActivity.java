package com.touhid.electionvotingsystem;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class AuthActivity extends AppCompatActivity {


    private Button mVotebtns;
    private EditText mVoteauthtxt;
    private EditText mPassword;

    private String currentUser_id;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);


        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mVotebtns = (Button) findViewById(R.id.votebtns);

        firebaseFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        if(HaveAccountActivity.id != null){
            currentUser_id = HaveAccountActivity.id;
        }else {
            currentUser_id = mAuth.getCurrentUser().getUid();
        }


        mVoteauthtxt = findViewById(R.id.voteauthtxt);
        mPassword = findViewById(R.id.password);


        mVotebtns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                firebaseFirestore.collection("Users").document(currentUser_id)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        String password = mPassword.getText().toString().trim();

                        if (task.isSuccessful()) {

                            if (task.getResult().exists()) {

                                if (task.getResult().getString("Password").equals(password)) {

                                    String finish = task.getResult().getString("Finish");

                                    if (finish == null) {

                                        Intent main = new Intent(AuthActivity.this, Candidate_List_Activity.class);
                                        startActivity(main);
                                        finish();

                                    } else if (finish.equals("Voted")) {

                                        Toast.makeText(AuthActivity.this, "You already finish your Vote", Toast.LENGTH_SHORT).show();

                                        showElectionResult();

                                    }

                                } else {

                                    Toast.makeText(AuthActivity.this, "Incorrect password, it does not match with our database", Toast.LENGTH_LONG).show();

                                }


                            } else {

                                Toast.makeText(AuthActivity.this, "Data does not exit", Toast.LENGTH_LONG).show();
                            }

                        } else {

                            String error = task.getException().getMessage();
                            Toast.makeText(AuthActivity.this, "Retriving Error" + error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }



    private void showElectionResult() {

        Intent Result = new Intent(AuthActivity.this, ElectionResultActivity.class);
        startActivity(Result);
        finish();
    }
}
