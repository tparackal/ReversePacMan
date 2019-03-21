import javafx.scene.canvas.GraphicsContext;

/**
 * Parent class of all moving actors (sprites)
 * 
 * @author mike slattery
 */
public class Creature {
	double x, y;
	double dx, dy;
	int look;
	int motion;
	int ccount;

	public Creature(double x1, double y1, double dx1, double dy1, int m1)
	{
		x = x1; y = y1;
		dx = dx1; dy = dy1;
		motion = m1;
		ccount = 0;
	}
	
	public void update()
	{
		ccount++;
		switch(motion)
		{
			case 1:
				x-=dx;
				break;
			case 2:
				x+=dx;
				break;
			case 3:
				y-=dy;
				break;
			case 4:
				y+=dy;
				break;
			default:
				break;
		}
	}
	
	public void render(GraphicsContext gc)
	{
		
	}
}