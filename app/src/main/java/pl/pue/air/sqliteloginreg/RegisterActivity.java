package pl.pue.air.sqliteloginreg;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText usernameText;
    EditText passwordText;
    EditText confirmPasswordText;
    Button registerButton;
    TextView loginViewText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //locale language
        loadLocale();
        //
        ActionBar actionBar = getSupportActionBar(); //change actionbar title
        actionBar.setTitle(getResources().getString(R.string.app_name));
        //
        //language
        TextView changeLang = findViewById(R.id.language);
        changeLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLanguageDialog();
            }
        });
        //

        db = new DatabaseHelper(this);
        usernameText = (EditText)findViewById(R.id.edittext_username);
        passwordText = (EditText)findViewById(R.id.edittext_password);
        confirmPasswordText = (EditText)findViewById(R.id.confirm_password_text);
        registerButton = (Button)findViewById(R.id.button_register);
        loginViewText = (TextView)findViewById(R.id.login_view_text);
        loginViewText.setOnClickListener(new View.OnClickListener() {                           //login button
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(LoginIntent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {                          //register button
            @Override
            public void onClick(View view) {
                String user = usernameText.getText().toString().trim();
                String password = passwordText.getText().toString().trim();
                String confirm_password = confirmPasswordText.getText().toString().trim();                    //to confirm password

                if (passwordText.getText().toString() != null && !passwordText.getText().toString().isEmpty()) { //not empty/null
                    if (password.equals(confirm_password)) {                                                      //check both pw
                        long val = db.addUser(user, password);
                        if (val > 0) {
                            Toast.makeText(RegisterActivity.this, getString(R.string.haveregistered), Toast.LENGTH_SHORT).show();    //if correct register
                            //Toast.makeText(RegisterActivity.this, "You have registered", Toast.LENGTH_SHORT).show();    //if correct register
                            Intent moveToLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(moveToLogin);
                        } else {
                            Toast.makeText(RegisterActivity.this, getString(R.string.registrationerror), Toast.LENGTH_SHORT).show();   //if not print error
                            //Toast.makeText(RegisterActivity.this, "Registration Error", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(RegisterActivity.this, getString(R.string.password_not_match), Toast.LENGTH_SHORT).show();
                        //Toast.makeText(RegisterActivity.this, "Password is not matching", Toast.LENGTH_SHORT).show();
                    }
                } else {//
                    Toast.makeText(RegisterActivity.this, getString(R.string.entertext), Toast.LENGTH_SHORT).show(); //
                    //Toast.makeText(RegisterActivity.this, "Enter text", Toast.LENGTH_SHORT).show();

                }//
            }
        });
    }

    //
    private void showChangeLanguageDialog() {
        final String[] listItems = {"English", "Polish", "French", "German", "Spanish", "Turkish"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(RegisterActivity.this);
        mBuilder.setTitle("Choose language");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                switch (i){
                    case 0:
                        // English
                        setLocale("en");
                        recreate();
                        break;
                    case 1:
                        // Polish
                        setLocale("pl");
                        recreate();
                        break;
                    case 2:
                        // French
                        setLocale("fr");
                        recreate();
                        break;
                    case 3:
                        // German
                        setLocale("de");
                        recreate();
                        break;
                    case 4:
                        // Spanish
                        setLocale("es");
                        recreate();
                        break;
                    case 5:
                        // Turkish
                        setLocale("tr");
                        recreate();
                        break;
                    case 6:
                        // Russian
                        setLocale("ru");
                        recreate();
                        break;
                }
                dialogInterface.dismiss();   //dismiss alert dialog when language is selected
            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();                     //show alert dialog
    }
     /*
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                if (i == 0){
                    //English
                    setLocale("en");
                    recreate();
                }
                else if (i == 1){
                    //Polish
                    setLocale("pl");
                    recreate();
                }
                else if (i == 2){
                    //French
                    setLocale("fr");
                    recreate();
                }
                else if (i == 3){
                    //German
                    setLocale("de");
                    recreate();
                }
                else if (i == 4){
                    //Spanish
                    setLocale("es");
                    recreate();
                }
                dialogInterface.dismiss();   //dismiss alert dialog when language is selected

            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();                     //show alert dialog

    }
    */

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        //save data to shared preferences
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    //load language from shared preference
    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocale(language);
    }
    //
}
