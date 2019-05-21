package IronBarBoi.Nodes;

import IronBarBoi.Data.Funcs;
import IronBarBoi.Main;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.EquipmentSlot;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class GetNewRoF extends Task {
    @Override
    public boolean validate() {
        return Main.getROFCharges() <= 0 && !Equipment.isOccupied(EquipmentSlot.RING) ||
                Main.getROFCharges() <= 0 && !Equipment.isOccupied(EquipmentSlot.RING) && Bank.isOpen();
    }

    @Override
    public int execute() {
        Log.fine("-------GetNewRoF-------");

        if (!Bank.isOpen()){
            Bank.open();
            Time.sleepUntil(() -> Bank.isOpen(), 5000);
        }

        if (Bank.isOpen()){
            if (Inventory.isFull()){
                Bank.depositInventory();
                Time.sleepUntil(() -> Inventory.isEmpty(), 5000);
            }
            if (!Inventory.isFull()){
                if (Bank.contains(2568)){
                    Bank.withdraw(2568, 1);
                    Time.sleepUntil(() -> Inventory.contains(2568), 5000);
                    Funcs.miniSleep();
                } else {
                    Log.severe("Out of rings");
                    return -1;
                }
            }

            if (Inventory.contains(2568)){
                Bank.close();
                Time.sleepUntil(() -> Bank.isClosed(), 5000);
                Funcs.miniSleep();
            }
        }

        if (Inventory.contains(2568)){
            if (!Equipment.isOccupied(EquipmentSlot.RING)) {
                if (Inventory.getFirst(2568).click()) {
                    Log.info("Clicked RoF");
                    Time.sleepUntil(() -> Equipment.isOccupied(EquipmentSlot.RING), 5000);
                    Funcs.miniSleep();
                    Main.setROFCharges(140);
                }
            } else {
                Funcs.checkROFCharges();
            }
            Funcs.miniSleep();
        }
        return 300;
    }
}
