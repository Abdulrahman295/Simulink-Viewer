package AnnotatedClasses;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.*;
import javafx.geometry.Point2D;

public class Block {
    private ArrayList<Property> properties;
    private String name;
    private String type;
    
    @XmlAttribute(name = "SID")
    private String SID;

    public static int defualt_length = 50;
    private Point2D center;
    private int num_ports;
    
    // Constructors
    public Block() {
        this.properties = getProperties();
    }

    // Getters
    @XmlElement(name = "P")
    public ArrayList<Property> getProperties() {
        return properties;
    }

    @XmlAttribute(name = "BlockType")
    public String getType() {
        return type;
    }

    @XmlAttribute(name = "Name")
    public String getName() {
        return name;
    }

    
    public String getSID() {
        return SID;
    }

    public Point2D getCenter() {
        if(properties != null){
            for(Property property : properties){
                if(property.getName().equals("Position")){
                    String[] positions = property.getValue().substring(1,  property.getValue().length()-1).split(",");
                    this.center = new Point2D(((Double.parseDouble(positions[0]) + Double.parseDouble(positions[2]))/2)*1.4, ((Double.parseDouble(positions[1]) + Double.parseDouble(positions[3]))/2));
                    break;
                }
            }
        }
        else{
            this.center = new Point2D(0, 0);
        }
        return center;
    }

    public int getNum_ports() {
        if(properties != null){
            for(Property p : properties){
                if(p.getName().equals("Ports")){
                    this.num_ports = Character.getNumericValue(p.getValue().charAt(1));
                    return num_ports;
                }
            }
        }

        this.num_ports = 1;
        return num_ports;
    }

    // Setters
    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
        this.SID = name.substring(0, 1);
    }

    public void setCenter(Point2D center) {
        this.center = center;
    }

    public void setNum_ports(int num_ports) {
        this.num_ports = num_ports;
    }
}
