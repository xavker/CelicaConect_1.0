package com.example.celicaconect_10.Splash;

import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.celicaconect_10.MainActivity;
import com.example.celicaconect_10.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class Splash extends Activity {

    //FirebaseAuth mfirebaseAuth;
    //FirebaseAuth.AuthStateListener mauthListener;
    private AnimatorSet animatorSet;
    public static  final int REQUESTR_CODE=54563;
    /*List<AuthUI.IdpConfig > provider= Arrays.asList(
            //new AuthUI.IdpConfig.FacebookBuilder().build(),
            new AuthUI.IdpConfig.GoogleBuilder().build(),
            new AuthUI.IdpConfig.PhoneBuilder().build()
    );*/
    ImageView loja,nube1,nube2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        loja=findViewById(R.id.loja);
        nube1=findViewById(R.id.nube1);
        nube2=findViewById(R.id.nube2);

/*
        mfirebaseAuth= FirebaseAuth.getInstance();
        mauthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user!=null){
                    Toast.makeText(Splash.this,"Iniciaste Secion",Toast.LENGTH_LONG).show();
                }else {
                    startActivityForResult(AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(provider)
                            .setIsSmartLockEnabled(false)
                            .setTheme(R.style.LoginUIStyle)
                            .setLogo(R.mipmap.ic_launcher_round)
                            .build(),REQUESTR_CODE
                    );
                }
            }
        };*/


        Animation anim= AnimationUtils.loadAnimation(this, R.anim.loja);
        loja.startAnimation(anim);
        anim= AnimationUtils.loadAnimation(this, R.anim.nube1);
        nube1.startAnimation(anim);
        anim= AnimationUtils.loadAnimation(this, R.anim.nube2);
        nube2.startAnimation(anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               Intent intent=new Intent(Splash.this, MainActivity.class);
               startActivity(intent);
            }
        },3500);


    }

    @Override
    protected void onResume() {
        super.onResume();
        //mfirebaseAuth.addAuthStateListener(mauthListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
       // mfirebaseAuth.removeAuthStateListener(mauthListener);
    }

    public void botoncerrarCecion() {
        /*AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(Splash.this,"Secion Cerrada",Toast.LENGTH_LONG).show();
            }
        });*/
    }
}
