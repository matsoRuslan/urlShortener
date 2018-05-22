package com.matso.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UrlDTO {
    private String id;
    private String fullURL;
    private String createdOn;
    private String convertedURL;
}
