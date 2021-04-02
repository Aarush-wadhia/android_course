package org.yash.booklibrary.modal;

public class Books {
    private String bookName, bookAuthor, imageUrl, shortDescription;
    private boolean isExpandable = false;

    public Books(String bookName, String bookAuthor, String imageUrl, String shortDescription) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.imageUrl = imageUrl;
        this.shortDescription = shortDescription;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }
}
