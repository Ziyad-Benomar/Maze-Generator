import java.util.Arrays;
import java.util.List;

public class Move{
	int dj;
	int di;
	
	// Do not create new moves in other classes, use the predefined 
	// moves UP, DOWN, LEFT, RIGHT below.
	private Move(int dj, int di){
		this.dj = dj;
		this.di = di;
	}
	
	
	public final static Move UP = new Move(0,-1);
	public final static Move DOWN = new Move(0,1);
	public final static Move LEFT = new Move(-1,0);
	public final static Move RIGHT = new Move(1,0);
	
	
	public Move oppositeMove() {
		if (dj * di != 0)
			throw new Error("Diagonal moves are not allowed, cannot have dx!=0 and dy!=0");
		if (dj == -1)
			return RIGHT;
		if (dj == 1)
			return LEFT;
		if (di == -1)
			return DOWN;
		return UP;
	}
	
	public static List<Move> allMoves(){
		return Arrays.asList(new Move[] {UP, DOWN, LEFT, RIGHT});
	}
}
