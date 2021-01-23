package br.com.helper.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

//    @Bean
//    public ArticleMapper articleMapper(ModelMapper modelMapper) {
//        return new ArticleMapperImp(modelMapper);
//    }
//
//    @Bean
//    public ArticleService articleService(ArticleRepository articleRepository, ArticleMapper articleMapper) {
//        return new ArticleDomain(articleRepository, articleMapper);
//    }

}
