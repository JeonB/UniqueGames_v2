package com.uniqueGames.test;

import com.uniqueGames.model.Member;
import com.uniqueGames.model.Notice;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class GenericImpl<T extends GenericSuper> extends GenericDemo<T> {

    public GenericImpl(T data) {
        super(data);
    }

    @Override
    public GenericSuper fileCheck() {
        if (data.getFile() != null && !data.getFile().isEmpty()) {

            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String upload_file = data.getFile().getOriginalFilename();
            String image_id = uuid + upload_file;

            data.setImageId(image_id);

        } else {
            log.info(data.toString());
        }

        return data;
    }
}
