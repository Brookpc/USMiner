import java.awt.Point;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.wrappers.Tile;

public class Tele implements Strategy 
{

	@Override
	public boolean activate() {
		int distance;
		distance = (int) new Tile(2382, 4741).distanceTo();
		if (distance > 50) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		//Points to click in the game
		Point spellBook = new Point(743, 186);
		Point skillZone = new Point(641, 288);
		Point nextMenu = new Point(230, 447);
		Point Mining = new Point(265,415);
		Point inventory = new Point(659,187);


		Mouse.getInstance().click(spellBook);
		Time.sleep(300);
		Mouse.getInstance().click(skillZone);
		Time.sleep(300);
		Mouse.getInstance().click(nextMenu);
		Time.sleep(3000); //Sleep while menu changes
		Mouse.getInstance().click(Mining);
		Time.sleep(3000); //Sleep while animation
		Mouse.getInstance().click(inventory);
		Time.sleep(300);
	}
}