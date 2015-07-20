	import java.io.Serializable;
import java.net.InetAddress;


public class PeerList implements Serializable {
	private static final long serialVersionUID = 6529685098267757690L;
InetAddress ip ;
	public String Hname;
	public int Cookie,ttl,Pnum,noA;
	public Boolean Flag;
	public String getHname() {
		return Hname;
	}
	public void setHname(String hname) {
		Hname = hname;
	}
	public int getCookie() {
		return Cookie;
	}
	public void setCookie(int cookie) {
		Cookie = cookie;
	}
	public int getTtl() {
		return ttl;
	}
	public void setTtl(int ttl) {
		this.ttl = ttl;
	}
	public int getPnum() {
		return Pnum;
	}
	public void setPnum(int pnum) {
		Pnum = pnum;
	}
	public int getNoA() {
		return noA;
	}
	public void setNoA(int noA) {
		this.noA = noA;
	}
	public Boolean getFlag() {
		return Flag;
	}
	public void setFlag(Boolean flag) {
		Flag = flag;
	}
	

	
}
