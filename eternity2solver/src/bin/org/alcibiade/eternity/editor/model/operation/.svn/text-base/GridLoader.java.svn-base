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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.alcibiade.eternity.editor.io.IOUtil;
import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.model.QuadsFormatException;

public class GridLoader extends GridOperation {

	public GridLoader(GridModel gridModel) {
		super(gridModel);
	}

	public void load(String fileName) throws IOException, QuadsFormatException {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(fileName);
		load(is);
	}

	public void load(File file) throws IOException, QuadsFormatException {
		InputStream is = new FileInputStream(file);
		load(is);
	}

	private void load(InputStream is) throws IOException, QuadsFormatException {
		String text = IOUtil.toString(is);
		is.close();
		getModel().fromQuadString(text);
	}

}
