package algonquin.cst2335.androidlabs;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent=getIntent();
        String emailAddress=intent.getStringExtra("EmailAddress");
        Log.e("second activity", emailAddress);
        File file = new File( getFilesDir(), "Picture.png");

        if(file.exists())

        {
            Bitmap theImage = BitmapFactory.decodeFile(file.getPath());
            ((ImageView)findViewById(R.id.imageView)).setImageBitmap(theImage);
        }
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("second activity", "CallNumber");
                Intent call = new Intent(Intent.ACTION_DIAL);
                String phoneNumber=((EditText)findViewById(R.id.editTextPhone)).getText().toString();
                call.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(call);



            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("second activity", "changePICTURE");

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraResult.launch(cameraIntent);
            }

        });

    }
    ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {

                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == Activity.RESULT_OK) {

                        Intent data = result.getData();
                        Bitmap thumbnail=data.getParcelableExtra("data");
                        ((ImageView)findViewById(R.id.imageView)).setImageBitmap(thumbnail);
                        FileOutputStream fOut = null;
                        try { fOut = openFileOutput("Picture.png", Context.MODE_PRIVATE);

                            thumbnail.compress(Bitmap.CompressFormat.PNG, 100, fOut);

                            fOut.flush();

                            fOut.close();

                        }
                        catch (FileNotFoundException e)

                        { e.printStackTrace();

                        }catch (IOException e1){
                            e1.printStackTrace();
                        }

                    }

                }

            }
    );
}