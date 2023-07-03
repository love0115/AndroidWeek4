package algonquin.cst2335.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
        private static String TAG = "MainActivate";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.w("MainActivity" ,"Onclick");
                    SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("name","Loveleen");
                    editor.apply();
                    String emailAddress=((EditText)findViewById(R.id.emailEditText)).getText().toString();
                    startActivity(new Intent(MainActivity.this, SecondActivity.class).putExtra("EmailAddress",emailAddress));

                }
            });
            Log.w("MainActivity", "In onCreate() - Loading Widgets");
        }

        @Override
        protected void onStart() {
            super.onStart();
            Log.w("MainActivity", "In onStart() - Loading Widgets");

        }

        @Override
        protected void onResume() {

            super.onResume();
            Log.w("MainActivity", "In onResume() - Loading Widgets");

        }

        @Override
        protected void onPause() {
            super.onPause();
            Log.w("MainActivity", "In onPause() - Loading Widgets");

        }

        @Override
        protected void onStop() {
            super.onStop();
            Log.w("MainActivity", "In onStop() - Loading Widgets");

        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            Log.w("MainActivity", "In onDestroy() - Loading Widgets");

        }
    }