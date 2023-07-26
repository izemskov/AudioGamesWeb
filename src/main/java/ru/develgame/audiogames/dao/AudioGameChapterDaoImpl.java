package ru.develgame.audiogames.dao;

import ru.develgame.audiogames.entity.AudioGameChapter;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    @Override
    public AudioGameChapter getFirstAudioGameChapterByAudioGameId(int audioGameId) {
        Query query = entityManager.createNativeQuery("SELECT * FROM APP.AUDIOGAME_CHAPTER WHERE AUDIOGAME_ID=?1 " +
                "ORDER BY CHAPTER_NUM OFFSET 0 ROWS FETCH NEXT 1 ROWS ONLY", AudioGameChapter.class);
        query.setParameter(1, audioGameId);
        return (AudioGameChapter) query.getSingleResult();
    }
}
