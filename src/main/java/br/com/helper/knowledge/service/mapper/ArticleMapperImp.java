package br.com.helper.knowledge.service.mapper;

import br.com.helper.knowledge.service.dto.ArticleReqDto;
import br.com.helper.knowledge.service.dto.ArticleRespDto;
import br.com.helper.knowledge.service.dto.ResponseDto;
import br.com.helper.knowledge.service.model.Article;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapperImp implements ArticleMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public ArticleMapperImp(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Article createArticle(ArticleReqDto articleReqDto) {
        return modelMapper.map(articleReqDto, Article.class);
    }

    @Override
    public ArticleRespDto createArticleRespDto(Article article) {
        return modelMapper.map(article, ArticleRespDto.class);
    }

    @Override
    public ResponseDto createRespDto(Article article) {
        return modelMapper.map(article, ResponseDto.class);
    }
}
