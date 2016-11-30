package module.project.androidbraintech.jluapp.containers;

import java.io.Serializable;

/**
 * Created by Devesh 1 on 20-08-2016.
 */
public class ContentContactList {

    private String name;
    private String pos;
    String num;
    private int img;

    public ContentContactList(String name,String des,String num){
        this.name=name;
        this.pos=des;
        this.num=num;

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }
    public String getPos() {

        return pos;
    }
    public void setPos(String pos) {

        this.pos = pos;
    }
    public int getImg() {

        return img;
    }
    public void setImg(int img) {

        this.img = img;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}





