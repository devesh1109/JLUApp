package module.project.androidbraintech.jluapp.containers;

import java.io.Serializable;

/**
 * Created by Devesh 1 on 20-08-2016.
 */
public class ContentWhyJlu implements Serializable {

    private int wj_id;
    private String wj_title;
    private String wj_text;
    private String wj_url;

    public ContentWhyJlu(int wj_id, String wj_title, String wj_text, String wj_url) {
        this.wj_id = wj_id;
        this.wj_title = wj_title;
        this.wj_text = wj_text;
        this.wj_url = wj_url;
    }

    public int getWj_id() {
        return wj_id;
    }

    public String getWj_title() {
        return wj_title;
    }

    public String getWj_text() {
        return wj_text;
    }

    public String getWj_url() {
        return wj_url;
    }
}





