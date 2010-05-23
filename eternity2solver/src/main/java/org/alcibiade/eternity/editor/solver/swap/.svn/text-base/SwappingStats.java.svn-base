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

import java.util.LinkedList;
import java.util.Queue;

public class SwappingStats {

	private int windowSize;
	private Queue<Integer> queue;

	public SwappingStats(int windowSize) {
		this.windowSize = windowSize;
		queue = new LinkedList<Integer>();
	}

	public int countOccurrences(int coord) {
		int result = 0;

		for (Integer i : queue) {
			if (i == coord) {
				result += 1;
			}
		}

		return result;
	}

	public void recordSwap(SwapCoords coords) {
		queue.add(coords.getCoordA());
		queue.add(coords.getCoordB());

		while (queue.size() > windowSize) {
			queue.remove();
		}
	}

	public void reset() {
		queue.clear();
	}
}
