/* This file is part of Eternity II Editor.
 * 
 * fuck yeah seaking
 */

package org.alcibiade.eternity.editor.solver.genetic;

import java.util.HashSet;
import java.util.Set;
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
		
		//hashset ok but GridModel does not implement comparabel or equals.
		this.population = new HashSet<GridModel>();
		
		populate(populationSize);
	}
	
	private void populate(int elements) {
		int width = problemGrid.getSize();
		
		
		while (population.size() < elements) {
			GridModel somegrid = new GridModel()
			population.add(somegrid);
		}
	}
	
	public long getIterations() {
		return iterations;
	}
	
	
}
