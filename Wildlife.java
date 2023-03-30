package edu.iastate.cs228.hw1;

import java.io.FileNotFoundException;
import java.util.Scanner; 

/**
 *  
 * @author Ethan Kinneer (ekinneer@iastate.edu)
 *
 */

/**
 * 
 * The Wildlife class performs a simulation of a grid plain with
 * squares inhabited by badgers, foxes, rabbits, grass, or none. 
 *
 */
public class Wildlife 
{
	/**
	 * Update the new plain from the old plain in one cycle. 
	 * @param pOld  old plain
	 * @param pNew  new plain 
	 */
	public static void updatePlain(Plain pOld, Plain pNew)
	{
		// TODO 
		// 
		// For every life form (i.e., a Living object) in the grid pOld, generate  
		// a Living object in the grid pNew at the corresponding location such that 
		// the former life form changes into the latter life form. 
		// 
		// Employ the method next() of the Living class.
		int width = pOld.getWidth();

		for (int i = 0; i < width; i++){
			for (int j = 0; j < width; j++){
				 pNew.grid[i][j] = pOld.grid[i][j].next(pNew);
			}
		}

	}
	
	/**
	 * Repeatedly generates plains either randomly or from reading files. 
	 * Over each plain, carries out an input number of cycles of evolution. 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Simulation of Wildlife of the Plain\n" +
				"keys: 1 (random plain) 2 (file input) 3 (exit)");
		int input = sc.nextInt();
		while (input != 3) {
			if (input == 1) {
				System.out.println("Random plain selected");
				System.out.print("Enter grid width: ");
				int randomWidth = sc.nextInt();
				System.out.println("Enter number of cycles: ");
				int randomCycles = sc.nextInt();
				Plain randomPlain = new Plain(randomWidth);
				randomPlain.randomInit();
				System.out.println("Initial Plain: ");
				System.out.println(randomPlain);
				System.out.println("Final Plain: ");
				Plain bah = new Plain(randomWidth);
				for (int i = 0; i < randomCycles; i++){
					updatePlain(randomPlain, bah);
					randomPlain = bah;
				}

			} else if (input == 2) {
				System.out.println("File input selected");
				System.out.print("Enter file: ");
				String fileName = sc.next();
				System.out.println("Enter number of cycles: ");
				int fileCycles = sc.nextInt();
				Plain fPlain = new Plain(fileName);
				Plain ffPlain = new Plain(fPlain.getWidth());
				System.out.println("Initial Plain: ");
				System.out.println(fPlain);
				for (int j = 0; j < fileCycles; j++) {
					updatePlain(fPlain, ffPlain);
					fPlain = ffPlain;
				}
				System.out.println("Final Plain: ");
				System.out.println(ffPlain);
			}
			else {
				System.out.println("Please enter a correct menu option");
			}
			System.out.println("Simulation of Wildlife of the Plain\n" +
					"keys: 1 (random plain) 2 (file input) 3 (exit)");
			input = sc.nextInt();
		}
	}
}
