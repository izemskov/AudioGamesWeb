package ru.develgame.audiogames.dao;

public interface AudioGameSaveDao {
    boolean saveGame(int audioGameId, String chapterNum);
}
