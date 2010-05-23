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

import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.solver.ClusterListener;
import org.alcibiade.eternity.editor.solver.ClusterManager;

/**
 * Extension of the MkV with persiodic auto-reset to the current best solution.
 * 
 * Best used in a cluster along with other implementations.
 * 
 */
public class WeightedRandomMkIV extends WeightedRandomMkV implements ClusterListener {

	private static final long INCREMENT_STEPS = 10;
	private long step;
	private long nextStep;
	protected double originalWeightFactor;

	public WeightedRandomMkIV(GridModel grid, GridModel solutionGrid, ClusterManager clusterManager) {
		super(grid, solutionGrid, clusterManager);
		this.originalWeightFactor = weightFactor;
		resetSteps();
		clusterManager.addClusterListener(this);
	}

	private synchronized void resetSteps() {
		step = INCREMENT_STEPS;
		nextStep = iterations + step;
	}

	@Override
	public String getSolverName() {
		return "WeightedRandomMkIV Solver $Revision: 254 $";
	}

	@Override
	protected synchronized void computeWeights(GridModel grid, WeightMatrix weights) {
		if (iterations > nextStep) {
			resetBoard(grid);
		}

		super.computeWeights(grid, weights);
	}

	private void resetBoard(GridModel grid) {
		step += step / INCREMENT_STEPS;
		nextStep = iterations + step;

		GridModel bestSolution = clusterManager.getBestSolution();
		bestSolution.copyTo(grid);

		double wfMultiplier = computeWeightFactorMultiplier();
		Thread.currentThread().setName(String.format("%s WFx%.5f", getSolverName(), wfMultiplier));
		// clusterManager.logMessage(
		// "Adjusted weight factor to %02.5f = %02.5f x %1.5f",
		// weightFactor, originalWeightFactor, wfMultiplier);
		// clusterManager.logMessage("Reverted to best solution.");
	}

	protected double computeWeightFactorMultiplier() {
		double wfMultiplier = Math.sqrt(rand.nextDouble() + 0.5);
		weightFactor = originalWeightFactor * wfMultiplier;
		return wfMultiplier;
	}

	public synchronized void bestSolutionUpdated(int bestScore) {
		resetSteps();
	}
}
