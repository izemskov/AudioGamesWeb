package ru.develgame.audiogames.entity;

import javax.persistence.*;

@Entity
@Table(name = "AUDIOGAME_SAVE", schema = "APP")
public class AudioGameSave {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "AUDIOGAME_ID")
    private int audioGameId;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "CHAPTER_NUM")
    private String chapterNum;

    @Column(name = "AUTOPLAY")
    private boolean autoplay;

    public AudioGameSave() {
    }

    public AudioGameSave(int audioGameId, String username, String chapterNum) {
        this.audioGameId = audioGameId;
        this.username = username;
        this.chapterNum = chapterNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAudioGameId() {
        return audioGameId;
    }

    public void setAudioGameId(int audioGameId) {
        this.audioGameId = audioGameId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getChapterNum() {
        return chapterNum;
    }

    public void setChapterNum(String chapterNum) {
        this.chapterNum = chapterNum;
    }

    public boolean isAutoplay() {
        return autoplay;
    }

    public void setAutoplay(boolean autoplay) {
        this.autoplay = autoplay;
    }
}
