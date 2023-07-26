package ru.develgame.audiogames.jsf;

import ru.develgame.audiogames.dao.AudioGameDao;
import ru.develgame.audiogames.entity.AudioGame;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named("chooseGame")
@ViewScoped
public class ChooseGameBean implements Serializable {
    @Inject
    private transient AudioGameDao audioGameDao;

    private AudioGame audioGame;

    private List<AudioGame> allAudioGamesList;

    private List<Integer> allAudioGameIdsList;

    private Integer audioGameId;

    @PostConstruct
    public void init() {
        allAudioGamesList = audioGameDao.getAllAudioGames();
        allAudioGameIdsList = allAudioGamesList.stream().map(t -> t.getId()).collect(Collectors.toList());
    }

    public void newGame() {
        System.out.println(audioGame.getName());
    }

    public void listener() {
        System.out.println("test");
    }

    public AudioGame getAudioGame() {
        return audioGame;
    }

    public void setAudioGame(AudioGame audioGame) {
        this.audioGame = audioGame;
    }

    public List<AudioGame> getAllAudioGamesList() {
        return allAudioGamesList;
    }

    public Integer getAudioGameId() {
        return audioGameId;
    }

    public void setAudioGameId(Integer audioGameId) {
        this.audioGameId = audioGameId;
    }

    public List<Integer> getAllAudioGameIdsList() {
        return allAudioGameIdsList;
    }
}
