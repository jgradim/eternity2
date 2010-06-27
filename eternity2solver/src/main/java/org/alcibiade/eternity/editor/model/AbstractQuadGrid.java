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

package org.alcibiade.eternity.editor.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractQuadGrid implements Iterable<QuadModel>, Serializable {

	private static final long serialVersionUID = 1L;
	private int sizeX;
	private int sizeY;
	protected List<QuadModel> gridQuads;

	public AbstractQuadGrid(int width, int height) {
		assert width > 0;
		assert height > 0;
		setSize(width, height);
	}

	public QuadModel getQuad(int index) {
		return gridQuads.get(index);
	}

	public QuadModel getQuad(int x, int y) {
		QuadModel result = null;

		if (x >= 0 && x < sizeX && y >= 0 && y < sizeY) {
			result = gridQuads.get(x + y * sizeX);
		}

		return result;
	}

	public List<QuadModel> getQuads() {
		return gridQuads;
	}

	public void setQuads(List<QuadModel> gridQuads) {
		this.gridQuads = gridQuads;
	}
	
	public void setQuad(int position, QuadModel quad) {
		this.gridQuads.set(position, quad);
	}
	
	public void setSize(int width, int height) {
		sizeX = width;
		sizeY = height;
		int positions = sizeX * sizeY;
		gridQuads = new ArrayList<QuadModel>(positions);

		while (gridQuads.size() < positions) {
			gridQuads.add(new QuadModel());
		}
	}

	public int getWidth() {
		return sizeX;
	}

	public int getHeight() {
		return sizeY;
	}

	public int getPositions() {
		return sizeX * sizeY;
	}

	public String toQuadString() {
		StringBuffer text = new StringBuffer();
		int ix = 0;

		for (QuadModel quad : gridQuads) {
			if (quad.isLocked()) {
				text.append('[');
			} else {
				text.append(' ');
			}

			text.append(quad.getPattern(QuadModel.DIR_NORTH).getCodeString());
			text.append(' ');
			text.append(quad.getPattern(QuadModel.DIR_EAST).getCodeString());
			text.append(' ');
			text.append(quad.getPattern(QuadModel.DIR_SOUTH).getCodeString());
			text.append(' ');
			text.append(quad.getPattern(QuadModel.DIR_WEST).getCodeString());

			if (quad.isLocked()) {
				text.append(']');
			} else {
				text.append(' ');
			}

			if ((++ix % getWidth()) == 0) {
				text.append('\n');
			} else {
				text.append("   ");
			}

		}

		return text.toString();
	}

	public QuadModel getNeighbor(int index, int direction) {
		return getNeighbor(index % getWidth(), index / getWidth(), direction);
	}

	public QuadModel getNeighbor(int x, int y, int direction) {
		if (direction == QuadModel.DIR_NORTH) {
			y -= 1;
		} else if (direction == QuadModel.DIR_SOUTH) {
			y += 1;
		} else if (direction == QuadModel.DIR_WEST) {
			x -= 1;
		} else if (direction == QuadModel.DIR_EAST) {
			x += 1;
		} else {
			assert false;
		}

		return getQuad(x, y);
	}

	public int computeNeighborIndex(int index, int direction) {
		int x = index % getWidth();
		int y = index / getWidth();
		int nOffs = -1;

		if (direction == QuadModel.DIR_NORTH && y > 0) {
			nOffs = (x) + getWidth() * (y - 1);
		} else if (direction == QuadModel.DIR_EAST && x < getWidth() - 1) {
			nOffs = (x + 1) + getWidth() * (y);
		} else if (direction == QuadModel.DIR_SOUTH && y < getHeight() - 1) {
			nOffs = (x) + getWidth() * (y + 1);
		} else if (direction == QuadModel.DIR_WEST && x > 0) {
			nOffs = (x - 1) + getWidth() * (y);
		}

		return nOffs;
	}

	public int countSides(int index) {
		int x = index % getWidth();
		int y = index / getWidth();
		int sides = 0;

		if (x == 0 || x == getWidth() - 1) {
			sides += 1;
		}

		if (y == 0 || y == getHeight() - 1) {
			sides += 1;
		}

		return sides;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<QuadModel> iterator() {
		return gridQuads.iterator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int result = sizeX * 17 + sizeY;

		for (QuadModel quad : gridQuads) {
			result *= 1 + quad.hashCode();
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		boolean result = false;

		if (o instanceof AbstractQuadGrid) {
			AbstractQuadGrid otherGrid = (AbstractQuadGrid) o;
			result = gridQuads.equals(otherGrid.gridQuads);
		}

		return result;
	}

}
