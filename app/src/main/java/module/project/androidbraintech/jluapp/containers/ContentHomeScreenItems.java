package module.project.androidbraintech.jluapp.containers;

import android.graphics.drawable.Drawable;

/**
 * Created by Tushar-PC on 19-08-2016.
 */
public class ContentHomeScreenItems {

    String text;
    int id;
    int image;

   public ContentHomeScreenItems(int id, String text, int image){
        this.id=id;
        this.text=text;
        this.image=image;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getImage() {
        return image;
    }
}
