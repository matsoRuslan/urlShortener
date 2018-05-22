package com.matso.controller;

import com.matso.dto.UrlDTO;
import com.matso.service.UrlService.UrlService;
import org.apache.commons.validator.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;


@Controller
public class MainController {
    @Autowired
    private UrlService urlService;


    @RequestMapping(value = "/convert", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String convertURL(@RequestParam(value = "fullURL") String fullURL, ModelMap model, HttpServletRequest request) {

        String domainURL = urlService.getDomainURL(request);
        UrlDTO urlDTO;
        try {
            urlDTO = urlService.saveURL(fullURL, domainURL);
        } catch (ValidatorException e) {
            model.addAttribute("errorDescription", e.getMessage());
            return "error";
        }
        model.addAttribute("fullURL", urlDTO.getFullURL());
        model.addAttribute("convertedURL", urlDTO.getConvertedURL());
        model.addAttribute("createdON", urlDTO.getCreatedOn());
        return "shortenResult";
    }

    @RequestMapping(value = "/reverse", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String reverseConvertedURL(@RequestParam(value = "convertedURL") String convertedURL, ModelMap model, HttpServletRequest request) {
        String domainURL = urlService.getDomainURL(request);
        UrlDTO urlDTO;
        try {
            urlDTO = urlService.getURL(convertedURL, domainURL);
        } catch (NoResultException e) {
            model.addAttribute("errorDescription", "Entered URL is not present in database, sorry!");
            return "error";
        }
        model.addAttribute("fullURL", urlDTO.getFullURL());
        model.addAttribute("convertedURL", urlDTO.getConvertedURL());
        model.addAttribute("createdON", urlDTO.getCreatedOn());
        return "reversedResult";
    }


    @RequestMapping(value = "/{shortedURL}", method = RequestMethod.GET)
    public String redirectToRealPage(@PathVariable("shortedURL") String shortedURL, ModelMap model, HttpServletRequest request) {
        String fullShortedURL = urlService.getDomainURL(request) + "/" + shortedURL;
        try {
            String pageURL = urlService.getOriginalURLFromConverted(fullShortedURL);
            return "redirect://" + pageURL;
        } catch (NoResultException e) {
            model.addAttribute("errorDescription", "Entered URL is not present in database, sorry!");
            return "error";
        }
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homePage() {
        return "url";
    }

}
