import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MazePanel extends JPanel {
	private final int PANEL_WIDTH;
	private final int PANEL_HEIGHT;
	public int CELL_SIZE = 25;
	private Maze maze;
	
	private Color backgroundColor;
	private Color visitedColor;
	private Color pathColor;
	private Color currCellColor;
	
	// mode
	private final static int DEFAULT = 0;
	private final static int GENERATE = 1;
	private final static int SOLVE = 2;
	private int mode = DEFAULT;;
	
	private int curri = -1;
	private int currj = -1;
	
	
	public MazePanel(Maze maze) {
		this.PANEL_WIDTH = maze.width * CELL_SIZE;
		this.PANEL_HEIGHT = maze.height * CELL_SIZE;
		this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT)); 
		this.setDefaultMode();
		this.maze = maze;
		createFrame();
	}
	
	
	public void createFrame() {
		JFrame frame = new JFrame("Maze");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.add(this);
	    frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}
	
	public Maze getMaze() {
		return maze;
	}
	
	public void setCurrentCell(int i, int j) {
		curri = i;
		currj = j;
	}
	
	// Settings: default, generating, solving
	public void setDefaultMode() {
		mode = DEFAULT;
		backgroundColor = Color.white;
		visitedColor = Color.orange;
		pathColor = Color.green;
		currCellColor = Color.red;
	}
	
	public void setGenMode() {
		mode = GENERATE;
		backgroundColor = Color.gray;
		visitedColor = Color.white;
		currCellColor = Color.red;
	}
	
	public void setSolveMode() {
		mode = SOLVE;
		backgroundColor = Color.white;
		visitedColor = Color.white;
		pathColor = Color.green;
		currCellColor = Color.red;
	}
	
	
	

	
	// Paint
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(3));
		
		// draw rectangle containing the maze
		g2d.setColor(backgroundColor);
		g2d.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
		
		// show visited cells
		if (mode == GENERATE) {
			g2d.setColor(visitedColor);
			for (var i = 0; i < maze.height; i++)
				for(var j = 0; j < maze.width; j++)
					if (maze.isVisited(i,j))
						g2d.fillRect(j*CELL_SIZE, i*CELL_SIZE, CELL_SIZE, CELL_SIZE);
		}
		
		// show the optimal path
		if (mode == SOLVE) {
			g2d.setColor(pathColor);
			// we work on a copy of maze.path because repaint() is asynchronous, 
			// and maze.path can be modified (maze.path.removeLast()) while the 
			// repainting still hasn't finished
			var path = (LinkedList<Integer>) maze.path.clone();
			for (var pair : path) {
				int i = pair / maze.width;
				int j = pair % maze.width;
				g2d.fillRect(j*CELL_SIZE, i*CELL_SIZE, CELL_SIZE, CELL_SIZE);
			}
		}
		
		// show the current cell
		if (mode != DEFAULT) {
			g2d.setColor(currCellColor);
			g2d.fillRect(currj*CELL_SIZE, curri*CELL_SIZE, CELL_SIZE, CELL_SIZE);
		}
		
		// show the maze walls
		g2d.setColor(Color.black);
		g2d.drawRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
		for (var i = 0; i < maze.height; i++) {
			for(var j = 0; j < maze.width; j++) {
				var cellMoves = maze.getPossibleMoves(i,j);
				if (!cellMoves.contains(Move.DOWN))
					g2d.drawLine(j*CELL_SIZE, (i+1)*CELL_SIZE, (j+1)*CELL_SIZE, (i+1)*CELL_SIZE);
				if (!cellMoves.contains(Move.RIGHT))
					g2d.drawLine((j+1)*CELL_SIZE, i*CELL_SIZE, (j+1)*CELL_SIZE, (i+1)*CELL_SIZE);
			}
		}		
	}
	
	
	
	// Slowing down to see animation
	public void wait(int milliseconds) {
		try {
		  Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
		  Thread.currentThread().interrupt();
		}
	}
	
	public void animate(int milliseconds) {			
		repaint();
		wait(milliseconds);
	}
	
	
}
