package com.developer.chithlal.mjc.app.IntroActivity;

import static com.developer.chithlal.mjc.app.util.Constants.CONSUMER_MODE;
import static com.developer.chithlal.mjc.app.util.Constants.ENGINEER_MODE;
import static com.developer.chithlal.mjc.app.util.Constants.KEY_USER_TYPE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.developer.chithlal.mjc.R;
import com.developer.chithlal.mjc.app.Login.LoginActivity;
import com.developer.chithlal.mjc.app.signup.SignupActivity;


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
                    Intent consumerIntent = new Intent(mContext, SignupActivity.class);
                    consumerIntent.putExtra(KEY_USER_TYPE,CONSUMER_MODE);
                    startActivity(consumerIntent);
                    finish();
                }

            }
        });
        mEngineerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent engineerIntent = new Intent(mContext, SignupActivity.class);
                engineerIntent.putExtra(KEY_USER_TYPE,ENGINEER_MODE);
                startActivity(engineerIntent);
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
