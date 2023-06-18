package ParserClass;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileInputStream;
import java.io.StringReader;
import AnnotatedClasses.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class FileParser {

    public static Sys Parse(File file){
        try {
            String systemData = Extractsystem(file);
            StringReader reader = new StringReader(systemData);
            JAXBContext jaxbContext = JAXBContext.newInstance(Sys.class, Property.class, Block.class, Ln.class, Branch.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (Sys) unmarshaller.unmarshal(reader);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String Extractsystem(File file){
        try {
            FileInputStream fis = new FileInputStream(file);
            Scanner scanner = new Scanner(fis);
            String data = scanner.useDelimiter("\\A").next();
            scanner.close();
            Pattern pattern = Pattern.compile("<System>(.*?)</System>", Pattern.DOTALL);
            Matcher matcher = pattern.matcher(data);
            if(matcher.find()){
                return matcher.group();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
