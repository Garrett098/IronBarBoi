package IronBarBoi.Paint;

import IronBarBoi.Data.Funcs;
import IronBarBoi.Main;
import org.rspeer.runetek.api.commons.StopWatch;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.event.types.ItemTableEvent;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.script.Script;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

public class MainPaint {
    public static int barsMade;
    public static StopWatch stopWatch;
    public static BufferedImage image;

    public static RenderEvent re;
    static int sc_Counter = 0;

     public static int smithExpStart;
    //
    static int smithExpGained;

    public static void Start(){
        stopWatch = StopWatch.start();
        smithExpStart = Skills.getExperience(Skill.SMITHING);

    }


    public static void notify(Graphics2D g) {
        int y = 35;
        int x = 10;
        //
        smithExpGained = Skills.getExperience(Skill.SMITHING) - smithExpStart;





        g.drawString("[MyNamesGeph] IronBarBoi", x, y);
        g.drawString("Runtime: " + stopWatch.toElapsedString(), x, y += 20);
        g.drawString("RoF Charges: " + Main.ROFCharges, x, y += 20);
        g.drawString("Bars made: " + barsMade, x, y += 20);
        g.drawString("Smithing Exp: " + smithExpGained, x, y += 20);

    }


    public static void notify1(ItemTableEvent itemTableEvent) {
        if (itemTableEvent.getChangeType() == ItemTableEvent.ChangeType.ITEM_ADDED) {
            if (itemTableEvent.getDefinition().getName().contains("Iron bar")) {
                barsMade += 1;
                Main.ROFCharges -= 1;
            }
        }
    }

}
