package ru.develgame.audiogames.exception;

public class AudioGameSaveNotFoundException extends RuntimeException {
    public AudioGameSaveNotFoundException(String message) {
        super(message);
    }
}
