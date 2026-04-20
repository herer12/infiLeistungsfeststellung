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
import java.util.Collections;
import java.util.List;

public class DataLayer implements IDataLayer, IDbInitialize {

    private ObjectMapper mapper = new ObjectMapper();
    String fileName = "articles.json";

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
        JsonRoot db = readDatabase();
        return db.articles.toArray(new Article[0]);
    }

    @Override
    public Article getArticleByID(String articleID) {
        // * gibt den Artikel mit der 'articleID' zurück
        // TODO:
        JsonRoot db = readDatabase();
        for (Article a : db.articles) {
            if (a.getArticleID().equals(articleID)) return a;
        }
        return null;
    }

    @Override
    public boolean saveArticle(Article a) {
        // * speichert den Artikel a
        // TODO:

        JsonRoot db = readDatabase();
        if (db == null) return false;

        ArrayList<Article> list = new ArrayList<>(db.articles);

        boolean found = false;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getArticleID().equals(a.getArticleID())) {
                list.set(i, a); // ersetzen
                found = true;
                break;
            }
        }

        if (!found) {
            list.add(a); // neu hinzufügen
        }

        db.articles = list;

        return writeDatabase(db);

    }

    @Override
    public void saveAllArticles(Article[] articles) {
        // * speichert alle Artikel
        // TODO:


        JsonRoot db = readDatabase();
        if (db == null) return;

        // KOMPLETT ersetzen
        db.articles = List.of(articles);

        writeDatabase(db);
    }

    private JsonRoot readDatabase() {
        try {
            return mapper.readValue(new File(fileName), JsonRoot.class);
        } catch (IOException e) {
            System.out.println("Error reading JSON DB: " + e.getMessage());
            return null;
        }
    }
    private boolean writeDatabase(JsonRoot db) {
        try {
            mapper.writeValue(new File(fileName), db);
            return true;
        } catch (IOException e) {
            System.out.println("Error writing JSON DB: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void createDatabase(String dbSchema) {
        // * erzeugt eine leere JSON-Datei, die Artikel sind ein Zweig (Array-Objekt im JSON-File)
        // * die Datei soll auch eine Versionsnummer in den Metadaten speichern
        // * der Name der Datei wird über den Parameter dbSchema gesteuert
        // TODO:


        JsonRoot db = new JsonRoot();
        fileName = dbSchema;
        db.version = 1;
        db.articles = List.of(new Article[0]);

        try {
            mapper.writeValue(new File(dbSchema), db);
            System.out.println("JSON database created: " + dbSchema);

        } catch (IOException e) {
            System.out.println("Error creating JSON DB: " + e.getMessage());
        }
    }
}

