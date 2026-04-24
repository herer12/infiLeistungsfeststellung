package szu.infi.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import szu.infi.domain.Article;
import szu.infi.domain.IDataLayer;
import szu.infi.domain.IDbInitialize;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DataLayer implements IDataLayer, IDbInitialize {

    private String filePath = "articles.xml";

    @Override
    public Article[] getAllArticles() {
        return new Article[0];
    }

    @Override
    public Article getArticleByID(String articleID) {
        return null;
    }

    @Override
    public boolean saveArticle(Article newArticle) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new File(filePath));

            Element articles = (Element) doc.getElementsByTagName("articles").item(0);

            NodeList list = doc.getElementsByTagName("article");

            boolean replaced = false;

            for (int i = 0; i < list.getLength(); i++) {
                Element existing = (Element) list.item(i);
                String id = existing.getElementsByTagName("articleID").item(0).getTextContent();

                if (id.equals(newArticle.getArticleID())) {
                    // ersetzen
                    Element newElem = createArticleElement(doc, newArticle);
                    articles.replaceChild(newElem, existing);
                    replaced = true;
                    break;
                }
            }

            // wenn nicht gefunden → hinzufügen
            if (!replaced) {
                articles.appendChild(createArticleElement(doc, newArticle));
            }

            writeToFile(doc);
            return true;

        } catch (Exception e) {
            System.out.println("Error saving Article to database" + e.getMessage());
        }
        return false;
    }

    private static Element createArticleElement(Document doc, Article a) {
        Element article = doc.createElement("article");

        Element id = doc.createElement("articleID");
        id.setTextContent(a.getArticleID());

        Element price = doc.createElement("price");
        price.setTextContent(String.valueOf(a.getPrice()));

        Element desc = doc.createElement("description");
        desc.setTextContent(a.getDescription());

        article.appendChild(id);
        article.appendChild(price);
        article.appendChild(desc);

        return article;
    }

    private void writeToFile(Document doc)  {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(new DOMSource(doc), new StreamResult(new File(filePath)));
        }catch (Exception e){
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    @Override
    public void saveAllArticles(Article[] articles) {
            try {

                Document doc = documentBuilder();
                Element root = createBase(doc);
                Element articlese = createArticlese(doc, root);

                // alle Artikel neu hinzufügen
                for (Article a : articles) {
                    Element article = createArticleElement(doc, a);
                    articlese.appendChild(article);
                }

                writeToFile(doc);

            } catch (Exception e) {
                System.out.println("Error saving all articles: " + e.getMessage());
            }
    }

    private Document documentBuilder(){
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            return builder.newDocument();
        }catch (Exception e){
            return null;
        }
    }

    private Element createBase(Document doc){
        try {

            Element root = doc.createElement("database");
            root.setAttribute("version", "1");
            doc.appendChild(root);

            return root;
        } catch (Exception e) {
            System.out.println( "Error creating base: " + e.getMessage());
        }
        return null;

    }

    private Element createArticlese(Document doc, Element root){
        Element articlese = doc.createElement("articles");
        root.appendChild(articlese);
        return articlese;
    }


    @Override
    public void createDatabase(String dbSchema) {
        try {

            filePath = dbSchema;

            Document doc =documentBuilder();

            // Dokument erstellen
            createBase(doc);

            // Datei schreiben
            writeToFile(doc);

            System.out.println("Leere XML-Datenbank erstellt!");
        }catch (Exception e){
            System.out.println("Error creating XML DB: " + e.getMessage());
        }

    }

}
