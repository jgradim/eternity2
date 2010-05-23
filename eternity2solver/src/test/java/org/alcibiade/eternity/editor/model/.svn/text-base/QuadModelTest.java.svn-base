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

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QuadModelTest {
	private List<Pattern> patterns;
	private Pattern defaultPattern;

	@Before
	public void setUp() throws Exception {
		patterns = new ArrayList<Pattern>(Pattern.getAllPatterns());
		defaultPattern = Pattern.getDefaultPattern();
		patterns.remove(defaultPattern);
	}

	@After
	public void tearDown() throws Exception {
		patterns = null;
		defaultPattern = null;
	}

	@Test
	public void testEmptyQuad() {
		QuadModel quad = new QuadModel();
		assertTrue(quad.isClear());

		quad.setPattern(QuadModel.DIR_NORTH, patterns.get(1));
		assertFalse(quad.isClear());

		quad.setPattern(QuadModel.DIR_NORTH, defaultPattern);
		assertTrue(quad.isClear());
	}

	@Test
	public void testLocking() {
		QuadModel quad = new QuadModel();
		assertFalse(quad.isLocked());
		quad.setLocked(true);
		assertTrue(quad.isLocked());
		quad.setLocked(false);
		assertFalse(quad.isLocked());
	}

	@Test
	public void testPatterns() {
		QuadModel quad = new QuadModel();
		quad.setPattern(QuadModel.DIR_NORTH, patterns.get(1));
		quad.setPattern(QuadModel.DIR_EAST, patterns.get(2));
		quad.setPattern(QuadModel.DIR_SOUTH, patterns.get(3));
		quad.setPattern(QuadModel.DIR_WEST, patterns.get(4));
		assertEquals(patterns.get(1), quad.getPattern(QuadModel.DIR_NORTH));
		assertEquals(patterns.get(2), quad.getPattern(QuadModel.DIR_EAST));
		assertEquals(patterns.get(3), quad.getPattern(QuadModel.DIR_SOUTH));
		assertEquals(patterns.get(4), quad.getPattern(QuadModel.DIR_WEST));
	}

	@Test
	public void testCopy() {
		QuadModel quad = new QuadModel();
		quad.setPattern(QuadModel.DIR_NORTH, patterns.get(1));
		quad.setPattern(QuadModel.DIR_EAST, patterns.get(2));
		quad.setPattern(QuadModel.DIR_SOUTH, patterns.get(3));
		quad.setPattern(QuadModel.DIR_WEST, patterns.get(4));

		QuadModel copy = new QuadModel();
		quad.copyTo(copy);
		assertTrue(quad.equals(copy));

		copy.setPattern(QuadModel.DIR_SOUTH, patterns.get(5));
		assertFalse(quad.equals(copy));
	}

	@Test
	public void testClone() {
		QuadModel quad = new QuadModel();
		quad.setPattern(QuadModel.DIR_NORTH, patterns.get(1));
		quad.setPattern(QuadModel.DIR_EAST, patterns.get(2));
		quad.setPattern(QuadModel.DIR_SOUTH, patterns.get(3));
		quad.setPattern(QuadModel.DIR_WEST, patterns.get(4));

		QuadModel clone = quad.clone();
		assertTrue(quad.equals(clone));

		clone.setPattern(QuadModel.DIR_SOUTH, patterns.get(5));
		assertFalse(quad.equals(clone));
	}

	@Test
	public void testMatchDegrees() {
		QuadModel quad = new QuadModel();
		quad.setPattern(QuadModel.DIR_NORTH, patterns.get(1));
		quad.setPattern(QuadModel.DIR_EAST, patterns.get(2));
		quad.setPattern(QuadModel.DIR_SOUTH, patterns.get(3));
		quad.setPattern(QuadModel.DIR_WEST, patterns.get(4));

		QuadModel quadEmpty = new QuadModel();
		assertEquals(0, quad.matchDegrees(quadEmpty));

		QuadModel quadRot = quad.clone();
		quadRot.rotateClockwise(2);
		assertEquals(4, quad.matchDegrees(quadRot));

		QuadModel quad2 = new QuadModel();
		quad2.setPattern(QuadModel.DIR_NORTH, patterns.get(5));
		quad2.setPattern(QuadModel.DIR_EAST, patterns.get(2));
		quad2.setPattern(QuadModel.DIR_SOUTH, patterns.get(6));
		quad2.setPattern(QuadModel.DIR_WEST, patterns.get(4));
		quad2.rotateCounterclockwise();
		assertEquals(2, quad.matchDegrees(quad2));
	}

	@Test
	public void testEquals() {
		QuadModel quad = new QuadModel();
		quad.setPattern(QuadModel.DIR_NORTH, patterns.get(1));
		quad.setPattern(QuadModel.DIR_EAST, patterns.get(2));
		quad.setPattern(QuadModel.DIR_SOUTH, patterns.get(3));
		quad.setPattern(QuadModel.DIR_WEST, patterns.get(4));

		QuadModel quadRot = quad.clone();
		assertTrue(quad.equals(quadRot));
		assertTrue(quad.equalsIgnoreRotation(quadRot));

		quadRot.rotateClockwise(2);
		assertFalse(quad.equals(quadRot));
		assertTrue(quad.equalsIgnoreRotation(quadRot));
	}

	@Test
	public void testMatches() {
		QuadModel quad1 = new QuadModel(Pattern.PAT_00, Pattern.PAT_01, Pattern.PAT_02,
				Pattern.PAT_03);
		QuadModel quad2 = new QuadModel(Pattern.PAT_00, Pattern.PAT_01, Pattern.PAT_02,
				Pattern.PAT_03);
		assertTrue(quad1.matches(quad2));
		assertTrue(quad2.matches(quad1));

		quad2.setPattern(1, null);
		assertTrue(quad1.matches(quad2));
		assertTrue(quad2.matches(quad1));

		quad2.setPattern(2, Pattern.PAT_09);
		assertFalse(quad1.matches(quad2));
		assertFalse(quad2.matches(quad1));
	}

	@Test
	public void testEqualsIgnoreRotationInteger() {
		QuadModel quad = new QuadModel();
		quad.setPattern(QuadModel.DIR_NORTH, patterns.get(1));
		quad.setPattern(QuadModel.DIR_EAST, patterns.get(2));
		quad.setPattern(QuadModel.DIR_SOUTH, patterns.get(3));
		quad.setPattern(QuadModel.DIR_WEST, patterns.get(4));

		QuadModel quadRot = new QuadModel();
		quadRot.setPattern(QuadModel.DIR_NORTH, patterns.get(1));
		quadRot.setPattern(QuadModel.DIR_EAST, patterns.get(5));
		quadRot.setPattern(QuadModel.DIR_SOUTH, patterns.get(3));
		quadRot.setPattern(QuadModel.DIR_WEST, patterns.get(6));
		quadRot.rotateClockwise();

		assertTrue(quad.equalsIgnoreRotation(quadRot, 0));
		assertTrue(quad.equalsIgnoreRotation(quadRot, 1));
		assertTrue(quad.equalsIgnoreRotation(quadRot, 2));
		assertFalse(quad.equalsIgnoreRotation(quadRot, 3));
		assertFalse(quad.equalsIgnoreRotation(quadRot, 4));
	}

	@Test
	public void testFlipHorizontal() {
		QuadModel quad = new QuadModel();
		quad.setPattern(QuadModel.DIR_NORTH, patterns.get(1));
		quad.setPattern(QuadModel.DIR_EAST, patterns.get(2));
		quad.setPattern(QuadModel.DIR_SOUTH, patterns.get(3));
		quad.setPattern(QuadModel.DIR_WEST, patterns.get(4));

		QuadModel quadf = new QuadModel();
		quadf.setPattern(QuadModel.DIR_NORTH, patterns.get(1));
		quadf.setPattern(QuadModel.DIR_EAST, patterns.get(4));
		quadf.setPattern(QuadModel.DIR_SOUTH, patterns.get(3));
		quadf.setPattern(QuadModel.DIR_WEST, patterns.get(2));

		quad.flipHorizontal();
		assertEquals(quadf, quad);
	}

	@Test
	public void testFlipVertical() {
		QuadModel quad = new QuadModel();
		quad.setPattern(QuadModel.DIR_NORTH, patterns.get(1));
		quad.setPattern(QuadModel.DIR_EAST, patterns.get(2));
		quad.setPattern(QuadModel.DIR_SOUTH, patterns.get(3));
		quad.setPattern(QuadModel.DIR_WEST, patterns.get(4));

		QuadModel quadf = new QuadModel();
		quadf.setPattern(QuadModel.DIR_NORTH, patterns.get(3));
		quadf.setPattern(QuadModel.DIR_EAST, patterns.get(2));
		quadf.setPattern(QuadModel.DIR_SOUTH, patterns.get(1));
		quadf.setPattern(QuadModel.DIR_WEST, patterns.get(4));

		quad.flipVertical();
		assertEquals(quadf, quad);
	}

	@Test
	public void testReadOnly() {
		QuadModel quad = new QuadModel();
		quad.setPattern(QuadModel.DIR_EAST, Pattern.getDefaultPattern());

		quad.setReadOnly(true);

		try {
			quad.setPattern(QuadModel.DIR_EAST, Pattern.getDefaultPattern());
			fail();
		} catch (AssertionError e) {
			// Dp nothing
		}

		quad.setReadOnly(false);

		quad.setPattern(QuadModel.DIR_EAST, Pattern.getDefaultPattern());
	}

	@Test
	public void testNullMatching() {
		QuadModel quad1 = new QuadModel(Pattern.PAT_01, Pattern.PAT_02, Pattern.PAT_03,
				Pattern.PAT_04);
		QuadModel quad2 = new QuadModel(Pattern.PAT_05, Pattern.PAT_06, Pattern.PAT_07,
				Pattern.PAT_08);

		assertEquals(0, quad1.matchDegrees(quad2));
		assertEquals(0, quad2.matchDegrees(quad1));

		quad1.setPattern(0, null);
		assertEquals(1, quad1.matchDegrees(quad2));
		assertEquals(1, quad2.matchDegrees(quad1));

		quad2.setPattern(0, null);
		assertEquals(2, quad1.matchDegrees(quad2));
		assertEquals(2, quad2.matchDegrees(quad1));

		quad1.setPattern(2, null);
		assertEquals(3, quad1.matchDegrees(quad2));
		assertEquals(3, quad2.matchDegrees(quad1));

		quad1.setPattern(3, null);
		assertEquals(4, quad1.matchDegrees(quad2));
		assertEquals(4, quad2.matchDegrees(quad1));
	}
}
