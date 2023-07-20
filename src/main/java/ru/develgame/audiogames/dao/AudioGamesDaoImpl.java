package ru.develgame.audiogames.dao;

import ru.develgame.audiogames.entity.AudioGame;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
public class AudioGamesDaoImpl implements AudioGamesDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    @Inject
    private Logger logger;

    @Override
    public List<AudioGame> getAllAudioGames() {
        Query query = entityManager.createNativeQuery("SELECT ID, NAME, FOLDER FROM APP.AUDIOGAME", AudioGame.class);
        List messages = new ArrayList<>(query.getResultList());
        return messages;
    }

    @Override
    public boolean addAudioGames(AudioGame audioGame) {
        try {
            userTransaction.begin();
            entityManager.persist(audioGame);
            userTransaction.commit();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Cannot add audio game", e);
            return false;
        }

        return true;
    }
}
