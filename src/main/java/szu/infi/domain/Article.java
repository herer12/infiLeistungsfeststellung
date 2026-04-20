package szu.infi.domain;

public class Article {

    private String articleID;
    private int price;
    private String description;

    public Article() {}

    public Article(String id, String description, int price) {
        this.articleID = id;
        this.description = description;
        this.price = price;
    }


    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-40s %d", getArticleID(), getDescription(), getPrice());
    }
}
