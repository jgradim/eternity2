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

package org.alcibiade.eternity.editor.solver.concurrent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.Serializable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.model.Pattern;
import org.alcibiade.eternity.editor.model.QuadModel;
import org.gridgain.grid.Grid;
import org.gridgain.grid.GridFactory;
import org.junit.Test;

public class SerializationTest {

	@Test
	public void testPatterns() throws Exception {
		GridModel gridModel = new GridModel(3);
		for (int i = 0; i < 9; i++) {
			gridModel.getQuad(i).setPattern(QuadModel.DIR_NORTH, Pattern.getPatternByCode(1));
			gridModel.getQuad(i).setPattern(QuadModel.DIR_SOUTH, Pattern.getPatternByCode(2));
			gridModel.getQuad(i).setPattern(QuadModel.DIR_EAST, Pattern.getPatternByCode(3));
			gridModel.getQuad(i).setPattern(QuadModel.DIR_WEST, Pattern.getPatternByCode(4));
		}

		// Local test

		RemoteRunner runner = new RemoteRunner(gridModel);
		assertEquals(new Integer(0), runner.call());

		// Remote test

		if (System.getProperty("GRIDGAIN_HOME") == null) {
			System.out.println("Variable GRIDGAIN_HOME not found, skipping remote pattern test");
		} else {
			Grid grid = GridFactory.start();
			ExecutorService executor = grid.newGridExecutorService();
			Future<Integer> futureResult = executor.submit(new RemoteRunner(gridModel));
			executor.awaitTermination(10, TimeUnit.SECONDS);
			GridFactory.stop(true);

			assertEquals(new Integer(0), futureResult.get());
		}
	}

	private class RemoteRunner implements Callable<Integer>, Serializable {
		private static final long serialVersionUID = 1L;

		private GridModel gridModel;

		public RemoteRunner(GridModel gridModel) {
			this.gridModel = gridModel;
		}

		public Integer call() throws Exception {
			try {
				for (int i = 1; i < 9; i++) {
					QuadModel qA = gridModel.getQuad(i - 1);
					QuadModel qB = gridModel.getQuad(i);

					assertEquals(qA, qB);
					Pattern pattern = qA.getPattern(QuadModel.DIR_EAST);
					assertSame(pattern, qB.getPattern(QuadModel.DIR_EAST));
					assertSame(pattern, Pattern.getPatternByCode(pattern.getCode()));
				}

				return 0;
			} catch (AssertionError e) {
				e.printStackTrace();
				return -1;
			}
		}
	}
}
