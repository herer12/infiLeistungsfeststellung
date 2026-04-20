package szu.infi.sqlite;

import szu.infi.domain.Article;
import szu.infi.domain.IDataLayer;
import szu.infi.domain.IDbInitialize;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataLayer implements IDataLayer, IDbInitialize {

    private String dbFileName = "articles.db";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + this.dbFileName);
    }

    public DataLayer() {
        // -- nothing to do
    }

    public DataLayer(String dbFileName)
    {
        this.dbFileName = dbFileName;
    }

    @Override
    public Article[] getAllArticles() {
        // * gibt alle Artikel zurück
        // TODO:
        return null;
    }

    @Override
    public Article getArticleByID(String articleID) {
        // * gibt den Artikel mit der 'articleID' zurück
        // TODO:
        return null;
    }

    @Override
    public boolean saveArticle(Article a) {
        // * speichert den Artikel a
        // TODO:
        return false;
    }

    @Override
    public void saveAllArticles(Article[] articles) {
        // * speichert alle Artikel
        // TODO:
    }

    @Override
    public void createDatabase(String dbSchema) {
        // * erzeugt ein SQLite Datenbankschema mit dem Namen 'dbSchema', mit einer Tabelle 'articles'
        // * articles hat folgende Attribute:
        //   - id           varchar(8)
        //   - description  varchar(40)
        //   - price        int
        // * die URL für SQLite (z.B. "jdbc:sqlite:<dbSchema>")
        // TODO:
    }
}

