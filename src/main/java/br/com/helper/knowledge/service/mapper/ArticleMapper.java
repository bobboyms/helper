package br.com.helper.knowledge.service.mapper;

import br.com.helper.knowledge.service.dto.ArticleReqDto;
import br.com.helper.knowledge.service.dto.ArticleRespDto;
import br.com.helper.knowledge.service.dto.ResponseDto;
import br.com.helper.knowledge.service.model.Article;

public interface ArticleMapper {

    Article createArticle(ArticleReqDto articleReqDto);
    ArticleRespDto createArticleRespDto(Article article);
    ResponseDto createRespDto(Article article);

}
