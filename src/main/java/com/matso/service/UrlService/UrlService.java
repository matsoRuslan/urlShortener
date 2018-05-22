package com.matso.service.UrlService;

import com.matso.dto.UrlDTO;
import org.apache.commons.validator.ValidatorException;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

public interface UrlService {
    UrlDTO saveURL(String fullURL, String baseUrl) throws ValidatorException;

    UrlDTO getURL(String convertedURL, String baseUrl) throws NoResultException;

    String getOriginalURLFromConverted(String convertedURL) throws NoResultException;

    String getDomainURL(HttpServletRequest request);
}
