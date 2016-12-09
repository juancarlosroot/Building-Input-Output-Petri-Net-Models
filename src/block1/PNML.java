package block1;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.List;

@XmlRootElement
public class PNML{
	PetriNet net;

	@XmlElement
	public PetriNet getNet(){
		return net;
	}
	
	public void setNet(List<Place> placeList,List<Transition> transitionList,List<Arc> arcList,String id, String type, String tokenId,
	 String tokenEnabled, int tokenRed, int tokenGreen, int tokenBlue) {
		net = new PetriNet(placeList,transitionList,arcList,id,type,tokenId,tokenEnabled,tokenRed,tokenGreen,tokenBlue);
	}

}