package AnnotatedClasses;
import javax.xml.bind.annotation.XmlElement;
import java.util.*;
import javafx.geometry.Point2D;

public class Branch {
    private ArrayList<Property> properties;
    private ArrayList<Point2D> points; // all the points of the line [start point, intermediate points, destination]
    
    // Constructors
    public Branch() {
        this.properties = getProperties();
        this.points = new ArrayList<Point2D>();
    }

    // Getters
    @XmlElement(name = "P")
    public ArrayList<Property> getProperties() {
        return properties;
    }

    // get the center point of the destination block
    public Point2D getDestinationCenterPoint(HashMap<String, Point2D> ID_CenterPoint_Map){
        if(properties != null){
            for(Property property : properties){
                if(property.getName().equals("Dst")){
                    return ID_CenterPoint_Map.get(property.getValue().substring(0, 1));
                }
            }
        }
        return null;
    }

    // get all the points of the line [start point, intermediate points, destination]
    public ArrayList<Point2D> getPoints(HashMap<String, Point2D> ID_CenterPoint_Map, Point2D startPoint) {
        points.add(startPoint);
        
        if(properties != null){
            for(Property property : properties){
                if(property.getName().equals("Points")){
                    // get all the intermediate points
                    String[] pnts = property.getValue().substring(1,  property.getValue().length()-1).split(";");

                    for(String pnt: pnts){
                        String[] XY_Coordinates = pnt.split(",");
                        Point2D prevPoint = points.get(points.size()-1);
                        points.add(new Point2D(Double.parseDouble(XY_Coordinates[0]) + prevPoint.getX(), Double.parseDouble(XY_Coordinates[1]) + prevPoint.getY()));
                    }
                }
            }
        }

        Point2D prevPoint = points.get(points.size()-1);
        Point2D lastPoint = getDestinationCenterPoint(ID_CenterPoint_Map);
        points.add(new Point2D(lastPoint.getX(), prevPoint.getY()));
        return points;
    }
    
    // Setters
    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    public void setPoints(ArrayList<Point2D> points) {
        this.points = points;
    }
}
