package snippet;
import javax.swing.JFrame;
import java.awt.Color;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
//		JFrame obj = new JFrame();
//
//		int gridWidth = 30;
//		int gridLength = 20;
//		int snakeLength = 3;

//		GameLogic gl1 = new GameLogicSet(gridWidth, gridLength, snakeLength);

		GameSimulation gs2 = new GameSimulation(1);
		gs2.scenario();

		GameSimulation gs3 = new GameSimulation(2);
		gs3.scenario();

		GameSimulation gs1 = new GameSimulation(0);
		gs1.scenario();


//		Gameplay gameplay = new Gameplay(gridWidth, gridLength, gl1);
//
//		obj.setBounds(0,0,gameplay.getWidth(),gameplay.getHeight());
//		obj.setBackground(Color.DARK_GRAY);;
//		obj.setResizable(false);
//		obj.setVisible(true);
//		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		obj.add(gameplay);
	}

}
