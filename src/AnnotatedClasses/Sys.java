package AnnotatedClasses;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;
import javafx.geometry.Point2D;


@XmlRootElement(name = "System")
public class Sys {
    private ArrayList<Block> blocks;
    private ArrayList<Ln> lines;
    private ArrayList<Property> properties;
    private HashMap<String, Point2D> Id_CenterPoint_Map; // Map of ID and CenterPoint of each block in the system

    // Constructors
    public Sys() {
        this.blocks = getBlocks();
        this.lines = getLines();
        this.properties = getProperties();
    }

    // Getters
    @XmlElement(name = "Block")
    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    @XmlElement(name = "Line")
    public ArrayList<Ln> getLines() {
        return lines;
    }

    @XmlElement(name = "P")
    public ArrayList<Property> getProperties() {
        return properties;
    }

    // itrerate over blocks and get the center point of each block
    public HashMap<String, Point2D> getId_CenterPoint_Map() {
        if(blocks != null){
            Id_CenterPoint_Map = new HashMap<String, Point2D>();
            for(Block block : blocks){
                Id_CenterPoint_Map.put(block.getSID(), block.getCenter());
            }
        }
        return Id_CenterPoint_Map;
    }

    // Setters
    public void setBlocks(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }

    public void setLines(ArrayList<Ln> lines) {
        this.lines = lines;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }
}
