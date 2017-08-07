package pl.lborowy.sdaselfie;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() { // ... anonimowa
            @Override
            public void onClick(View view) {
                // Intent pośredni (daj mi jakąś aplikacje do zrobienia zdjęcia)
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // zabezpieczenie się przed brakiem porządanej aplikacji
                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(cameraIntent, 1);
                }
                else {
                    Log.e(TAG, "No activity found"); // Log.e - wyświetla się na czerwono
                }
            }
        });
    }
}
