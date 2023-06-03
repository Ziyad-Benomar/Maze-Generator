import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

public class Maze {
	private Cell[][] board;
	final int width;
	final int height;
	LinkedList<Integer> path = new LinkedList<Integer>();
	
	public Maze(int width, int height) {
		this.width = width;
		this.height = height;
		this.board = new Cell[height][width];
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				this.board[i][j] = new Cell();
		
	}
	
	
	public void addMove(int i, int j, Move move) {
		if (board[i][j].moves.contains(move))
			return;
		board[i][j].moves.add(move);
		if (isValid(i + move.di, j + move.dj))
			board[i + move.di][j + move.dj].moves.add(move.oppositeMove());
	}
	
	// Elementary methods
	public boolean isValid(int i, int j) {
		return (i>=0 && i<height && j>=0 && j<width);
	}
	
	public HashSet<Move> getPossibleMoves(int i, int j){
		return board[i][j].moves;
	}
	
	public boolean isIsolated(int i, int j) {
		return board[i][j].moves.isEmpty();
	}
	
	public boolean isVisited(int i, int j) {
		return board[i][j].visited;
	}
	
	public void markAsVisited(int i, int j) {
		board[i][j].visited = true;
	}
	
	public void unmark(int i, int j) {
		board[i][j].visited = false;
	}
	
	public void unmarkAll() {
		for (var i = 0; i < height; i++)
			for (var j = 0; j < width; j++)
				unmark(i,j);
	}
	
	
	
	
	
	
	private class Cell {
		boolean visited = false;
		HashSet<Move> moves = new HashSet<Move>();
	}
}


