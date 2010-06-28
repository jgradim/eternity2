/* This file is part of Eternity II Editor.
 * 
 * fuck yeah seaking
 */

package org.alcibiade.eternity.editor.solver.genetic;

import java.util.Set;
import java.util.HashSet;
import java.util.Random;
import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.model.QuadModel;
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
			/* Get the two best individuals */		
			GridModel individualA = getMostFitIndividual();
			population.remove(individualA);
			GridModel individualB = getMostFitIndividual();
			population.add(individualA);

			/* Remove the two worst individuals */
			GridModel individualZ = getLeastFitIndividual();
			population.remove(individualZ);
			GridModel individualY = getLeastFitIndividual();
			population.remove(individualY);
			
			
			/* Show stuff on the g u i */
			/* Submit fittest for evaluation */
			//individualB.copyTo(problemGrid);
			individualA.copyTo(solutionGrid);
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
		
		Random generator = new Random();
		
		int cutPoint = generator.nextInt(totalElements);
		
		for (int i = 0; i < cutPoint; i++) {
			childA.setQuad(i, parentA.getQuad(i));
			childB.setQuad(i, parentB.getQuad(i));		
		}
		
		for (int i = cutPoint; i < totalElements; i++) {			
			childA.setQuad(i, parentB.getQuad(i));
			childB.setQuad(i, parentA.getQuad(i));		
		}
		
		int dice = generator.nextInt(100);
		
		if (dice > 95) {
			mutate(childA);
			mutate(childB);
		} else if (dice > 80) {
			mutate(childA);
		}
		
		childrenSet.add(childA);
		childrenSet.add(childB);
		
		return childrenSet;
	}
	
	public void mutate(GridModel individual) {
		Random generator = new Random();
		int quadIndex = generator.nextInt(individual.getPositions());
		QuadModel gene = individual.getQuad(quadIndex);
		
		gene.rotateClockwise();
	}

	public String getSolverName() {
		return "Dumb Genetic Solver";
	}
}

