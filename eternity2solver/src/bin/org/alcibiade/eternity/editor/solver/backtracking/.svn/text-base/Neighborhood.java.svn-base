package org.alcibiade.eternity.editor.solver.backtracking;

import org.alcibiade.eternity.editor.model.AbstractQuadGrid;
import org.alcibiade.eternity.editor.model.Pattern;
import org.alcibiade.eternity.editor.model.QuadModel;

public class Neighborhood extends AbstractQuadGrid {

	private static final long serialVersionUID = 1L;

	public Neighborhood(int width, int height) {
		super(width, height);
	}

	public Neighborhood(Neighborhood neighborhood) {
		this(neighborhood.getWidth(), neighborhood.getHeight());
		for (int i = 0; i < getPositions(); i++) {
			neighborhood.getQuad(i).copyTo(getQuad(i));
		}
	}

	public boolean isValidNeighborhood() {
		boolean valid = true;
		Pattern defpat = Pattern.getDefaultPattern();

		if (getWidth() == 2) {
			// Corner neighborhood 2x2
			valid = valid && getQuad(0, 0).countDefaultPattern() == 2;

			valid = valid && getQuad(1, 0).countDefaultPattern() == 1;
			valid = valid && getQuad(1, 0).getPattern(QuadModel.DIR_NORTH) == defpat;

			valid = valid && getQuad(0, 1).countDefaultPattern() == 1;
			valid = valid && getQuad(0, 1).getPattern(QuadModel.DIR_WEST) == defpat;

			valid = valid && getQuad(1, 1).countDefaultPattern() == 0;
		} else if (getHeight() == 2) {
			// Border neighborhood 3x2
			valid = valid && getQuad(0, 0).countDefaultPattern() == 1;
			valid = valid && getQuad(0, 0).getPattern(QuadModel.DIR_NORTH) == defpat;
			valid = valid && getQuad(1, 0).countDefaultPattern() == 1;
			valid = valid && getQuad(1, 0).getPattern(QuadModel.DIR_NORTH) == defpat;
			valid = valid && getQuad(2, 0).countDefaultPattern() == 1;
			valid = valid && getQuad(2, 0).getPattern(QuadModel.DIR_NORTH) == defpat;

			valid = valid && getQuad(0, 1).countDefaultPattern() == 0;
			valid = valid && getQuad(1, 1).countDefaultPattern() == 0;
			valid = valid && getQuad(2, 1).countDefaultPattern() == 0;
		} else {
			assert getWidth() == 3;
			assert getHeight() == 3;
			// Inner neighborhood 3x3
			for (int i = 0; i < getPositions(); i++) {
				valid = valid && getQuad(i).countDefaultPattern() == 0;
			}
		}

		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				QuadModel quad = getQuad(x, y);
				if (x < getWidth() - 1) {
					valid = valid
							&& quad.getPattern(QuadModel.DIR_EAST) == getQuad(x + 1, y).getPattern(
									QuadModel.DIR_WEST);
				}

				if (y < getHeight() - 1) {
					valid = valid
							&& quad.getPattern(QuadModel.DIR_SOUTH) == getQuad(x, y + 1)
									.getPattern(QuadModel.DIR_NORTH);
				}
			}
		}

		return valid;
	}

	private static final int[] indices_2x2 = { 1, 2, 3 };
	private static final int[] indices_3x2 = { 0, 3, 4, 5, 2 };
	private static final int[] indices_3x3 = { 1, 2, 5, 8, 7, 6, 3, 0 };

	public int[] getIndices() {
		if (getWidth() == 2) {
			return indices_2x2;
		} else if (getHeight() == 2) {
			return indices_3x2;
		} else {
			return indices_3x3;
		}
	}
}
