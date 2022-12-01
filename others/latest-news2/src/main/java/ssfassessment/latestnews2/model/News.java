package ssfassessment.latestnews2.model;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;


public class News implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(News.class);
    private String id;
    private int publishedYear;
    private String title;
    private String url;
    private String imageUrl;
    private String content;
    private String tags;
    private String categories;
    

   public News(){};

   public News(String id, String title, String url, String imageUrl, String content, String tags, String categories, int publishedYear){
    this.id = id;
    this.title = title;
    this.url = url;
    this.imageUrl = imageUrl;
    this.content =  content;
    this.tags = tags;
    this.categories = categories;
   }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getPublishedYear() {
        return publishedYear;
    }
    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }
    public String getCategories() {
        return categories;
    }
    public void setCategories(String categories) {
        this.categories = categories;
    }
    

    public static List<News> createJson(String json) throws IOException {
        List<News> newsList = new ArrayList<>();
          try(InputStream is = new ByteArrayInputStream(json.getBytes())){
            JsonReader reader = Json.createReader(is);
            JsonObject obj = reader.readObject();
            JsonArray arr =  obj.getJsonArray("Data");
            for(int i = 0; i < arr.size(); i ++){
                JsonObject  newsObject  = arr.getJsonObject(i);
                News news = new News();
                //logger.info("dataObject >>> " + newsObject);
                news.setId(newsObject.getString("id"));
                news.setTitle(newsObject.getString("title"));
                news.setUrl(newsObject.getString("url"));
                news.setPublishedYear(newsObject.getInt("published_on"));
                news.setImageUrl(newsObject.getString("imageurl"));
                news.setCategories(newsObject.getString("categories"));
                news.setTags(newsObject.getString("tags"));
                news.setContent(newsObject.getString("body"));
                //logger.info("successfully created >>> " + news);
                newsList.add(news);
               

            }
        }
        //logger.info("saved >>>" + newsList);
        return newsList;
    }
}