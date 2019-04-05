import javafx.geometry.*;

/**
 * Class for the first ghost
 * 
 * (The two ghosts are separate classes since they use different controls)
 * 
 * @author Patrick Hara, Tharun Parackal
 */
public class Ghost extends Creature
{	
	public Ghost(double x1, double y1, double dx1, double dy1, int m1) 
	{
		super(x1, y1, dx1, dy1, m1);
	}
	public void update() 
	{
		super.update();
		int col = Maze.toCol(x);
		int row = Maze.toRow(y);
		if((row == 14) && (col == 0) && (motion == 1)) //  set Ghost to the right side if it goes to the left side
		{
			x = 27 * Maze.CELLSIZE;
			y = 14 * Maze.CELLSIZE;
			return;
		}
		if((row == 14) && (col == 27) && (motion == 2)) // set Ghost to the left side if it goes to the right side
		{
			x = 0 * Maze.CELLSIZE;
			y = 14 * Maze.CELLSIZE;
			return;
		}
		motion=ReversePacMan.dir;
//		int jcol = ((int)x)/Maze.CELLSIZE; int jrow = ((int)y)/Maze.CELLSIZE;
		Point2D dvec = ReversePacMan.dirs[ReversePacMan.dir];
		int inFront = ReversePacMan.maze1.getMazeValue(row+(int)dvec.getY(), col+(int)dvec.getX());
		
		if(inFront==Maze.WALL) // ghost does not go through wall
		{
			motion=0;
		}
	}
	
}
