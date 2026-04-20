package szu.infi.mysql;

import szu.infi.domain.Article;
import szu.infi.domain.IDataLayer;

public class DataLayer implements IDataLayer {
    @Override
    public Article[] getAllArticles() {
        return new Article[0];
    }

    @Override
    public Article getArticleByID(String articleID) {
        return null;
    }

    @Override
    public boolean saveArticle(Article a) {
        return false;
    }

    @Override
    public void saveAllArticles(Article[] articles) {

    }
}
