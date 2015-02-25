
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.methods.Skill;

@ScriptManifest(author = "Ark",
category = Category.MINING, 
description = "Ultimate Scape 2 - Power Miner",
name = "USPMiner by Ark",
servers = { "Ultimate Scape" },
version = 2.0)

public class USPMiner extends Script implements Paintable {

	private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();

	public long startTime;

	private final Color color1 = new Color(100,255,0);
	private final Font font2 = new Font( "Arial", 0, 14 );

	public static int startExp;
	public static int expGained;

	public static Image img1;
	
	//GUI
	Gui x = new Gui();
	public boolean guiWait = true;
	public static String banks = "banks";
	public static String drops = "drops";
	public static String does = "banks"; //default
	/********************************************************************************************************************************************/

	public boolean onExecute() {
		x.setVisible(true);
		while (guiWait) {
			Time.sleep(200);
		}

		if (does.equals("banks")) {
			strategies.add(new Relog());
			strategies.add(new Tele());
			strategies.add(new Mine());
			strategies.add(new Bank());
		}
		else if(does.equals("drops")) {
			strategies.add(new Relog());
			strategies.add(new Tele());
			strategies.add(new Mine());
			strategies.add(new Drop());
		}

		img1 = getImage( "http://i.imgur.com/VWLlWBw.png" );
		startTime = System.currentTimeMillis();
		startExp = Skill.MINING.getExperience();

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
		if (guiWait) {
			Time.sleep(200);
		}else {
		g.drawImage(img1, 4, 23, null);
		g.setFont(font2);
		g.setColor(color1);
		g.drawString(addDecimals(expGained), 87, 62);
		g.drawString(runTime(startTime), 87,77);
		}

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

	@SuppressWarnings("serial")
	public class Gui extends JFrame {

		private JPanel contentPane;

		public void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Gui frame = new Gui();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		public Gui() {
			initComponents();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 150, 180);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			lblUSMiner = new JLabel("USMiner");
			lblUSMiner.setFont(new Font("Arial", Font.PLAIN, 20));
			lblUSMiner.setBounds(15, 11, 101, 16);
			contentPane.add(lblUSMiner);

			//Ready Label
			lblReady = new JLabel("Ready?");
			lblReady.setBounds(17, 49, 82, 14);
			contentPane.add(lblReady);

			//Setup radio buttons
			Banks.setActionCommand(banks);
			Banks.setSelected(true);
			Drops.setActionCommand(drops);
			Drops.setSelected(false);

			//Group the radio buttons.
			ButtonGroup group = new ButtonGroup();
			group.add(Banks);
			group.add(Drops);

			//Register listeners for the radio buttons.
			Banks.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					does = "banks";
				}
			});
			Drops.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					does = "drops";
				}
			});

			Drops.setBounds(10, 50, 95, 23);
			Banks.setBounds(10, 75, 95, 23);
			contentPane.add(Drops);
			contentPane.add(Banks);



			//Start Button
			btnStart = new JButton("Start");
			btnStart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					guiWait= false;
					x.dispose();
				}
			});
			btnStart.setBounds(10, 112, 95, 23);
			contentPane.add(btnStart);
		}
		private void initComponents() {
			lblUSMiner = new JLabel();
			lblReady = new JLabel();
			Banks = new JRadioButton("Banks");
			Drops = new JRadioButton("Drops");


		}
		private JLabel lblUSMiner;
		private JButton btnStart;
		private JRadioButton Banks;
		private JRadioButton Drops;
		private JLabel lblReady;

	}
}
