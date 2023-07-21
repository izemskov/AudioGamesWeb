package ru.develgame.audiogames.dao;

import ru.develgame.audiogames.entity.AudioGameChapter;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
public class AudioGameChapterDaoImpl implements AudioGameChapterDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    @Inject
    private Logger logger;

    @Override
    public boolean addAudioGameChapter(AudioGameChapter audioGameChapter) {
        try {
            userTransaction.begin();
            entityManager.persist(audioGameChapter);
            userTransaction.commit();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Cannot add audio game", e);
            return false;
        }

        return true;
    }
}
