package com.matso.dao;

import com.matso.entity.UrlTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class UrlDAO {

    @PersistenceContext
    @Autowired
    private EntityManager entityManager;


    @Transactional
    public void persist(UrlTable urlTable) {
        entityManager.persist(urlTable);
    }

    @Transactional
    public void update(UrlTable urlTable) {
        entityManager.merge(urlTable);
    }

    public UrlTable findById(Long id) {
        UrlTable urlTable = (UrlTable) entityManager.createQuery("from UrlTable where id = '" + id + "'").getSingleResult();
        return urlTable;
    }

    public UrlTable findByFullURL(String fullURL) {
        UrlTable urlTable = (UrlTable) entityManager.createQuery("from UrlTable where fullURL = '" + fullURL + "'").getSingleResult();
        return urlTable;
    }

    public UrlTable findByConvertedURL(String convertedURL) {
        UrlTable urlTable = (UrlTable) entityManager.createQuery("from UrlTable where convertedURL = '" + convertedURL + "'").getSingleResult();
        return urlTable;
    }
}
