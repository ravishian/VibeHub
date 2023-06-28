package com.Soni5.vibehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {


    EditText editTextPassword, editTextEmail, editTextusername, editTextconfirm,editTextname,editTextMobileNumber;
    Button buttonSignup;
    TextView textView;

    FirebaseAuth mAuth;
    FirebaseFirestore firestore;
    HashMap<String,String>  v = new HashMap<>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        editTextname = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSignup = findViewById(R.id.buttonSignup);
        editTextusername = findViewById(R.id.editTextUsername);
        editTextconfirm = findViewById(R.id.editTextConfirmPassword);
        editTextMobileNumber = findViewById(R.id.editTextMobileNumber);
        textView  = findViewById(R.id.login_btn);



        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String cpassword = editTextconfirm.getText().toString().trim();

                if (isValidInput(email, password,cpassword)) {
                    signup(email, password);
                }
            }
        });


    }

     boolean isValidInput(String email, String password,String cpassword) {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.length() < 8) {
            Toast.makeText(this, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!password.equals(cpassword))

        {
            Toast.makeText(this, "Password must be  Same", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    void signup(String email, String password ) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {




                            Date currentDate = new Date();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                            String formattedDate = dateFormat.format(currentDate);

                            v.put("Email",editTextEmail.getText().toString().trim());
                            v.put("Name",editTextusername.getText().toString().trim());
                            v.put("Id",mAuth.getUid().toString());
                            v.put("Date",formattedDate.toString());
                            v.put("Username",editTextusername.getText().toString());
                            v.put("Number",editTextMobileNumber.getText().toString());
                            v.put("Bio","Hello world");
                            v.put("Link","www.google.com");


                           firestore.collection("User").document(mAuth.getUid()).set(v).addOnCompleteListener(new OnCompleteListener<Void>() {
                               @Override
                               public void onComplete(@NonNull Task<Void> task)
                               {
                                   Toast.makeText(SignUpActivity.this, "Signup successful", Toast.LENGTH_SHORT).show();
                               }
                           });






                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // Signup failed, display error message
                            Toast.makeText(SignUpActivity.this, "Signup failed: " + task.getException().getMessage(),
                                 Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
