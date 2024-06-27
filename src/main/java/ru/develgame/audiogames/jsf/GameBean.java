package ru.develgame.audiogames.jsf;

import ru.develgame.audiogames.dao.AudioGameChapterDao;
import ru.develgame.audiogames.dao.AudioGameDao;
import ru.develgame.audiogames.dao.AudioGameSaveDao;
import ru.develgame.audiogames.entity.AudioGame;
import ru.develgame.audiogames.entity.AudioGameChapter;
import ru.develgame.audiogames.entity.AudioGameSave;
import ru.develgame.audiogames.exception.AudioGameChapterNotFoundException;
import ru.develgame.audiogames.exception.AudioGameNotFoundException;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named("game")
@SessionScoped
public class GameBean implements Serializable {
    @Inject
    private transient AudioGameDao audioGameDao;

    @Inject
    private transient AudioGameChapterDao audioGameChapterDao;

    @Inject
    private transient AudioGameSaveDao audioGameSaveDao;

    private AudioGame audioGame;

    private AudioGameChapter audioGameChapter;

    private List<String> links;

    private Boolean autoplay = false;

    public void newAudioGame(int audioGameId) {
        audioGame = audioGameDao.getAudioGame(audioGameId);
        if (audioGame == null) {
            throw new AudioGameNotFoundException(String.format("Audio game with id %s not found", audioGameId));
        }

        loadChapterByNum(null);
    }

    public void loadAudioGame(int audioGameId) {
        AudioGameSave audioGameSave = audioGameSaveDao.loadGame(audioGameId);
        if (audioGameSave != null) {
            audioGame = audioGameDao.getAudioGame(audioGameId);
            if (audioGame == null) {
                throw new AudioGameNotFoundException(String.format("Audio game with id %s not found", audioGameId));
            }

            loadChapterByNum(audioGameSave.getChapterNum());
        }
    }

    public void loadChapterByNum(String chapterNum) {
        if (chapterNum == null) {
            audioGameChapter = audioGameChapterDao.getFirstAudioGameChapterByAudioGameId(audioGame.getId());
        }
        else {
            audioGameChapter = audioGameChapterDao.getChapterByAudioGameIdAndChapterNum(audioGame.getId(), chapterNum);
        }

        if (audioGameChapter == null) {
            throw new AudioGameChapterNotFoundException("Audio game chapter not found");
        }

        links = new ArrayList<>();
        if (audioGameChapter.getChapterLink() != null) {
            links.addAll(Arrays.asList(audioGameChapter.getChapterLink().split(",")));
        }
    }

    public void saveGame() {
        audioGameSaveDao.saveGame(audioGame.getId(), audioGameChapter.getChapterNum());
    }

    public void loadGame() {
        AudioGameSave audioGameSave = audioGameSaveDao.loadGame(audioGame.getId());
        if (audioGameSave != null) {
            loadChapterByNum(audioGameSave.getChapterNum());
        }
    }

    public String getAudioFile() {
        return String.format("/resources/audiogames/%s/chapter_%03d.mp3", audioGame.getFolder(),
                Integer.parseInt(audioGameChapter.getChapterNum()));
    }

    public AudioGameChapter getAudioGameChapter() {
        return audioGameChapter;
    }

    public List<String> getLinks() {
        return links;
    }

    public Boolean getAutoplay() {
        return autoplay;
    }

    public void setAutoplay(Boolean autoplay) {
        this.autoplay = autoplay;
    }
}
