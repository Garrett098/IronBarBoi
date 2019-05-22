package IronBarBoi.Data;

import IronBarBoi.Main;
import IronBarBoi.Paint.MainPaint;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.event.types.ChatMessageEvent;
import org.rspeer.runetek.event.types.SkillEvent;
import org.rspeer.ui.Log;

public class ChattyBoi {


    public static void notify(ChatMessageEvent chatMessageEvent) {
        String msg1;
        //Checks for RoF charges in chat
        if (chatMessageEvent.getMessage().contains("You can smelt")) {
            msg1 = chatMessageEvent.getMessage();
            msg1 = msg1.replaceAll("[^0-9]", "");
            //msg1 = msg1.strip();
            Main.setROFCharges(Integer.parseInt(msg1));

        }

        //Screenshot on level up
        //If the paint isn't showing before the screenshot, then show it
        if (chatMessageEvent.getMessage().contains("Congratulations, you've")){
            if (!MainPaint.isPaintVisable()){
                MainPaint.setShowPaint(true);
                if (Funcs.takeScreenshot(String.format("%s Smithing", Skills.getCurrentLevel(Skill.SMITHING)))){
                    Log.fine("Took screenshot!");
                }
            } else {
                if (Funcs.takeScreenshot(String.format("%s Smithing", Skills.getCurrentLevel(Skill.SMITHING)))){
                    Log.fine("Took screenshot!");
                }
            }
        }
    }

    public static void notify(SkillEvent skillevent){

    }



}
