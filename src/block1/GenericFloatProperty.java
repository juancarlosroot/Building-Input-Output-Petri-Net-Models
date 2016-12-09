package block1;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class GenericFloatProperty {
	float value;
	public GenericFloatProperty(float value){
		setValue(value);		
	}
	@XmlElement
	public float getValue(){
		return value;
	}
	public void setValue(float value){
		this.value = value;
	}
}