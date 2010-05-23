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

package org.alcibiade.eternity.editor.gui.action;

import java.awt.event.ActionEvent;
import java.io.IOException;

import org.alcibiade.eternity.editor.gui.EditableStatusProvider;
import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.model.QuadsFormatException;
import org.alcibiade.eternity.editor.model.operation.GridLoader;

public class LoadGridAction extends GridUpdateAction {
	private static final long serialVersionUID = 1L;
	private GridLoader loader;
	private String fileName;

	public LoadGridAction(GridModel gridModel, String filename, String modelname,
			EditableStatusProvider editable) {
		super(modelname, editable);
		loader = new GridLoader(gridModel);
		this.fileName = filename;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			loader.load(fileName);
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (QuadsFormatException ex) {
			ex.printStackTrace();
		}
	}

}
