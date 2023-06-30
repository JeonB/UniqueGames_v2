package com.uniqueGames.config;

import com.uniqueGames.interceptor.LoginCheckInterceptor;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final String[] whitelist = {"/css/**", "/js/**", "/img/**",
            "/", "/login", "/logout", "/findPassword", "/register", "/registerOk", "/register/check",
            "/member/**",
            "/compuPost/**",
            "/category/**",
            "/comment/**",
            "/search/**",
            "/mybatis",
            "/error"
    };
    //whitelist 같은 경우 로그인하지 않아도 접근할 수 있는 자원들 목록

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)               //낮을수록 먼저 호출됨. 인터셉터 체인 순서
                .addPathPatterns("/**") //모든 요청에 대해 인터셉터 실행
                .excludePathPatterns(whitelist); //인터셉터에서 제외할 패턴(URL) 지정

        /*
        registry.addInterceptor(new ExampleInterceptor())
           .order(2)
           .addPathPatterns("/**")
           .excludePathPatterns("/css/**" , "/*.ico" , "/error");
        * */
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver());
    }
}
