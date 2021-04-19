package org.yash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class BookDetails extends AppCompatActivity {

    private ImageView book_image;
    private TextView book_name, book_author, book_description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        book_image = findViewById(R.id.book_image);
        book_name = findViewById(R.id.book_name);
        book_author = findViewById(R.id.book_author);
        book_description = findViewById(R.id.book_description);
    }
}