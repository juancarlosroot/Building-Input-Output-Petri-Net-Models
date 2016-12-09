package block1;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class GenericBooleanProperty {
	Boolean value;
	public GenericBooleanProperty(Boolean value){
		setValue(value);		
	}
	@XmlElement
	public Boolean getValue(){
		return value;
	}
	public void setValue(Boolean value){
		this.value = value;
	}
}