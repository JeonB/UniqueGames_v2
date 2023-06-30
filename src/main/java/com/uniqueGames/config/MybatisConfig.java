package com.uniqueGames.config;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
@MapperScan(basePackages= {"com.uniqueGames.repository"})
public class MybatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Autowired DataSource dataSource, ApplicationContext applicationContext) throws Exception{
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean(); //팩토리 빈 생성
        factoryBean.setDataSource(dataSource); //미리 만들어 놓은 DataSource 빈을 주입하여 넣어준다.
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/*.xml")); //쿼리 실행을 위해 만들어 놓은 해당 위치의 xml 파일을 맵퍼로 설정
        factoryBean.setTypeAliasesPackage("com.uniqueGames.model");
        SqlSessionFactory factory = factoryBean.getObject();
        assert factory != null;
        factory.getConfiguration().setMapUnderscoreToCamelCase(true);
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(@Autowired SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory); //sqlSessionTemplate 에 만들어 놓은 팩토리 주입
    }
}