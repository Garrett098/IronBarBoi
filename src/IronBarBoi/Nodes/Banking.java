package IronBarBoi.Nodes;

import IronBarBoi.Data.Data;
import IronBarBoi.Data.Funcs;
import IronBarBoi.Main;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class Banking extends Task {
    @Override
    public boolean validate() {
        return Main.ROFCharges > 0 && !
                Inventory.contains("Iron ore") || Bank.isOpen();
    }

    @Override
    public int execute() {
        Log.fine("-----Banking------");
        if (Data.BANK_AREA.contains(Players.getLocal())) {
            if (Bank.isOpen()) {
                if (Inventory.containsAll("Iron bar")) {
                    Bank.depositInventory();
                    Time.sleepUntil(() -> Inventory.isEmpty(), 3000);
                    Funcs.miniSleep();
                }
                if (Inventory.isEmpty() && Bank.withdrawAll("Iron ore")) {
                    Time.sleepUntil(() -> Inventory.containsAll("Iron ore"), 5000);
                    Funcs.miniSleep();

                } else {
                    Log.severe("We're out of iron ore"); return -1;
                }
                if (Inventory.containsAll("Iron ore")) {
                    Bank.close();
                    Time.sleepUntil(() -> Bank.isClosed(), 5000);
                    Funcs.miniSleep();
                }

            } else {
                Log.info("Opening bank");
                Bank.open();
                Time.sleepUntil(() -> Bank.isOpen(), 8000);
                Funcs.miniSleep();
            }
        } else {
            Movement.walkToRandomized(Data.BANK_AREA.getCenter());
            Time.sleepUntil(() -> Movement.getDestinationDistance() < Random.mid(2,4), 3000);
            Funcs.miniSleep();
        }
        return 300;
    }
}
