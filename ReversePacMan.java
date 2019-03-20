import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ReversePacMan extends Application
{

	final static int WIDTH = 840; // pixel width
	final static int HEIGHT = 930; // pixel height
	
	public static void main(String[] args) 
	{
		launch(args);
	}
	
	@Override
    public void start(Stage theStage) throws FileNotFoundException, IOException 
    {
        theStage.setTitle( "Reverse PacMan" );
        
        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );
        
        Canvas canvas = new Canvas( WIDTH, HEIGHT ); // 600 pixels wide, 500 pixels tall
        root.getChildren().add( canvas );
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        render(gc);
        
        theStage.show();
    }
    
    void render(GraphicsContext gc) throws FileNotFoundException, IOException
    {
    	Canvas canvas = new Canvas( 600, 500 ); // 600 pixels wide, 500 pixels tall
 		Maze maze = new Maze("level1.txt");
 		maze.initMaze();
 		maze.loadmaze(gc);
    }

}
