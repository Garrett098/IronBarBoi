package IronBarBoi.Data;

import org.rspeer.RSPeer;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.EquipmentSlot;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.script.Script;
import org.rspeer.ui.Log;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Funcs {

    public static RenderEvent re;

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
            if (EquipmentSlot.RING.interact("Check")){
                Log.info("Clicked check on the ring slot");
            }
        }

        public static void miniSleep(){
            Time.sleep(Random.mid(350, 770));
        }

        public static boolean takeScreenshot(String fileName){
            Image img = re.getProvider().getImage();
            BufferedImage bi = (BufferedImage) img;
            Path path = Script.getDataDirectory();
            File f = new File(path + String.format("\\IronBarBoi\\%s\\%s.png", RSPeer.getGameAccount().getUsername(), fileName));
            f.mkdirs();
            try {
                ImageIO.write(bi, "bmp", f);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    }
