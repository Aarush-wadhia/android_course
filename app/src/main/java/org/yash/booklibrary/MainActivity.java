package org.yash.booklibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button book_library, about;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        book_library = findViewById(R.id.book_library);
        about = findViewById(R.id.about);

        setOnClick();
    }

    public void setOnClick() {
        book_library.setOnClickListener(this);
        about.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.book_library:
                Intent intent = new Intent(MainActivity.this, MyBookLibrary.class);
                startActivity(intent);
                break;

            case R.id.about:
                break;
        }
    }
}
