/* This file is part of Eternity II Editor.
 *
 * fuck yeah seaking
 */

package org.alcibiade.eternity.editor.solver.genetic;

import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.solver.ClusterManager;

public class RouletteGeneticSolver extends GeneticSolver {

	private Random randomGenerator;
	private int mutations;		// number of mutations, defaults to gridModel.size() - 1
	private GridModelComparator gridModelComparator;

	public RouletteGeneticSolver(GridModel grid, GridModel solutionGrid, ClusterManager clusterManager, int populationSize) {
		super(grid, solutionGrid, clusterManager, populationSize);
		this.randomGenerator = new Random();
		this.mutations = grid.getSize() - 1;
		this.gridModelComparator = new GridModelComparator();
	}

	public void setMutations(int s) { mutations = s; };

	// solve
	@Override
	public void run() {

		notifyStart();
		clusterManager.showStartMessage();
		GridModel solution = null;

		boolean solved = false;
		while (!solved && !interrupted) {

			// select
			ArrayList<GridModel> breeders = rouletteWheelSelection();
			GridModel bestGrid = breeders.get(0);

			// check for correct board
			if (bestGrid.countPairs() > clusterManager.getBestScore())
				bestGrid.copyTo(problemGrid);
			solved = clusterManager.submitSolution(bestGrid);


			if(solved) {
				solution = bestGrid;
				break;
			}
			// FIXME: should not be necessary
			mutate(bestGrid);

			// breed
			this.population = breed(breeders);

			if (slowmotion) {
				try {
					Thread.sleep(SLOWMOTION_DELAY);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			bestGrid.copyTo(solutionGrid);

			iterations++;
		}

		if (solved) {
			solution.copyTo(solutionGrid);
			clusterManager.showStats(iterations);
		}

		notifyEnd(solved);
	}

	// roulette-wheel selection
	private ArrayList<GridModel> rouletteWheelSelection() {
		ArrayList<GridModel> selection = new ArrayList<GridModel>();
		ArrayList<GridModel> pop_clone = (ArrayList<GridModel>)population.clone();

		Collections.sort(pop_clone, gridModelComparator);
		Collections.reverse(pop_clone);

		float totalFitness = 0;
		for(GridModel g : population)
			totalFitness += GeneticSolver.fitness(g);

		while(selection.size() < population.size() / 2) {
			float die = randomGenerator.nextFloat();
			for (int i = 0; i < pop_clone.size(); i++) {
				float p = GeneticSolver.fitness(pop_clone.get(i)) / totalFitness;
				if(die <= p) {
					selection.add(pop_clone.get(i));
					break;
				}
			}
		}
		return selection;
	}

	private ArrayList<GridModel> breed(ArrayList<GridModel> breeders) {
		ArrayList<GridModel> newPopulation = (ArrayList<GridModel>)breeders.clone();
		int s = breeders.size();
		for(int i = 0; i < s/2; i++) {
			int ra = randomGenerator.nextInt(s);
			int rb = randomGenerator.nextInt(s);
			newPopulation.addAll(crossover(breeders.get(ra), breeders.get(rb)));
		}
		return newPopulation;
	}

	//
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

		// introduce mutations
		mutate(childA);
		mutate(childB);

		ArrayList<GridModel> children = new ArrayList<GridModel>();
		children.add(childA);
		children.add(childB);

		return children;
	}

	public void mutate(GridModel individual) {
		for(int i = 0; i < mutations; i++) {

			// probablity check, 50%
			if(randomGenerator.nextInt(100) < 50) continue;

			// mutate
			int quadIndex = randomGenerator.nextInt(individual.getPositions());
			//individual.optimizeQuadRotation(quadIndex);
			individual.getQuad(quadIndex).rotateClockwise();
		}
	}

	@Override
	public String getSolverName() {
		return "Roulette Wheel Selection Genetic Solver";
	}
}

