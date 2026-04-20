package szu.infi.domain;

public interface IDataLayer {

    Article[] getAllArticles();

    Article getArticleByID(String articleID);

    boolean saveArticle(Article a);

    void saveAllArticles(Article[] articles);
}
