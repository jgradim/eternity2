/* This file is part of Eternity II Editor.
 * 
 * fuck yeah seaking
 */

package org.alcibiade.eternity.editor.solver.genetic;

import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.solver.ClusterManager;
import org.alcibiade.eternity.editor.solver.genetic.GeneticSolver;

public class DumbGeneticSolver extends GeneticSolver {

	
	public DumbGeneticSolver(GridModel grid, GridModel solutionGrid,
		ClusterManager clusterManager, int populationSize) {
		
		super(grid, solutionGrid, clusterManager, populationSize);
	}
	
	public void run() {
		notifyStart();
		clusterManager.showStartMessage();
		
		GridModel individualA = getMostFitIndividual();
		GridModel individualB = getMostFitIndividual();
		
		Set<GridModel> children = crossing(individualA, individualB, 2);
		
		
		notifyEnd(true);
	}
	

	private void crossing(GridModel parentA, gridModel parentB, int children) {
		
	
	
	}

	public String getSolverName() {
		return "Dumb Genetic Solver";
	}
}
