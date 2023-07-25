package ru.develgame.audiogames.dao;

import ru.develgame.audiogames.entity.AudioGame;

import java.util.List;

public interface AudioGameDao {
    List<AudioGame> getAllAudioGames();

    AudioGame getAudioGame(int id);

    boolean addAudioGame(AudioGame audioGame);
}
