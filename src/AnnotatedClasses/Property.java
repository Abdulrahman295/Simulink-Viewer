package AnnotatedClasses;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class Property {
    private String name;
    private String value;

    // Constructors
    public Property() {
    }

    // Getters
    @XmlAttribute(name = "Name")
    public String getName() {
        return name;
    }

    @XmlValue
    public String getValue() {
        return value;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
