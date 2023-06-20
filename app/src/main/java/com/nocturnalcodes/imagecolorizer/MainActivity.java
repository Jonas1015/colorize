package com.nocturnalcodes.imagecolorizer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    int[] imageResIds = {
      R.drawable.kili1,
      R.drawable.kili2,
      R.drawable.kili3,
    };
    int imageIndex = 0;
    boolean color = true;
    boolean red = true;
    boolean green = true;
    boolean blue = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        loadImage();
    }

    private void loadImage() {
        Glide.with(this).load(imageResIds[imageIndex]).into(imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    private void updateSaturation(){
        ColorMatrix colorMatrix = new ColorMatrix();
        if(color){
            red = green = blue = true;
            colorMatrix.setSaturation(1);
        } else {
            colorMatrix.setSaturation(0);
        }

        ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);
        imageView.setColorFilter(colorFilter);
    }

    private void updateColors(){
        ColorMatrix colorMatrix = new ColorMatrix();
        float[] matrix = {
                1, 0, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 1, 0,
        };
        if(!red) matrix[0] = 0;
        if(!green) matrix[6] = 0;
        if(!blue) matrix[12] = 0;
        colorMatrix.set(matrix);
        ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);
        imageView.setColorFilter(colorFilter);
    }
}