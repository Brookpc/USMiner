

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.wrappers.Npc;
import org.rev317.min.api.wrappers.Tile;



public class Bank implements Strategy{

	private static final int[] ITEM_IDS = {437,439,441,443,454,445,448,450,452};

	@Override
	public boolean activate() {
		if(Inventory.isFull()) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		Npc[] banker = Npcs.getNearest(953);
		Tile bankLoc = new Tile(2397,4716);
		if (bankLoc.distanceTo() > 10) {
		bankLoc.walkTo();
		}
		if (Game.getOpenInterfaceId() != 5292 && Inventory.isFull()) {
			if (banker != null) {
				Menu.sendAction(20,536,0,0,541,3);
				Time.sleep(new SleepCondition() {
					@Override
					public boolean isValid() {                       
						return Game.getOpenInterfaceId() == 5292;
					}

				}, 2000);
			}
		}
		if (Game.getOpenInterfaceId() == 5292 && Inventory.isFull()) {
			if(ITEM_IDS != null) {
				for (final int item : ITEM_IDS) {
					try {
						Menu.sendAction(431, item - 1, 1, 5064, 542, 3);
						Time.sleep(200);

					} catch(Exception e) {
						System.out.println(e.getMessage());
					}      
				}

				Menu.sendAction(200,0,5384,6,5698,1);
			}
		}
	}

}
