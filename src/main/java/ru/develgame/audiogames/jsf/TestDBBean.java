package ru.develgame.audiogames.jsf;

import org.primefaces.shaded.commons.io.IOUtils;
import ru.develgame.audiogames.dao.AudioGameChapterDao;
import ru.develgame.audiogames.dao.AudioGameDao;
import ru.develgame.audiogames.entity.AudioGame;
import ru.develgame.audiogames.entity.AudioGameChapter;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ViewScoped
@Named("testdb")
public class TestDBBean implements Serializable {
    @Inject
    private transient AudioGameDao audioGameDao;

    @Inject
    private transient AudioGameChapterDao audioGameChapterDao;

    public void addAudioGame() {
        String uuid = UUID.randomUUID().toString();
        audioGameDao.addAudioGame(new AudioGame("name " + uuid, "folder " + uuid));
    }

    public List<AudioGame> getAudioGames() {
        return new ArrayList<>(audioGameDao.getAllAudioGames());
    }

    public void parser1() throws IOException {
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        try (InputStream inputStream = servletContext.getResourceAsStream("/resources/in.txt")) {
            String[] lines = IOUtils.toString(inputStream, StandardCharsets.UTF_8).split("\n");

            Pattern chapterNumPattern = Pattern.compile("<ul><a NAME=\\\"(\\d+)\\\"></a>", Pattern.CASE_INSENSITIVE);
            Pattern chapterNamePattern = Pattern.compile("(\\d+)</h2>", Pattern.CASE_INSENSITIVE);
            Pattern ignorePattern = Pattern.compile("<h2>", Pattern.CASE_INSENSITIVE);
            Pattern ignorePattern2 = Pattern.compile("</ul>", Pattern.CASE_INSENSITIVE);
            Pattern linkPattern = Pattern.compile("<a href=\\\"#(\\d+)\\\">(\\d+)</a>");

            AudioGameChapter audioGameChapter = null;
            for (String line : lines) {
                line = line.trim();

                Matcher matcher = chapterNumPattern.matcher(line);
                if (matcher.matches()) {
                    if (audioGameChapter != null) {
                        audioGameChapter.setAudioGameId(1);
                        if (audioGameChapter.getChapterName() == null || audioGameChapter.getChapterName().isEmpty()
                                || audioGameChapter.getChapterNum() == null || audioGameChapter.getChapterNum().isEmpty()
                                || audioGameChapter.getChapterText() == null || audioGameChapter.getChapterText().isEmpty()) {
                            throw new RuntimeException();
                        }

                        audioGameChapterDao.addAudioGameChapter(audioGameChapter);
                    }

                    audioGameChapter = new AudioGameChapter();
                    audioGameChapter.setChapterNum(matcher.group(1));
                    continue;
                }

                matcher = chapterNamePattern.matcher(line);
                if (matcher.matches()) {
                    audioGameChapter.setChapterName(matcher.group(1));
                    continue;
                }

                matcher = ignorePattern.matcher(line);
                if (matcher.matches()) {
                    continue;
                }

                matcher = ignorePattern2.matcher(line);
                if (matcher.matches()) {
                    continue;
                }

                matcher = linkPattern.matcher(line);
                while (matcher.find()) {
                    String link = matcher.group(1);
                    String chapterLink = audioGameChapter.getChapterLink();
                    if (chapterLink != null) {
                        chapterLink += "," + link;
                    }
                    else {
                        chapterLink = link;
                    }
                    audioGameChapter.setChapterLink(chapterLink);
                }

                line = line.replaceAll("<a href=\\\"#\\d+\\\">(\\d+)</a>", "$1");
                String chapterText = audioGameChapter.getChapterText();
                if (chapterText != null) {
                    chapterText += "<br/>" + line;
                }
                else {
                    chapterText = line;
                }
                audioGameChapter.setChapterText(chapterText);
            }
        }
    }

    public void parser2() throws IOException {
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        try (InputStream inputStream = servletContext.getResourceAsStream("/resources/in1.txt")) {
            String[] lines = IOUtils.toString(inputStream, StandardCharsets.UTF_8).split("\n");

            Pattern newChapterPattern = Pattern.compile("<a name=\\\".*?\\\"></a>(.*?)<br></h1>", Pattern.CASE_INSENSITIVE);
            Pattern chapterLinkPattern = Pattern.compile("<i>.*?(\\d+).*?</i>", Pattern.CASE_INSENSITIVE);

            AudioGameChapter audioGameChapter = null;
            for (String line : lines) {
                line = line.trim();

                Matcher matcher = newChapterPattern.matcher(line);
                if (matcher.matches()) {
                    if (audioGameChapter != null) {
                        if (audioGameChapter.getChapterName() == null || audioGameChapter.getChapterName().isEmpty()
                                || audioGameChapter.getChapterNum() == null || audioGameChapter.getChapterNum().isEmpty()
                                || audioGameChapter.getChapterText() == null || audioGameChapter.getChapterText().isEmpty()) {
                            throw new RuntimeException();
                        }

                        audioGameChapterDao.addAudioGameChapter(audioGameChapter);
                    }

                    audioGameChapter = new AudioGameChapter();
                    audioGameChapter.setAudioGameId(101);
                    audioGameChapter.setChapterName(matcher.group(1)
                            .replaceAll("<i>", "")
                            .replaceAll("</i>", ""));
                    audioGameChapter.setChapterNum(audioGameChapter.getChapterName());

                } else if (audioGameChapter != null) {
                    line = line.replaceAll("<div>", "")
                            .replaceAll("</div>", "")
                            .replaceAll("<blockquote>", "")
                            .replaceAll("</blockquote>", "")
                            .replaceAll("<br>", "<br/>")
                            .replaceAll("<h5>", "")
                            .replaceAll("</h5>", "")
                            .replaceAll("<h1.*?>", "");

                    matcher = chapterLinkPattern.matcher(line);
                    while (matcher.find()) {
                        String link = matcher.group(1);
                        String chapterLink = audioGameChapter.getChapterLink();
                        if (chapterLink != null) {
                            chapterLink += "," + link;
                        }
                        else {
                            chapterLink = link;
                        }
                        audioGameChapter.setChapterLink(chapterLink);
                    }

                    line = line.replaceAll("<i>", "")
                            .replaceAll("</i>", "");

                    String chapterText = audioGameChapter.getChapterText();
                    if (chapterText != null) {
                        chapterText += "<br/>" + line;
                    }
                    else {
                        chapterText = line;
                    }
                    audioGameChapter.setChapterText(chapterText);
                }
            }
        }
    }
}
