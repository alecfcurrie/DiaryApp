package model;

import java.awt.*;

public class EventEntry extends Entry{

    private Image  image;
    public EventEntry(String title, String desc, Image image) {
        super(title, desc);
        this.image = image;
    }

    public Image getImage() {
        return image;
    }
}
