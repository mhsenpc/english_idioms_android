package sibpardazan.gharb.englishidioms;

import java.io.Serializable;

public class Idiom implements Serializable {
    private int id;
    private String englishPhrase;
    private String example;
    private String persianTranslation;
    private String persianDescription;
    private boolean isBookmarked;

    public Idiom(int id, String englishPhrase, String example, String persianTranslation, String persianDescription) {
        this.id = id;
        this.englishPhrase = englishPhrase;
        this.example = example;
        this.persianTranslation = persianTranslation;
        this.persianDescription = persianDescription;
        this.isBookmarked = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnglishPhrase() {
        return englishPhrase;
    }

    public void setEnglishPhrase(String englishPhrase) {
        this.englishPhrase = englishPhrase;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getPersianTranslation() {
        return persianTranslation;
    }

    public void setPersianTranslation(String persianTranslation) {
        this.persianTranslation = persianTranslation;
    }

    public String getPersianDescription() {
        return persianDescription;
    }

    public void setPersianDescription(String persianDescription) {
        this.persianDescription = persianDescription;
    }

    public boolean isBookmarked() {
        return isBookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        isBookmarked = bookmarked;
    }

    @Override
    public String toString() {
        return englishPhrase;
    }
}