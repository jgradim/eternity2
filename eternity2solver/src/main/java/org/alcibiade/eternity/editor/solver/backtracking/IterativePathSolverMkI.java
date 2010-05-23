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

public class IterativePathSolverMkI extends EternitySolver implements ClusterListener {

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

	public IterativePathSolverMkI(GridModel grid, GridModel solutionGrid,
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
		return "Iterative Path Solver MkI $Revision: 245 $";
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
		QuadModel top = solutionGrid.getNeighbor(destIndex, QuadModel.DIR_NORTH);
		QuadModel right = solutionGrid.getNeighbor(destIndex, QuadModel.DIR_EAST);
		QuadModel bottom = solutionGrid.getNeighbor(destIndex, QuadModel.DIR_SOUTH);
		QuadModel left = solutionGrid.getNeighbor(destIndex, QuadModel.DIR_WEST);

		for (int srcIndex = 0; !result && srcIndex < positions; srcIndex++) {
			QuadModel src = pieces.getQuad(srcIndex);

			if (!checkPreconditions(src, dest, left, top, right, bottom)) {
				continue;
			}

			if (!validateUpdate(srcIndex, destIndex)) {
				continue;
			}

			src.copyTo(dest);
			src.clear();

			for (int rot = 0; !result && rot < 4; rot++) {
				dest.rotateClockwise();

				boolean validPosition = checkPostconditions(dest, left, top, right, bottom);

				if (validPosition) {
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

	protected boolean validateUpdate(int srcOffset, int destOffset) {
		return true;
	}

	private boolean checkPreconditions(QuadModel src, QuadModel dest, QuadModel left,
			QuadModel top, QuadModel right, QuadModel bottom) {
		boolean result = !src.isClear();
		result = result && src.countPattern(defaultpat) == countNull(top, right, left, bottom);
		return result;
	}

	private boolean checkPostconditions(QuadModel dest, QuadModel left, QuadModel top,
			QuadModel right, QuadModel bottom) {
		boolean valid = true;

		if (valid) {
			Pattern west = dest.getPattern(QuadModel.DIR_WEST);

			if (left == null) {
				valid = west == defaultpat;
			} else if (!left.isClear()) {
				valid = west != defaultpat && west == left.getPattern(QuadModel.DIR_EAST);
			}
		}

		if (valid) {
			Pattern north = dest.getPattern(QuadModel.DIR_NORTH);

			if (top == null) {
				valid = north == defaultpat;
			} else if (!top.isClear()) {
				valid = north != defaultpat && north == top.getPattern(QuadModel.DIR_SOUTH);
			}
		}

		if (valid) {
			Pattern east = dest.getPattern(QuadModel.DIR_EAST);

			if (right == null) {
				valid = east == defaultpat;
			} else if (!right.isClear()) {
				valid = east != defaultpat && east == right.getPattern(QuadModel.DIR_WEST);
			}
		}

		if (valid) {
			Pattern south = dest.getPattern(QuadModel.DIR_SOUTH);

			if (bottom == null) {
				valid = south == defaultpat;
			} else if (!bottom.isClear()) {
				valid = south != defaultpat && south == bottom.getPattern(QuadModel.DIR_NORTH);
			}
		}
		return valid;
	}

	protected static int countNull(Object... references) {
		int nulls = 0;
		for (Object o : references) {
			if (o == null) {
				nulls++;
			}
		}
		return nulls;
	}

	public void bestSolutionUpdated(int bestScore) {
		if (clusterManager.isSolutionFound()) {
			this.interrupt();
		}
	}
}
