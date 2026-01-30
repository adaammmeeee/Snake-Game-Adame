package snippet;
import javax.swing.JFrame;
import java.awt.Color;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame obj = new JFrame();

		int gridWidth = 30;
		int gridLength = 20;
		int snakeLength = 3;

		GameLogic gl = new GameLogicBit(gridWidth, gridLength, snakeLength);

		GameSimulation.scenario1(gl);


		Gameplay gameplay = new Gameplay(gridWidth, gridLength);

		obj.setBounds(0,0,gameplay.getWidth(),gameplay.getHeight());
		obj.setBackground(Color.DARK_GRAY);;
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gameplay);
	}

}
