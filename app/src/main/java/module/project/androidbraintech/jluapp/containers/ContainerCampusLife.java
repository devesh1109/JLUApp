package module.project.androidbraintech.jluapp.containers;

/**
 * Created by apple on 22/08/16.
 */

public class ContainerCampusLife {
    private String pic_title, pic_text, imgUrl;
    private int id;

    public ContainerCampusLife(){

    }

    public ContainerCampusLife(int id,String pic_title , String pic_text, String img) {

        this.id = id;
        this.pic_title = pic_title;
        this.pic_text = pic_text;
        this.imgUrl = img;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPic_text() {

        return pic_text;
    }

    public void setPic_text(String pic_text) {
        this.pic_text = pic_text;
    }

    public String getPic_title() {

        return pic_title;
    }

    public void setPic_title(String pic_title) {
        this.pic_title = pic_title;
    }
}