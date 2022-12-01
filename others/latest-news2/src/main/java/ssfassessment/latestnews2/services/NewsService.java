package ssfassessment.latestnews2.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ssfassessment.latestnews2.model.News;

@Service
public class NewsService implements NewsRepo{
    private static final Logger logger = LoggerFactory.getLogger(NewsService.class);
    private static final String mainUrl = "https://min-api.cryptocompare.com/data/v2/news/?";


    @Autowired
    @Qualifier("newsTemplate")
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    @Qualifier("newsGet")
    RedisTemplate<String, Object> redisTemplate2;

    public List<News> getArticles(){
        String apiKey = System.getenv("API_KEY");
        String articlesUrl = UriComponentsBuilder.fromUriString(mainUrl)
                            .query("lang=EN")
                            .toUriString();


        logger.info(" complete news uri api link >>>> " + articlesUrl);

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = null;
       
        List<News> newsList = new ArrayList<>();
        try {
            resp = template.getForEntity(articlesUrl, String.class);
            newsList = News.createJson(resp.getBody());
            
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return newsList;
                            
    }

    @Override
    public void saveArticles(List<News> newsToSave){
        for(News news : newsToSave){
            redisTemplate.opsForValue().set(news.getId(), news);
            logger.info("saved to redis >> " + news.getId());
        }
    }

    @Override
    public Object findById(String id) {
        logger.info("id >>> " + id);
        Set<String> keys = redisTemplate2.keys("*");
        logger.info("keys >>> " + keys.toString());
        
        Object o = redisTemplate2.opsForValue().get(id);
        if(o == null){
            return "{\"error\": \"cannot find news article\"" + id + "\"\n}";
        }
       
        return o;
    }
    
}
