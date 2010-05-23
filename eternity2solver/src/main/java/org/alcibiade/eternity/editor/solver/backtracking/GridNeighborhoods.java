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

import java.util.HashSet;
import java.util.Set;

public class GridNeighborhoods {

	private Set<Neighborhood> edges = new HashSet<Neighborhood>();
	private Set<Neighborhood> corners = new HashSet<Neighborhood>();
	private Set<Neighborhood> inners = new HashSet<Neighborhood>();

	public Set<Neighborhood> getEdges() {
		return edges;
	}

	public Set<Neighborhood> getInners() {
		return inners;
	}

	public Set<Neighborhood> getCorners() {
		return corners;
	}

	public int size() {
		return edges.size() + corners.size() + inners.size();
	}

	public void add(Neighborhood neighborhood) {
		if (neighborhood.getWidth() == 2) {
			corners.add(neighborhood);
		} else if (neighborhood.getHeight() == 2) {
			edges.add(neighborhood);
		} else {
			inners.add(neighborhood);
		}
	}
}
