package block1;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlType(propOrder = {
        "id",
        "graphics",
        "name",
        "orientation",
        "rate",
        "timed",
        "infiniteServer",
        "priority"
})
public class Transition {
	String id;
	Graphics graphics;
	Name name;
	GenericIntProperty orientation;
	GenericFloatProperty rate;
	GenericBooleanProperty timed;
	GenericBooleanProperty infiniteServer;
	GenericIntProperty priority;


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
	public GenericIntProperty getOrientation(){
		return orientation;
	}
	@XmlElement
	public GenericFloatProperty getRate(){
		return rate;
	}
	@XmlElement
	public GenericBooleanProperty getTimed(){
		return timed;
	}
	@XmlElement
	public GenericBooleanProperty getInfiniteServer(){
		return infiniteServer;
	}
	@XmlElement
	public GenericIntProperty getPriority(){
		return priority;
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
	public void setOrientation(int value){
		this.orientation = new GenericIntProperty(value);
	}
	public void setRate(Float value){
		this.rate = new GenericFloatProperty(value);
	}
	public void setTimed(Boolean value){
		this.timed = new GenericBooleanProperty(value);
	}
	public void setInfiniteServer(Boolean value){
		this.infiniteServer = new GenericBooleanProperty(value);
	}
	public void setPriority(int value){
		this.priority = new GenericIntProperty(value);
	}
}