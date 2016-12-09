package block1;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class GenericStringProperty {
	String value;
	public GenericStringProperty(String value){
		setValue(value);		
	}
	@XmlElement
	public String getValue(){
		return value;
	}
	public void setValue(String value){
		this.value = value;
	}
}