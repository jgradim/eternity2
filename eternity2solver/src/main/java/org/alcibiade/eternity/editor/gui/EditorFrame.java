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

package org.alcibiade.eternity.editor.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import org.alcibiade.eternity.editor.gui.action.AboutAction;
import org.alcibiade.eternity.editor.gui.action.CopyGridAction;
import org.alcibiade.eternity.editor.gui.action.E2CloneGridAction;
import org.alcibiade.eternity.editor.gui.action.ExitAction;
import org.alcibiade.eternity.editor.gui.action.FileNewAction;
import org.alcibiade.eternity.editor.gui.action.FileOpenAction;
import org.alcibiade.eternity.editor.gui.action.FileSaveAsAction;
import org.alcibiade.eternity.editor.gui.action.FillGridAction;
import org.alcibiade.eternity.editor.gui.action.FillGridBorderAction;
import org.alcibiade.eternity.editor.gui.action.FillGridCenterAction;
import org.alcibiade.eternity.editor.gui.action.FlipGridAction;
import org.alcibiade.eternity.editor.gui.action.LoadGridAction;
import org.alcibiade.eternity.editor.gui.action.PasteGridAction;
import org.alcibiade.eternity.editor.gui.action.ResetGridAction;
import org.alcibiade.eternity.editor.gui.action.ResizeGridAction;
import org.alcibiade.eternity.editor.gui.action.RotateGridAction;
import org.alcibiade.eternity.editor.gui.action.RotateGridCenterAction;
import org.alcibiade.eternity.editor.gui.action.ShuffleGridAction;
import org.alcibiade.eternity.editor.gui.action.SwitchQuadIdsGridAction;
import org.alcibiade.eternity.editor.model.GridModel;

