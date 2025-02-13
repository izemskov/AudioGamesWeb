package ru.develgame.audiogames.dao;

import ru.develgame.audiogames.entity.AudioGameChapter;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.UserTransaction;
import java.util.List;
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
        List<AudioGameChapter> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null;
        }
        return resultList.get(0);
    }

    @Override
    public AudioGameChapter getChapterByAudioGameIdAndChapterNum(int audioGameId, String chapterNum) {
        Query query = entityManager.createNativeQuery("SELECT * FROM APP.AUDIOGAME_CHAPTER WHERE AUDIOGAME_ID=?1 AND CHAPTER_NUM=?2 " +
                "ORDER BY CHAPTER_NUM OFFSET 0 ROWS FETCH NEXT 1 ROWS ONLY", AudioGameChapter.class);
        query.setParameter(1, audioGameId);
        query.setParameter(2, chapterNum);
        List<AudioGameChapter> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null;
        }
        return resultList.get(0);
    }
}
