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

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.model.operation.GridFiller;
import org.alcibiade.eternity.editor.solver.SolverFactory;
import org.alcibiade.eternity.editor.solver.path.PathFactory;
import org.alcibiade.eternity.editor.stats.Collector;
import org.alcibiade.eternity.editor.stats.SolverMeter;
import org.gridgain.grid.GridException;

public class StatsApp {

	public static void main(String[] args) {

		List<String> solvers = new ArrayList<String>();
		// solvers.add(SolverFactory.LABEL_ITPATHMKI);
		// solvers.add(SolverFactory.LABEL_ITPATHMKII);
		solvers.add(SolverFactory.LABEL_ITPATHMKIII);
		// solvers.add(SolverFactory.LABEL_SWAPWMKI);
		// solvers.add(SolverFactory.LABEL_SWAPWMKII);
		// solvers.add(SolverFactory.LABEL_SWAPWMKIII);
		// solvers.add(SolverFactory.LABEL_SWAPWMKIV);
		solvers.add(SolverFactory.LABEL_SWAPWMKV);
		solvers.add(SolverFactory.LABEL_SWAPWMKVI);
		// solvers.add(SolverFactory.LABEL_SWAPWMKVII);

		List<String> paths = new ArrayList<String>();
		paths.add(PathFactory.LABEL_LINEAR);
		paths.add(PathFactory.LABEL_HUMAN);
		// paths.add(PathFactory.LABEL_HUMAN2);
		// paths.add(PathFactory.LABEL_SNAIL);

		int sizeMin = 5;
		int sizeMax = 9;
		int samples = 400;

		try {
			computeStats(solvers, paths, samples, sizeMin, sizeMax);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// GridFactory.stop(true);
		}
	}

	private static void computeStats(List<String> solvers, List<String> paths, int samples,
			int sizeMin, int sizeMax) throws GridException, InterruptedException,
			ExecutionException {
		// Grid cpuGrid = GridFactory.start();

		for (int gridsize = sizeMin; gridsize <= sizeMax; gridsize++) {

			int patterns = (int) (gridsize * 1.5);
			Collector reference = null;

			for (String solverName : solvers) {

				List<String> pathsQueue = new ArrayList<String>();
				if (SolverFactory.isSolverPathSensitive(solverName)) {
					pathsQueue.addAll(paths);
				} else {
					pathsQueue.add(null);
				}

				for (String pathName : pathsQueue) {

					String collectorName = computeCollectorName(solverName, pathName, gridsize,
							patterns);

					Collector collector = new Collector(collectorName, reference);
					if (reference == null) {
						reference = collector;
					}

					// ExecutorService executor =
					// cpuGrid.newGridExecutorService();
					ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime()
							.availableProcessors());
					Set<Future<Collector>> collectors = new LinkedHashSet<Future<Collector>>();

					for (int sample = 0; sample < samples; sample++) {
						GridModel grid = new GridModel(gridsize);
						GridFiller filler = new GridFiller(grid);
						filler.fillRandom(patterns);
						grid.shuffle();
						SolverMeter solverMeter = new SolverMeter(solverName, pathName, grid);
						collectors.add(executor.submit(solverMeter));
					}

					for (Future<Collector> futureCollector : collectors) {
						collector.recordExecutions(futureCollector.get());
					}

					executor.shutdown();
					while (!executor.isTerminated()) {
						try {
							executor.awaitTermination(10, TimeUnit.SECONDS);
						} catch (InterruptedException e) {
							// Do nothing, keep looping
						}
					}

					collector.displayResults();
				}
			}

			System.out.println();
		}
	}

	private static String computeCollectorName(String solverName, String pathName, int gridsize,
			int patterns) {
		String collectorName = solverName;

		if (pathName != null) {
			collectorName += " - " + pathName;
		}

		while (collectorName.length() < 37) {
			collectorName = collectorName + " ";
		}

		collectorName += gridsize + "x" + gridsize + "x" + patterns;
		return collectorName;
	}
}
