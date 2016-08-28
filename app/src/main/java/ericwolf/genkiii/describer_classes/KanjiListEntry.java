package ericwolf.genkiii.describer_classes;

/**
 * Created by Eric on 11.08.2016.
 */
public class KanjiListEntry {




    public String getKanji() {
        return kanji;
    }

    public void setKanji(String kanji) {
        this.kanji = kanji;
    }

    private String kanji;

    public String getOnyomi() {
        return onyomi;
    }

    public void setOnyomi(String onyomi) {
        this.onyomi = onyomi;
    }

    private String onyomi;

    public String getKunyomi() {
        return kunyomi;
    }

    public void setKunyomi(String kunyomi) {
        this.kunyomi = kunyomi;
    }

    private String kunyomi;

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    private String meaning;



    public String getDescr() {
        return description;
    }

    public void setDescr(String description) {
        this.description = description;
    }

    private String description;



    public String getExamples() {
        return examples;
    }

    public void setExamples(String examples) {
        this.examples = examples;
    }

    private String examples;


}

