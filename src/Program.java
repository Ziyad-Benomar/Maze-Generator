
public class Program {

	public static void main(String[] args) {
		//Maze maze = new Maze(20,20);
		
		var mazeGen = new MazeGenerator(20,15);
		var mazePanel = mazeGen.backtracker();
		//var maze = MazeGenerator.backtracker(20,20);
		
		var solver = new MazeSolver(mazePanel);
		solver.solve();
		System.out.println("solved !");
	}

}
