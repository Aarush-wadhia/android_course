package org.yash;

public class Books {
    private int id;
    private String book_name;
    private String book_author;
    private String book_description;
    private String book_image;

    public Books(int id, String book_name, String book_author, String book_description, String book_image) {
        this.id = id;
        this.book_name = book_name;
        this.book_author = book_author;
        this.book_description = book_description;
        this.book_image = book_image;
    }

    public Books() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBook_description() {
        return book_description;
    }

    public void setBook_description(String book_description) {
        this.book_description = book_description;
    }

    public String getBook_image() {
        return book_image;
    }

    public void setBook_image(String book_image) {
        this.book_image = book_image;
    }
}
