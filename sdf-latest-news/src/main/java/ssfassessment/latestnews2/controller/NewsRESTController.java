package ssfassessment.latestnews2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import ssfassessment.latestnews2.exception.NewsNotFoundException;
import ssfassessment.latestnews2.services.NewsService;

@RestController
@RequestMapping(path = "/news",  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class NewsRESTController{
    private static final Logger logger = LoggerFactory.getLogger(NewsRESTController.class);

    @Autowired
    NewsService service;

    @GetMapping(path = "/{id}")
    public ResponseEntity<String> getArticleById(@PathVariable String id, Model model) {
        Object n = service.findById(id);

        if(n == null){
            throw new NewsNotFoundException(id);
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json");
        responseHeaders.add("Accept", "application/json");
        return ResponseEntity.ok().headers(responseHeaders).body(n.toString());
    }
}
    

