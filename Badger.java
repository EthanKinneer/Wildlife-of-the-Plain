package edu.iastate.cs228.hw1;

/**
 *  
 * @author Ethan Kinneer (ekinneer@iastate.edu)
 *
 */

/**
 * A badger eats a rabbit and competes against a fox. 
 */
public class Badger extends Animal
{
	/**
	 * Constructor 
	 * @param p: plain
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Badger (Plain p, int r, int c, int a) 
	{
		plain = p;
		row = r;
		column = c;
		age = a;
	}
	
	/**
	 * A badger occupies the square. 	 
	 */
	public State who()
	{
		return State.BADGER;
	}
	
	/**
	 * A badger dies of old age or hunger, or from isolation and attack by a group of foxes. 
	 * @param pNew     plain of the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(Plain pNew)
	{
		int[] population = new int[5];
		this.census(population);
		if (age >= BADGER_MAX_AGE){
			return new Empty(pNew, row, column);
		} else if (population[BADGER] == 1 && population[FOX] > 1) {
			return new Fox(pNew, row, column, 0);
		} else if ((population[BADGER] + population[FOX]) > population[RABBIT]){
			return new Empty(pNew, row, column);
		} else {
			age++;
			return new Badger(pNew, row, column, age);
		}
	}
}
