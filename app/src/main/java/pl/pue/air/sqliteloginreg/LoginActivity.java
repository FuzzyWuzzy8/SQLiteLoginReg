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

public class LoginActivity extends AppCompatActivity {

    EditText usernameText;
    EditText passwordText;
    Button loginButton;
    TextView registerViewText;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
        loginButton = (Button)findViewById(R.id.button_login);
        registerViewText = (TextView)findViewById(R.id.register_view_text);
        registerViewText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                                                      // new activity - transfer to register page
                Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        //check login/pw
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = usernameText.getText().toString().trim();
                String password = passwordText.getText().toString().trim();
                Boolean res = db.checkUser(user, password);

                if (passwordText.getText().toString() != null && !passwordText.getText().toString().isEmpty()) { //checking if string is null/empty
                    if (res == true) {
                        Intent HomePage = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(HomePage);
                    } else {
                        Toast.makeText(LoginActivity.this, getString(R.string.loginerror), Toast.LENGTH_SHORT).show();
                        //Toast.makeText(LoginActivity.this, "Login Error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, getString(R.string.entertext), Toast.LENGTH_SHORT).show();
                    //Toast.makeText(LoginActivity.this, "Enter text", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showChangeLanguageDialog() {
        final String[] listItems = {"English", "Polish", "French", "German", "Spanish", "Turkish"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(LoginActivity.this);
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

    // moved to switch
    /*
    private void showChangeLanguageDialog() {
        final String[] listItems = {"English", "Polish", "French", "German", "Spanish"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(LoginActivity.this);
        mBuilder.setTitle("Choose language");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener(){
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
}
