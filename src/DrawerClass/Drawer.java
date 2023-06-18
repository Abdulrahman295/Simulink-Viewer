package DrawerClass;
import java.io.File;
import java.util.ArrayList;
import AnnotatedClasses.Block;
import AnnotatedClasses.Branch;
import AnnotatedClasses.Ln;
import AnnotatedClasses.Sys;
import javafx.geometry.Point2D;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Drawer {
    public static void drawLine(Sys system, Pane root){
        ArrayList<Ln> lines = system.getLines();

        for (Ln line : lines) {
            ArrayList<Point2D> points = line.getPoints(system.getId_CenterPoint_Map());

            for(int i = 0; i < points.size()-1; i++){
                Point2D startPoint = points.get(i);
                Point2D endPoint = points.get(i+1);

                Line l = new Line(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
                l.setStroke(Color.BLACK);
                l.setStrokeWidth(2);
                   
                if( i == points.size()-2){
                    if(line.hasDestinationBlock()){
                            drawTriangle(startPoint ,endPoint, root);
                    }
                    else{
                        drawCircle(endPoint, root);
                    }
                }
                root.getChildren().add(l);
            }
        }
    }

    public static void drawBlock(Sys system, Pane root){
        ArrayList<Block> blocks = system.getBlocks();
        for (Block block : blocks) {
            Rectangle rect = new Rectangle(block.getCenter().getX() - Block.defualt_length/2, block.getCenter().getY() - Block.defualt_length/2, Block.defualt_length, Block.defualt_length);
            rect.setFill(Color.WHITE);
            rect.setStroke(Color.BLACK);
            rect.setStrokeWidth(2);
            rect.setEffect(new DropShadow(2, 2, 2, Color.GRAY));
            root.getChildren().addAll(rect); 
            addImage(block, root);
            addText(block, root);
        } 
    }

    public static void drawBranch(Sys system, Pane root){
        ArrayList<Ln> lines = system.getLines();
        ArrayList<Branch> branches;
            for (Ln l : lines) {
                ArrayList<Point2D> Line_points = l.getPoints(system.getId_CenterPoint_Map());
                branches = l.getBranches();
                if(branches == null) continue;
                for(Branch b : branches){
                    ArrayList<Point2D> points = b.getPoints(system.getId_CenterPoint_Map(), Line_points.get(Line_points.size()-1));
                    for(int i = 0; i < points.size()-1; i++){
                        Point2D startPoint = points.get(i);
                        Point2D endPoint = points.get(i+1);

                        Line line = new Line(startPoint.getX(),startPoint.getY(), endPoint.getX(), endPoint.getY());
                        line.setStroke(Color.BLACK);
                        line.setStrokeWidth(2);

                        if(i == points.size()-2){
                            drawTriangle(startPoint, endPoint, root);
                        }
                        root.getChildren().add(line);
                    }
                }
            }
    }

    public static void drawTriangle(Point2D startPoint ,Point2D endPoint, Pane root){
        Polygon triangle = new Polygon();
        
        // adjust the triangle to be oustisde the block
        if(endPoint.getX() > startPoint.getX()){
            endPoint = new Point2D(endPoint.getX() - Block.defualt_length/2, endPoint.getY());
            Double[] coordinates = {endPoint.getX()-10,  endPoint.getY()-5, endPoint.getX(), endPoint.getY(), endPoint.getX()-10, endPoint.getY()+5};
            triangle.getPoints().addAll(coordinates);
        }
        else{
            endPoint = new Point2D(endPoint.getX() + Block.defualt_length/2, endPoint.getY());
            Double[] coordinates = {endPoint.getX()+10,  endPoint.getY()+5, endPoint.getX(), endPoint.getY(), endPoint.getX()+10, endPoint.getY()-5};
            triangle.getPoints().addAll(coordinates);
        }
        root.getChildren().add(triangle);
    }

    public static void drawCircle(Point2D center, Pane root){
        Circle circle = new Circle(center.getX(), center.getY(), 3);
        circle.setFill(Color.BLACK);
        root.getChildren().add(circle);
    }

    public static void addImage(Block block, Pane root){
        String path = "Icons\\" + block.getName() + ".png";
        Image image = new Image(new File(path).toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(45);
        imageView.setFitWidth(45);
        imageView.setLayoutY(block.getCenter().getY() - Block.defualt_length/2 + 3);
        imageView.setLayoutX(block.getCenter().getX() - Block.defualt_length/2 + 3);
        root.getChildren().add(imageView);
    }

    public static void addText(Block block, Pane root){
        Text text = new Text(block.getName());
        text.setLayoutY(block.getCenter().getY() + 43);
        text.setLayoutX(block.getCenter().getX() - 25 );
        text.setStyle("-fx-font: 15 arial; -fx-font-weight: bold; -fx-fill: #25a2ff;");
        root.getChildren().add(text);
    }

}
