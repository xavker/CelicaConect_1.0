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

public class Registrar extends AppCompatActivity {
    private TextInputEditText usuario,clave;
    private Button registrar,irainiciar;
    private FirebaseAuth firebaseAuth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        firebaseAuth = FirebaseAuth.getInstance();

        usuario=findViewById(R.id.login_usuario);
        clave=findViewById(R.id.login_clave);
        registrar=findViewById(R.id.botonregistrar);
        irainiciar=findViewById(R.id.botonirainiciar);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrar_datos();
            }
        });
        irainiciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(Registrar.this,Login.class);
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
            Toast.makeText(Registrar.this,"Ingrese un correo valido",Toast.LENGTH_SHORT).show();
            return;
        } if(TextUtils.isEmpty(passwor)){
            Toast.makeText(Registrar.this,"Ingrese una contraseña valid",Toast.LENGTH_SHORT).show();
            return;
        }
        firebaseAuth.createUserWithEmailAndPassword(user,passwor)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Registrar.this,"Registro exitoso",Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(Registrar.this, Splash.class);
                            i.putExtra("key",firebaseAuth.getCurrentUser().getEmail().toString());
                            startActivity(i);
                            finish();
                        }else{
                            Toast.makeText(Registrar.this,"Registro Fallido",Toast.LENGTH_SHORT).show();

                        }

                    }
                });
    }




}