package ru.develgame.audiogames.dao;

import ru.develgame.audiogames.entity.AudioGameSave;
import ru.develgame.audiogames.jsf.UserBean;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
public class AudioGameSaveDaoImpl implements AudioGameSaveDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    @Inject
    private Logger logger;

    @Inject
    private UserBean userBean;

    @Override
    public AudioGameSave loadGame(int audioGameId) {
        Query query = entityManager.createNativeQuery("SELECT * FROM APP.AUDIOGAME_SAVE WHERE AUDIOGAME_ID=?1 " +
                "AND USERNAME=?2 OFFSET 0 ROWS FETCH NEXT 1 ROWS ONLY", AudioGameSave.class);
        query.setParameter(1, audioGameId);
        query.setParameter(2, userBean.getUsername());
        List<AudioGameSave> resultList = query.getResultList();

        if (resultList.isEmpty()) {
            return null;
        }

        return resultList.get(0);
    }

    @Override
    public boolean saveGame(int audioGameId, String chapterNum, boolean autoplay) {
        try {
            userTransaction.begin();

            Query query = entityManager.createNativeQuery("SELECT * FROM APP.AUDIOGAME_SAVE WHERE AUDIOGAME_ID=?1 " +
                    "AND USERNAME=?2 OFFSET 0 ROWS FETCH NEXT 1 ROWS ONLY", AudioGameSave.class);
            query.setParameter(1, audioGameId);
            query.setParameter(2, userBean.getUsername());
            List<AudioGameSave> resultList = query.getResultList();

            AudioGameSave audioGameSave;
            if (resultList.isEmpty()) {
                audioGameSave = new AudioGameSave();
                audioGameSave.setAudioGameId(audioGameId);
                audioGameSave.setUsername(userBean.getUsername());
            }
            else {
                audioGameSave = resultList.get(0);
            }
            audioGameSave.setChapterNum(chapterNum);
            audioGameSave.setAutoplay(autoplay);

            entityManager.persist(audioGameSave);
            userTransaction.commit();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Cannot save audio game progress", e);
            return false;
        }

        return true;
    }
}
