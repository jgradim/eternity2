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

package org.alcibiade.eternity.editor.application;

import java.io.File;
import java.io.IOException;

import org.alcibiade.eternity.editor.log.NullLog;
import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.model.QuadsFormatException;
import org.alcibiade.eternity.editor.model.operation.GridLoader;
import org.alcibiade.eternity.editor.solver.ClusterListener;
import org.alcibiade.eternity.editor.solver.ClusterManager;
import org.alcibiade.eternity.editor.solver.swap.WeightedRandomMkI;
import org.alcibiade.eternity.editor.solver.swap.WeightedRandomMkV;
import org.alcibiade.eternity.editor.stats.GraphData;

public class GraphApp implements ClusterListener {
	private static final int SCORE_MIN = 350;
	private static final int SCORE_MAX = 390;

	private ClusterManager clusterManager;
	private WeightedRandomMkI currentSolver;
	private GraphData graphData = new GraphData();;
	private GridModel problemGrid = new GridModel();;
	private double weightRatio;

	public GraphApp() throws IOException, QuadsFormatException {
		File file = new File("EternityII.txt");
		GridLoader loader = new GridLoader(problemGrid);
		loader.load(file);
	}

	public void runSolvers() {
		int iteration = 0;

		while (true) {
			for (weightRatio = 0.95; weightRatio <= 1.05; weightRatio += 0.005) {
				System.out.println(String.format("Running iteration %d at ratio %.3f", iteration,
						weightRatio));
				solveSingle();
			}

			iteration += 1;
			displayStats();
		}
	}

	private void solveSingle() {
		problemGrid.shuffle();
		GridModel solution = new GridModel();
		clusterManager = new ClusterManager(new NullLog());
		clusterManager.addClusterListener(this);
		currentSolver = new WeightedRandomMkV(problemGrid, solution, clusterManager);
		currentSolver.setWeightFactor(currentSolver.getWeightFactor() * weightRatio);
		currentSolver.run();
	}

	public void displayStats() {
		System.out.println(graphData);
	}

	@Override
	public void bestSolutionUpdated(int bestScore) {
		if (SCORE_MIN <= bestScore && bestScore <= SCORE_MAX) {
			long elapsed = clusterManager.getElapsedTime();
			graphData.recordElapsed(bestScore, weightRatio, elapsed);
		}

		if (bestScore >= SCORE_MAX) {
			currentSolver.interrupt();
		}
	}

	public static void main(String[] args) {
		try {
			GraphApp app = new GraphApp();
			app.runSolvers();
			app.displayStats();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
