package ru.develgame.audiogames.jsf;

import ru.develgame.audiogames.dao.AudioGamesDao;
import ru.develgame.audiogames.entity.AudioGame;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ViewScoped
@Named("testdb")
public class TestDBBean implements Serializable {
    @Inject
    private transient AudioGamesDao audioGamesDao;

    public void addAudioGame() {
        String uuid = UUID.randomUUID().toString();
        audioGamesDao.addAudioGames(new AudioGame("name " + uuid, "folder " + uuid));
    }

    public List<AudioGame> getAudioGames() {
        return new ArrayList<>(audioGamesDao.getAllAudioGames());
    }
}
