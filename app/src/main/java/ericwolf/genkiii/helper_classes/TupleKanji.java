package ericwolf.genkiii.helper_classes;

/**
 * Created by Eric on 11.08.2016.
 */
public class TupleKanji<on,kun,meaning, examples, description> {

    public String getOn() { return on; }

    public final String on;

    public String getKun() {
        return kun;
    }

    public final String kun;

    public String getMeaning() {
        return meaning;
    }

    public final String meaning;

    public String getDesc() {
        return description;
    }

    public final String description;

    public String getExamples() {
        return examples;
    }

    public final String examples;



    public TupleKanji(String on, String kun, String meaning, String examples, String description) {
        this.on = on;
        this.kun = kun;
        this.meaning = meaning;
        this.description = description;
        this.examples = examples;
    }

}
