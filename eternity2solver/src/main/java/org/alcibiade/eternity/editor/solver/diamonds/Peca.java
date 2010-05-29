package org.alcibiade.eternity.editor.solver.diamonds;

public class Peca {
	
	int north;
	int east;
	int south;
	int west;
	
	int line;
	int column;
	
	int pontos;
	
	//int id;
	
	Peca(int n, int e, int s, int w)
	{
		north = n;
		east = e;
		south = s;
		west = w;
		
		pontos = 0;
	}
	
	public boolean equals(Peca p)
	{
		if(north != p.getNorth() || east != p.getEast() || south != p.getSouth() || west != p.getWest() || line != p.getLine() || column != p.getColumn())
			return false;
		else
			return true;
	}
	
	public void rotate(int option)
	{
		int[] old = new int[4];
		old[0] = north; 
		old[1] = east;
		old[2] = south;
		old[3] = west;
		
		switch(option)
		{
			case 0:
			{
				//no rotation, do nothing
			}
			break;
			case 1:
			{
				//rotate 90º clockwise
				this.north = old[3];
				this.east  = old[0];
				this.south = old[1];
				this.west  = old[2];
			}
			break;
			case 2:
			{
				//rotate 180º clockwise
				this.north = old[2];
				this.east  = old[3];
				this.south = old[0];
				this.west  = old[1];
			}
			break;
			case 3:
			{
				//rotate 270º clockwise
				this.north = old[1];
				this.east  = old[2];
				this.south = old[3];
				this.west  = old[0];
			}
			break;
		}
	}
	
	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
	public void setCoordinates(int line, int column)
	{
		this.line = line;
		this.column = column;
	}

	public int getNorth() {
		return north;
	}

	public void setNorth(int north) {
		this.north = north;
	}

	public int getEast() {
		return east;
	}

	public void setEast(int east) {
		this.east = east;
	}

	public int getSouth() {
		return south;
	}

	public void setSouth(int south) {
		this.south = south;
	}

	public int getWest() {
		return west;
	}

	public void setWest(int west) {
		this.west = west;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
	
	
}
