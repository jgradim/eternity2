/* This file is part of Eternity II Editor.
 * 
 * Eternity II Editor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Eternity II Editor is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Eternity II Editor.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Eternity II Editor project is hosted on SourceForge:
 * http://sourceforge.net/projects/eternityii/
 * and maintained by Yannick Kirschhoffer <alcibiade@alcibiade.org>
 */

package org.alcibiade.eternity.editor.solver.swap;

import java.util.Random;

import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.solver.ClusterManager;
import org.alcibiade.eternity.editor.solver.EternitySolver;
import org.alcibiade.eternity.editor.solver.RandomFactory;

public abstract class SwappingSolver extends EternitySolver {

	private GridModel problemGrid;
	private GridModel solutionGrid;

	private long iterationsLimit = -1;
	private WeightMatrix weights;

	protected long iterations = 0;
	protected Random rand;

	public SwappingSolver(GridModel grid, GridModel solutionGrid, ClusterManager clusterManager) {
		super(clusterManager);
		this.problemGrid = grid;
		this.solutionGrid = solutionGrid;
		this.rand = RandomFactory.getRandom();
		problemGrid.copyTo(solutionGrid);
		weights = new WeightMatrix(grid.getSize());
	}

	@Override
	public long getIterations() {
		return iterations;
	}

	public void setIterationsLimit(long limit) {
		iterationsLimit = limit;
	}

	@Override
	public void run() {
		notifyStart();
		clusterManager.showStartMessage();

		boolean solved = clusterManager.submitSolution(solutionGrid);

		while (!solved && !interrupted && (iterationsLimit == -1 || iterations < iterationsLimit)) {
			solve(solutionGrid);

			solved = clusterManager.submitSolution(solutionGrid);

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

	private void solve(GridModel grid) {
		computeWeights(grid, weights);
		int coordA = computeSwapCoord(grid, weights);
		adaptWeights(grid, weights, coordA);
		int coordB = computeSwapCoord(grid, weights);

		SwapCoords coords = new SwapCoords(coordA, coordB);
		if (validateSwap(grid, coords)) {
			grid.swap(coords.getCoordA(), coords.getCoordB());
			grid.optimizeQuadRotation(coords.getCoordA());
			grid.optimizeQuadRotation(coords.getCoordB());
			iterations++;
		}
	}

	protected abstract void computeWeights(GridModel grid, WeightMatrix weights);

	protected abstract int computeSwapCoord(GridModel grid, WeightMatrix weightMatrix);

	protected abstract void adaptWeights(GridModel grid, WeightMatrix weights, int firstSelection);

	protected abstract boolean validateSwap(GridModel grid, SwapCoords coords);
}
