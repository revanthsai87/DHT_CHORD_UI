package _cache.driver;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime; 
public class LRUEntry implements Comparable<LRUEntry>{
	private String Domain;
	private String IPAddress;	
	private String timestamp;

	public LRUEntry() {
		super();
	}
	
	public String getDomain() {
		return Domain;
	}
	public void setDomain(String domain) {
		Domain = domain;
	}
	public String getIPAddress() {
		return IPAddress;
	}
	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public int compareTo(LRUEntry o) {
		// TODO Auto-generated method stub
		return timestamp.compareTo(o.getTimestamp());
		
	}

	
	
}
