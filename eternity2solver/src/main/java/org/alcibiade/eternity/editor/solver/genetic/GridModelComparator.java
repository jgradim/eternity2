package org.alcibiade.eternity.editor.solver.genetic;

// comparator class for population sorting

import java.util.Comparator;
import org.alcibiade.eternity.editor.model.GridModel;

public class GridModelComparator implements Comparator<GridModel> {

	@Override
	public int compare(GridModel a, GridModel b) {
		float fitnessA = GeneticSolver.fitness(a);
		float fitnessB = GeneticSolver.fitness(b);

		if(fitnessA < fitnessB) return 1;
		if(fitnessA > fitnessB) return -1;
		return 0;
	}
}