import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import java.io.File;
import AnnotatedClasses.*;
import ParserClass.*;
import DrawerClass.*;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        
        // parse the file and get the system
        Sys system = FileParser.Parse(new File("Simulink.mdl"));

        // draw system heirarchy
        Drawer.drawLine(system, root);
        Drawer.drawBranch(system, root);
        Drawer.drawBlock(system, root);
        
        // show the scene
        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("SIMULINK");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
