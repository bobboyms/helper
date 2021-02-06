package br.com.helper.knowledge.service;

import br.com.helper.beans.ValidationMessage;
import br.com.helper.exception.ObjectNotFoundException;
import br.com.helper.knowledge.repository.ArticleRepository;
import br.com.helper.knowledge.service.dto.ArticleReqDto;
import br.com.helper.knowledge.service.dto.ArticleRespDto;
import br.com.helper.knowledge.service.dto.ResponseDto;
import br.com.helper.knowledge.service.interfaces.ArticleService;
import br.com.helper.knowledge.service.mapper.ArticleMapper;
import br.com.helper.knowledge.service.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ArticleDomain implements ArticleService {

    private final ArticleMapper articleMapper;
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleDomain(ArticleRepository articleRepository, ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }

    @Override
    public ResponseDto save(ArticleReqDto articleReqDto) {
        return articleMapper.createRespDto(articleRepository.
                save(articleMapper.createArticle(articleReqDto)));
    }

    @Override
    public Article findOne(String id) {
        return articleRepository.findById(id).
                orElseThrow(()-> new ObjectNotFoundException(Collections.
                        singletonList(new ValidationMessage("Not Found Article"))));

    }

    @Override
    public ArticleRespDto findById(String id) {
        return articleMapper.createArticleRespDto(findOne(id));
    }

    @Override
    public ResponseDto update(ArticleReqDto articleReqDto, String id) {
        findById(id);

        final Article article = articleMapper.createArticle(articleReqDto);
        article.setId(id);
        return articleMapper.createRespDto(articleRepository.save(article));
    }

    @Override
    public void delete(String id) {
        articleRepository.delete(findOne(id));
    }


}
