package org.alcibiade.eternity.editor.solver.diamonds;

import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.solver.ClusterManager;
import org.alcibiade.eternity.editor.solver.EternitySolver;

import org.alcibiade.eternity.editor.model.QuadsFormatException;

public class DiamondsAdapter extends EternitySolver {

	private GridModel problemGrid;
	private GridModel solutionGrid;

	private long iterationsLimit = -1;

	protected long iterations = 0;
  
	public DiamondsAdapter(GridModel grid, GridModel solutionGrid, ClusterManager clusterManager) {
		super(clusterManager);
		this.problemGrid = grid;
		this.solutionGrid = solutionGrid;
	}
	
	@Override
	public void run() {
		notifyStart();
		clusterManager.showStartMessage();
		System.out.println(this.problemGrid.getFeatures().size());

		for(GridModel g : this.problemGrid.getFeatures()){
			System.out.println(g.toQuadString());
			System.out.println("----");
		}

		notifyEnd(true);
	}
  
	@Override
	public long getIterations() {
		return iterations;
	}

	@Override
	public String getSolverName() {
		return "";
	}
}
