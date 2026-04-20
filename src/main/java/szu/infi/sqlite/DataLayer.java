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
        return DriverManager.getConnection("jdbc:sqlite:C:/Schule/Schuldateien/Informatik/SQL mit java/LF_DB_Layer_SS01/" + this.dbFileName);
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
        String sql = "SELECT * FROM articles";

        try(Connection connection = connect()){
            ResultSet rs = connection.createStatement().executeQuery(sql);
            return deserializeArticles(rs);
        }catch (Exception e){
            System.out.println("Error connecting to database" + e.getMessage());
        }
        return null;
    }

    private Article[] deserializeArticles(ResultSet rs) {
        List<Article> articles = new ArrayList<>();
        try {
            while (rs.next()){
                articles.add(desarilizeArticle(rs));
            }
            return articles.toArray(new Article[articles.size()]);
        }catch (Exception e){
            System.out.println("Error deserializing all articles" + e.getMessage());
            return null;
        }

    }

    private Article desarilizeArticle(ResultSet rs)  {
        try {
            Article a = new Article();
            a.setArticleID(rs.getString("id"));
            a.setDescription(rs.getString("description"));
            a.setPrice(rs.getInt("price"));
            return a;
        } catch (Exception e) {
            System.out.println("Error deserializing one article" + e.getMessage());
            return null;
        }
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

        String sql = "INSERT INTO articles (id, description, price) VALUES (?, ?, ?)";

        try(Connection connection = connect()){

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, a.getArticleID());
            ps.setString(2, a.getDescription());
            ps.setInt(3, a.getPrice());
            ps.executeUpdate();
            return true;

        }catch (Exception e){
            System.out.println("Error saving Article to database" + e.getMessage());
        }
        return false;
    }

    @Override
    public void saveAllArticles(Article[] articles) {
        // * speichert alle Artikel
        // TODO:


        String sql = "INSERT INTO articles (id, description, price) VALUES (?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (Article a : articles) {

                ps.setString(1, a.getArticleID());
                ps.setString(2, a.getDescription());
                ps.setInt(3, a.getPrice());

                ps.addBatch();
            }

            ps.executeBatch();

        } catch (SQLException e) {
            System.out.println("Error saving all articles: " + e.getMessage());
        }
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

        dbFileName = dbSchema;
        String sql = """
                CREATE TABLE IF NOT EXISTS articles (
                    id VARCHAR(8),
                    description VARCHAR(40),
                    price INT
                );""";

        try(Connection conn = connect()){

            conn.createStatement().executeUpdate(sql);
            System.out.println("Database created successfully");

        } catch (Exception e) {
            System.out.println("Error creating database" + e.getMessage());
        }

    }
}

