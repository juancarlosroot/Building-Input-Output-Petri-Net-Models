package block1;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlType(propOrder = {
        "graphics",
        "name",
        "initialMarking",
        "capacity"
})
public class Place {
	String id;
	Graphics graphics;
	Name name;
	Name initialMarking;
	GenericIntProperty capacity;

	@XmlAttribute
	public String getId(){
		return id;
	}
	@XmlElement
	public Graphics getGraphics(){
		return graphics;
	}
	@XmlElement
	public Name getName(){
		return name;
	}
	@XmlElement
	public Name getInitialMarking(){
		return initialMarking;
	}
	@XmlElement
	public GenericIntProperty getCapacity(){
		return capacity;
	}

	public void setId(String id){
		this.id = id;
	}
	public void setGraphics(float x, float y){
		graphics = new Graphics(x,y);
	}
	public void setName(float x, float y){
		name = new Name(id,x,y);
	}
	public void setInitialMarking(String def, int value,float x, float y){
		initialMarking = new Name(def,value,x,y);
	}
	public void setCapacity(int value){
		capacity = new GenericIntProperty(value);
	}
}