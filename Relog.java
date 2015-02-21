
import java.awt.Point;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;

public class Relog implements Strategy {
	public boolean activate() {
		
		if (USPMiner.isLoggedIn()) {
			return false;
		}
		return true;
	}

	public void execute() {
		Point login = new Point(452,280);

		Mouse.getInstance().click(login);
		Time.sleep(new SleepCondition() {
			@Override
			public boolean isValid() {
				return USPMiner.isLoggedIn();
			}
		}, 6000);
	}
}