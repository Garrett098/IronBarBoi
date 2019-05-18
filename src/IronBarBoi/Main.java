package IronBarBoi;

import IronBarBoi.Data.ChattyBoi;
import IronBarBoi.Data.Data;
import IronBarBoi.Data.Funcs;
import IronBarBoi.GUI.DebugGUI;
import IronBarBoi.Nodes.*;
import IronBarBoi.Paint.MainPaint;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.event.listeners.ChatMessageListener;
import org.rspeer.runetek.event.listeners.ItemTableListener;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.types.ChatMessageEvent;
import org.rspeer.runetek.event.types.ItemTableEvent;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.Task;
import org.rspeer.script.task.TaskScript;

import java.awt.*;


@ScriptMeta(developer = "MyNamesGeph", name = "IronBarBoi", desc = "Makes iron bars in Edgeville", version = 0.01,  category = ScriptCategory.SMITHING)
public class Main extends TaskScript implements RenderListener, ItemTableListener, ChatMessageListener {

    private Task[] TASKS = {new SmeltBars(), new Banking(), new GetNewRoF()};
    DebugGUI GUI = new DebugGUI();
    public static int ROFCharges = 0;
    int sc_Counter = 0;


    @Override
    public void onStart() {
        //GUI.setVisible(true);
        submit(TASKS);
        MainPaint.Start();
        Funcs.checkROFCharges();

    }


    @Override
    public void notify(RenderEvent renderEvent) {
        Graphics g = renderEvent.getSource();
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //
        MainPaint.notify(g2);
        MainPaint.re = renderEvent;


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



}
