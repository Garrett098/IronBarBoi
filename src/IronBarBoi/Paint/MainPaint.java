package IronBarBoi.Paint;

import IronBarBoi.Main;
import org.rspeer.runetek.api.commons.StopWatch;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.event.types.ItemTableEvent;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MainPaint {
    private static int barsMade;
    private static StopWatch stopWatch;

    private static int smithExpStart;
    private static int smithExpGained;

    private static boolean showPaint;

    public static void Start(){
        stopWatch = StopWatch.start();
        smithExpStart = Skills.getExperience(Skill.SMITHING);

    }


    public static void notify(Graphics2D g) {
        int y = 35;
        int x = 10;
        //
        smithExpGained = Skills.getExperience(Skill.SMITHING) - smithExpStart;

        g.drawString(showPaint ? "Hide Paint" : "Show Paint", x, 330);
        if (showPaint) {
            g.drawString("[MyNamesGeph] IronBarBoi", x, y);
            g.drawString("Runtime: " + stopWatch.toElapsedString(), x, y + 20);
            g.drawString("RoF Charges: " + Main.getROFCharges(), x, y + 40);
            g.drawString("Bars made: " + barsMade, x, y + 60);
            g.drawString("Smithing Exp: " + smithExpGained, x, y + 80);
            g.drawRect(x - 2, y - 12, 155, 95);
        }

    }


    public static void notify1(ItemTableEvent itemTableEvent) {
        if (itemTableEvent.getChangeType() == ItemTableEvent.ChangeType.ITEM_ADDED) {
            if (itemTableEvent.getDefinition().getName().contains("Iron bar")) {
                barsMade += 1;
                Main.setROFCharges(Main.getROFCharges() - 1);
            }
        }
    }

    public static void notify(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            if (mouseEvent.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK){
                //Log.fine(mouseEvent.getPoint()); //debug
                if (mouseEvent.getX() > 10 && mouseEvent.getX() < 70){
                    if (mouseEvent.getY() > 320 && mouseEvent.getY() < 330){
                        showPaint = !showPaint;
                    }
                }
            }
        }

    }

    public static boolean isPaintVisable(){
        return showPaint;
    }
    public static void setShowPaint(boolean status){
        showPaint = status;
    }
}
