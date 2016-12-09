package block1;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlType(propOrder = {
        "value",
        "graphics"
})
public class Name {
	String value;
	Graphics graphics;

	public Name(String value, float x, float y){
		setValue(value);
		setGraphics(x,y);
	}
	public Name(String def, int value, float x, float y){
		setValue(def+","+Integer.toString(value));
		setGraphics(x,y);
	}

	@XmlElement
	public String getValue(){
		return value;
	}
	@XmlElement
	public Graphics getGraphics(){
		return graphics;
	}

	public void setValue(String value){
		this.value = value;
	}
	public void setGraphics(float x, float y){
		graphics = new Graphics("offset",x,y);
	}
}