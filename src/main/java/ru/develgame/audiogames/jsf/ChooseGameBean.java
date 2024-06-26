package ru.develgame.audiogames.jsf;

import ru.develgame.audiogames.dao.AudioGameDao;
import ru.develgame.audiogames.entity.AudioGame;
import ru.develgame.audiogames.exception.AudioGameNotFoundException;

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

    @Inject
    private GameBean gameBean;

    private List<AudioGame> allAudioGamesList;

    private List<Integer> allAudioGameIdsList;

    private Integer audioGameId;

    @PostConstruct
    public void init() {
        allAudioGamesList = audioGameDao.getAllAudioGames();
        allAudioGameIdsList = allAudioGamesList.stream().map(t -> t.getId()).collect(Collectors.toList());
    }

    public String newGame() {
        if (audioGameId == null && !allAudioGamesList.isEmpty()) {
            audioGameId = allAudioGamesList.get(0).getId();
        }

        AudioGame audioGame = allAudioGamesList.stream()
                .filter(t -> t.getId() == audioGameId.intValue())
                .findFirst()
                .orElseThrow(() -> new AudioGameNotFoundException("Audio game not found"));
        gameBean.newAudioGame(audioGame.getId());

        return "GAME";
    }

    public String loadGame() {
        AudioGame audioGame = allAudioGamesList.stream()
                .filter(t -> t.getId() == audioGameId.intValue())
                .findFirst()
                .orElseThrow(() -> new AudioGameNotFoundException("Audio game not found"));
        gameBean.loadAudioGame(audioGame.getId());

        return "GAME";
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
