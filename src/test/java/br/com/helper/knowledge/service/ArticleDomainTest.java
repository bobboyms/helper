package br.com.helper.knowledge.service;

import br.com.helper.exception.ObjectNotFoundException;
import br.com.helper.knowledge.repository.ArticleRepository;
import br.com.helper.knowledge.service.dto.ArticleReqDto;
import br.com.helper.knowledge.service.dto.ArticleRespDto;
import br.com.helper.knowledge.service.dto.ResponseDto;
import br.com.helper.knowledge.service.interfaces.ArticleService;
import br.com.helper.knowledge.service.mapper.ArticleMapper;
import br.com.helper.knowledge.service.model.Article;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;


@SpringBootTest
@RunWith(SpringRunner.class)
class ArticleDomainTest {

    @MockBean
    ArticleRepository articleRepository;

//    @MockBean
//    private ArticleMapper articleMapper;

//    @MockBean
//    ArticleMapper articleMapper;

    @Autowired
    ArticleService articleService;

    @Test
    void save() {

        ArticleReqDto articleReqDto = new ArticleReqDto("Mock");
        Article article = new Article("","Mock");

//        Mockito.when(articleMapper.createArticle(articleReqDto)).
//                thenReturn(article);

        Mockito.when(articleRepository.save(article)).
                thenReturn(article);

//        Mockito.when(articleMapper.createArticle(new ArticleReqDto("Mock"))).
//                thenReturn(new Article("xndc", "Mock"));

        ResponseDto responseDto = articleService.save(articleReqDto);
        System.out.println(responseDto);

    }

    @Test
    void findById() {
        Mockito.when(articleRepository.findById("xndc")).
                thenReturn(Optional.of(new Article("xndc", "Mock")));

        ArticleRespDto article = articleService.findById("xndc");
        Assert.assertEquals(article.getId(), "xndc");
        Assert.assertEquals(article.getTitle(), "Mock");

        try {
            articleService.findById("xxxx");
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), ObjectNotFoundException.class);
        }
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}