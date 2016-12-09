package block1;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;
@XmlType(propOrder = {
        "id",
        "enabled",
        "red",
        "green",
        "blue"
})
public class Token {
	String id;
	String enabled;
	int red;
	int green;
	int blue;

	public Token(String tokenId,String tokenEnabled,int tokenRed,int tokenGreen,int tokenBlue){
		setId(tokenId);
		setEnabled(tokenEnabled);
		setRed(tokenRed);
		setGreen(tokenGreen);
		setBlue(tokenBlue);
	}

	@XmlAttribute
	public String getId(){
		return this.id;
	}
	@XmlAttribute
	public String getEnabled(){
		return this.enabled;
	}
	@XmlAttribute
	public int getRed(){
		return this.red;
	}
	@XmlAttribute
	public int getGreen(){
		return this.green;
	}
	@XmlAttribute
	public int getBlue(){
		return this.blue;
	}
	public void setId(String id){
		this.id = id;
	}
	public void setEnabled(String enabled){
		this.enabled = enabled;
	}
	public void setRed(int red){
		this.red = red;
	}
	public void setGreen(int green){
		this.green = green;
	}
	public void setBlue(int blue){
		this.blue = blue;
	}
}