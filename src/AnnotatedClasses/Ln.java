package AnnotatedClasses;
import javax.xml.bind.annotation.XmlElement;
import java.util.*;
import javafx.geometry.Point2D;

public class Ln {
    private ArrayList<Property> properties;
    private ArrayList<Branch> branches;
    private ArrayList<Point2D> points; // all the points of the line [source, intermediate points, destination]

    // Constructors
    public Ln() {
        this.properties = getProperties();
        this.branches = getBranches();
        this.points = new ArrayList<Point2D>();
    }

    // Getters
    @XmlElement(name = "P")
    public ArrayList<Property> getProperties() {
        return properties;
    }

    @XmlElement(name = "Branch")
    public ArrayList<Branch> getBranches() {
        return branches;
    }

    public boolean hasDestinationBlock(){
        if(properties != null){
            for(Property property : properties){
                if(property.getName().equals("Dst")){
                    return true;
                }
            }
        }
        return false;
    }

    // get the center point of the source block
    public Point2D getSourceCenterPoint(HashMap<String, Point2D> ID_CenterPoint_Map){
        if(properties != null){
            for(Property property : properties){
                if(property.getName().equals("Src")){
                    return ID_CenterPoint_Map.get(property.getValue().substring(0, 1));
                }
            }
        }
        return null;
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

    // get all the points of the line [source, intermediate points, destination]
    public ArrayList<Point2D> getPoints(HashMap<String, Point2D> ID_CenterPoint_Map) {
        points.add(getSourceCenterPoint(ID_CenterPoint_Map));
        boolean is_first_intermediate_point = true;
        if(properties != null){
            for(Property property : properties){
                if(property.getName().equals("Points")){
                    // get all the intermediate points
                    String[] pnts = property.getValue().substring(1,  property.getValue().length()-1).split(";");

                    for(String pnt : pnts){
                        String[] XY_Coordinates = pnt.split(",");
                        Point2D prevPoint = points.get(points.size()-1);

                        // current point coordinates
                        double current_X = Double.parseDouble(XY_Coordinates[0]);
                        double current_Y = Double.parseDouble(XY_Coordinates[1]);

                        // if the current point is the first intermediate point, we need to add half of the block length to the X coordinate to avoid overlapping
                        if(is_first_intermediate_point){
                            if (current_X >= 0){
                                current_X += 35;
                            }
                            else {
                                current_X -= 35;
                            }
                            is_first_intermediate_point = false;
                        }

                        points.add(new Point2D((current_X + prevPoint.getX()), (current_Y + prevPoint.getY())));
                    }
                    break;
                }
            }
        }
        
        // if the current line doesn't have branches, add the destination center point
        if (getDestinationCenterPoint(ID_CenterPoint_Map) != null){
            Point2D prevPoint = points.get(points.size()-1);
            Point2D lastPoint = getDestinationCenterPoint(ID_CenterPoint_Map);
            points.add(new Point2D(lastPoint.getX(), prevPoint.getY()));
        }
            
        return points;
    }

    // Setters
    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    public void setBranches(ArrayList<Branch> branches) {
        this.branches = branches;
    }

    public void setPoints(ArrayList<Point2D> points) {
        this.points = points;
    }
}
