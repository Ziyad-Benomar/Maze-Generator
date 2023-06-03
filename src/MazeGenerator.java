import java.util.Collections;

public class MazeGenerator {
	private Maze maze;
	MazePanel mazePanel;
	private int animationTimeStep = 5;
	
	public MazeGenerator(int width, int height) {
		maze = new Maze(width, height);
		mazePanel = new MazePanel(maze);
	}
	
	// There are many methods for generating a maze. I implement here 
	// a recursive backtracking method.
	
	
	// Recursive Backtracking
	//-----------------------
	public MazePanel backtracker() {
		// set colors
		mazePanel.setGenMode();
		// generate the maze
		mazePanel.animate(1000);
		backtracker(0,0);
		// unmark the cells and return to default colors setting
		maze.unmarkAll();
		mazePanel.setDefaultMode();
		mazePanel.animate(1000);
		// return the maze
		return mazePanel;	
	}
	
	public void backtracker(int i, int j){
		mazePanel.setCurrentCell(i,j);
		mazePanel.animate(animationTimeStep);
		// check if cell is visited
		if (maze.isVisited(i,j))
			return;
		maze.markAsVisited(i,j);
		// recursive call to backtracker()
		var allMoves = Move.allMoves();
		Collections.shuffle(allMoves);
		for (var move: allMoves) {
			var di = move.di;
			var dj = move.dj;
			if (maze.isValid(i+di, j+dj) && maze.isIsolated(i+di, j+dj)) {
				maze.addMove(i,j,move);
				backtracker(i+di, j+dj);
			}
		}
	}
	
	

}