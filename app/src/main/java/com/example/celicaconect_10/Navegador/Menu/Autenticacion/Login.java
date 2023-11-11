package com.example.celicaconect_10.Navegador.Menu.Autenticacion;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.celicaconect_10.MainActivity;
import com.example.celicaconect_10.R;
import com.example.celicaconect_10.Splash.Splash;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider;


public class Login extends AppCompatActivity {
    private TextInputEditText usuario,clave;
    private Button iraregistrar1,botonlogin1;
    private FirebaseAuth firebaseAuth;

// ...
// Initialize Firebase Auth

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_login);
        firebaseAuth = FirebaseAuth.getInstance();

        usuario=findViewById(R.id.login_usuario);
        clave=findViewById(R.id.login_clave);
        iraregistrar1=findViewById(R.id.botoniraregistro);
        botonlogin1=findViewById(R.id.botonlogin);

        botonlogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrar_datos();
            }
        });
        iraregistrar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(Login.this,Registrar.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void registrar_datos() {
        String user,passwor;
        user=usuario.getText().toString();
        passwor=clave.getText().toString();

        if(TextUtils.isEmpty(user)){
            Toast.makeText(Login.this,"Ingrese un correo valido",Toast.LENGTH_SHORT).show();
            return;
        } if(TextUtils.isEmpty(passwor)){
            Toast.makeText(Login.this,"Ingrese una contraseña valid",Toast.LENGTH_SHORT).show();
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(user,passwor)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this,"Ingreso exitoso",Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(Login.this, MainActivity.class);
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            i.putExtra("key",firebaseAuth.getCurrentUser().getEmail().toString());
                            startActivity(i);
                            finish();
                        }else{
                            Toast.makeText(Login.this,"Ingreso Fallido",Toast.LENGTH_SHORT).show();

                        }

                    }
                });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if(currentUser != null){
            Intent i=new Intent(Login.this, Splash.class);
            i.putExtra("key",firebaseAuth.getCurrentUser().getEmail().toString());
            startActivity(i);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


}
