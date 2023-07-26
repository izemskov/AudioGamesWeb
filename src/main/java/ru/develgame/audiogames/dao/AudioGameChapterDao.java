package ru.develgame.audiogames.dao;

import ru.develgame.audiogames.entity.AudioGameChapter;

public interface AudioGameChapterDao {
    boolean addAudioGameChapter(AudioGameChapter audioGameChapter);

    AudioGameChapter getFirstAudioGameChapterByAudioGameId(int audioGameId);
}
