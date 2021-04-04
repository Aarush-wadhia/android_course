package org.yash.booklibrary;

import org.yash.booklibrary.modal.Books;

import java.util.ArrayList;

public class Utils {

    public static Utils instance;

    public static ArrayList<Books> booksArrayList;

    private Utils(){
        if (null == booksArrayList) {
            booksArrayList = new ArrayList<>();
            initData();
        }
    }

    private void initData() {
        booksArrayList.add(new Books(1, "RDPD", "Robert Kiyosaki", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b9/Rich_Dad_Poor_Dad.jpg/220px-Rich_Dad_Poor_Dad.jpg", "This book was written by Robert Kiyosaki"));
        booksArrayList.add(new Books(2, "HTWFNIP", "Dale Cargnie", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1442726934i/4865._UY2473_SS2473_.jpg", "This book was written by Dale Carnegie"));
    }

    public static ArrayList<Books> getAllBooksData() {
        return booksArrayList;
    }

    public static Books getBookById(int id) {
        for (Books b : booksArrayList) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    public static Utils getInstance() {
        if (null == instance) {
            instance = new Utils();
        }
        return instance;
    }

}
