package szu.infi.json;

import szu.infi.domain.Article;

import java.util.ArrayList;
import java.util.List;

public class JsonRoot {
    public int version = 1;
    public List<Article> articles = new ArrayList<>();
}
