package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class TestActivity {

    public static final String TITLE = "Roller coaster";
    public static final String DESC = "Saw Maverick at Cedar Point";
    Activity activity;
    Image image;

    @BeforeEach
    void setup() {
        try {
            image = ImageIO.read(new File("./res/images/MaverickImg.jpg"));
        } catch (IOException e) {
            fail("Failed to read image");
        }
        activity = new Activity(TITLE, DESC, image);
    }

    @Test
    void testConstructor() {
        assertEquals(TITLE, activity.getTitle());
        assertEquals(DESC, activity.getDesc());
        assertEquals(image, activity.getImage());
    }

}
