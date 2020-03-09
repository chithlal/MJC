package com.developer.chithlal.mjc.app.IntroActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.developer.chithlal.mjc.R;
import com.developer.chithlal.mjc.app.Login.LoginActivity;


public class IntroActivity extends AppCompatActivity {

    /*Views*/
    CardView mConsumerCard,mEngineerCard;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        mConsumerCard = findViewById(R.id.intro_cv_consumer);
        mEngineerCard = findViewById(R.id.intro_cv_engineer);

        mConsumerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContext != null) {
                    Intent consumerIntent = new Intent(mContext, LoginActivity.class);
                    startActivity(consumerIntent);
                }
            }
        });
        mEngineerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent engineerIntent = new Intent(mContext, LoginActivity.class);
                startActivity(engineerIntent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
