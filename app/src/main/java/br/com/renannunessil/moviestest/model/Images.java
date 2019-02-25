package br.com.renannunessil.moviestest.model;

import com.google.gson.annotations.SerializedName;

public class Images {

    @SerializedName("medium")
    private String mediumImage;
    @SerializedName("original")
    private String originalImage;

    public Images() {
    }

    public String getMediumImage() {
        return mediumImage;
    }

    public void setMediumImage(String mediumImage) {
        this.mediumImage = mediumImage;
    }

    public String getOriginalImage() {
        return originalImage;
    }

    public void setOriginalImage(String originalImage) {
        this.originalImage = originalImage;
    }
}
