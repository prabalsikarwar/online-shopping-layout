package com.example.socialx;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup extends Fragment {
    TextView already  ;
    CardView cardView;
    EditText editText1,editText2,editText3,editText4;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view2 =inflater.inflate(R.layout.activity_signup, container, false);
        already=(TextView)view2.findViewById(R.id.signin);
        cardView=view2.findViewById(R.id.card1signup);
        progressDialog= new ProgressDialog(getContext());
        editText1=view2.findViewById(R.id.Emailid);
        editText2=view2.findViewById(R.id.Passwordsignup);
        editText3=view2.findViewById(R.id.name);
        editText4=view2.findViewById(R.id.phone);
       firebaseAuth=FirebaseAuth.getInstance();
       firebaseUser=firebaseAuth.getCurrentUser();
        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAuth();
            }
        });
        return view2;
    }
    private void checkAuth() {
        String email = editText1.getText().toString();
        String password = editText2.getText().toString();
        String name = editText3.getText().toString();
        String phone = editText4.getText().toString();
        if (!email.matches(emailPattern)) {
            editText1.setError("Enter Connect Email");

        } else if (password.isEmpty() || password.length() < 6) {
            editText2.setError("Enter Proper password");


        } else if(name.isEmpty()){
            editText3.setError("Enter name");
        } else if(phone.isEmpty()){
            editText4.setError("Enter Phone Number");
        }else {

            progressDialog.setMessage("Please Wait While Registartion");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        Intent intent = new Intent(getActivity(), Home.class);
                        startActivity(intent);
                        Toast.makeText(getActivity(), "Registration Succesful", Toast.LENGTH_SHORT).show();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "" + task.getException(), Toast.LENGTH_SHORT).show();
                    }

                }
            });


    }


}


}