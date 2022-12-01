package ssfassessment.latestnews2.controller;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ssfassessment.latestnews2.model.News;
import ssfassessment.latestnews2.services.NewsService;


@Controller
@RequestMapping(path = "/")
public class NewsController {
    private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsService service;
    

    @GetMapping
    public String getArticles(Model model){
        List<News> newsList = service.getArticles(); 

        model.addAttribute("newsList", newsList);
        return "index";
    }


    @PostMapping("/articles")
    public String saveArticles(@RequestParam("saveList") List<String> saveList,
                               Model model) {
        List<News> newsList = service.getArticles();
        model.addAttribute("newsList", newsList);

        List<News> newsListToSave = new LinkedList<>();
        for (String id : saveList) {
            for (int i = 0; i < newsList.size(); i++) {
                if (newsList.get(i).getId().equals(id)) {
                    newsListToSave.add(newsList.get(i));
                }
            }
        }
        service.saveArticles(newsListToSave);

        return "index";
    }
}


