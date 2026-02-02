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

		GameLogic gl1 = new GameLogicBit(gridWidth, gridLength, snakeLength);

		int[] dimensions = {10,100,1000,10000};
		int[] snakeLengths = {3,30,300,3000};
		int scenarios = 3;

		System.out.println("Benchmarking set implementation");
		for (int i=0; i<scenarios; i++)
		{
			GameSimulation.scenario(new GameLogicSet(dimensions[i], dimensions[i], snakeLengths[i]));
			System.gc();
		}

		System.out.println("Benchmarking list implementation");
		for (int i=0; i<scenarios; i++)
		{
			GameSimulation.scenario(new GameLogicList(dimensions[i], dimensions[i], snakeLengths[i]));
			System.gc();
		}

		System.out.println("Benchmarking bit implementation");
		for (int i=0; i<scenarios; i++)
		{
			GameSimulation.scenario(new GameLogicBit(dimensions[i], dimensions[i], snakeLengths[i]));
			System.gc();
		}




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
