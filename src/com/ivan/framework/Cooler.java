package com.ivan.framework;

public class Cooler {
    private VideoCard videoCard;
    public Cooler() {}

    public VideoCard getVideoCard() {
        return videoCard;
    }

    public void setVideoCard(VideoCard videoCard) {
        this.videoCard = videoCard;
    }

    @Override
    public String toString() {
        return "Cooler{" +
                "videoCard=" + videoCard.getCompany() +
                '}';
    }
}
