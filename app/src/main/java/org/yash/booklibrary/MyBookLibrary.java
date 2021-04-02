package org.yash.booklibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.yash.booklibrary.adapters.BookAdapter;
import org.yash.booklibrary.modal.Books;

import java.util.ArrayList;

public class MyBookLibrary extends AppCompatActivity {

    RecyclerView book_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_book_library);
        book_list = findViewById(R.id.book_list);
        ArrayList<Books> bookList = new ArrayList<>();
        bookList.add(new Books("RDPD", "Robert Kiyosaki", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b9/Rich_Dad_Poor_Dad.jpg/220px-Rich_Dad_Poor_Dad.jpg", "This book was written by Robert Kiyosaki"));
        bookList.add(new Books("HTWFNIP", "Dale Cargnie", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1442726934i/4865._UY2473_SS2473_.jpg", "This book was written by Dale Carnegie"));
        BookAdapter bookAdapter = new BookAdapter(bookList);
        book_list.setLayoutManager(new LinearLayoutManager(this));
        book_list.setAdapter(bookAdapter);
    }
}