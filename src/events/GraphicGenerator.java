package events;

import schedulingalgorithms.Run;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import processes.Process;

/**
 * tp5
 * Created by jeronimocarlos on 5/22/17.
 */
public class GraphicGenerator {

    public static void draw(List<Process> processes, List<Run> history){
        BufferedImage img = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();

        g.setColor(Color.white);
        g.fillRect(0,0,img.getWidth(),img.getHeight());

        int yString = 0;
        int yMargin = 20;
        int xMargin = 10;

        int clock = 100;

        g.setColor(Color.BLACK);
        for(Process process : processes){
            g.drawString("Process " + process.getId(), xMargin, yString + yMargin);
            yString += 40;
        }

        for(Run runnable : history){
            int index = processes.indexOf(runnable.getP());
            g.fillRect(clock + xMargin + 5, yMargin + 40 * index, runnable.getRunTime()*5, 2);
            clock += runnable.getRunTime()*5;
        }
        try {
            ImageIO.write(img, "png", new File("output.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
