import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.methods.Skill;

@ScriptManifest(author = "Ark",
category = Category.MINING, 
description = "Ultimate Scape 2 - Power Miner",
name = "USPMiner by Ark",
servers = { "Ultimate Scape" },
version = 1.0)

public class USPMiner extends Script implements Paintable {

	private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();

	public long startTime;

	private final Color color1 = new Color(100,255,0);
	private final Font font2 = new Font( "Arial", 0, 14 );

	public static int startExp;
	public static int expGained;

	public static Image img1;
	/********************************************************************************************************************************************/

	public boolean onExecute() {
		img1 = getImage( "http://i.imgur.com/0vKVzVq.png" );
		startTime = System.currentTimeMillis();
		startExp = Skill.MINING.getExperience();

		strategies.add(new Relog());
		strategies.add(new Tele());
		strategies.add(new Mine());
		strategies.add(new Bank());

		provide(strategies);

		return true;
	}
	/***************************************************************************************************************************************/
	public static Image getImage( String url )
	{
		try {
			return ImageIO.read( new URL( url ) );
		} catch( IOException e ) {
			return null;
		}
	}
	/***************************************************************************************************************************************/

	/********************************************************************************************************************************************/
	@Override
	public void paint(Graphics arg0) {
		final int expGained = Skill.MINING.getExperience() - startExp;

		Graphics2D g = (Graphics2D) arg0;
		g.drawImage(img1, 4, 23, null);
		g.setFont(font2);
		g.setColor(color1);
		g.drawString(addDecimals(expGained), 91, 62);
		g.drawString(runTime(startTime), 91,75);


	}

	public String addDecimals(int i)
	{
		DecimalFormat x = new DecimalFormat("#,###");


		return "" + x.format(i);
	}
	/********************************************************************************************************************************************/

	public static boolean isLoggedIn() {
		return SceneObjects.getNearest().length > 0;
	}

	/********************************************************************************************************************************************/
	public static String runTime(long i) {

		DecimalFormat nf = new DecimalFormat("00");
		long millis = System.currentTimeMillis() - i;
		long hours = millis / (1000 * 60 * 60);
		millis -= hours * (1000 * 60 * 60);
		long minutes = millis / (1000 * 60);
		millis -= minutes * (1000 * 60);
		long seconds = millis / 1000;

		return nf.format(hours) + ":" + nf.format(minutes) + ":"
		+ nf.format(seconds);
	}


}
