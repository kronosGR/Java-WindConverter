package me.kandz.WindConverter;

/**
 * Created by kronos on 26.02.18.
 */

public class Beaufort {

    private int beaufort;
    private int picture;
    private String desc;
    private String knots;
    private String ms;
    private String kmh;
    private String mph;
    private String land;
    private String sea;

    /**
     *
     * @param beaufort
     * @param picture
     * @param desc
     * @param knots
     * @param ms
     * @param kmh
     * @param mph
     * @param land
     * @param sea
     */

    public Beaufort(int beaufort, int picture, String desc, String knots, String ms, String kmh, String mph, String land, String sea) {
        this.beaufort = beaufort;
        this.picture = picture;
        this.desc = desc;
        this.knots = knots;
        this.ms = ms;
        this.kmh = kmh;
        this.mph = mph;
        this.land = land;
        this.sea = sea;
    }

    public int getBeaufort() {
        return beaufort;
    }

    public void setBeaufort(int beaufort) {
        this.beaufort = beaufort;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getKnots() {
        return knots;
    }

    public void setKnots(String knots) {
        this.knots = knots;
    }

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    public String getKmh() {
        return kmh;
    }

    public void setKmh(String kmh) {
        this.kmh = kmh;
    }

    public String getMph() {
        return mph;
    }

    public void setMph(String mph) {
        this.mph = mph;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getSea() {
        return sea;
    }

    public void setSea(String sea) {
        this.sea = sea;
    }
}
