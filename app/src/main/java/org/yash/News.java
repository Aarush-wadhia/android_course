package org.yash;

public class News {
    String title = "", description = "", category = "", pubdate = "";

    public News(String title, String description, String pubdate) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.pubdate = pubdate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getPubdate() {
        return pubdate;
    }
}
