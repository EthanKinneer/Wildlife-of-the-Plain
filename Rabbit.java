package edu.iastate.cs228.hw1;

/**
 *  
 * @author Ethan Kinneer (ekinneer@iastate.edu)
 *
 */

/*
 * A rabbit eats grass and lives no more than three years.
 */
public class Rabbit extends Animal {
	/**
	 * Creates a Rabbit object.
	 *
	 * @param p: plain
	 * @param r: row position
	 * @param c: column position
	 * @param a: age
	 */

	public Rabbit(Plain p, int r, int c, int a) {
		plain = p;
		row = r;
		column = c;
		age = a;

	}

	// Rabbit occupies the square.
	public State who() {
		return State.RABBIT;
	}

	/**
	 * A rabbit dies of old age (lives no more than 3 years) or hunger. It may also be eaten by a badger or a fox.
	 *
	 * @param pNew plain of the next cycle
	 * @return Living  new life form occupying the same square
	 */
	public Living next(Plain pNew) {
		int[] population = new int[5];
		this.census(population);

		if (age >= RABBIT_MAX_AGE){
			return new Empty(pNew, row, column);
		} else if (population[GRASS] == 0) {
			return new Empty(pNew, row, column);
		} else if ((population[FOX] + population[BADGER]) >= population[RABBIT] && population[FOX] > population[BADGER]){
			return new Fox(pNew, row, column,0);
		} else if(population[BADGER] > population[RABBIT]) {
			return new Badger(pNew, row, column, 0);
		}else {
			age++;
			return new Rabbit(pNew, row, column, age);
		}
	}
}