public class EditorFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final int DEFAULT_SIZE = 7;

	public EditorFrame() {
		super("Eternity II");

		// -- Grid components --

		GridModel gridmodel = new GridModel(DEFAULT_SIZE);

		GridView grid = new GridView(gridmodel);
		grid.setPreferredSize(new Dimension(750, 750));

		// -- Info panel --

		Document scoreDocument = new PlainDocument();
		Document statusDocument = new PlainDocument();
		Document infosDocument = new PlainDocument();

		JTextField statusTf = new JTextField(statusDocument, null, 16);
		JTextField scoreTf = new JTextField(scoreDocument, null, 16);
		JTextField infosTf = new JTextField(infosDocument, null, 16);
		statusTf.setEditable(false);
		scoreTf.setEditable(false);
		infosTf.setEditable(false);

		JPanel labels = new JPanel(new GridLayout(3, 1));
		labels.add(new JLabel("Grid infos"));
		labels.add(new JLabel("Grid status"));
		labels.add(new JLabel("Score"));
		labels.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		JPanel infos = new JPanel(new GridLayout(3, 1));
		infos.add(infosTf);
		infos.add(statusTf);
		infos.add(scoreTf);
		infos.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		JCheckBox showIds = new JCheckBox(new SwitchQuadIdsGridAction(grid));

		JPanel infoPanel = new JPanel(new BorderLayout());
		infoPanel.add(BorderLayout.WEST, labels);
		infoPanel.add(BorderLayout.CENTER, infos);
		infoPanel.add(BorderLayout.SOUTH, showIds);

		infoPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Grid Informations"), BorderFactory
						.createEmptyBorder(5, 5, 5, 5)), infoPanel.getBorder()));

		// -- Solver panel --

		JPanel solvePanel = new SolverManager(grid);

		// -- Main frame --

		JPanel partRight = new JPanel(new BorderLayout());
		partRight.add(BorderLayout.NORTH, infoPanel);
		partRight.add(BorderLayout.CENTER, solvePanel);

		JPanel frameContent = new JPanel(new BorderLayout());
		frameContent.add(BorderLayout.CENTER, grid);
		frameContent.add(BorderLayout.EAST, partRight);

		new GridAnalyst(grid, scoreDocument, statusDocument, infosDocument, showIds);

		setContentPane(frameContent);
		setJMenuBar(buildMenuBar(grid));

		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Dimension mysize = getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenSize.width / 2 - (mysize.width / 2), screenSize.height / 2
				- (mysize.height / 2));
	}

	private JMenuBar buildMenuBar(GridView gridView) {
		int menuShortcutKeyMask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

		GridModel gridModel = gridView.getGridModel();
		JMenuBar menubar = new JMenuBar();

		JMenu mFile = new JMenu("File");
		JMenuItem itemNew = new JMenuItem(new FileNewAction(gridModel, gridView, DEFAULT_SIZE));
		mFile.add(itemNew);
		JMenuItem itemOpen = new JMenuItem(new FileOpenAction(gridModel, gridView, this));
		itemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, menuShortcutKeyMask));
		mFile.add(itemOpen);
		JMenuItem itemSaveAs = new JMenuItem(new FileSaveAsAction(gridModel, gridView, this));
		itemSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, menuShortcutKeyMask));
		mFile.add(itemSaveAs);
		JMenuItem itemQuit = new JMenuItem(new ExitAction());
		itemQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, menuShortcutKeyMask));

		mFile.add(itemQuit);

		JMenu mEdit = new JMenu("Edit");
		JMenuItem itemCopy = new JMenuItem(new CopyGridAction(gridModel));
		itemCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, menuShortcutKeyMask));
		mEdit.add(itemCopy);
		JMenuItem itemPaste = new JMenuItem(new PasteGridAction(gridModel, gridView));
		itemPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, menuShortcutKeyMask));
		mEdit.add(itemPaste);

		JMenu mGrid = new JMenu("Grid");

		mGrid.add(new JMenuItem(new ResetGridAction(gridModel, gridView)));
		mGrid.add(new JMenuItem(new ShuffleGridAction(gridModel, gridView)));

		JMenu mgRotate = new JMenu("Rotate");
		mGrid.add(mgRotate);

		mgRotate.add(new JMenuItem(new RotateGridAction(gridModel, true, gridView)));
		mgRotate.add(new JMenuItem(new RotateGridAction(gridModel, false, gridView)));
		mgRotate.add(new JMenuItem(new RotateGridCenterAction(gridModel, true, gridView)));
		mgRotate.add(new JMenuItem(new RotateGridCenterAction(gridModel, false, gridView)));

		JMenu mgFlip = new JMenu("Flip");
		mGrid.add(mgFlip);

		mgFlip.add(new JMenuItem(new FlipGridAction(gridModel, true, gridView)));
		mgFlip.add(new JMenuItem(new FlipGridAction(gridModel, false, gridView)));

		JMenu mgSize = new JMenu("Size");
		mGrid.add(mgSize);

		mgSize.add(new JMenuItem(new ResizeGridAction(gridModel, 2, gridView)));
		mgSize.add(new JMenuItem(new ResizeGridAction(gridModel, 3, gridView)));
		mgSize.add(new JMenuItem(new ResizeGridAction(gridModel, 4, gridView)));
		mgSize.add(new JMenuItem(new ResizeGridAction(gridModel, 5, gridView)));
		mgSize.add(new JMenuItem(new ResizeGridAction(gridModel, 6, gridView)));
		mgSize.add(new JMenuItem(new ResizeGridAction(gridModel, 7, gridView)));
		mgSize.add(new JMenuItem(new ResizeGridAction(gridModel, 8, gridView)));
		mgSize.add(new JMenuItem(new ResizeGridAction(gridModel, 9, gridView)));
		mgSize.add(new JMenuItem(new ResizeGridAction(gridModel, 10, gridView)));
		mgSize.add(new JMenuItem(new ResizeGridAction(gridModel, 11, gridView)));
		mgSize.add(new JMenuItem(new ResizeGridAction(gridModel, 12, gridView)));
		mgSize.add(new JMenuItem(new ResizeGridAction(gridModel, 13, gridView)));
		mgSize.add(new JMenuItem(new ResizeGridAction(gridModel, 14, gridView)));
		mgSize.add(new JMenuItem(new ResizeGridAction(gridModel, 15, gridView)));
		mgSize.add(new JMenuItem(new ResizeGridAction(gridModel, 16, gridView)));

		JMenu mgFB = new JMenu("Whole board");
		mgFB.add(new JMenuItem(new FillGridAction(gridModel, 2, gridView)));
		mgFB.add(new JMenuItem(new FillGridAction(gridModel, 3, gridView)));
		mgFB.add(new JMenuItem(new FillGridAction(gridModel, 4, gridView)));
		mgFB.add(new JMenuItem(new FillGridAction(gridModel, 5, gridView)));
		mgFB.add(new JMenuItem(new FillGridAction(gridModel, 6, gridView)));
		mgFB.add(new JMenuItem(new FillGridAction(gridModel, 7, gridView)));
		mgFB.add(new JMenuItem(new FillGridAction(gridModel, 8, gridView)));
		mgFB.add(new JMenuItem(new FillGridAction(gridModel, 9, gridView)));
		mgFB.add(new JMenuItem(new FillGridAction(gridModel, 10, gridView)));
		mgFB.add(new JMenuItem(new FillGridAction(gridModel, 11, gridView)));
		mgFB.add(new JMenuItem(new FillGridAction(gridModel, 12, gridView)));
		mgFB.add(new JMenuItem(new FillGridAction(gridModel, 13, gridView)));
		mgFB.add(new JMenuItem(new FillGridAction(gridModel, 14, gridView)));
		mgFB.add(new JMenuItem(new FillGridAction(gridModel, 15, gridView)));
		mgFB.add(new JMenuItem(new FillGridAction(gridModel, 16, gridView)));
		mgFB.add(new JMenuItem(new FillGridAction(gridModel, 17, gridView)));
		mgFB.add(new JMenuItem(new FillGridAction(gridModel, 18, gridView)));
		mgFB.add(new JMenuItem(new FillGridAction(gridModel, 19, gridView)));
		mgFB.add(new JMenuItem(new FillGridAction(gridModel, 20, gridView)));
		mgFB.add(new JMenuItem(new FillGridAction(gridModel, 21, gridView)));
		mgFB.add(new JMenuItem(new FillGridAction(gridModel, 22, gridView)));
		mgFB.add(new JMenuItem(new FillGridAction(gridModel, 23, gridView)));
		mgFB.add(new JMenuItem(new FillGridAction(gridModel, 24, gridView)));
		mgFB.add(new JMenuItem(new FillGridAction(gridModel, 25, gridView)));
		mgFB.add(new JMenuItem(new FillGridAction(gridModel, 26, gridView)));
		mgFB.add(new JMenuItem(new FillGridAction(gridModel, 27, gridView)));
		mgFB.add(new JMenuItem(new FillGridAction(gridModel, 28, gridView)));
		mgFB.add(new JMenuItem(new FillGridAction(gridModel, 29, gridView)));
		mgFB.add(new JMenuItem(new FillGridAction(gridModel, 30, gridView)));

		JMenu mgFO = new JMenu("Border");
		mgFO.add(new JMenuItem(new FillGridBorderAction(gridModel, 2, gridView)));
		mgFO.add(new JMenuItem(new FillGridBorderAction(gridModel, 3, gridView)));
		mgFO.add(new JMenuItem(new FillGridBorderAction(gridModel, 4, gridView)));
		mgFO.add(new JMenuItem(new FillGridBorderAction(gridModel, 5, gridView)));
		mgFO.add(new JMenuItem(new FillGridBorderAction(gridModel, 6, gridView)));
		mgFO.add(new JMenuItem(new FillGridBorderAction(gridModel, 7, gridView)));
		mgFO.add(new JMenuItem(new FillGridBorderAction(gridModel, 8, gridView)));
		mgFO.add(new JMenuItem(new FillGridBorderAction(gridModel, 9, gridView)));
		mgFO.add(new JMenuItem(new FillGridBorderAction(gridModel, 10, gridView)));
		mgFO.add(new JMenuItem(new FillGridBorderAction(gridModel, 11, gridView)));
		mgFO.add(new JMenuItem(new FillGridBorderAction(gridModel, 12, gridView)));
		mgFO.add(new JMenuItem(new FillGridBorderAction(gridModel, 13, gridView)));
		mgFO.add(new JMenuItem(new FillGridBorderAction(gridModel, 14, gridView)));
		mgFO.add(new JMenuItem(new FillGridBorderAction(gridModel, 15, gridView)));
		mgFO.add(new JMenuItem(new FillGridBorderAction(gridModel, 16, gridView)));
		mgFO.add(new JMenuItem(new FillGridBorderAction(gridModel, 17, gridView)));
		mgFO.add(new JMenuItem(new FillGridBorderAction(gridModel, 18, gridView)));
		mgFO.add(new JMenuItem(new FillGridBorderAction(gridModel, 19, gridView)));
		mgFO.add(new JMenuItem(new FillGridBorderAction(gridModel, 20, gridView)));
		mgFO.add(new JMenuItem(new FillGridBorderAction(gridModel, 21, gridView)));
		mgFO.add(new JMenuItem(new FillGridBorderAction(gridModel, 22, gridView)));
		mgFO.add(new JMenuItem(new FillGridBorderAction(gridModel, 23, gridView)));
		mgFO.add(new JMenuItem(new FillGridBorderAction(gridModel, 24, gridView)));
		mgFO.add(new JMenuItem(new FillGridBorderAction(gridModel, 25, gridView)));
		mgFO.add(new JMenuItem(new FillGridBorderAction(gridModel, 26, gridView)));
		mgFO.add(new JMenuItem(new FillGridBorderAction(gridModel, 27, gridView)));
		mgFO.add(new JMenuItem(new FillGridBorderAction(gridModel, 28, gridView)));
		mgFO.add(new JMenuItem(new FillGridBorderAction(gridModel, 29, gridView)));
		mgFO.add(new JMenuItem(new FillGridBorderAction(gridModel, 30, gridView)));

		JMenu mgFI = new JMenu("Center");
		mgFI.add(new JMenuItem(new FillGridCenterAction(gridModel, 2, gridView)));
		mgFI.add(new JMenuItem(new FillGridCenterAction(gridModel, 3, gridView)));
		mgFI.add(new JMenuItem(new FillGridCenterAction(gridModel, 4, gridView)));
		mgFI.add(new JMenuItem(new FillGridCenterAction(gridModel, 5, gridView)));
		mgFI.add(new JMenuItem(new FillGridCenterAction(gridModel, 6, gridView)));
		mgFI.add(new JMenuItem(new FillGridCenterAction(gridModel, 7, gridView)));
		mgFI.add(new JMenuItem(new FillGridCenterAction(gridModel, 8, gridView)));
		mgFI.add(new JMenuItem(new FillGridCenterAction(gridModel, 9, gridView)));
		mgFI.add(new JMenuItem(new FillGridCenterAction(gridModel, 10, gridView)));
		mgFI.add(new JMenuItem(new FillGridCenterAction(gridModel, 11, gridView)));
		mgFI.add(new JMenuItem(new FillGridCenterAction(gridModel, 12, gridView)));
		mgFI.add(new JMenuItem(new FillGridCenterAction(gridModel, 13, gridView)));
		mgFI.add(new JMenuItem(new FillGridCenterAction(gridModel, 14, gridView)));
		mgFI.add(new JMenuItem(new FillGridCenterAction(gridModel, 15, gridView)));
		mgFI.add(new JMenuItem(new FillGridCenterAction(gridModel, 16, gridView)));
		mgFI.add(new JMenuItem(new FillGridCenterAction(gridModel, 17, gridView)));
		mgFI.add(new JMenuItem(new FillGridCenterAction(gridModel, 18, gridView)));
		mgFI.add(new JMenuItem(new FillGridCenterAction(gridModel, 19, gridView)));
		mgFI.add(new JMenuItem(new FillGridCenterAction(gridModel, 20, gridView)));
		mgFI.add(new JMenuItem(new FillGridCenterAction(gridModel, 21, gridView)));
		mgFI.add(new JMenuItem(new FillGridCenterAction(gridModel, 22, gridView)));
		mgFI.add(new JMenuItem(new FillGridCenterAction(gridModel, 23, gridView)));
		mgFI.add(new JMenuItem(new FillGridCenterAction(gridModel, 24, gridView)));
		mgFI.add(new JMenuItem(new FillGridCenterAction(gridModel, 25, gridView)));
		mgFI.add(new JMenuItem(new FillGridCenterAction(gridModel, 26, gridView)));
		mgFI.add(new JMenuItem(new FillGridCenterAction(gridModel, 27, gridView)));
		mgFI.add(new JMenuItem(new FillGridCenterAction(gridModel, 28, gridView)));
		mgFI.add(new JMenuItem(new FillGridCenterAction(gridModel, 29, gridView)));
		mgFI.add(new JMenuItem(new FillGridCenterAction(gridModel, 30, gridView)));

		JMenu mgFill = new JMenu("Auto Fill");
		mgFill.add(mgFB);
		mgFill.add(mgFO);
		mgFill.add(mgFI);
		mgFill.add(new E2CloneGridAction(gridModel, gridView));

		mGrid.add(mgFill);

		JMenu mgLoad = new JMenu("Load model");
		mGrid.add(mgLoad);

		mgLoad.add(new JMenuItem(new LoadGridAction(gridModel,
				"org/alcibiade/eternity/editor/models/model_yannick_0204.txt", "2x2x4 Original",
				gridView)));
		mgLoad.add(new JMenuItem(new LoadGridAction(gridModel,
				"org/alcibiade/eternity/editor/models/model_yannick_0304.txt", "3x3x4 Original",
				gridView)));
		// mgLoad.add(new JMenuItem(new LoadGridAction(gridmodel,
		// "model_web_0404.txt", "4x4x4 Puzzle Web")));
		mgLoad.add(new JMenuItem(new LoadGridAction(gridModel,
				"org/alcibiade/eternity/editor/models/model_yannick_0504.txt", "5x5x4 Original",
				gridView)));
		mgLoad.add(new JMenuItem(new LoadGridAction(gridModel,
				"org/alcibiade/eternity/editor/models/model_yannick_0505.txt", "5x5x5 Original",
				gridView)));
		mgLoad.add(new JMenuItem(new LoadGridAction(gridModel,
				"org/alcibiade/eternity/editor/models/model_yannick_0604.txt", "6x6x4 Original",
				gridView)));
		mgLoad.add(new JMenuItem(new LoadGridAction(gridModel,
				"org/alcibiade/eternity/editor/models/model_yannick_0606.txt", "6x6x6 Original",
				gridView)));
		mgLoad.add(new JMenuItem(new LoadGridAction(gridModel,
				"org/alcibiade/eternity/editor/models/model_yannick_0704.txt", "7x7x4 Original",
				gridView)));
		mgLoad.add(new JMenuItem(new LoadGridAction(gridModel,
				"org/alcibiade/eternity/editor/models/model_yannick_0706.txt", "7x7x6 Original",
				gridView)));
		mgLoad.add(new JMenuItem(new LoadGridAction(gridModel,
				"org/alcibiade/eternity/editor/models/model_yannick_1015.txt", "10x10x15 Original",
				gridView)));
		mgLoad.add(new JMenuItem(new LoadGridAction(gridModel,
				"org/alcibiade/eternity/editor/models/model_yannick_1116.txt", "11x11x16 Original",
				gridView)));
		mgLoad.add(new JMenuItem(new LoadGridAction(gridModel,
				"org/alcibiade/eternity/editor/models/model_yannick_1218.txt", "12x12x18 Original",
				gridView)));
		mgLoad.add(new JMenuItem(new LoadGridAction(gridModel,
				"org/alcibiade/eternity/editor/models/model_yannick_1623.txt", "16x16x23 Original",
				gridView)));

		JMenu mInfo = new JMenu("?");
		mInfo.add(new JMenuItem(new AboutAction(this)));

		menubar.add(mFile);
		menubar.add(mEdit);
		menubar.add(mGrid);
		menubar.add(mInfo);

		return menubar;
	}
}
