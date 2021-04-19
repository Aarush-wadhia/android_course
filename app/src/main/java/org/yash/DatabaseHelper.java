package org.yash;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "library";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);
        initDatabase(db);
    }

    private void createTable(SQLiteDatabase db) {
        String createTableSQL = "CREATE TABLE library (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, author VARCHAR," +
                "image_url TEXT, description TEXT)";
        db.execSQL(createTableSQL);
    }

    private void initDatabase(SQLiteDatabase db) {
        ContentValues firstBook = new ContentValues();
        firstBook.put("name", "Magic of thinking big");
        firstBook.put("author", "D.J. Schwartz");
        firstBook.put("image_url", "https://images-na.ssl-images-amazon.com/images/I/814n2fhKdvL.jpg");
        firstBook.put("description", "The Magic of Thinking Big gives you useful methods, not empty promises. Dr. Schwartz presents a carefully designed program for getting the most out of your job, your marriage and family life, and your community. He proves that you don't need to be an intellectual or have innate talent to attain great success and satisfaction, but you do need to learn and understand the habit of thinking and behaving in ways that will get you there");

        db.insert("library", null, firstBook);

        ContentValues secondBook = new ContentValues();
        secondBook.put("name", "How to win friends and influence people");
        secondBook.put("author", "Dale carnegie");
        secondBook.put("image_url", "https://images-na.ssl-images-amazon.com/images/I/611OWa8x+WL.jpg");
        secondBook.put("description", "You can go after the job you want...and get it! You can take the job you have...and improve it! You can take any situation you're in...and make it work for you!\n" +
                "\n" +
                "Since its release in 1936, How to Win Friends and Influence People has sold more than 15 million copies. Dale Carnegie's first book is a timeless bestseller, packed with rock-solid advice that has carried thousands of now famous people up the ladder of success in their business and personal lives.\n" +
                "\n" +
                "As relevant as ever before, Dale Carnegie's principles endure, and will help you achieve your maximum potential in the complex and competitive modern age.\n" +
                "\n" +
                "Learn the six ways to make people like you, the twelve ways to win people to your way of thinking, and the nine ways to change people without arousing resentment");

        db.insert("library", null, secondBook);

        ContentValues thirdBook = new ContentValues();
        thirdBook.put("name", "Think and grow rich");
        thirdBook.put("author", "N. Hill");
        thirdBook.put("image_url", "https://www.juggernaut.in/image/filters:format(webp):quality(60)/book_covers/be8fb846a7854bd5943f9fc2dd6373ab.jpg");
        thirdBook.put("description", "Think and Grow Rich. To the greatest extent possible, the text and formatting have been kept exactly the same as in the original release with the exception of some minor formatting changes.");

        db.insert("library", null, thirdBook);

        ContentValues fourthBook = new ContentValues();
        fourthBook.put("name", "A boy who loved");
        fourthBook.put("author", "Durjoy datta");
        fourthBook.put("image_url", "https://images-na.ssl-images-amazon.com/images/I/91Nn56wE96L.jpg");
        fourthBook.put("description", "Raghu likes to show that there is nothing remarkable about his life—loving, middle-class parents, an elder brother he looks up to and plans to study in an IIT. And that’s how he wants things to seem—normal.");

        db.insert("library", null, fourthBook);

        ContentValues fifthBook = new ContentValues();
        fifthBook.put("name", "The lost throne");
        fifthBook.put("author", "J. Patterson");
        fifthBook.put("image_url", "https://images-na.ssl-images-amazon.com/images/I/918cQjBanfL.jpg");
        fifthBook.put("description", "Hewn into the towering cliffs of central Greece, the Metéora monasteries are all but inacessible. The Holy Trinity is the most isolated, its sacred brotherhood the guardians of a long-forgotten secret.");

        db.insert("library", null, fifthBook);

        ContentValues sixthBook = new ContentValues();
        sixthBook.put("name", "Rich dad and poor dad");
        sixthBook.put("author", "Robert kiyosaki");
        sixthBook.put("image_url", "https://upload.wikimedia.org/wikipedia/en/thumb/b/b9/Rich_Dad_Poor_Dad.jpg/220px-Rich_Dad_Poor_Dad.jpg");
        sixthBook.put("description", "Rich Dad Poor Dad is Robert's story of growing up with two dads — his real father and the father of his best friend, his \"rich dad\" — and the ways in which both men shaped his thoughts about money and investing. The book explodes the myth that you need to earn a high income to be rich and explains the difference between working for money and having your money work for you");

        db.insert("library", null, sixthBook);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
