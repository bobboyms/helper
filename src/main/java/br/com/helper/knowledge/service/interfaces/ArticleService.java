package br.com.helper.knowledge.service.interfaces;

import br.com.helper.knowledge.service.dto.ArticleReqDto;
import br.com.helper.knowledge.service.dto.ArticleRespDto;
import br.com.helper.knowledge.service.dto.ResponseDto;
import br.com.helper.knowledge.service.model.Article;

public interface ArticleService {

    void delete(String id);

    Article findOne(String id);

    ArticleRespDto findById(String id);
    ResponseDto save(ArticleReqDto articleReqDto);
    ResponseDto update(ArticleReqDto articleReqDto, String id);


}
