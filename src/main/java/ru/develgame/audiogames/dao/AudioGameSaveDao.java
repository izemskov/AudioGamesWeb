package ru.develgame.audiogames.dao;

import ru.develgame.audiogames.entity.AudioGameSave;

public interface AudioGameSaveDao {
    AudioGameSave loadGame(int audioGameId);

    boolean saveGame(int audioGameId, String chapterNum);
}
