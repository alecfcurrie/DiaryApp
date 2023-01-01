package model;

import java.awt.*;

public class Activity extends Entry{

    private Image  image;
    public Activity(String title, String desc, Image image) {
        super(title, desc);
        this.image = image;
    }

    public Image getImage() {
        return image;
    }
}
