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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.model.QuadModel;
import org.alcibiade.eternity.editor.solver.ClusterListener;
import org.alcibiade.eternity.editor.solver.ClusterManager;
import org.alcibiade.eternity.editor.solver.EternitySolver;
import org.alcibiade.eternity.editor.solver.RandomFactory;

public class AStarSolverMkI extends EternitySolver implements ClusterListener {

	private GridModel solutionGrid;
	// This is set to problemGrid.getSize()^2 for convenience.
	private int positions;
	// This is set to problemGrid.getSize() for convenience.
	private int gsize;

	private GridModel problemGrid;
	private long iterations = 0;
	private Random random;

	public AStarSolverMkI(GridModel problemGrid, GridModel solutionGrid,
			ClusterManager clusterManager) {
		super(clusterManager);
		this.solutionGrid = solutionGrid;
		this.problemGrid = problemGrid;

		solutionGrid.reset();
		solutionGrid.setSize(problemGrid.getSize());

		this.gsize = problemGrid.getSize();
		this.positions = gsize * gsize;
		this.random = RandomFactory.getRandom();
	}

	@Override
	public String getSolverName() {
		return "AStar Solver MkI $Revision: 245 $";
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
		boolean solved = solve();

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
			QuadModel piece = problemGrid.getQuad(i);
			if (piece.isLocked()) {
				piece.copyTo(solutionGrid.getQuad(i));
				piece.clear();
			}
		}

		for (int i = 0; i < positions; i++) {
			QuadModel piece = problemGrid.getQuad(i);

			if (!piece.isLocked()) {
				for (int j = 0; j < positions; j++) {
					QuadModel solPiece = solutionGrid.getQuad(j);

					if (solPiece.isClear()
							&& solutionGrid.countSides(j) == piece.countDefaultPattern()) {
						piece.copyTo(solPiece);
						solutionGrid.optimizeQuadRotation(j);
						break;
					}
				}
			}
		}
	}

	@Override
	public void bestSolutionUpdated(int bestScore) {
		// Do nothing
	}

	private boolean solve() {
		// http://en.wikipedia.org/wiki/A*

		// System.out.println(solutionGrid.toQuadString());
		boolean result = false;

		SortedSet<Path> openSet = new TreeSet<Path>();
		openSet.add(new Path());

		Set<Path> closedSet = new HashSet<Path>();

		while (!openSet.isEmpty()) {
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

			// Lowest f_score

			Path x = openSet.first();

			if (x.distance() == 0) {
				for (Swap s : x) {
					s.apply();
				}

				return true;
			}

			openSet.remove(x);
			closedSet.add(x);

			// System.out.println("" + openSet.size() + ", " + closedSet.size()
			// + ", " + x);

			// Find neighbors

			Set<Path> neighbors = computeNeighbors(x);
			for (Path y : neighbors) {
				if (closedSet.contains(y)) {
					continue;
				}

				if (!openSet.contains(y)) {
					openSet.add(y);
				}
			}

			iterations++;
		}

		return result;
	}

	private Set<Path> computeNeighbors(Path p) {
		Set<Path> result = new HashSet<Path>();

		if (p.size() < solutionGrid.getPositions() - 1) {
			for (int i = 0; i < positions; i++) {
				p.apply();

				QuadModel quad = solutionGrid.getQuad(i);
				List<Integer> possibleIndices = new ArrayList<Integer>();

				for (int j = 0; j < positions; j++) {
					if (i != j && solutionGrid.countSides(j) == quad.countDefaultPattern()) {
						possibleIndices.add(j);
					}
				}
				p.revert();

				if (!possibleIndices.isEmpty()) {
					int randomIndex = random.nextInt(possibleIndices.size());
					int dest = possibleIndices.get(randomIndex);
					result.add(new Path(p, new Swap(i, dest)));
				}
			}
		}

		return result;
	}

	protected class Path extends ArrayList<Swap> implements Comparable<Path> {

		private static final long serialVersionUID = 1L;
		private int score = 0;

		public Path() {
			// Do nothing
			score = _distance();
		}

		public Path(Path p, Swap swap) {
			addAll(p);
			add(swap);
			score = _distance();
		}

		public int distance() {
			return score;
		}

		private int _distance() {
			apply();

			score = 0;
			score += solutionGrid.countConnections();
			score -= solutionGrid.countPairs();
			score *= score;
			if (score > 0) {
				score += size();
			}

			revert();

			return score;
		}

		public void revert() {
			for (int i = size() - 1; i >= 0; i--) {
				get(i).revert();
			}
		}

		public void apply() {
			for (int i = 0; i < size(); i++) {
				get(i).apply();
			}
		}

		@Override
		public String toString() {
			return super.toString() + " (score: " + score + ")";
		}

		@Override
		public boolean equals(Object o) {
			boolean result = super.equals(o);
			return result;
		}

		@Override
		public int compareTo(Path o) {
			int result = score - o.score;

			if (result == 0) {
				result = size() - o.size();
			}

			for (int i = 0; result == 0 && i < size(); i++) {
				Swap ms = get(i);
				Swap os = o.get(i);

				result = ms.getSrcIndex() - os.getSrcIndex();

				if (result == 0) {
					result = ms.getDstIndex() - os.getDstIndex();
				}
			}

			return result;
		}
	}

	protected class Swap {
		private int srcIndex;
		private int dstIndex;

		private int srcOrientation;
		private int dstOrientation;

		public Swap(int src, int dst) {
			this.srcIndex = src;
			this.dstIndex = dst;
		}

		public int getSrcIndex() {
			return srcIndex;
		}

		public int getDstIndex() {
			return dstIndex;
		}

		public void apply() {
			// System.out.println(solutionGrid.toQuadString());
			//
			// System.out
			// .println("Swapping " + this + "  // " + srcOrientation + "|" +
			// dstOrientation);

			solutionGrid.swap(srcIndex, dstIndex);
			srcOrientation = solutionGrid.optimizeQuadRotation(srcIndex);
			dstOrientation = solutionGrid.optimizeQuadRotation(dstIndex);
			// System.out.println(solutionGrid.toQuadString());
		}

		public void revert() {
			// System.out.println(solutionGrid.toQuadString());
			//
			// System.out.println("Reverting " + this + "  // " + srcOrientation
			// + "|"
			// + dstOrientation);
			solutionGrid.swap(srcIndex, dstIndex);
			solutionGrid.getQuad(srcIndex).rotateCounterclockwise(dstOrientation);
			solutionGrid.getQuad(dstIndex).rotateCounterclockwise(srcOrientation);
			// System.out.println(solutionGrid.toQuadString());
		}

		@Override
		public boolean equals(Object obj) {
			boolean result = false;

			if (obj instanceof Swap) {
				Swap os = (Swap) obj;
				result = (srcIndex == os.srcIndex && dstIndex == os.dstIndex)
						|| (srcIndex == os.dstIndex && dstIndex == os.srcIndex);
			}

			return result;
		}

		@Override
		public int hashCode() {
			return srcIndex + dstIndex;
		}

		@Override
		public String toString() {
			return "" + srcIndex + "<->" + dstIndex;
		}
	}

}
