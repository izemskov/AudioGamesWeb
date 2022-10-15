package ru.develgame.audiogames.dao;

import ru.develgame.audiogames.entity.AudioGame;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@RequestScoped
@Transactional
public class AudioGamesDao implements Serializable {
    @PersistenceContext
    private EntityManager em;

    public List<AudioGame> getAllAudioGames() {
        return em.createNamedQuery("getAllGames", AudioGame.class).getResultList();
    }
}
