package szu.infi;

import szu.infi.domain.Article;
import szu.infi.domain.IDataLayer;
import szu.infi.domain.IDbInitialize;
import szu.infi.factory.DBInstance;
import szu.infi.logic.LZLogic;

public class Main {

    public static void main(String[] args) {

        System.out.println("Good luck");

        IDataLayer dblayer = DBInstance.getInstance("SQLite");
        LZLogic logic = new LZLogic(dblayer);

        IDbInitialize dbSetup = (IDbInitialize)dblayer;
        dbSetup.createDatabase("articles.db");

        logic.initializeArticles();
        logic.showAllArticles();
        Article[] articles = logic.getAllArticles();

        System.out.println("Json:");

        IDataLayer jsonLayer = DBInstance.getInstance("JSON");
        ((IDbInitialize) jsonLayer).createDatabase("articles.json");
        jsonLayer.saveAllArticles(articles);

        Article test = new Article("Test", "Testartikel neu", 999);
        jsonLayer.saveArticle(test);
    }
}
