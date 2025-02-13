package ru.develgame.audiogames.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "AUDIOGAME_CHAPTER", schema = "APP")
public class AudioGameChapter {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "AUDIOGAME_ID")
    private int audioGameId;

    @Column(name = "CHAPTER_NUM")
    private String chapterNum;

    @Column(name = "CHAPTER_LINK")
    private String chapterLink;

    @Column(name = "CHAPTER_NAME")
    private String chapterName;

    @Column(name = "CHAPTER_TEXT")
    private String chapterText;

    public AudioGameChapter() {
    }

    public AudioGameChapter(int audioGameId, String chapterNum, String chapterLink, String chapterName, String chapterText) {
        this.audioGameId = audioGameId;
        this.chapterNum = chapterNum;
        this.chapterLink = chapterLink;
        this.chapterName = chapterName;
        this.chapterText = chapterText;
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

    public String getChapterNum() {
        return chapterNum;
    }

    public void setChapterNum(String chapterNum) {
        this.chapterNum = chapterNum;
    }

    public String getChapterLink() {
        return chapterLink;
    }

    public void setChapterLink(String chapterLink) {
        this.chapterLink = chapterLink;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapterText() {
        return chapterText;
    }

    public void setChapterText(String chapterText) {
        this.chapterText = chapterText;
    }
}
