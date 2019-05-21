package IronBarBoi.Data;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.movement.position.Area;

import java.util.Arrays;
import java.util.function.Predicate;

public class Data {

    public static double DISTANCE_ZERO = 2.147483647E9;
    public static final Area BANK_AREA = Area.rectangular(3099, 3500, 3093, 3493);

    public static final Predicate<SceneObject> FURNACE_PRED = x -> x.getName().contains("Furnace") && x.getId() == 16469 && Arrays.toString(x.getActions()).contains("Smelt");

}
