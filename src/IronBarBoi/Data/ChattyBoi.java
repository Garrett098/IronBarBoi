package IronBarBoi.Data;

import IronBarBoi.Main;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.event.types.ChatMessageEvent;
import org.rspeer.runetek.event.types.SkillEvent;
import org.rspeer.ui.Log;

public class ChattyBoi {


    public static void notify(ChatMessageEvent chatMessageEvent) {
        if (chatMessageEvent.getMessage().contains("You can smelt")) {
            String msg = chatMessageEvent.getMessage();
            msg = msg.replaceAll("[^0-9]", "");
            msg = msg.strip();
            Main.ROFCharges = Integer.parseInt(msg);

        }
    }

    public static void notify(SkillEvent skillevent){
        if (skillevent.getType() == 1){
            if (Funcs.takeScreenshot(String.format("%s Smithing", Skills.getCurrentLevel(Skill.SMITHING)))){
                Log.fine("Took screenshot!");
            }
        }
    }



}
