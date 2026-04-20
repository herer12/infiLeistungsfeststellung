package szu.infi.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import szu.infi.domain.Article;
import szu.infi.domain.IDataLayer;
import szu.infi.domain.IDbInitialize;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DataLayer implements IDataLayer, IDbInitialize {

    public DataLayer() {
        // TODO:
    }

    public DataLayer(String dbFileName)
    {
        this();
        // TODO:
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
        // * erzeugt eine leere JSON-Datei, die Artikel sind ein Zweig (Array-Objekt im JSON-File)
        // * die Datei soll auch eine Versionsnummer in den Metadaten speichern
        // * der Name der Datei wird über den Parameter dbSchema gesteuert
        // TODO:
    }
}

