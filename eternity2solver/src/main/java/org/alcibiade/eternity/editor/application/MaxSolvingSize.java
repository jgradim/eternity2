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

import org.alcibiade.eternity.editor.log.ConsoleLog;
import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.model.operation.GridFiller;
import org.alcibiade.eternity.editor.solver.ClusterManager;
import org.alcibiade.eternity.editor.solver.EternitySolver;
import org.alcibiade.eternity.editor.solver.swap.WeightedRandomMkII;

public class MaxSolvingSize {
	public static void main(String[] args) {
		int size = 2;

		while (true) {
			System.out.println("=== Size " + size);

			GridModel grid = new GridModel(size);
			GridFiller filler = new GridFiller(grid);
			filler.fillRandom((int) (size * 1.5));
			grid.shuffle();

			GridModel solution = new GridModel(size);

			ClusterManager cluster = new ClusterManager(new ConsoleLog());
			EternitySolver solver = new WeightedRandomMkII(grid, solution, cluster);
			solver.run();

			size++;
		}
	}
}
