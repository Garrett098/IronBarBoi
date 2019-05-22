package IronBarBoi.Nodes;

import IronBarBoi.Data.Data;
import IronBarBoi.Data.Funcs;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;

public class SmeltBars extends Task {

    @Override
    public boolean validate() {
        return Inventory.contains("Iron ore");
    }

    @Override
    public int execute() {
        //Log.fine("-----SmeltBars-----"); //debug
        if (isSmithing()){
            //Log.info("Already smelting bars");
            Time.sleepUntil(() -> !isSmithing(), 45000);
            Funcs.miniSleep();
        } else {
            SceneObject furnace = SceneObjects.getNearest(Data.FURNACE_PRED);
            if (furnace.interact("Smelt")){
                //Log.info("Clicked on furnace");
                if (Time.sleepUntil(() -> Interfaces.isVisible(270,15), 5000)){
                    Funcs.miniSleep();
                    //Log.info("Clicked iron bar creation");
                    Interfaces.getComponent(270,15).click();
                    Time.sleep(1200,2400);
                }
            }
        }


        return Random.mid(200, 450);
    }


    public static boolean isSmithing(){
        boolean wait1 = Players.getLocal().isAnimating();
        Time.sleep(650);
        boolean wait2 = Players.getLocal().isAnimating();
        return (wait1 || wait2);
    }
}
