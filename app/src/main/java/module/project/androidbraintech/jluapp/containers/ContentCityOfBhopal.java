package module.project.androidbraintech.jluapp.containers;

import java.io.Serializable;

/**
 * Created by apple on 24/09/16.
 */

public class ContentCityOfBhopal {

    private int cob_id;
    private String cob_title;
    private String cob_text;
    private String cob_url;

    public ContentCityOfBhopal(int cob_id, String cob_title, String cob_text, String cob_url) {
        this.cob_id = cob_id;
        this.cob_title = cob_title;
        this.cob_text = cob_text;
        this.cob_url = cob_url;
    }

    public String getCob_url() {
        return cob_url;
    }



    public String getCob_text() {

        return cob_text;
    }



    public String getCob_title() {

        return cob_title;
    }




}
