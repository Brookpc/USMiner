import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.methods.Skill;
import org.rev317.min.api.wrappers.SceneObject;
import org.rev317.min.api.wrappers.Tile;


public class Mine implements Strategy {

	@Override
	public boolean activate() {
		if (!Inventory.isFull()) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		int curLvl;
		Tile startLoc = new Tile(2384,4721);
		if (startLoc.distanceTo() >= 6) {	
		startLoc.walkTo();
		Time.sleep(5000);
		}
		
		SceneObject rock = null;
		int rockActCode = 0;
		curLvl = Skill.MINING.getRealLevel();

		if( curLvl < 10 ) {
			rock = SceneObjects.getClosest(2090);
			rockActCode = 2090;
			
		} // Copper
		if( curLvl >= 10 && curLvl < 30 ) {
			rock = SceneObjects.getClosest(2093);
			rockActCode = 2093;
			
		} // iron
		if( curLvl >= 30 && curLvl < 40 ) {
			rock = SceneObjects.getClosest(2096);
			rockActCode = 2096;
			
		}// Coal
		if( curLvl >= 40 && curLvl < 55 ) {
			rock = SceneObjects.getClosest(2098);
			rockActCode = 2098;
			
		}// Gold
		if( curLvl >= 55 && curLvl < 70 ) {
			rock = SceneObjects.getClosest(2102);
			rockActCode = 2102;
		
		}// Mithril
		if( curLvl >= 70 && curLvl < 85 ) {
			rock = SceneObjects.getClosest(2104);
			rockActCode = 2104;
			
		}
		if( curLvl >= 85) {
			rock = SceneObjects.getClosest(2106);
			rockActCode = 2106;
			
			
		}// Rune

		
		if (rock != null && Players.getMyPlayer().getAnimation() == -1) {
			Menu.sendAction(502, rock.getHash(), rock.getLocalRegionX(),
					rock.getLocalRegionY(),rockActCode, 4);
			Time.sleep(2000);
		}
		
	}

}
