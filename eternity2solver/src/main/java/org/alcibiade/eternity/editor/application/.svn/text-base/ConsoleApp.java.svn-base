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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.alcibiade.eternity.editor.log.ConsoleLog;
import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.model.QuadsFormatException;
import org.alcibiade.eternity.editor.solver.ClusterManager;
import org.alcibiade.eternity.editor.solver.EternitySolver;
import org.alcibiade.eternity.editor.solver.SolverFactory;
import org.alcibiade.eternity.editor.solver.UnknownSolverException;
import org.alcibiade.eternity.editor.solver.path.PathFactory;
import org.alcibiade.eternity.editor.solver.path.PathProvider;
import org.alcibiade.eternity.editor.solver.path.UnknownPathException;
import org.alcibiade.eternity.editor.solver.restarter.RestartingSolver;

/**
 * ConsoleApp: A console application to launch solvers
 * 
 * Syntax:
 * 
 * ConsoleApp <SolverName> [<SovlerName> ...]
 * 
 * Starts given solvers an reads grid data from standard input
 * 
 * ConsoleApp <GridFile> <SolverName> [<SolverName> ...]
 * 
 * Starts given solvers on the grid file.
 * 
 * @author Yannick Kirschhoffer <alcibiade@alcibiade.org>
 * 
 */
public class ConsoleApp {

	public static void main(String[] args) {
		if (args.length == 0) {
			showHelp();
			return;
		}

		try {
			Random random = new Random();
			/*
			 * Load grid data from either the file pointed by args[0] or the
			 * standard input.
			 */

			StringBuilder quadsBuffer = new StringBuilder();
			int firstsolverarg = loadGridData(args, quadsBuffer);
			String quadsString = quadsBuffer.toString();

			/*
			 * Startup solvers
			 */

			Map<EternitySolver, GridModel> solvers = new HashMap<EternitySolver, GridModel>();
			Pattern multipleSolverPattern = Pattern.compile("(\\d+)x(.*)");
			Pattern restartSolverPattern = Pattern.compile("(.*)\\[(\\d+)\\]");
			Pattern pathSolverPattern = Pattern.compile("(.*)\\|(.*)");

			ClusterManager clusterManager = new ClusterManager(new ConsoleLog(),
					firstsolverarg == 0 ? null : "solution_" + args[0]);

			for (int argIndex = firstsolverarg; argIndex < args.length; argIndex++) {
				int instances = 1;
				int restartSecs = -1;
				String solverName = args[argIndex];
				String pathName = PathFactory.LABEL_LINEAR;

				if (solverName.length() == 0) {
					continue;
				} else {
					Matcher intanceMatcher = multipleSolverPattern.matcher(solverName);
					if (intanceMatcher.find()) {
						instances = Integer.parseInt(intanceMatcher.group(1));
						solverName = intanceMatcher.group(2);
					}

					Matcher restartMatcher = restartSolverPattern.matcher(solverName);
					if (restartMatcher.find()) {
						solverName = restartMatcher.group(1);
						restartSecs = Integer.parseInt(restartMatcher.group(2));
					}

					Matcher pathMatcher = pathSolverPattern.matcher(solverName);
					if (pathMatcher.find()) {
						solverName = pathMatcher.group(1);
						pathName = pathMatcher.group(2);
					}
				}

				/*
				 * We create a specific grid instance for each solver, and we
				 * shuffle it to increase parallel processing performance.
				 */

				for (int instance = 0; instance < instances; instance++) {
					GridModel grid = new GridModel();
					GridModel solution = new GridModel();
					grid.fromQuadString(quadsString);
					if (!solvers.isEmpty()) {
						grid.shuffle();
					}

					EternitySolver solver;
					PathProvider path = PathFactory.createPath(pathName);

					if (restartSecs > 0) {
						solver = new RestartingSolver(solverName, path, grid, solution,
								clusterManager, restartSecs * 1000 + random.nextInt(100));
					} else {
						solver = SolverFactory.createSolver(solverName, grid, solution,
								clusterManager, path);
					}

					solver.start();
					solvers.put(solver, solution);
				}
			}

			// Wait for solution

			boolean solved = false;

			while (!solved) {
				for (EternitySolver solver : solvers.keySet()) {
					if (!solver.isAlive()) {
						solved = true;

						GridModel solution = solvers.get(solver);
						System.out.println(solution.toQuadString());
					}
				}

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			// End all threads

			for (EternitySolver solver : solvers.keySet()) {
				solver.interrupt();

				try {
					solver.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (QuadsFormatException e) {
			e.printStackTrace();
		} catch (UnknownSolverException e) {
			System.out.println(e.getMessage());
		} catch (UnknownPathException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void showHelp() {
		System.out.println("Syntax: <board file> <solver> [<solver>]...");
		System.out.println("");
		System.out.println("Solvers can be invoked:");
		System.out.println("    - SolverName:      Basic run");
		System.out.println("    - NxSolverName:    Run N instances of solver");
		System.out.println("    - SolverName[T]:   Run an instance and restart it every T seconds");
		System.out
				.println("    - NxSolverName[T]: N instance of sovler restarting every T seconds");
		System.out.println("");
		System.out.println("Backtracking solvers can have specific path specified this way:");
		System.out.println("     SolverType|PathName");
		System.out.println("");

		System.out
				.println("Available solver implementations:   (* means contextual path can be specified)");
		for (String label : SolverFactory.getAvailableSolvers()) {
			String note = "";

			if (SolverFactory.isSolverPathSensitive(label)) {
				note = " *";
			}

			System.out.println("    - " + label + note);
		}
		System.out.println("");

		System.out.println("Available paths:");
		for (String label : PathFactory.getAvailablePaths()) {
			System.out.println("    - " + label);
		}
		System.out.println("");

	}

	private static int loadGridData(String[] args, StringBuilder quadsBuffer)
			throws FileNotFoundException, IOException {
		int firstsolverarg = 0;

		File gridfile = new File(args[0]);

		if (gridfile.exists()) {
			firstsolverarg++;
			LineNumberReader lnr = new LineNumberReader(new FileReader(gridfile));

			String line = lnr.readLine();

			while (line != null) {
				quadsBuffer.append(line);
				quadsBuffer.append('\n');
				line = lnr.readLine();
			}
		} else {
			LineNumberReader lnr = new LineNumberReader(new InputStreamReader(System.in));

			String line = lnr.readLine();

			while (line != null && line.length() > 0) {
				quadsBuffer.append(line);
				quadsBuffer.append('\n');
				line = lnr.readLine();
			}
		}
		return firstsolverarg;
	}
}
