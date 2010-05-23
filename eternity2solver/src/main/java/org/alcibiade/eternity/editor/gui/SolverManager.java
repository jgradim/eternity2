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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import org.alcibiade.eternity.editor.log.DocumentLog;
import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.model.GridScoreRecorder;
import org.alcibiade.eternity.editor.solver.ClusterListener;
import org.alcibiade.eternity.editor.solver.ClusterManager;
import org.alcibiade.eternity.editor.solver.EternitySolver;
import org.alcibiade.eternity.editor.solver.SolverFactory;
import org.alcibiade.eternity.editor.solver.SolverObserver;
import org.alcibiade.eternity.editor.solver.UnknownSolverException;
import org.alcibiade.eternity.editor.solver.path.PathFactory;
import org.alcibiade.eternity.editor.solver.path.PathProvider;
import org.alcibiade.eternity.editor.solver.path.UnknownPathException;

public class SolverManager extends JPanel implements ActionListener, SolverObserver, ItemListener,
		ClusterListener {

	private static final String LABEL_GO = "Start";
	private static final String LABEL_INT = "Interrupt";
	private static final boolean displayGraph = false;

	private static final long serialVersionUID = 1L;

	private JCheckBox solverslowmotion;
	private JCheckBox updateMainGrid;

	private JButton button = new JButton(LABEL_GO);

	private Document solLog;

	private JComboBox solversCombo;
	private JComboBox pathsCombo;

	private ClusterManager clusterManager = null;
	private EternitySolver solver = null;

	private GridView gridView;
	private GridModel problemGrid;
	private GridModel solutionGrid;

	public SolverManager(GridView gridView) {
		this.gridView = gridView;
		GridModel grid = gridView.getGridModel();
		problemGrid = grid;
		solutionGrid = new GridModel(grid.getSize());

		solversCombo = new JComboBox(new SolverComboBoxModel());
		pathsCombo = new JComboBox(new PathComboBoxModel());

		solversCombo.addItemListener(this);

		setLayout(new BorderLayout());

		button.addActionListener(this);

		solLog = new PlainDocument();
		JTextArea ta_log = new JTextArea(solLog);
		ta_log.setEditable(false);
		solLog.addDocumentListener(new Autoscroller(ta_log));
		JScrollPane sp_log = new JScrollPane(ta_log);
		sp_log.setPreferredSize(new Dimension(100, 100));

		GridView gridview = new GridView(solutionGrid);
		gridview.setPreferredSize(new Dimension(300, 300));
		gridview.setEditable(false);

		JPanel solverbuttons = new JPanel(new GridLayout());
		solverbuttons.add(button);

		solverslowmotion = new JCheckBox("Slow motion", false);
		solverslowmotion.addActionListener(this);

		updateMainGrid = new JCheckBox("Update main grid", false);

		JPanel items_north = new JPanel();
		items_north.setLayout(new BoxLayout(items_north, BoxLayout.PAGE_AXIS));

		JPanel items_south = new JPanel();
		items_south.setLayout(new BoxLayout(items_south, BoxLayout.PAGE_AXIS));

		items_north.add(gridview);

		if (displayGraph) {
			GridScoreRecorder recorder = new GridScoreRecorder(solutionGrid);
			GridScoreGraph scoregraph = new GridScoreGraph(recorder);
			scoregraph.setPreferredSize(new Dimension(300, 100));
			recorder.start();

			items_north.add(scoregraph);
		}

		JPanel switches = new JPanel(new GridLayout(1, 2));
		switches.add(solverslowmotion);
		switches.add(updateMainGrid);

		items_north.add(solversCombo);
		items_north.add(pathsCombo);

		items_south.add(switches);
		items_south.add(solverbuttons);

		add(BorderLayout.NORTH, items_north);
		add(BorderLayout.CENTER, sp_log);
		add(BorderLayout.SOUTH, items_south);

		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Solver"), BorderFactory.createEmptyBorder(15, 15,
						15, 15)), getBorder()));
	}

	public synchronized void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == solverslowmotion) {
			if (solver != null) {
				solver.setSlowMotion(solverslowmotion.isSelected());
			}
		} else {
			if (solver != null) {
				stopSolver();
			} else {
				startSolver();
			}
		}
	}

	public synchronized void startSolver() {
		String selectedsolver = (String) solversCombo.getSelectedItem();
		String selectedPath = (String) pathsCombo.getSelectedItem();

		try {
			if (selectedsolver != null) {
				PathProvider path = PathFactory.createPath(selectedPath);
				clusterManager = new ClusterManager(new DocumentLog(solLog));
				clusterManager.addClusterListener(this);
				solver = SolverFactory.createSolver(selectedsolver, problemGrid, solutionGrid,
						clusterManager, path);
			}

			gridView.setEditable(false);

			button.setText(LABEL_INT);
			solver.addSolverObserver(this);
			solver.setSlowMotion(solverslowmotion.isSelected());
			solver.start();
		} catch (UnknownSolverException e) {
			e.printStackTrace();
		} catch (UnknownPathException e) {
			e.printStackTrace();
		}
	}

	public synchronized void stopSolver() {
		solver.interrupt();
		solver = null;
	}

	private class SolverComboBoxModel extends DefaultComboBoxModel {
		private static final long serialVersionUID = 1L;

		public SolverComboBoxModel() {
			for (String solver : SolverFactory.getAvailableSolvers()) {
				addElement(solver);
			}
		}
	}

	private class PathComboBoxModel extends DefaultComboBoxModel {
		private static final long serialVersionUID = 1L;

		public PathComboBoxModel() {
			for (String path : PathFactory.getAvailablePaths()) {
				addElement(path);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.alcibiade.eternity.editor.solver.ClusterListener#bestSolutionUpdated
	 * ()
	 */
	public void bestSolutionUpdated(int bestScore) {
		if (updateMainGrid.isSelected()) {
			clusterManager.getBestSolution().copyTo(problemGrid);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.alcibiade.eternity.editor.solver.SolverObserver#solverEnded(boolean)
	 */
	public void solverEnded(boolean success) {
		button.setText(LABEL_GO);
		solver = null;
		gridView.setEditable(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.alcibiade.eternity.editor.solver.SolverObserver#solverStarted()
	 */
	public void solverStarted() {
		// Nothing to do.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	public void itemStateChanged(ItemEvent e) {
		String selectedsolver = (String) solversCombo.getSelectedItem();
		pathsCombo.setEnabled(SolverFactory.isSolverPathSensitive(selectedsolver));
	}

}
