package block1;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;
@XmlType(propOrder = {
        "token",
        "place",
        "transition",
        "arc"
})
public class PetriNet{
	String type;
	String id;
	Token token;
	List<Place> place;
	List<Transition> transition;
	List<Arc> arc;

	public PetriNet(List<Place> placeList, List<Transition> transitionList, List<Arc> arcList,String id, String type, 
		String tokenId, String tokenEnabled, int tokenRed, int tokenGreen, int tokenBlue){
		setType(type);
		setId(id);
		setToken(tokenId,tokenEnabled,tokenRed,tokenGreen,tokenBlue);
		setPlaces(placeList);
		setTransitions(transitionList);
		setArcs(arcList);
	}

	@XmlAttribute
	public String getType() {
		return type;
	}
	@XmlAttribute
	public String getId() {
		return id;
	}
	@XmlElement
	public Token getToken(){
		return token;
	}
	@XmlElement
	public List<Place> getPlace(){
		return place;
	}
	@XmlElement
	public List<Transition> getTransition(){
		return transition;
	}
	@XmlElement
	public List<Arc> getArc(){
		return arc;
	}

	public void setType(String type) {
		this.type = type;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setToken(String tokenId,String tokenEnabled,int tokenRed,int tokenGreen,int tokenBlue){
		this.token = new Token(tokenId,tokenEnabled,tokenRed,tokenGreen,tokenBlue);
	}
	public void setPlaces(List<Place> placeList){
		this.place = placeList;
	}
	public void setTransitions(List<Transition> transitionList){
		this.transition = transitionList;
	}
	public void setArcs(List<Arc> arcList){
		this.arc = arcList;
	}
}