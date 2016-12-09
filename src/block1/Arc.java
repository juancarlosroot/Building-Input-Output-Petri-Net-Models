package block1;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;
@XmlType(propOrder = {
        "id",
        "source",
        "target",
        "inscription",
        "tagged",
        "arcpath",
        "type"
})
public class Arc {
	String id;
	String source;
	String target;
	GenericStringProperty inscription;
	GenericBooleanProperty tagged;
	List<ArcPath> arcpath;
	Type type;

	@XmlAttribute
	public String getId(){
		return id;
	}
	@XmlAttribute
	public String getSource(){
		return source;
	}
	@XmlAttribute
	public String getTarget(){
		return target;
	}
	@XmlElement
	public GenericStringProperty getInscription(){
		return inscription;
	}
	@XmlElement
	public GenericBooleanProperty getTagged(){
		return tagged;
	}
	@XmlElement
	public List<ArcPath> getArcpath(){
		return arcpath;
	}
	@XmlElement
	public Type getType(){
		return type;
	}

	public void setId(String id){
		this.id = id;
	}
	public void setSource(String source){
		this.source = source;
	}
	public void setTarget(String target){
		this.target = target;
	}
	public void setInscription(String value){
		this.inscription = new GenericStringProperty(value);
	}
	public void setTagged(Boolean value){
		this.tagged = new GenericBooleanProperty(value);
	}
	public void setArcPath(List<ArcPath> arcPathList){
		this.arcpath = arcPathList;
	}
	public void setType(String type){
		this.type = new Type(type);
	}

}