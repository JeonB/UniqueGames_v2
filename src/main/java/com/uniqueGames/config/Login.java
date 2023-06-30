package com.uniqueGames.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) //파라미터에만 사용
@Retention(RetentionPolicy.RUNTIME) //런타임까지 애노테이션 정보가 남아있게 하기위해
public @interface Login {
}
