package ru.develgame.audiogames.jsf;

import ru.develgame.audiogames.dao.AudioGameChapterDao;
import ru.develgame.audiogames.dao.AudioGameDao;
import ru.develgame.audiogames.entity.AudioGame;
import ru.develgame.audiogames.entity.AudioGameChapter;
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

    private AudioGame audioGame;

    private AudioGameChapter audioGameChapter;

    private List<String> links;

    public void newAudioGame(int audioGameId) {
        audioGame = audioGameDao.getAudioGame(audioGameId);
        if (audioGame == null) {
            throw new AudioGameNotFoundException(String.format("Audio game with id %s not found", audioGameId));
        }

        audioGameChapter = audioGameChapterDao.getFirstAudioGameChapterByAudioGameId(audioGameId);
        if (audioGameChapter == null) {
            throw new AudioGameChapterNotFoundException("Audio game chapter not found");
        }

        links = new ArrayList<>();
        if (audioGameChapter.getChapterLink() != null) {
            links.addAll(Arrays.asList(audioGameChapter.getChapterLink().split(",")));
        }
    }

    public AudioGameChapter getAudioGameChapter() {
        return audioGameChapter;
    }

    public List<String> getLinks() {
        return links;
    }
}
