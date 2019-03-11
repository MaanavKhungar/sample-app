package com.wowza.gocoder.sdk.sampleapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    String SUe="";
    String SUp="";
    String LIe="";
    String LIp="";

    TextInputEditText SUmailET;
    TextInputEditText SUpassET;
    TextInputEditText LImailET;
    TextInputEditText LIpassET;

    CardView SgnUpCrd;
    CardView LogInCrd;

    Button SgnUp;
    Button LogIn;

    TextView SgnUpChg;
    TextView LogInChg;

    int htpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        SUmailET=(TextInputEditText)findViewById(R.id.SUmailET);
        SUpassET=(TextInputEditText)findViewById(R.id.SUpassET);
        LImailET=(TextInputEditText)findViewById(R.id.LImailET);
        LIpassET=(TextInputEditText)findViewById(R.id.LIpassET);
        SgnUpCrd=(CardView)findViewById(R.id.SgnUpCard);
        LogInCrd=(CardView)findViewById(R.id.LogInCard);
        SgnUp=(Button)findViewById(R.id.SgnUpBtn);
        LogIn=(Button)findViewById(R.id.LogInBtn);
        SgnUpChg=(TextView) findViewById(R.id.SgnUpChg);
        LogInChg=(TextView)findViewById(R.id.LogInChg);

        Display display = getWindowManager().getDefaultDisplay();
        htpage=display.getHeight();

        SgnUpCrd.setVisibility(View.GONE);
        ObjectAnimator animation = ObjectAnimator.ofFloat(SgnUpCrd, "translationY", -htpage);
        animation.setDuration(100);
        animation.start();
        SgnUpCrd.setVisibility(View.VISIBLE);


                LogInChg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SgnUpCrd.setVisibility(View.VISIBLE);
                        ObjectAnimator animation = ObjectAnimator.ofFloat(SgnUpCrd, "translationY", 0);
                        animation.setDuration(1000);
                        animation.start();
                        ObjectAnimator trial = ObjectAnimator.ofFloat(LogInCrd, "translationY", htpage);
                        trial.setDuration(1000).addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                LogInCrd.setVisibility(View.GONE);
                                //super.onAnimationEnd(animation);
                            }
                        });
                        trial.start();

                      //  LogInCrd.setVisibility(View.GONE);
                    }
                });
        SgnUpChg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               LogInCrd.setVisibility(View.VISIBLE);
                ObjectAnimator animation = ObjectAnimator.ofFloat(SgnUpCrd, "translationY", -htpage);
                animation.setDuration(1000);
                animation.start();
                ObjectAnimator trial = ObjectAnimator.ofFloat(LogInCrd, "translationY", 0);
                trial.setDuration(1000).addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        SgnUpCrd.setVisibility(View.GONE);
                        //super.onAnimationEnd(animation);
                    }
                });
                trial.start();
             //   SgnUpCrd.setVisibility(View.GONE);
            }
        });





    }
    public  void OnSgnUp()
    {
        mAuth.createUserWithEmailAndPassword(SUe,SUp).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"done",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(getApplicationContext(),MainActivity.class) ;
                    startActivity(i);
                    finish();
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"not",Toast.LENGTH_LONG).show();

                }

            }
        });
    }

}
