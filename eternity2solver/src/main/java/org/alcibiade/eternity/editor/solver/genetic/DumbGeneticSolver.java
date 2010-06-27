/* This file is part of Eternity II Editor.
 * 
 * fuck yeah seaking
 */

package org.alcibiade.eternity.editor.solver.genetic;

import java.util.Set;
import java.util.HashSet;
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
		
		boolean finished = false;
		
		do {
			//System.out.printf("Iteration %d\n", iterations++);
			GridModel individualA = takeMostFitIndividual();
			GridModel individualB = takeMostFitIndividual();
			
			//submit most fit individual for evaluation
			
			individualA.copyTo(problemGrid);
			individualB.copyTo(solutionGrid);
			clusterManager.submitSolution(individualA);
			
			if (clusterManager.isSolutionFound() == false) {
				Set<GridModel> children = crossover(individualA, individualB);
				population.addAll(children);
			}
			
		} while (!finished);
		
		notifyEnd(true);
	}
	

	private HashSet<GridModel> crossover(GridModel parentA, GridModel parentB) {
		HashSet<GridModel> childrenSet = new HashSet<GridModel>();

		GridModel childA = parentA.clone();
		GridModel childB = parentB.clone();		
		
		int totalElements = childA.getQuads().size();
		int cutPoint = totalElements / 2;
		
		for (int i = 0; i < cutPoint; i++) {
			childA.setQuad(i, parentA.getQuad(i));
			childB.setQuad(i, parentB.getQuad(i));		
		}
		
		for (int i = cutPoint; i < totalElements; i++) {			
			childA.setQuad(i, parentB.getQuad(i));
			childB.setQuad(i, parentA.getQuad(i));		
		}

		
		childrenSet.add(childA);
		childrenSet.add(childB);
		
		return childrenSet;
	}
	
	

	public String getSolverName() {
		return "Dumb Genetic Solver";
	}
}
