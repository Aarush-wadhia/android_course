package org.yash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BookDetails extends AppCompatActivity {

    private ImageView book_image;
    private TextView book_name, book_author, book_description;
    private Thread getBookDetailThread;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        book_image = findViewById(R.id.book_image);
        book_name = findViewById(R.id.book_name);
        book_author = findViewById(R.id.book_author);
        book_description = findViewById(R.id.book_description);
        databaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        if (null != intent) {
            int bookId = intent.getIntExtra(Constants.INTENT_BOOK_ID, -1);
            if (bookId != -1) {
                getBookDetails(bookId);
            }
        }
    }

    private void getBookDetails(int bookId) {
        getBookDetailThread = new Thread(new Runnable() {
            @Override
            public void run() {
               try {
                   SQLiteDatabase db = databaseHelper.getReadableDatabase();
                   Cursor cursor = db.rawQuery("SELECT * FROM library where id=?", new String[] {String.valueOf(bookId)});
                   if (null != cursor) {
                       if (cursor.moveToFirst()) {
                           Books books = new Books();

                           int nameIndex = cursor.getColumnIndex("name");
                           int authorIndex = cursor.getColumnIndex("author");
                           int image_urlIndex = cursor.getColumnIndex("image_url");
                           int descriptionIndex = cursor.getColumnIndex("description");

                           books.setBook_name(cursor.getString(nameIndex));
                           books.setBook_author(cursor.getString(authorIndex));
                           books.setBook_image(cursor.getString(image_urlIndex));
                           books.setBook_description(cursor.getString(descriptionIndex));

                           cursor.close();
                           db.close();
                           if (books != null) {
                               runOnUiThread(new Runnable() {
                                   @Override
                                   public void run() {
                                       book_name.setText(books.getBook_name());
                                       book_author.setText(books.getBook_author());
                                       book_description.setText(books.getBook_description());
                                       Glide.with(BookDetails.this).load(books.getBook_image()).into(book_image);
                                   }
                               });
                           }
                       }
                   }
                   db.close();
               }catch (SQLException e) {
                   e.printStackTrace();
               }
            }
        });
        getBookDetailThread.start();
    }
}