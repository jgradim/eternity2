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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.alcibiade.eternity.editor.model.operation.GridFiller;
import org.alcibiade.eternity.editor.model.operation.GridLoader;
import org.junit.Test;

public class GridModelTest {

	@Test
	public void testFill() {
		GridModel grid = new GridModel(5);
		GridFiller filler = new GridFiller(grid);
		filler.FillRandomAsymetric(7);
		for (QuadModel quad : grid) {
			assertFalse(quad.isClear());
		}

		PatternStats stats = grid.getPatternStats();
		assertEquals(grid.countConnections(), grid.countPairs());
		assertTrue(stats.getPatterns().size() <= 7);
	}

	@Test
	public void testClone() {
		GridModel grid1 = new GridModel(5);
		GridFiller filler = new GridFiller(grid1);
		filler.FillRandomAsymetric(7);
		GridModel grid2 = grid1.clone();

		assertTrue(grid1 != grid2);
	}

	@Test
	public void testEquals() {
		GridModel grid1 = new GridModel(5);
		GridModel grid2 = new GridModel(6);
		GridModel grid3 = new GridModel(6);
		assertFalse(grid1.equals(grid2));
		assertTrue(grid2.equals(grid3));

		grid3.getQuad(1, 1).setPattern(QuadModel.DIR_EAST, Pattern.getPatternByCode(1));
		assertFalse(grid1.equals(grid2));
	}

	@Test
	public void testRotateClockwise() {
		GridModel grid1 = new GridModel(5);
		GridFiller filler = new GridFiller(grid1);
		filler.FillRandomAsymetric(7);
		GridModel grid2 = grid1.clone();

		assertTrue(grid1.equals(grid2));
		grid2.rotateClockwise();

		QuadModel q1 = grid1.getQuad(1, 1).clone();
		QuadModel q2 = grid2.getQuad(3, 1);
		q1.rotateClockwise();
		assertTrue(q1.equals(q2));

		assertFalse(grid1.equals(grid2));
		assertEquals(grid2.countConnections(), grid2.countPairs());
		grid2.rotateClockwise();
		assertFalse(grid1.equals(grid2));
		assertEquals(grid2.countConnections(), grid2.countPairs());
		grid2.rotateClockwise();
		assertFalse(grid1.equals(grid2));
		assertEquals(grid2.countConnections(), grid2.countPairs());
		grid2.rotateClockwise();
		assertTrue(grid1.equals(grid2));
		assertEquals(grid2.countConnections(), grid2.countPairs());
	}

	@Test
	public void testRotateCounterclockwise() {
		GridModel grid1 = new GridModel(5);
		GridFiller filler = new GridFiller(grid1);
		filler.FillRandomAsymetric(7);
		GridModel grid2 = grid1.clone();

		assertTrue(grid1.equals(grid2));
		grid2.rotateCounterclockwise();

		QuadModel q1 = grid1.getQuad(3, 1).clone();
		QuadModel q2 = grid2.getQuad(1, 1);
		q1.rotateCounterclockwise();
		assertTrue(q1.equals(q2));

		assertFalse(grid1.equals(grid2));
		assertEquals(grid2.countConnections(), grid2.countPairs());
		grid2.rotateCounterclockwise();
		assertFalse(grid1.equals(grid2));
		assertEquals(grid2.countConnections(), grid2.countPairs());
		grid2.rotateCounterclockwise();
		assertFalse(grid1.equals(grid2));
		assertEquals(grid2.countConnections(), grid2.countPairs());
		grid2.rotateCounterclockwise();
		assertTrue(grid1.equals(grid2));
		assertEquals(grid2.countConnections(), grid2.countPairs());
	}

	@Test
	public void testRotateCenterClockwise() {
		GridModel grid1 = new GridModel(5);
		GridFiller filler = new GridFiller(grid1);
		filler.FillRandomAsymetric(7);
		GridModel grid2 = grid1.clone();

		assertTrue(grid1.equals(grid2));
		grid2.rotateCenterClockwise();
		assertFalse(grid1.equals(grid2));
		grid2.rotateCenterClockwise();
		assertFalse(grid1.equals(grid2));
		grid2.rotateCenterClockwise();
		assertFalse(grid1.equals(grid2));
		grid2.rotateCenterClockwise();
		assertTrue(grid1.equals(grid2));
	}

