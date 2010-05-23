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
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.model.PatternStats;
import org.alcibiade.eternity.editor.model.QuadsFormatException;
import org.junit.Test;

public class GridLoaderTest {

	@Test
	public void testLoadClasspath() throws IOException, QuadsFormatException {
		GridModel grid = new GridModel();
		GridLoader loader = new GridLoader(grid);
		loader.load("Test_Grid_06x06x08.txt");
		PatternStats stats = grid.getPatternStats();
		assertEquals(6, grid.getSize());
		assertEquals(8, stats.getPatterns().size());
		assertTrue(grid.isComplete());
	}

	@Test
	public void testLoadFile() throws IOException, QuadsFormatException {
		File tempFile = File.createTempFile("grid", ".txt");
		tempFile.deleteOnExit();

		GridModel srcGrid = new GridModel(5);
		GridFiller filler = new GridFiller(srcGrid);
		filler.fillRandom(6);

		String quadString = srcGrid.toQuadString();
		FileWriter fileWriter = new FileWriter(tempFile);
		fileWriter.write(quadString);
		fileWriter.flush();
		fileWriter.close();

		GridModel dstGrid = new GridModel();
		GridLoader loader = new GridLoader(dstGrid);
		loader.load(tempFile);
		tempFile.delete();

		assertEquals(srcGrid, dstGrid);
	}
}
