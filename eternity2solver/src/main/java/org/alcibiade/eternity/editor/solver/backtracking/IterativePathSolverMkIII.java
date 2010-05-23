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

package org.alcibiade.eternity.editor.solver.backtracking;

import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.model.Pattern;
import org.alcibiade.eternity.editor.model.QuadModel;
import org.alcibiade.eternity.editor.solver.ClusterListener;
import org.alcibiade.eternity.editor.solver.ClusterManager;
import org.alcibiade.eternity.editor.solver.EternitySolver;
import org.alcibiade.eternity.editor.solver.path.PathProvider;

public class IterativePathSolverMkIII extends EternitySolver implements ClusterListener {

	protected static Pattern defaultpat = Pattern.getDefaultPattern();

	protected GridModel solutionGrid;
	// This is set to problemGrid.getSize()^2 for convenience.
	protected int positions;
	// This is set to problemGrid.getSize() for convenience.
	protected int gsize;
	// The number of quads that aren't locked in the initial position. This will
	// represent our maximal search depth.
	private int openPositions;

	protected int record = 0;

	protected PathProvider path;
	protected GridModel pieces;
	protected long iterations = 0;

	public IterativePathSolverMkIII(GridModel grid, GridModel solutionGrid,
			ClusterManager clusterManager, PathProvider pathProvider) {
		super(clusterManager);
		this.solutionGrid = solutionGrid;

		pieces = grid.clone();
		solutionGrid.reset();
		solutionGrid.setSize(grid.getSize());

		this.gsize = grid.getSize();
		this.positions = gsize * gsize;
		this.path = pathProvider;

		// Compute the number of open positions to search

		this.openPositions = 0;
		for (QuadModel quad : grid) {
			if (!quad.isLocked()) {
				openPositions += 1;
			}
		}
	}

	@Override
	public String getSolverName() {
		return "Iterative Path Solver MkIII $Revision: 245 $";
	}

	@Override
	public long getIterations() {
		return iterations;
	}

	@Override
	public void run() {
		notifyStart();
		clusterManager.showStartMessage();

		moveLockedPieces();
		boolean solved = solve(0);

		if (solved) {
			clusterManager.submitSolution(solutionGrid);
			clusterManager.showStats(iterations);
		}

		notifyEnd(solved);
	}

	private void moveLockedPieces() {
		int gsize = solutionGrid.getSize();
		int positions = gsize * gsize;

		for (int i = 0; i < positions; i++) {
			QuadModel piece = pieces.getQuad(i);
			if (piece.isLocked()) {
				piece.copyTo(solutionGrid.getQuad(i));
				piece.clear();
			}
		}
	}

	private boolean solve(int destOffset) {
		boolean result = false;

		if (interrupted) {
			return false;
		}

		if (slowmotion) {
			try {
				Thread.sleep(SLOWMOTION_DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		iterations++;

		if (destOffset == openPositions) {
			result = true;
		} else {
			updateRecordData(destOffset);

			int destIndex = path.getNextPathIndex(pieces, solutionGrid);

			QuadModel dest = solutionGrid.getQuad(destIndex);

			result = explore(destOffset, destIndex);

			if (!result) {
				dest.clear();
			}
		}

		return result;
	}

	private void updateRecordData(int destOffset) {
		if (destOffset > record) {
			GridModel remaining = pieces.clone();
			GridModel solutionGrid = this.solutionGrid.clone();
			for (int i = destOffset; i < openPositions; i++) {
				int offs = path.getNextPathIndex(pieces, solutionGrid);
				QuadModel target = solutionGrid.getQuad(offs);

				// Target may not be clear if there are locked quads already in
				// place.
				if (target.isClear()) {
					QuadModel missing = solutionGrid.getMissingQuad(offs);
					int bestFitness = Integer.MIN_VALUE;
					QuadModel bestQuad = null;

					for (QuadModel q : remaining) {
						if (!q.isClear()
								&& q.countDefaultPattern() == solutionGrid
										.countExternalBorders(offs)) {
							int fitness = q.matchDegrees(missing);

							if (fitness > bestFitness) {
								bestFitness = fitness;
								bestQuad = q;
							}
						}
					}

					bestQuad.copyTo(target);
					bestQuad.clear();
					solutionGrid.optimizeQuadRotation(offs);
				}
			}

			clusterManager.submitSolution(solutionGrid);
			record = destOffset;
		}
	}

	private boolean explore(int destOffset, int destIndex) {
		boolean result = false;

		QuadModel dest = solutionGrid.getQuad(destIndex);

		for (int srcIndex = 0; !result && srcIndex < positions; srcIndex++) {
			QuadModel src = pieces.getQuad(srcIndex);

			if (src.isClear()) {
				continue;
			}

			QuadModel missing = solutionGrid.getMissingQuad(destIndex);

			if (missing.countDefaultPattern() != src.countDefaultPattern()) {
				continue;
			}

			src.copyTo(dest);
			src.clear();

			for (int rot = 0; !result && rot < 4; rot++) {
				dest.rotateClockwise();
				if (dest.matches(missing)) {
					result = solve(destOffset + 1);
				}
			}

			dest.copyTo(src);
			if (!result) {
				dest.clear();
			}
		}

		return result;
	}

	public void bestSolutionUpdated(int bestScore) {
		if (clusterManager.isSolutionFound()) {
			this.interrupt();
		}
	}
}
