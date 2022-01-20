package businesslogic.disponibility;
import businesslogic.shift.Turn;

import businesslogic.shift.TurnKitchen;

import java.util.ArrayList;

public class Cook implements Employee {
    public boolean isAvaible(ArrayList<Turn> turnList){
        return true;
    }
}
