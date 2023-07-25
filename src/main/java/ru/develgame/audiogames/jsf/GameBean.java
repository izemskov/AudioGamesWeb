package ru.develgame.audiogames.jsf;

import ru.develgame.audiogames.dao.AudioGameChapterDao;
import ru.develgame.audiogames.dao.AudioGameDao;
import ru.develgame.audiogames.entity.AudioGame;
import ru.develgame.audiogames.exception.AudioGameNotFoundException;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named("game")
@SessionScoped
public class GameBean implements Serializable {
    @Inject
    private transient AudioGameDao audioGameDao;

    @Inject
    private transient AudioGameChapterDao audioGameChapterDao;

    private AudioGame audioGame;

    private int audioGameId;

    public void loadAudioGame(int audioGameId) {
        audioGame = audioGameDao.getAudioGame(audioGameId);
        if (audioGame == null) {
            throw new AudioGameNotFoundException(String.format("Audio game with id %s not found", audioGameId));
        }
    }

//    private AudioGameChapter audioGameChapter;
//
//    public void loadChapter(String chapterNum) {
//        if (chapterNum == null) {
//
//        }
//    }
//
//    public AudioGameChapter getAudioGameChapter() {
//        return audioGameChapter;
//    }


    public int getAudioGameId() {
        return audioGameId;
    }

    public void setAudioGameId(int audioGameId) {
        this.audioGameId = audioGameId;
    }
}
