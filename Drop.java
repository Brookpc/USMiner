import java.util.ArrayList;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.wrappers.Item;


public class Drop implements Strategy {
	
	private final int[] PICK_IDS = {1266,1268,1270,1272,1276};

	@Override
	public boolean activate() {
		if (Inventory.isFull()) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		if (Inventory.isFull()) {
			try {
			Inventory.clear();
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void dropAllExcept(int[] PICK_IDS) {
		final ArrayList<Integer> dontDrop = new ArrayList<Integer>();
		if (Inventory.getCount(false) <= 2) {
			return;
		} else {
			for (int i : PICK_IDS) {
				dontDrop.add(i);
			}
		}
		for (Item inventoryItem : Inventory.getItems()) {
			if (!dontDrop.contains(inventoryItem.getId())) {
				Menu.sendAction(431, inventoryItem.getId() - 1,
						inventoryItem.getSlot(), 5064, 2213, 3);
				Time.sleep(80);
			}
		}
	}

}
