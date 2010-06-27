/* This file is part of Eternity II Editor.
 * 
 * fuck yeah seaking
 */

package org.alcibiade.eternity.editor.solver.genetic;

import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.solver.ClusterManager;
import org.alcibiade.eternity.editor.solver.EternitySolver;

public abstract class GeneticSolver extends EternitySolver {

	protected GridModel problemGrid;
	protected GridModel solutionGrid;
	protected Set<GridModel> population;
	protected long iterations;
	
	public GeneticSolver(GridModel grid, GridModel solutionGrid, ClusterManager clusterManager, int populationSize) {
		super(clusterManager);	
		
		this.problemGrid = grid;
		this.solutionGrid = solutionGrid;
		///System.out.println(grid.toQuadString());
		
		//hashset ok but GridModel does not implement comparabel or equals.
		this.population = new HashSet<GridModel>();
		
		populate(populationSize, grid);
	}
	
	private void populate(int populationSize, GridModel theGrid) {
	
		//fitness(theGrid);


	
		while (population.size() < populationSize) {
			GridModel somegrid = theGrid.clone();
			somegrid.shuffle();
			population.add(somegrid);
			
//			System.out.println("---");
//			System.out.println(somegrid.toQuadString());
//			System.out.println("---");			
			
		}
	
	}
	
	/**
	 * returns the fitness of a grid (0..1)
	 */
	public float fitness(GridModel grid) {
		
//		System.out.println("--------- Fitness for --------------");
//		
//		System.out.println(grid.toQuadString());
		
		float connections = grid.countConnections();
		float pairs = grid.countPairs();
		float fitness = pairs / connections;
//		System.out.printf("Conn: %.0f\tPairs: %.0f\tFitness: %f\n", connections, pairs, fitness);
//		System.out.println("------------------------------------");

		return fitness;
	}
	
	/**
	 * returns the most fit individual
	 */
	public GridModel takeMostFitIndividual() {
		
		assert this.population.size() > 0;
		
		Iterator it = this.population.iterator();
		GridModel bestIndividual = null;
		float bestFitness = 0;
		
		while (it.hasNext()) {
		
			GridModel thisIndividual = (GridModel)it.next();
			float thisFitness = fitness(thisIndividual);
			
			if (thisFitness >= bestFitness) {
				bestIndividual = thisIndividual;
				bestFitness = thisFitness;
			}
		};
		
		this.population.remove(bestIndividual);
		return bestIndividual;
	}
	
	public long getIterations() {
		return iterations;
	}
	
	
}
