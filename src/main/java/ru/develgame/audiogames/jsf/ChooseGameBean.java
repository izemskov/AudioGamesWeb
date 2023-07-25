package ru.develgame.audiogames.jsf;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("chooseGame")
@ViewScoped
public class ChooseGameBean {
    private int audioGameId;

    public int getAudioGameId() {
        return audioGameId;
    }

    public void setAudioGameId(int audioGameId) {
        this.audioGameId = audioGameId;
    }
}
