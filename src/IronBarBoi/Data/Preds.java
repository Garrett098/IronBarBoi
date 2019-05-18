package IronBarBoi.Data;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.adapter.scene.SceneObject;

import java.util.Arrays;
import java.util.function.Predicate;

public class Preds {

    public static final Predicate<Npc> hillgiant_PRED = x -> x.getName().equals("Hill Giant") &&
            Arrays.toString(x.getActions()).contains("Attack") &&
            x.getTargetIndex() == -1 &&
            x.getPosition().isPositionInteractable();

    public static final Predicate<Item> LOBSTER_PRED = x -> x.getName().contains("Lobster") && Arrays.toString(x.getActions()).contains("Eat");

    public static final Predicate<SceneObject> FURNACE_PRED = x -> x.getName().contains("Furnace") && x.getId() == 16469 && Arrays.toString(x.getActions()).contains("Smelt");
}
