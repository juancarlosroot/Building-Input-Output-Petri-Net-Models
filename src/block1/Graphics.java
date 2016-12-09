package block1;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Graphics {
	Position position;
	Position offset;

	public Graphics(float x, float y){
		setPosition(x,y);
	}
	public Graphics(String offset,float x, float y){
		setOffset(x,y);
	}

	@XmlElement
	public Position getPosition(){
		return position;
	}
	@XmlElement
	public Position getOffset(){
		return offset;
	}

	public void setPosition(float x, float y){
		position = new Position(x,y);
	}
	public void setOffset(float x, float y){
		offset = new Position(x,y);
	}
}