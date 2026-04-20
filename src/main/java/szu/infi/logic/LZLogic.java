package szu.infi.logic;

import szu.infi.domain.Article;
import szu.infi.domain.IDataLayer;

public class LZLogic {

    private IDataLayer dblayer;

    public LZLogic(IDataLayer dblayer) {
        this.dblayer = dblayer;
    }

    public void initializeArticles() {

        Article[] articles = new Article[2];

        articles[0] = new Article("T1", "Testartikel 1", 100);
        articles[1] = new Article("T2", "Testartikel 2", 200);

        dblayer.saveAllArticles(articles);
    }

    public void showAllArticles() {
        Article[] articles = dblayer.getAllArticles();
        int i = 1;
        for (Article a : articles) {
            System.out.print(i + ": ");
            System.out.println(a);
        }
    }

    public Article[] getAllArticles() {
        return dblayer.getAllArticles();
    }
}
