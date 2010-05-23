package org.alcibiade.eternity.editor.solver.backtracking;

import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.model.Pattern;
import org.alcibiade.eternity.editor.model.QuadModel;
import org.alcibiade.eternity.editor.solver.collection.EnablingList;

public class NeighborhoodMatcher {
	public GridNeighborhoods computeNeighborHoods(GridModel grid) {
		GridNeighborhoods neighborhoods = new GridNeighborhoods();

		for (int i = 0; i < grid.getPositions(); i++) {
			QuadModel quad = grid.getQuad(i);
			EnablingList<QuadModel> otherQuads = new EnablingList<QuadModel>();

			for (int j = 0; j < grid.getPositions(); j++) {
				if (i != j) {
					QuadModel otherQuad = grid.getQuad(j);
					if (quad.countDefaultPattern() >= otherQuad.countDefaultPattern()) {
						otherQuads.add(otherQuad);
					}
				}
			}

			computeNeighborhood(neighborhoods, quad, otherQuads);
		}

		return neighborhoods;
	}

	private void computeNeighborhood(GridNeighborhoods neighborhoods, QuadModel quad,
			EnablingList<QuadModel> otherQuads) {
		Pattern defpat = Pattern.getDefaultPattern();

		Neighborhood neighborhood;

		int borders = quad.countDefaultPattern();
		assert borders >= 0 && borders < 3;

		if (borders == 0) {
			neighborhood = new Neighborhood(3, 3);
			quad.copyTo(neighborhood.getQuad(1, 1));
		} else if (borders == 1) {
			neighborhood = new Neighborhood(3, 2);
			QuadModel target = neighborhood.getQuad(1, 0);
			quad.copyTo(target);
			while (target.getPattern(QuadModel.DIR_NORTH) != defpat) {
				target.rotateClockwise();
			}
		} else {
			neighborhood = new Neighborhood(2, 2);
			QuadModel target = neighborhood.getQuad(0, 0);
			quad.copyTo(target);
			while (target.getPattern(QuadModel.DIR_NORTH) != defpat
					|| target.getPattern(QuadModel.DIR_WEST) != defpat) {
				target.rotateClockwise();
			}
		}

		fillNeighborhood(neighborhoods, neighborhood, otherQuads);
	}

	private void fillNeighborhood(GridNeighborhoods neighborhoods, Neighborhood neighborhood,
			EnablingList<QuadModel> otherQuads) {
		int emptyIndex = -1;

		for (int i : neighborhood.getIndices()) {
			if (neighborhood.getQuad(i).isClear()) {
				emptyIndex = i;
				break;
			}
		}

		if (emptyIndex < 0) {
			if (neighborhood.isValidNeighborhood()) {
				Neighborhood n = new Neighborhood(neighborhood);
				neighborhoods.add(n);
			}
		} else {
			int otherQuadsSize = otherQuads.size();
			for (int qix = 0; qix < otherQuadsSize; qix++) {
				QuadModel quad = otherQuads.get(qix);
				otherQuads.disable(quad);

				QuadModel emptyQuad = neighborhood.getQuad(emptyIndex);
				quad.copyTo(emptyQuad);

				for (int d = 0; d < 4; d++) {
					emptyQuad.rotateClockwise();

					if (validate(neighborhood, emptyIndex)) {
						fillNeighborhood(neighborhoods, neighborhood, otherQuads);
					}
				}

				otherQuads.enable(quad);
				emptyQuad.clear();
			}
		}
	}

	private boolean validate(Neighborhood neighborhood, int emptyIndex) {
		boolean validationResult = true;
		QuadModel quad = neighborhood.getQuad(emptyIndex);

		for (int d = 0; d < 4; d++) {
			QuadModel neighbor = neighborhood.getNeighbor(emptyIndex, d);
			if (neighbor != null && !neighbor.isClear()) {
				validationResult = validationResult
						&& quad.getPattern(d) == neighbor.getPattern((d + 2) % 4);
			}
		}

		return validationResult;
	}
}
