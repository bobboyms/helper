package br.com.helper.knowledge.controller;

import br.com.helper.knowledge.service.dto.ArticleReqDto;
import br.com.helper.knowledge.service.dto.ArticleRespDto;
import br.com.helper.knowledge.service.dto.ResponseDto;
import br.com.helper.knowledge.service.interfaces.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleRespDto> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok(articleService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ResponseDto> save(@RequestBody ArticleReqDto articleReqDto) {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(articleService.save(articleReqDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> update(@RequestBody ArticleReqDto articleReqDto, @PathVariable("id") String id) {
        return ResponseEntity.ok(articleService.update(articleReqDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        articleService.delete(id);
        return ResponseEntity.ok().build();
    }

}
