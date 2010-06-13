/* This file is part of Eternity II Editor.
 * 
 * fuck yeah seaking
 */

package org.alcibiade.eternity.editor.solver.genetic;

import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.solver.ClusterManager;
import org.alcibiade.eternity.editor.solver.genetic.GeneticSolver;

public class DumbGeneticSolver extends GeneticSolver {

	
	public DumbGeneticSolver(GridModel grid, GridModel solutionGrid, ClusterManager clusterManager, int populationSize) {
		super(grid, solutionGrid, clusterManager, populationSize);
	}
	

	public String getSolverName() {
		return "Dumb Genetic Solver";
	}
}