	@Test
	public void testRotateCenterCounterclockwise() {
		GridModel grid1 = new GridModel(5);
		GridFiller filler = new GridFiller(grid1);
		filler.FillRandomAsymetric(7);
		GridModel grid2 = grid1.clone();

		assertTrue(grid1.equals(grid2));
		grid2.rotateCenterCounterclockwise();
		assertFalse(grid1.equals(grid2));
		grid2.rotateCenterCounterclockwise();
		assertFalse(grid1.equals(grid2));
		grid2.rotateCenterCounterclockwise();
		assertFalse(grid1.equals(grid2));
		grid2.rotateCenterCounterclockwise();
		assertTrue(grid1.equals(grid2));
	}

	@Test
	public void testFlipHorizontal() {
		GridModel grid1 = new GridModel(5);
		GridFiller filler = new GridFiller(grid1);
		filler.FillRandomAsymetric(7);
		GridModel grid2 = grid1.clone();

		assertTrue(grid1.equals(grid2));

		grid2.flipHorizontal();

		assertFalse(grid1.equals(grid2));
		assertEquals(grid2.countConnections(), grid2.countPairs());

		grid2.flipHorizontal();

		assertEquals(grid2.countConnections(), grid2.countPairs());
		assertTrue(grid1.equals(grid2));
	}

	@Test
	public void testFlipVertical() {
		GridModel grid1 = new GridModel(5);
		GridFiller filler = new GridFiller(grid1);
		filler.FillRandomAsymetric(7);
		GridModel grid2 = grid1.clone();

		assertTrue(grid1.equals(grid2));

		grid2.flipVertical();

		assertFalse(grid1.equals(grid2));
		assertEquals(grid2.countConnections(), grid2.countPairs());

		grid2.flipVertical();

		assertEquals(grid2.countConnections(), grid2.countPairs());
		assertTrue(grid1.equals(grid2));
	}

	@Test
	public void testReadOnly() {
		GridModel grid = new GridModel(4);
		grid.getQuad(4).setPattern(QuadModel.DIR_NORTH, Pattern.getDefaultPattern());
		grid.shuffle();

		grid.setReadOnly(true);

		try {
			grid.shuffle();
			fail();
		} catch (AssertionError e) {
			// Dp nothing
		}

		grid.countPairs();

		try {
			grid.getQuad(4).setPattern(QuadModel.DIR_NORTH, Pattern.getDefaultPattern());
			fail();
		} catch (AssertionError e) {
			// Dp nothing
		}

		grid.setReadOnly(false);

		grid.shuffle();
	}

	@Test
	public void testMissing() {
		Pattern pattern = Pattern.PAT_01;
		GridModel grid = new GridModel(4);
		grid.getQuad(1).setPattern(QuadModel.DIR_WEST, pattern);
		QuadModel quad = grid.getMissingQuad(0);
		assertEquals(Pattern.getDefaultPattern(), quad.getPattern(QuadModel.DIR_NORTH));
		assertEquals(pattern, quad.getPattern(QuadModel.DIR_EAST));
		assertEquals(null, quad.getPattern(QuadModel.DIR_SOUTH));
		assertEquals(Pattern.getDefaultPattern(), quad.getPattern(QuadModel.DIR_WEST));
	}

	@Test
	public void testOptimize() throws IOException, QuadsFormatException {
		GridModel grid = new GridModel();
		GridLoader loader = new GridLoader(grid);
		loader.load("Test_Grid_06x06x08.txt");

		QuadModel quad = grid.getQuad(12);
		quad.setPattern(QuadModel.DIR_NORTH, Pattern.PAT_12);
		quad.setPattern(QuadModel.DIR_SOUTH, Pattern.PAT_19);
		quad.setPattern(QuadModel.DIR_EAST, Pattern.PAT_31);

		int rotations = grid.optimizeQuadRotation(12);
		assertEquals(0, rotations);
		assertEquals(Pattern.getDefaultPattern(), quad.getPattern(QuadModel.DIR_WEST));
	}
}
