package edu.iastate.cs228.hw1;

/**
 *  
 * @author Ethan Kinneer (ekinneer@iastate.edu)
 *
 */

import java.io.*;
import java.sql.Array;
import java.util.Scanner;
import java.util.Random; 

/**
 * 
 * The plain is represented as a square grid of size width x width. 
 *
 */
public class Plain 
{
	private final int width; // grid size: width X width
	
	public Living[][] grid; 
	
	/**
	 *  Default constructor reads from a file
	 *  // Assumption: The input file is in correct format.
	 *
	 * 	You may create the grid plain in the following steps:
	 *
	 * 	1) Reads the first line to determine the width of the grid.
	 *
	 * 	2) Creates a grid object.
	 *
	 * 	3) Fills in the grid according to the input file.
	 *
	 * 	Be sure to close the input file when you are done.
	 */
	public Plain(String inputFileName) throws FileNotFoundException
	{
		File file = new File("./" + inputFileName);
		Scanner sc = new Scanner(file);
		String firstLine = sc.nextLine();
		this.width = firstLine.length() / 3;
		this.grid = new Living[width][width];
		Scanner whole = new Scanner(file);
		for (int i = 0; i < width; i++){
			for (int j = 0; j < width; j++){
				String next = whole.next();
				switch (next.charAt(0)){
					case 'B': grid[i][j] = new Badger(this, i, j, Character.getNumericValue(next.charAt(1)));
						break;
					case 'E': grid[i][j] = new Empty(this, i, j);
						break;
					case 'F': grid[i][j] = new Fox(this, i, j, Character.getNumericValue(next.charAt(1)));
						break;
					case 'G': grid[i][j] = new Grass(this, i, j);
						break;
					case 'R': grid[i][j] = new Rabbit(this, i, j, Character.getNumericValue(next.charAt(1)));
						break;
				}
			}
		}
		sc.close();
		whole.close();
	}
	
	/**
	 * Constructor that builds a w x w grid without initializing it. 
	 * @param w the grid
	 */
	public Plain(int w)
	{
		this.width = w;
		this.grid = new Living[width][width];
	}
	
	
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * Initialize the plain by randomly assigning to every square of the grid  
	 * one of BADGER, FOX, RABBIT, GRASS, or EMPTY.  
	 * 
	 * Every animal starts at age 0.
	 */
	public void randomInit()
	{
		Random generator = new Random(); 
		for (int i = 0; i < width; i++){
			for (int j = 0; j < width; j++){
				switch (generator.nextInt(5)){
					case 0: grid[i][j] = new Badger(this, i, j,0);
						break;
					case 1: grid[i][j] = new Empty(this, i, j);
						break;
					case 2: grid[i][j] = new Fox(this, i, j,0);
						break;
					case 3: grid[i][j] = new Grass(this, i, j);
						break;
					case 4: grid[i][j] = new Rabbit(this, i, j,0);
						break;
				}
			}
		}
	}
	
	
	/**
	 * Output the plain grid. For each square, output the first letter of the living form
	 * occupying the square. If the living form is an animal, then output the age of the animal 
	 * followed by a blank space; otherwise, output two blanks.  
	 */
	public String toString()
	{
		String output = "";
		for (int i = 0; i < width; i++){
			for (int j = 0; j < width; j++){
				switch (grid[i][j].who()){
					case BADGER:
						output += ("B");
						output += (((Animal) grid[i][j]).myAge());
						break;
					case EMPTY:
						output += ("E ");
						break;
					case FOX:
						output += ("F");
						output += (((Animal) grid[i][j]).myAge());
						break;
					case GRASS:
						output += ("G ");
						break;
					case RABBIT:
						output += ("R");
						output+= (((Animal) grid[i][j]).myAge());
						break;
				}
				output += (" ");
			}
			output += ("\n");
		}
		return output;
	}
	

	/**
	 * Write the plain grid to an output file.  Also useful for saving a randomly 
	 * generated plain for debugging purpose. 
	 * @throws FileNotFoundException
	 */
	public void write(String outputFileName) throws FileNotFoundException {
		try{
			File outputTxt = new File(outputFileName);
				FileWriter fWriter = new FileWriter(outputTxt);
				String output = "";
				for (int i = 0; i < width; i++){
					for (int j = 0; j < width; j++){
						switch (grid[i][j].who()){
							case BADGER:
								output += ("B");
								output += (((Animal) grid[i][j]).myAge());
								break;
							case EMPTY:
								output += ("E ");
								break;
							case FOX:
								output += ("F");
								output += (((Animal) grid[i][j]).myAge());
								break;
							case GRASS:
								output += ("G ");
								break;
							case RABBIT:
								output += ("R");
								output+= (((Animal) grid[i][j]).myAge());
								break;
						}
						output += (" ");
					}
					output += ("\n");
				}
				fWriter.write(output);
				fWriter.close();
				System.out.println("File created and wrote to successfully: " + outputTxt.getName());
		}catch (IOException e){
			System.out.println("Error occurred with creating file");
			e.printStackTrace();
		}
	}			
}
