package com.project.finalyear.thaispellinggame.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.finalyear.thaispellinggame.R;

import java.util.HashMap;

/**
 * Created by Namwan on 11/16/2017.
 */

public class RegisterActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private EditText regis_username;
    private EditText regis_email;
    private EditText regis_password;
    private Button btn_create_account;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseRef;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

//        mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setTitle("Sign Up");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        regis_username = (EditText) findViewById(R.id.register_name);
        regis_email = (EditText) findViewById(R.id.register_email);
        regis_password = (EditText) findViewById(R.id.register_password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btn_create_account = (Button) findViewById(R.id.create_account_button);

        btn_create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = regis_username.getText().toString();
                String email = regis_email.getText().toString();
                String password = regis_password.getText().toString();

                if ((TextUtils.isEmpty(name)) | (TextUtils.isEmpty(email)) | (TextUtils.isEmpty(password))) {
                    Toast.makeText(RegisterActivity.this,
                            "กรุณากรอกข้อมูลให้ครบ!", Toast.LENGTH_SHORT).show();
                }else{
                    if (password.length() < 6) {
                        Toast.makeText(getApplicationContext(), "รหัสไม่ถูกต้อง ลองใหม่อีกครั้ง!", Toast.LENGTH_SHORT).show();
                        return;

                    }else RegisterAccount(name, email, password);
                }
            }
        });


    }

    private void RegisterAccount(final String name, final String email, String password) {

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {

                            Toast.makeText(RegisterActivity.this,
                                    "เกิดข้อผิดพลาด: " + task.getException() + " กรุณาลองอีกครั้ง!", Toast.LENGTH_SHORT).show();

                        } else {

                            String current_user_id = mAuth.getCurrentUser().getUid();
                            mDatabaseRef = FirebaseDatabase.getInstance().getReference()
                                    .child("Users").child(current_user_id);

                            mDatabaseRef.child("name").setValue(name);
                            mDatabaseRef.child("email").setValue(email);
                            mDatabaseRef.child("online").setValue(true);
                            mDatabaseRef.child("image").setValue("default_profile_pic");
                            mDatabaseRef.child("thumb_image").setValue("default");
                            mDatabaseRef.child("rank").setValue("0");
                            mDatabaseRef.child("level").setValue("0");
                            mDatabaseRef.child("score").setValue("0");
                            mDatabaseRef.child("bestScore").setValue("0")
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()) {

                                                Toast.makeText(RegisterActivity.this,
                                                        "สำเร็จ!", Toast.LENGTH_SHORT).show();

                                                Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
                                                //mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(mainIntent);
                                                finish();
                                            }
                                        }
                                    });
                        }

                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
