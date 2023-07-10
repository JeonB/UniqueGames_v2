package com.uniqueGames.test;

import com.uniqueGames.model.Member;
import com.uniqueGames.model.Notice;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class GenericDemo<T> {

    T data;

    public GenericDemo(T data) {
        log.info(data.toString());
        this.data = data;
    }

    protected abstract GenericSuper fileCheck();

}
