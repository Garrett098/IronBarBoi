package IronBarBoi.Data;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.EquipmentSlot;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import org.rspeer.ui.Log;

public class Funcs {

        public static void checkROFCharges(){
            if (!Tab.EQUIPMENT.isOpen()){
                Tab currentTab = Tabs.getOpen();
                Tabs.open(Tab.EQUIPMENT);
                Funcs.miniSleep();
                if (EquipmentSlot.RING.interact("Check")){
                    Log.info("Clicked check on the ring slot");
                }
                Tabs.open(currentTab);
            }
        }

        public static void miniSleep(){
            Time.sleep(Random.mid(350, 770));
        }

}
