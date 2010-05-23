package org.alcibiade.eternity.editor.solver.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.model.Pattern;
import org.alcibiade.eternity.editor.model.QuadModel;
import org.alcibiade.eternity.editor.solver.ClusterManager;
import org.alcibiade.eternity.editor.solver.path.PathProvider;

public class IterativePathSolverMkII extends IterativePathSolverMkI {

	private List<Set<Pattern>> patternSets = new ArrayList<Set<Pattern>>();

	public IterativePathSolverMkII(GridModel grid, GridModel solutionGrid,
			ClusterManager clusterManager, PathProvider pathProvider) {
		super(grid, solutionGrid, clusterManager, pathProvider);

		for (int i = 0; i < (4 * positions); i++) {
			patternSets.add(new TreeSet<Pattern>());
		}
	}

	@Override
	public String getSolverName() {
		return "Iterative Path Solver MkII $Revision: 234 $";
	}

	@Override
	protected boolean validateUpdate(int srcOffset, int destOffset) {
		boolean result = super.validateUpdate(srcOffset, destOffset);

		if (result) {
			initializeLists();
			result = isFit(srcOffset, destOffset);
		}

		return result;
	}

	private boolean isFit(int srcOffset, int destOffset) {
		QuadModel quad = pieces.getQuad(srcOffset).clone();

		boolean result = false;

		for (int rot = 0; !result && rot < 4; rot++) {
			quad.rotateClockwise();
			int matches = 0;

			for (int d = 0; d < 4; d++) {
				Pattern pattern = quad.getPattern(d);
				if (patternSets.get(4 * destOffset + d).contains(pattern)) {
					matches += 1;
				}
			}

			result = (matches == 4);
		}

		return result;
	}

	private void initializeLists() {
		for (int offs = 0; offs < positions; offs++) {
			fillNeighborValues(offs);
			Set<QuadModel> matches = findMatches(offs);

			for (int orientation = 0; orientation < 4; orientation++) {
				Set<Pattern> validPatterns = patternSets.get(offs * 4 + orientation);
				if (validPatterns.isEmpty()) {
					for (QuadModel match : matches) {
						validPatterns.add(match.getPattern(orientation));
					}
				}
			}
		}

		for (int index = 0; index < positions; index++) {
			for (int d = 0; d < 4; d++) {
				int neighborIndex = solutionGrid.computeNeighborIndex(index, d);
				if (neighborIndex >= 0) {
					Set<Pattern> s1 = patternSets.get(index * 4 + d);
					Set<Pattern> s2 = patternSets.get(neighborIndex * 4 + ((d + 2) % 4));

					Iterator<Pattern> s1It = s1.iterator();
					while (s1It.hasNext()) {
						Pattern p = s1It.next();
						if (!s2.contains(p)) {
							s1It.remove();
						}
					}
				}
			}
		}
	}

	private Set<QuadModel> findMatches(int offs) {
		Set<QuadModel> matches = new HashSet<QuadModel>();

		for (int srcOffset = 0; srcOffset < positions; srcOffset++) {
			QuadModel srcQuad = pieces.getQuad(srcOffset).clone();

			for (int orientation = 0; orientation < 4; orientation++) {
				srcQuad.rotateClockwise();
				boolean matching = true;

				for (int testDir = 0; testDir < 4; testDir++) {
					Set<Pattern> validPatterns = patternSets.get(offs * 4 + testDir);
					if (validPatterns.isEmpty()
							|| validPatterns.contains(srcQuad.getPattern(testDir))) {
						// This is matching
					} else {
						matching = false;
					}
				}

				if (matching) {
					matches.add(srcQuad.clone());
				}
			}
		}
		return matches;
	}

	private void fillNeighborValues(int offs) {
		QuadModel quad = solutionGrid.getQuad(offs);

		for (int d = 0; d < 4; d++) {
			Set<Pattern> validPatterns = patternSets.get(offs * 4 + d);
			validPatterns.clear();

			if (quad.isClear()) {
				QuadModel neighbor = solutionGrid.getNeighbor(offs, d);
				if (neighbor == null) {
					// Border cell
					validPatterns.add(defaultpat);
				} else if (neighbor.isClear()) {
					// Empty neighbor
					// validPatterns.addAll(getAllSourcePatterns());
				} else {
					// Standard piece neighbor
					Pattern neighborPattern = neighbor.getPattern((d + 2) % 4);
					validPatterns.add(neighborPattern);
				}
			} else {
				validPatterns.add(quad.getPattern(d));
			}
		}
	}

}
