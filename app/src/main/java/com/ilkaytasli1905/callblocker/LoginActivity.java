package com.ilkaytasli1905.callblocker;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by İlkay Taşlı on 2.02.2018.
 */

public class LoginActivity extends AppCompatActivity {
    DBHelper db;
    EditText etName;
    EditText etPassword;
    EditText etPasswordAgain;
    private static String systemLanguage = Locale.getDefault().getCountry();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DBHelper(this,systemLanguage);
        db.getWritableDatabase();
        if(db.isUserCreated()){
            setContentView(R.layout.login_main);
            if(systemLanguage.equals("TR")) {
                AutoResizeTextView t = (AutoResizeTextView) findViewById(R.id.autoText);
                t.setText("Merhaba İlkay Taşlı. Hoşgeldiniz. Uygulamaya giriş yapabilmek için belirlemiş olduğunuz parolanızı aşağıdaki kutuya girip devam et butonuna tıklayınız.");
            }

            else{

            }
        }
        else{
            setContentView(R.layout.register_main);
            if(systemLanguage.equals("TR")) {
                AutoResizeTextView t = findViewById(R.id.autoText);
                t.setText("Merhaba. Size hitap edebilmemiz için isminizi , ayarlarınızın güvenliği ve değiştirilememesi için ise parolanızı belirtilen kutucuklara girerek kaydetme işlemini gerçekleştirin.");
                etName = findViewById(R.id.etName);
                etName.setHint("İsminizi Girin");
                etPassword = findViewById(R.id.etPassword);
                etPassword.setHint("Parolanızı Girin");
                etPasswordAgain = findViewById(R.id.etPasswordAgain);
                etPasswordAgain.setHint("Parolanızı Tekrar Girin");
                Button b = findViewById(R.id.button);
                b.setText("Kaydet");
            }
            else{
                AutoResizeTextView t = findViewById(R.id.autoText);
                t.setText("Hello. Enter your name to talk you and enter your password for your settings safety and someone can not change your settings.");
                etName = findViewById(R.id.etName);
                etName.setHint("Enter Name");
                etPassword = findViewById(R.id.etPassword);
                etPassword.setHint("Enter Password");
                etPasswordAgain = findViewById(R.id.etPasswordAgain);
                etPasswordAgain.setHint("Enter Password Again");
                Button b = findViewById(R.id.button);
                b.setText("Save");
            }
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void saveClicked(View v){
        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);
        etPasswordAgain = findViewById(R.id.etPasswordAgain);
        if(etName.getText().toString().equals("") ||
           etPassword.getText().toString().equals("") ||
           etPasswordAgain.getText().toString().equals("")){
            if(systemLanguage.equals("TR")) {
                Toast.makeText(getApplicationContext(),"Lütfen tüm alanları doldurun.",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(),"Please fill in all fields.",Toast.LENGTH_LONG).show();
            }
        }
        else if (etPassword.getText().length()<6 || etPasswordAgain.getText().length()<6){
            if(systemLanguage.equals("TR")) {
                Toast.makeText(getApplicationContext(),"Şifre en az 6 haneli olmalıdır.",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(),"Password must be at least 6 digits.",Toast.LENGTH_LONG).show();
            }
        }
        else if (!etPassword.getText().toString().equals(etPasswordAgain.getText().toString())){
            if(systemLanguage.equals("TR")) {
                Toast.makeText(getApplicationContext(),"Şifreler aynı olmalıdır.",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(),"Passwords must be the same.",Toast.LENGTH_LONG).show();
            }
        }
        else{
            Boolean result = db.insertUserInfo(etName.getText().toString(),etPassword.getText().toString(),getApplicationContext());
            if(result) {
                this.recreate();
            }
        }
    }
    public void loginClicked(View v){

    }
}
