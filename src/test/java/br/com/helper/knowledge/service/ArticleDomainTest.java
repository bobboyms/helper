package br.com.helper.knowledge.service;

import br.com.helper.exception.ObjectNotFoundException;
import br.com.helper.knowledge.repository.ArticleRepository;
import br.com.helper.knowledge.service.dto.ArticleReqDto;
import br.com.helper.knowledge.service.dto.ArticleRespDto;
import br.com.helper.knowledge.service.dto.ResponseDto;
import br.com.helper.knowledge.service.interfaces.ArticleService;
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

    @Autowired
    ArticleService articleService;

    @Test
    void save() {

        ArticleReqDto articleReqDto = new ArticleReqDto("Mock");

        Mockito.when(articleRepository.save(Mockito.any(Article.class))).
                thenReturn(new Article("xndc","Mock"));

        ResponseDto responseDto = articleService.save(articleReqDto);

        Assert.assertEquals("xndc", responseDto.getId());

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
        ArticleReqDto articleReqDto = new ArticleReqDto("Mock");

        Mockito.when(articleRepository.save(Mockito.any(Article.class))).
                thenReturn(new Article("xndc","Mock"));

        Mockito.when(articleRepository.findById("xndc")).
                thenReturn(Optional.of(new Article("xndc", "Mock")));

        ResponseDto responseDto = articleService.update(articleReqDto, "xndc");

        Assert.assertEquals("xndc", responseDto.getId());
    }

    @Test
    void delete() {


        Mockito.when(articleRepository.findById("xndc")).
                thenReturn(Optional.of(new Article("xndc", "Mock")));

        articleService.delete("xndc");

        try {
            articleService.delete("xxxx");
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), ObjectNotFoundException.class);
        }


    }
}