package com.ivan.framework;

public class Computer {
    private Hdd hdd;
    private MatherBoard matherBoard;
    private VideoCard videoCard;
    private SoftWare softWare;
    private Cooler cooler;

    public Computer() {}

    public Hdd getHdd() {
        return hdd;
    }

    public void setHdd(Hdd hdd) {
        this.hdd = hdd;
    }

    public MatherBoard getMatherBoard() {
        return matherBoard;
    }

    public void setMatherBoard(MatherBoard matherBoard) {
        this.matherBoard = matherBoard;
    }

    public VideoCard getVideoCard() {
        return videoCard;
    }

    public void setVideoCard(VideoCard videoCard) {
        this.videoCard = videoCard;
    }

    public SoftWare getSoftWare() {
        return softWare;
    }

    public void setSoftWare(SoftWare softWare) {
        this.softWare = softWare;
    }

    public Cooler getCooler() {
        return cooler;
    }

    public void setCooler(Cooler cooler) {
        this.cooler = cooler;
    }

    @Override
    public String toString() {
        return "Computer{" + "\n" +
                "hdd=" + hdd + "\n" +
                ", matherBoard=" + matherBoard + "\n" +
                ", videoCard=" + videoCard + "\n" +
                ", softWare=" + softWare + "\n" +
                ", cooler=" + cooler + "\n" +
                '}';
    }
}
