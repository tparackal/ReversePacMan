import javafx.geometry.Point2D;

public class Ghost2 extends Creature
{
	public Ghost2(double x1, double y1, double dx1, double dy1, int m1) 
	{
		super(x1, y1, dx1, dy1, m1);
	}
	public void update() 
	{
		super.update();
		motion=ReversePacMan.dir2;
		int jcol = ((int)x)/Maze.CELLSIZE; int jrow = ((int)y)/Maze.CELLSIZE;
		Point2D dvec = ReversePacMan.dirs[ReversePacMan.dir2];
		int inFront = ReversePacMan.maze1.getMazeValue(jrow+(int)dvec.getY(), jcol+(int)dvec.getX());
		if(inFront==Maze.WALL) // ghost does not go through wall
		{
			motion=0;
		}
	}
}
