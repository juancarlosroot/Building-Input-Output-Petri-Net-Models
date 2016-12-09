package block1;
import javax.xml.bind.annotation.XmlAttribute;

public class Type {
	String value;

	public Type(String type){
		setValue(type);
	}

	@XmlAttribute
	public String getValue(){
		return value;
	}
	public void setValue(String value){
		this.value = value;
	}
}