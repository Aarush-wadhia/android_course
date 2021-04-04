package org.yash.booklibrary;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import org.yash.booklibrary.modal.Books;

public class BookDetail extends AppCompatActivity {

    private ImageView book_image;
    private TextView book_name, book_author, book_description;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detail);
        book_image = findViewById(R.id.book_img);
        book_name = findViewById(R.id.book_name);
        book_author = findViewById(R.id.book_author);
        book_description = findViewById(R.id.book_description);

        Intent intent = getIntent();
        if (null != intent) {
            int book_id = intent.getIntExtra(Constant.BOOK_ID, -1); // 1
            if (book_id != -1) {
                initData(book_id);
            }
        }
    }

    private void initData(int book_id) {
        Books books = Utils.getInstance().getBookById(book_id);
        Glide.with(this).load(books.getImageUrl()).into(book_image);
        book_name.setText(books.getBookName());
        book_author.setText(books.getBookAuthor());
        book_description.setText(books.getShortDescription());
    }
}
