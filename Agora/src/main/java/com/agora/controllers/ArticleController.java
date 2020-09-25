package com.agora.controllers;
import com.agora.models.Article;
import com.agora.models.PublishArticleTemplate;
import com.agora.models.User;
import com.agora.services.ArticleService;
import com.agora.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("article")
public class ArticleController {
	
	private ArticleService service;
	private UserService userService;

	@Autowired
    public ArticleController(ArticleService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

	@CrossOrigin
    @GetMapping(produces = "application/json")
    @ResponseBody
    public ResponseEntity<Set<Article>> findAllArticles() {
        Set<Article> result = service.findAll();
        if(result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(result);
    }

	@CrossOrigin
    @GetMapping(value = "{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Article> findArticleById(@PathVariable String id) {
        Article result = service.findArticleById(Integer.parseInt(id));
        if(result == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(result);
    }

	@CrossOrigin
    @PostMapping
    @ResponseBody
    public ResponseEntity<Article> insertArticle(@RequestBody PublishArticleTemplate publishArticleTemplate) {
	    System.out.println(publishArticleTemplate.getUser_id());
        User user = userService.findUserById(publishArticleTemplate.getUser_id());


        Article article = new Article(user, publishArticleTemplate.getTitle(), publishArticleTemplate.getDescription(), publishArticleTemplate.getImage(), publishArticleTemplate.getPublishedAt(),
                publishArticleTemplate.getContent(), publishArticleTemplate.getStatus());

	    if(article.getArticle_id() != 0) {
            return ResponseEntity.badRequest().build();
        }

        service.save(article);

        if(article.getArticle_id() == 0) {
            // Failed to insert properly
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.accepted().body(article);
    }

	@CrossOrigin
    @DeleteMapping(value = "{id}")
    @ResponseBody
    public ResponseEntity<Boolean> deleteArticle(@PathVariable String id){

        Article article = service.findArticleById(Integer.parseInt(id));

        if(article == null){
            return ResponseEntity.badRequest().build();
        }

        service.delete(article);

        return ResponseEntity.accepted().body(true);
    }

}
