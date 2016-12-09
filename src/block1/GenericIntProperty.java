package block1;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class GenericIntProperty {
	int value;
	public GenericIntProperty(int value){
		setValue(value);		
	}
	@XmlElement
	public int getValue(){
		return value;
	}
	public void setValue(int value){
		this.value = value;
	}
}