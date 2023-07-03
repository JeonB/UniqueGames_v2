package com.uniqueGames.config;

import com.uniqueGames.model.CompanyVo;
import com.uniqueGames.model.MemberVo;
import com.uniqueGames.model.SessionConstants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {
    //resolveArgument를 실행하기 위한 조건
    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        //MemberVo 타입인지 확인
        boolean hasMemberType = MemberVo.class.isAssignableFrom(parameter.getParameterType());
        //CompanyVo 타입인지 확인
        boolean hasCompanyType = CompanyVo.class.isAssignableFrom(parameter.getParameterType());
        //@Login 어노테이션이 존재하는지 확인
        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
        return hasLoginAnnotation && (hasMemberType||hasCompanyType);
    }
    //세션에 있는 member 객체를 찾아서 반환. 못찾으면 null 반환
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {


        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession(false); //세션 자동생성 방지
        if (session == null) {
            return null;
        }
        return session.getAttribute(SessionConstants.LOGIN_MEMBER);
    }
}
