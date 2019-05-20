package IronBarBoi.Paint;

import IronBarBoi.Main;
import org.rspeer.runetek.api.commons.StopWatch;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.event.types.ItemTableEvent;

import java.awt.*;

public class MainPaint {
    private static int barsMade;
    private static StopWatch stopWatch;

    private static int smithExpStart;
    private static int smithExpGained;

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
        g.drawRect(x - 2, y - 92, 155, 95);

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
