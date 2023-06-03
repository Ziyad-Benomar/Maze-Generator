import java.util.LinkedList;

public class MazeSolver {
	private Maze maze;
	private MazePanel mazePanel;
	private int animationTimeStep = 10;
	
	
	public MazeSolver(Maze maze) {
		this.maze = maze;
		mazePanel = new MazePanel(maze);
	}
	
	public MazeSolver(MazePanel mazePanel) {
		this.mazePanel = mazePanel;
		this.maze = mazePanel.getMaze();
	}
	

	// We implement a recursive backtracking solver
	
	public boolean solve() {
		// set colors
		mazePanel.setSolveMode();
		
		// solve the maze
		var solved = solve(0,0);
		
		return solved;
	}
	
	
	public boolean solve(int i,int j) {		
		// If the maze is solved, return true
		if (i==maze.height-1 && j==maze.width-1) {
			maze.path.addLast(i*maze.width + j);
			mazePanel.setCurrentCell(i,j);
			mazePanel.animate(animationTimeStep);
			return true;
		}
		// If the cell is visited, then do not explore it again
		if (maze.isVisited(i,j))
			return false;
		// Mark the cell as visited
		maze.markAsVisited(i,j);
		// Add it to the constructed path
		maze.path.addLast(i*maze.width + j);
		// Animation
		mazePanel.setCurrentCell(i,j);
		mazePanel.animate(animationTimeStep);
		// Explore neighboring cells
		var moves = maze.getPossibleMoves(i,j);
		for (var move: moves) {
			int di = move.di;
			int dj = move.dj;
			// If going to a neighboring leads to solving the maze
			// then return true, and the optimal path is constructed
			if (solve(i+di,j+dj))
				return true;
		}
		// remove the current cell from the path
		maze.path.removeLast();
		return false;
	}
}
