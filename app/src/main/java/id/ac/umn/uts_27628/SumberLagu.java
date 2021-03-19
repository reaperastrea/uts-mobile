package id.ac.umn.uts_27628;

import java.io.Serializable;

public class SumberLagu implements Serializable {
    private String judul;
    private String artis;
    private String uri;
    public SumberLagu(String judul, String artis, String uri){
        this.judul = judul;
        this.artis = artis;
        this.uri = uri;
    }

    public String getJudul(){ return this.judul;}
    public String getArtis(){ return this.artis;}
    public String getUri(){ return this.uri;}

    public void setJudul(String judul){ this.judul = judul;}
    public void setArtis(String artis){ this.artis = artis;}
    public void setUri(String uri){ this.uri = uri;}
    public String toString(){ return this.getJudul();}
}
