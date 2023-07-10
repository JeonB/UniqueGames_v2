package com.uniqueGames.test;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class GenericSuper {
    MultipartFile file;
    String imageId;
}
