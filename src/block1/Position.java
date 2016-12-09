package block1;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Position {
	float x;
	float y;

	public Position(float x, float y){
		setX(x);
		setY(y);
	}
	@XmlAttribute
	public float getX(){
		return x;
	}
	@XmlAttribute
	public float getY(){
		return y;
	}

	public void setX(float x){
		this.x = x;
	}
	public void setY(float y){
		this.y = y;
	}
}