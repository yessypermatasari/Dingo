package id.sch.smktelkom_mlg.privateassignment.xirpl134.dingo;

import java.io.Serializable;

/**
 * Created by Yessy Permatasari on 5/12/2017.
 */

public class SecondListDetail implements Serializable {
    private String poster;
    private String overview;
    private String terbit;
    private String judul;
    private String backdrop;


    public SecondListDetail(String judul, String terbit, String overview, String backdrop) {
        this.poster = poster;
        this.overview = overview;
        this.terbit = terbit;
        this.judul = judul;
        this.backdrop = backdrop;
    }

    public String getPoster() {
        return poster;
    }

    public String getOverview() {
        return overview;
    }

    public String getTerbit() {
        return terbit;
    }

    public String getJudul() {
        return judul;
    }

    public String getBackdrop() {
        return backdrop;
    }
}
