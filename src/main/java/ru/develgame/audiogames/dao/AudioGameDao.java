package ru.develgame.audiogames.dao;

import ru.develgame.audiogames.entity.AudioGame;

import java.util.List;

public interface AudioGameDao {
    List<AudioGame> getAllAudioGames();

    boolean addAudioGame(AudioGame audioGame);
}
