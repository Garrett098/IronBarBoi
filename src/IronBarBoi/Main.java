package IronBarBoi;

import IronBarBoi.Data.ChattyBoi;
import IronBarBoi.Data.Data;
import IronBarBoi.Data.Funcs;
import IronBarBoi.GUI.DebugGUI;
import IronBarBoi.Nodes.Banking;
import IronBarBoi.Nodes.GetNewRoF;
import IronBarBoi.Nodes.SmeltBars;
import IronBarBoi.Paint.MainPaint;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.event.listeners.ChatMessageListener;
import org.rspeer.runetek.event.listeners.ItemTableListener;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.listeners.SkillListener;
import org.rspeer.runetek.event.types.ChatMessageEvent;
import org.rspeer.runetek.event.types.ItemTableEvent;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.runetek.event.types.SkillEvent;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.Task;
import org.rspeer.script.task.TaskScript;
import org.rspeer.ui.Log;

import java.awt.*;


@ScriptMeta(developer = "MyNamesGeph", name = "IronBarBoi", desc = "Makes iron bars in Edgeville", version = 0.02,  category = ScriptCategory.SMITHING)
public class Main extends TaskScript implements RenderListener, ItemTableListener, ChatMessageListener, SkillListener {

    private Task[] TASKS = {new SmeltBars(), new Banking(), new GetNewRoF()};
    DebugGUI GUI = new DebugGUI();
    public static int ROFCharges = 0;


    @Override
    public void onStart() {
        //GUI.setVisible(true);
        submit(TASKS);
        MainPaint.Start();
        if (Funcs.checkROFCharges()){
            if (ROFCharges < 0){
                Funcs.checkROFCharges();
                Log.info("Checked charges a second time");
            }
        }

    }


    @Override
    public void notify(RenderEvent renderEvent) {
        Graphics g = renderEvent.getSource();
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //
        MainPaint.notify(g2);
        Funcs.re = renderEvent;


        //

    }

    @Override
    public void notify(ItemTableEvent itemTableEvent) {
        if (!Data.BANK_AREA.contains(Players.getLocal())) {
            MainPaint.notify1(itemTableEvent);
        }
    }

    @Override
    public void notify(ChatMessageEvent chatMessageEvent) {
        ChattyBoi.notify(chatMessageEvent);


    }


    @Override
    public void notify(SkillEvent skillEvent) {
        ChattyBoi.notify(skillEvent);

    }
}
