package com.matso.service.UrlService.impl;

import com.matso.dao.UrlDAO;
import com.matso.dto.UrlDTO;
import com.matso.entity.UrlTable;
import com.matso.service.UrlService.UrlService;
import com.matso.util.EncodingUtil;
import org.apache.commons.validator.ValidatorException;
import org.apache.commons.validator.routines.UrlValidator;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

import static org.apache.commons.validator.routines.UrlValidator.ALLOW_2_SLASHES;
import static org.apache.commons.validator.routines.UrlValidator.ALLOW_ALL_SCHEMES;
import static org.apache.commons.validator.routines.UrlValidator.ALLOW_LOCAL_URLS;

@Service
@Transactional
public class UrlServiceImpl implements UrlService {

    @Autowired
    private UrlDAO urlDAO;


    public UrlDTO getURL(String convertedURL, String baseUrl) throws NoResultException {
        UrlDTO dto = new UrlDTO();
        String str = convertedURL.replace(baseUrl + "/", "");
        long id = EncodingUtil.toBase10(str);
        UrlTable url = urlDAO.findById(id);
        dto.setId(url.getId().toString());
        dto.setConvertedURL(url.getConvertedURL());
        dto.setFullURL(url.getFullURL());
        dto.setCreatedOn(url.getCreatedOn().toString());
        return dto;
    }

    public UrlDTO saveURL(String fullURL, String baseUrl) throws ValidatorException {
        UrlTable url = new UrlTable();
        if (validateURL(fullURL)) {
            fullURL = sanitizeURL(fullURL);
            try {
                url = urlDAO.findByFullURL(fullURL);
            } catch (NoResultException exception) {
                url.setFullURL(fullURL);
                urlDAO.persist(url);
                String convertedURL = baseUrl + "/" + EncodingUtil.toBase62(url.getId().intValue());
                url.setConvertedURL(convertedURL);
                urlDAO.update(url);
                url = urlDAO.findByFullURL(fullURL);
            }
            return generateURLShorterner(url);
        } else {
            throw new ValidatorException("Inserted URL is not valid! Maybe you forgot \"https://\" at the beginning?");
        }
    }


    private UrlDTO generateURLShorterner(UrlTable url) {
        UrlDTO dto = new UrlDTO();
        dto.setId(url.getId().toString());
        dto.setFullURL(url.getFullURL());
        dto.setCreatedOn(url.getCreatedOn().toString(DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss")));
        dto.setConvertedURL(url.getConvertedURL());
        return dto;
    }

    public String getOriginalURLFromConverted(String convertedURL) throws NoResultException {
        String fullURL = urlDAO.findByConvertedURL(convertedURL).getFullURL();
        return fullURL;
    }

    public String getDomainURL(HttpServletRequest request) {
        String domainURL = request.getScheme() + "://" + request.getServerName() +
                ("http".equals(request.getScheme()) && request.getServerPort() == 80 || "https".equals(request.getScheme()) && request.getServerPort() == 443 ? "" : ":" + request.getServerPort());
        return domainURL;
    }


    private boolean validateURL(String url) {
        UrlValidator urlValidator = new UrlValidator(ALLOW_ALL_SCHEMES + ALLOW_2_SLASHES + ALLOW_LOCAL_URLS);
        if (urlValidator.isValid(url)) {
            return true;
        } else {
            return false;
        }
    }


    private String sanitizeURL(String url) {
        if (url.substring(0, 7).equals("http://"))
            url = url.substring(7);

        if (url.substring(0, 8).equals("https://"))
            url = url.substring(8);

        if (url.charAt(url.length() - 1) == '/')
            url = url.substring(0, url.length() - 1);
        return url;
    }

}

