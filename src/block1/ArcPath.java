package block1;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlType;
@XmlType(propOrder = {
        "id",
        "x",
        "y",
        "curvePoint"
})
public class ArcPath {
	String id;
	int x;
	int y;
	Boolean curvePoint;

	public ArcPath(String id, int x, int y, Boolean curvePoint){
		setId(id);
		setX(x);
		setY(y);
		setCurvePoint(curvePoint);
	}

	@XmlAttribute
	public String getId(){
		return id;
	}
	@XmlAttribute
	public int getX(){
		return x;
	}
	@XmlAttribute
	public int getY(){
		return y;
	}
	@XmlAttribute
	public Boolean getCurvePoint(){
		return curvePoint;
	}

	public void setId(String id){
		this.id = id;
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public void setCurvePoint(Boolean curvePoint){
		this.curvePoint = curvePoint;
	}
}