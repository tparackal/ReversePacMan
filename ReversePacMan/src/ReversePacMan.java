import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ReversePacMan extends Application
{

//	final static int WIDTH = 840; // pixel width
//	final static int HEIGHT = 930; // pixel height
	final int FPS = 15;
	public static Maze maze;
	public static Creature[] creatures = new Creature[6];
	public static int dir;
	public static int dir2;
	public static Point2D[] dirs = {new Point2D(0,0),
			new Point2D(-1,0), new Point2D(1,0),
			new Point2D(0,-1), new Point2D(0,1)
	};
	public static void main(String[] args) 
	{
		launch(args);
	}
	void setHandlers(Scene scene) {
		scene.setOnKeyPressed(
				e -> {
					switch (e.getCode())
					{
						case A:
							dir=1;
							break;
						case LEFT:
							dir2=1; // A:Left
							break;
						case D:
							dir=2;
							break;
						case RIGHT:
							dir2=2; // D:Right
							break;
						case W:
							dir=3;
							break;
						case UP:
							dir2=3; // W:Up
							break;
						case S:
							dir=4;
							break;
						case DOWN:
							dir2=4; // S:Down
							break;
						default:
							break;
					}
				}
		);
					
	}
	public void update() {
		for (int i = 0; i < 2; i++) {
			creatures[i].update();
		}
	}
	void render(GraphicsContext gc) throws FileNotFoundException, IOException
	    {
//	    	Canvas canvas = new Canvas( 600, 500 ); // 600 pixels wide, 500 pixels tall
	 		maze = new Maze("level1.txt");
	 		maze.initMaze();
	 		maze.loadmaze(gc);
	 		gc.setFill(Color.AQUAMARINE);
	 		gc.fillRect(creatures[0].x, creatures[0].y, Maze.CELLSIZE, Maze.CELLSIZE);
	 		gc.setFill(Color.RED);
	 		gc.fillRect(creatures[1].x, creatures[1].y, Maze.CELLSIZE, Maze.CELLSIZE);
	    }
	void renderCreatures(GraphicsContext gc ) {
		maze.loadmaze(gc);
		gc.setFill(Color.AQUAMARINE);
 		gc.fillRect(creatures[0].x, creatures[0].y, Maze.CELLSIZE, Maze.CELLSIZE);
 		gc.setFill(Color.RED);
 		gc.fillRect(creatures[1].x, creatures[1].y, Maze.CELLSIZE, Maze.CELLSIZE);
	}
	@Override
    public void start(Stage theStage) throws FileNotFoundException, IOException 
    {
        theStage.setTitle( "Reverse PacMan" );
        creatures[0] = new Ghost(12*Maze.CELLSIZE, 14*Maze.CELLSIZE, 10, 10, 0);
        creatures[1] = new Ghost2(15*Maze.CELLSIZE, 14*Maze.CELLSIZE, 10, 10, 0);
        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );
        
        Canvas canvas = new Canvas( Maze.WIDTH, Maze.HEIGHT ); // 840 pixels wide, 930 pixels tall
        root.getChildren().add( canvas );
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        render(gc);
        setHandlers(theScene);
        KeyFrame kf = new KeyFrame(Duration.millis(1000 / FPS),
				e -> {
					// update position
					update();
					// draw frame
					renderCreatures(gc);
				}
			);
        Timeline mainLoop = new Timeline(kf);
		mainLoop.setCycleCount(Animation.INDEFINITE);
		mainLoop.play();
        theStage.show();
    }
    
   

}
