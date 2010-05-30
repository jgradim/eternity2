package org.alcibiade.eternity.editor.solver.diamonds;

import org.alcibiade.eternity.editor.model.GridModel;
import org.alcibiade.eternity.editor.solver.ClusterManager;
import org.alcibiade.eternity.editor.solver.EternitySolver;
import org.alcibiade.eternity.editor.solver.RandomFactory;

import org.alcibiade.eternity.editor.model.QuadsFormatException;

public class DiamondsAdapter extends EternitySolver {

  private TabuleiroLosangos diamondsBoard;
  
  private GridModel grid;

	private long iterationsLimit = -1;

	protected long iterations = 0;
  
  public DiamondsAdapter(GridModel grid, ClusterManager clusterManager) {
		
		super(clusterManager);
		this.grid = grid;
		
		diamondsBoard = new TabuleiroLosangos(Boards.Tab5x5);
		try {
		  grid.fromQuadString(diamondsBoard.tabuleiro.dumpToString());
		  diamondsBoard.setGridModel(this.grid);
		  diamondsBoard.setClusterManager(this.clusterManager);
		} catch(QuadsFormatException e) {
		  e.printStackTrace();
		}
		
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
	  
	  //boolean solved = clusterManager.submitSolution(grid);
	  
	  //boolean solved = diamondsBoard.solve(1, 0);
	  
	  if (diamondsBoard.solve(1, 0)) {
			clusterManager.showStats(diamondsBoard.iterations);
			
			try { diamondsBoard.grid.fromQuadString(diamondsBoard.tabuleiro.dumpToString()); } catch(Exception e) { }
			clusterManager.submitSolution(diamondsBoard.grid);
	    notifyEnd(true);
		}
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
