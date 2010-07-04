/* This file is part of Eternity II Editor.
 * 
 * fuck yeah seaking
 */

package org.alcibiade.eternity.editor.solver.genetic;

import java.util.ArrayList;
import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.solver.ClusterManager;
import org.alcibiade.eternity.editor.solver.EternitySolver;

public abstract class GeneticSolver extends EternitySolver {

	protected GridModel problemGrid;
	protected GridModel solutionGrid;
	protected final GridModel originalGrid;
	protected ArrayList<GridModel> population;
	protected long iterations;
	
	public GeneticSolver(GridModel grid, GridModel solutionGrid, ClusterManager clusterManager, int populationSize) {
		super(clusterManager);	
		
		this.problemGrid = grid;
		this.solutionGrid = solutionGrid;
		problemGrid.copyTo(solutionGrid);
		this.originalGrid = grid.clone();
		originalGrid.setReadOnly(true);

		this.population = new ArrayList<GridModel>();
		
		populate(populationSize, grid);
	}
	
	private void populate(int populationSize, GridModel theGrid) {
		while (population.size() < populationSize) {
			GridModel somegrid = theGrid.clone();
			somegrid.shuffle();
			population.add(somegrid);
		}
	}
	
	/**
	 * returns the fitness of a grid (0..1)
	 */
	 
//	public static float fitness(GridModel grid) {
//		
//		float connections = grid.countConnections() + grid.borderlineConnections() ;
//		float pairs = grid.countPairs() + grid.borderlinePairs();
//		float fitness = pairs / connections;
//	

//		assert (fitness >= 0) && (fitness <= 1);
//		return fitness;
//	}

	public static float fitness(GridModel grid) {
		
		float middleWeight = 0.4f;
		float borderWeight = 0.3f;
		float cornerWeight = 0.3f;
		
		float middle = grid.countPairs() / grid.countConnections();
		float corner = grid.cornerPairs() / grid.cornerConnections();
		float border = grid.borderPairs() / grid.borderConnections();
	
		System.out.printf("Middle: %d / %d\n", grid.countPairs(), grid.countConnections());
		System.out.printf("Corner: %d / %d\n", grid.cornerPairs(), grid.cornerConnections());
		System.out.printf("Border: %d / %d\n", grid.borderPairs(), grid.borderConnections());				
			
		float fitness = 
			middleWeight * middle + 
			borderWeight * border +
			cornerWeight * corner;
	

		assert (fitness >= 0) && (fitness <= 1);
		return fitness;
	}
	@Override
	public long getIterations() {
		return iterations;
	}
}
