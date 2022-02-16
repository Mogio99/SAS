package businesslogic.shift;

import persistence.PersistenceManager;
import persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TurnKitchen extends Turn {
    boolean saturation=false;
    public void setSaturation(boolean val) {
        saturation = val;
    }
    public boolean isSaturated() {
        return saturation;
    }

    public void setSaturated(boolean saturated) {
        this.saturation = saturated;
    }

    public static TurnKitchen loadKitchenTurnById(int id) {
        String query = "SELECT * FROM turn WHERE turn_id=" + id + ";";
        TurnKitchen kt = new TurnKitchen();
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                if (rs.getString("type").charAt(0) == 'k') {
                    kt.setStartDate(rs.getDate("start_date"));
                    kt.setEndDate(rs.getDate("end_date"));
                    kt.setSaturated(rs.getBoolean("saturation"));
                    kt.setId(id);
                }

            }
        });
        return kt;
    }


    public static void saveKitchenTurnSat(TurnKitchen kt) {
        String query = "UPDATE catering.Turns SET saturation = " + (kt.isSaturated() ? 1 : 0) + " WHERE id=" + kt.getId() + ";";

        PersistenceManager.executeUpdate(query);
    }

}
