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

package org.alcibiade.eternity.editor.model.operation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.model.PatternStats;
import org.junit.Test;

public class GridFillerTest {

	@Test
	public void testFillRandom() {
		for (int i = 0; i < 1000; i++) {
			GridModel g = new GridModel(6);
			GridFiller filler = new GridFiller(g);
			filler.fillRandom(18);
			PatternStats stats = g.getPatternStats();

			assertEquals(18, stats.getPatterns().size());
		}
	}

	@Test
	public void testASymetricFill() {
		for (int i = 0; i < 1000; i++) {
			GridModel g = new GridModel(4);
			GridFiller filler = new GridFiller(g);
			filler.FillRandomAsymetric(2);

			assertFalse(g.isHSymetric());
			assertFalse(g.isVSymetric());
			assertFalse(g.isRSymetric());
		}
	}

	@Test
	public void testFillBorderRandom() {
		for (int i = 0; i < 1000; i++) {
			GridModel g = new GridModel(6);
			GridFiller filler = new GridFiller(g);
			filler.FillBorderRandom(5);
			PatternStats stats = g.getPatternStats();
			assertEquals(5, stats.getPatterns().size());
			assertEquals(0, stats.getInnerPatterns().size());
			assertEquals(5, stats.getOuterPatterns().size());
		}
	}

	@Test
	public void testFillCenterRandom() {
		for (int i = 0; i < 1000; i++) {
			GridModel g = new GridModel(6);
			GridFiller filler = new GridFiller(g);
			filler.FillCenterRandom(5);
			PatternStats stats = g.getPatternStats();
			assertEquals(5, stats.getPatterns().size());
			assertEquals(5, stats.getInnerPatterns().size());
			assertEquals(0, stats.getOuterPatterns().size());
		}
	}

	@Test
	public void testFillBothRandom() {
		for (int i = 0; i < 1000; i++) {
			GridModel g = new GridModel(6);
			GridFiller filler = new GridFiller(g);
			filler.FillCenterRandom(5);
			filler.FillBorderRandom(5);
			PatternStats stats = g.getPatternStats();
			assertEquals(10, stats.getPatterns().size());
			assertEquals(5, stats.getInnerPatterns().size());
			assertEquals(5, stats.getOuterPatterns().size());
		}
	}

}
