/* This file is part of Eternity II Editor.
 * 
 * fuck yeah seaking
 */

package org.alcibiade.eternity.editor.solver.genetic;

import java.util.Random;
import java.util.ArrayList;
import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.model.QuadModel;
import org.alcibiade.eternity.editor.solver.ClusterManager;

public class DumbGeneticSolver extends GeneticSolver {

	private Random generator;
	private int mutations;		// number of mutations, defaults to gridModel.size() - 1
	
	public DumbGeneticSolver(GridModel grid, GridModel solutionGrid, ClusterManager clusterManager, int populationSize) {
		super(grid, solutionGrid, clusterManager, populationSize);
		this.generator = new Random();
		this.mutations = grid.getSize() - 1;
	}

	public void setMutations(int s) { mutations = s; };
	
	@Override
	public void run() {

		notifyStart();
		clusterManager.showStartMessage();

		boolean solved = clusterManager.submitSolution(solutionGrid);

		while (!solved && !interrupted) {

			//System.out.printf("Iteration %d\n", iterations++);
			// Get the two best individuals
			GridModel individualA = getMostFitIndividual();
			population.remove(individualA);
			GridModel individualB = getMostFitIndividual();
			population.add(individualA);

			// Remove the two worst individuals
			GridModel individualZ = getLeastFitIndividual();
			population.remove(individualZ);
			GridModel individualY = getLeastFitIndividual();
			population.remove(individualY);

			// Show stuff on the g u i
			// Submit fittest for evaluation
			//individualB.copyTo(problemGrid);
			individualA.copyTo(solutionGrid);
			solved = clusterManager.submitSolution(individualA);

			if (!solved) {
				ArrayList<GridModel> children = crossover(individualA, individualB);
				population.addAll(children);
			}

			if (slowmotion) {
				try {
					Thread.sleep(SLOWMOTION_DELAY);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		if (solved) {
			clusterManager.showStats(iterations);
		}

		notifyEnd(solved);
	}
	

	private ArrayList<GridModel> crossover(GridModel parentA, GridModel parentB) {
		ArrayList<GridModel> aFeatures = parentA.getFeatures();
		ArrayList<GridModel> bFeatures = parentB.getFeatures();
		
		//child A inherits the best feature from A with the best compatible feature (if it exists) from B
		GridModel childA = originalGrid.getCompatibleFeatures(aFeatures, bFeatures);
		//vice versa
		GridModel childB = originalGrid.getCompatibleFeatures(bFeatures, aFeatures);
		
		// remaining pieces for childA and childB
		GridModel remainingA = originalGrid.remainingPieces(childA);
		GridModel remainingB = originalGrid.remainingPieces(childB);
		
		//Fill the rest of child A with the remaning pieces of the original board
		childA.completeWith(remainingA);
		childB.completeWith(remainingB);

		ArrayList<GridModel> children = new ArrayList<GridModel>();
		children.add(childA);
		children.add(childB);

		return children;
	}

	public void mutate(GridModel individual) {
		for(int i = 0; i < mutations; i++) {
			int quadIndex = generator.nextInt(individual.getPositions());
			individual.optimizeQuadRotation(quadIndex);
		}
	}

	/*
	 * Dumbass Crossover
	 */
//	private HashSet<GridModel> crossover(GridModel parentA, GridModel parentB) {
//		HashSet<GridModel> childrenSet = new HashSet<GridModel>();

//		GridModel childA = parentA.clone();
//		GridModel childB = parentB.clone();		
//		
//		int totalElements = childA.getQuads().size();
//		
//		Random generator = new Random();
//		
//		int cutPoint = generator.nextInt(totalElements);
//		
//		for (int i = 0; i < cutPoint; i++) {
//			childA.setQuad(i, parentA.getQuad(i));
//			childB.setQuad(i, parentB.getQuad(i));		
//		}
//		
//		for (int i = cutPoint; i < totalElements; i++) {			
//			childA.setQuad(i, parentB.getQuad(i));
//			childB.setQuad(i, parentA.getQuad(i));		
//		}
//		
//		int dice = generator.nextInt(100);
//		
//		if (dice > 95) {
//			mutate(childA);
//			mutate(childB);
//		} else if (dice > 80) {
//			mutate(childA);
//		}
//		
//		childrenSet.add(childA);
//		childrenSet.add(childB);
//		
//		return childrenSet;
//	}

	@Override
	public String getSolverName() {
		return "Dumb Genetic Solver";
	}
}

