package pl.lborowy.sdaselfie;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    public static final int CAMERA_RESULT_REQUEST_CODE = 18000;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() { // ... anonimowa
            @Override
            public void onClick(View view) {
                // Intent pośredni (daj mi jakąś aplikacje do zrobienia zdjęcia)
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // zabezpieczenie się przed brakiem porządanej aplikacji
                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(cameraIntent, CAMERA_RESULT_REQUEST_CODE);
                    // requestCode ("id" Intentu - może być ich wiele)
                }
                else {
                    Log.e(TAG, "No activity found"); // Log.e - wyświetla się na czerwono
                }
            }
        });
    }

    // wywoła się, kiedy jakas aplikacja przez startActivityResult zwróci jakiś rezultat
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_RESULT_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras(); // Bundle z Intentu "Klucz&Wartość"

            Object returnedObject = bundle.get("data");
            if (returnedObject instanceof Bitmap) {
                Bitmap bitmap = (Bitmap) returnedObject;
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
