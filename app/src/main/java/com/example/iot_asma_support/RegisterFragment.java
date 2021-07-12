package com.example.iot_asma_support;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterFragment extends Fragment {

    FirebaseAuth auth;

    String qrCode;
    EditText etUsername;
    EditText etEmail;
    EditText etPass;
    EditText etConfirmPass;
    TextView uid;

    public RegisterFragment(FirebaseAuth auth){
        this.auth = auth;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        etUsername = view.findViewById(R.id.etUsernameSupport);
        etEmail = view.findViewById(R.id.etEmailSupport);
        etPass = view.findViewById(R.id.etPasswordSupport);
        etConfirmPass = view.findViewById(R.id.etConfirPasswordSupport);
        uid = view.findViewById(R.id.uid);

        Button btnScan = view.findViewById(R.id.btnScan);
        Button btnRegister = view.findViewById(R.id.btnRegisterSupport);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanQrCode();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerAccount(etEmail.getText().toString(), etPass.getText().toString());
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if(requestCode == 1){
            qrCode = intent.getStringExtra("SCAN_RESULT");
            uid.setText(qrCode);
        }
    }

    private void scanQrCode(){
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
        startActivityForResult(intent, 1);
    }

    public void registerAccount(String email, String password){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("Account").child(auth.getCurrentUser().getUid());

                    myRef.child("Support").setValue(createSupport());

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });
    }

    private Support createSupport(){
        return new Support(etUsername.getText().toString(), qrCode);
    }
}