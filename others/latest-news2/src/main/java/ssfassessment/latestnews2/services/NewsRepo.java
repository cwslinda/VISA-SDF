package ssfassessment.latestnews2.services;

import java.util.List;

import ssfassessment.latestnews2.model.News;

public interface NewsRepo {
    
    public void saveArticles(List<News> newsToSave);

    public Object findById(String Id);
}

    
