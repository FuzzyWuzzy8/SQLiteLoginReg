package pl.pue.air.sqliteloginreg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); //activity_home / activity_login   

        /*

            findViewById(R.id.home1).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startActivity(new Intent(HomeActivity.this, Home1.class)); //add class here
                }
            });   */
    }
}





