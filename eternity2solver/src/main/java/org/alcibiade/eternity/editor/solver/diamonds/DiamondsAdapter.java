package org.alcibiade.eternity.editor.solver.diamonds;

import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.solver.ClusterManager;
import org.alcibiade.eternity.editor.solver.EternitySolver;
import org.alcibiade.eternity.editor.solver.RandomFactory;

import org.alcibiade.eternity.editor.model.QuadsFormatException;

public class DiamondsAdapter extends EternitySolver {

  private TabuleiroLosangos diamondsBoard;
  
  private GridModel problemGrid;
	private GridModel solutionGrid;

	private long iterationsLimit = -1;

	protected long iterations = 0;
  
  public DiamondsAdapter(GridModel grid, GridModel solutionGrid, ClusterManager clusterManager) {
		
		super(clusterManager);
		this.problemGrid = grid;
		this.solutionGrid = solutionGrid;		
		
		diamondsBoard = new TabuleiroLosangos(Boards.Tab12x12);
		try {
		  problemGrid.fromQuadString(diamondsBoard.tabuleiro.dumpToString());
		  diamondsBoard.setGridModel(problemGrid);
		} catch(QuadsFormatException e) {
		  e.printStackTrace();
		}
		
		problemGrid.copyTo(solutionGrid);
		
		/*
		TabuleiroLosangos tab = new TabuleiroLosangos(Tab12x12);
		
		long inicio = System.currentTimeMillis();
		
		
		if (tab.solve(1, 0))
		{
			long fim = System.currentTimeMillis();
			tab.tabuleiro.dump();
			System.out.println("Iterations: " + tab.iterations);
			System.out.println("Elapsed time = " + (fim-inicio)/1000.0 + "seconds.");
		}
		*/
	}
	
	@Override
	public void run() {
	  notifyStart();
	  clusterManager.showStartMessage();
	  
	  boolean solved = clusterManager.submitSolution(solutionGrid);
	  
	  
	  
	  if (solved) {
			clusterManager.showStats(iterations);
		}
	  
	  notifyEnd(solved);
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
