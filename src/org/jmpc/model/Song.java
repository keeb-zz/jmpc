package org.jmpc.model;

public class Song {

    private String file = "unknown";
    private String time = "unknown";
    private String artist = "unknown";
    private String album = "unknown";
    private String track = "unknown";
    private String date = "unknown";
    private String genre = "unknown";
    private String composer = "unknown";

    private String title;

    public Song(String mpd) {
        System.out.println(mpd);
        String[] split = mpd.split("\n");

        for (String detail : split) {
            if (detail.contains("file:")) {
                this.setFile(detail);
            } else if (detail.contains("Time:")) {
                this.setTime(detail);
            } else if (detail.contains("Artist:")) {
                this.setArtist(detail);
            } else if (detail.contains("Title:")) {
                this.setTitle(detail);
            } else if (detail.contains("Album:")) {
                this.setTitle(detail);
            } else if (detail.contains("Track:")) {
                this.setTrack(detail);
            } else if (detail.contains("Date")) {
                this.setDate(detail);
            } else if (detail.contains("Genre")) {
                this.setGenre(detail);
            } else if (detail.contains("Composer")) {
                this.setComposer(detail);
            }
        }
        
    }


    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
