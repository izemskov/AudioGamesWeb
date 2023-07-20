package ru.develgame.audiogames.dao;

import ru.develgame.audiogames.entity.AudioGame;

import java.util.List;

public interface AudioGamesDao {
    List<AudioGame> getAllAudioGames();

    boolean addAudioGames(AudioGame audioGame);
}
