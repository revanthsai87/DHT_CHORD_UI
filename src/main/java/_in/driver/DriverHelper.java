package _in.driver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import _com.driver.Driver;
import de.uniba.wiai.lspi.chord.com.Entry;
import de.uniba.wiai.lspi.chord.data.ID;
import de.uniba.wiai.lspi.chord.data.URL;
import de.uniba.wiai.lspi.chord.service.Chord;
import de.uniba.wiai.lspi.chord.service.ServiceException;
import de.uniba.wiai.lspi.chord.service.impl.Entries;
import de.uniba.wiai.lspi.chord.service.impl.HashFunction;
import de.uniba.wiai.lspi.chord.service.impl.RetrievedKey;
import de.uniba.wiai.lspi.chord.service.Key;

/**
 * This is the helper class to run all the methods related to chord
 * implementation.
 * 
 */
public class DriverHelper {
	/**
	 * Set of running nodes.
	 */
	private Set<Chord> runningNodes = new HashSet<Chord>();
	/**
	 * Set of temporary failed nodes.
	 */
	private Set<Chord> failedChordNodes = new HashSet<Chord>();
	/**
	 * Set of all nodes in the chord ring.
	 */
	private Set<Chord> allNodes = new HashSet<Chord>();
	/**
	 * DNS Data list
	 */
	private List<DNS> dnsDataList = new ArrayList<DNS>();
	/**
	 * Correctness test data
	 */
	private List<DNS> correctnessTestDataList = new ArrayList<DNS>();
	/**
	 * Line separator based on system.
	 */
	private String separator = System.getProperty("line.separator");
	/**
	 * Bootstrap node or the first node which creates the chord ring.
	 */
	private Chord bootStrapNode;
	/**
	 * Bootstrap node URL String
	 */
	private String bootStrapNodeURL = "://localhost:8082/";
	/**
	 * URL
	 */
	private URL bootStrapCompleteURL;
	/**
	 * Port number initial value to start ring.
	 */
	private int port = 8181;
	/**
	 * URL to entries Map
	 */
	private Map<URL, FailedNode> urlToEntriesMap = new HashMap<URL, FailedNode>();
	/**
	 * Number of failures.
	 */
	private int numberofFailures = 0;
	/**
	 * Random number to select the chord node randomly for lookup.
	 */
	Random rand = new Random();

	/**
	 * Constructor
	 *
	 */
	public DriverHelper() {
		
	}					
	public DriverHelper(String[] args) {

	}
	
	Scanner sc = new Scanner(System.in);

	/**
	 * Method to create bootstrap node.
	 * 
	 * @param m
	 *            int number of bits
	 */
	public void createBootStrapNode(int m) {
		HashFunction.getHashFunction(m / 8);
		String protocol = URL.KNOWN_PROTOCOLS.get(URL.LOCAL_PROTOCOL);
		URL localURL = null;
		try {
			localURL = new URL(protocol + bootStrapNodeURL);
			bootStrapCompleteURL = localURL;
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
		Chord chord = new de.uniba.wiai.lspi.chord.service.impl.ChordImpl();

		try {
			// Creating the chord ring with the local URL.
			chord.create(localURL);
			allNodes.add(chord);
			//System.out.println(chord.getID());
			bootStrapNode = chord;
		} catch (ServiceException e) {
			throw new RuntimeException(" Could not create DHT !", e);
		}
	}

	/**
	 * Method to create and join n-1 left nodes to the chord ring.
	 * 
	 * @param N
	 */
	 static int i=0;
	public void createNNodes(int N) {
		for (; i < N; i++) {
			joinNode(port + i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void createAdditionalNode() {	
			joinNode(port + i);
			i++;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
	}

	/**
	 * Helper method used to join a node at specified port number.
	 * 
	 * @param portNumber
	 *            int
	 */
	public void joinNode(int portNumber) {
		Chord chord = new de.uniba.wiai.lspi.chord.service.impl.ChordImpl();
		String protocol = URL.KNOWN_PROTOCOLS.get(URL.LOCAL_PROTOCOL);
		URL localURL = null;
		try {
			localURL = new URL(protocol + bootStrapNodeURL + portNumber + "/");
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
		URL bootstrapURL = null;
		try {
			bootstrapURL = new URL(protocol + bootStrapNodeURL);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
		try {
			chord.join(localURL, bootstrapURL);
			//System.out.println(chord.getID());
			runningNodes.add(chord);
			allNodes.add(chord);
		} catch (ServiceException e) {
			throw new RuntimeException("Could not join DHT!", e);
		}

	}

	/**
	 * Method to insert data by reading the csv file and inserting the data in
	 * chord ring.
	 */
	public void insertData() {
		dnsDataList = CSVReader.read("./in.csv");
		for (DNS data : dnsDataList) {
			_in.driver.StringKey myKey=new _in.driver.StringKey(data.getWebsiteName());
			//StringKey myKey = new StringKey(data.getWebsiteName());
			data.setMyKey(myKey);
			try {
				bootStrapNode.insert(myKey, data.getIpAddress());
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Method to run queries.
	 **/
	public String runQueries(String inputURL) {
		String res=null;
		String output="";
		try {
			String[] temp=inputURL.split("\\.");
			int n1=temp.length;
			Key sk = new StringKey(temp[n1-2]+"."+temp[n1-1]);
			Chord chord = randomlySelectChordNode();
			//System.out.println("noDE WHICH IS SELECTED /////////////////*******: " + chord.getURL()); //debug krishna
			Driver d=new Driver();

			RetrievedKey retrievedKey = chord.retrieveWithHopCount(sk);
			Set<Serializable> values = retrievedKey.getValues();
			if (values.size() > 0) {
				for (Serializable k : values) {
					String value = k.toString();
					// If value is a NS record or CName record
					//IF ns record for now print the respective value.
					//If cname record return the vale to root.
					try {
						int num= Integer.parseInt(value);
						System.out.println("NS record Found at level 2");
						output+="NS record Found at level 2";
						res=d._LEVEL3_Helper(num, inputURL);

						// System.out.println("RES VALUE"+res);

					} catch(NumberFormatException e){
						//  System.out.println("sdfsf");
						if(value.charAt(0)!='{')  {
							System.out.println("CNAME record is: "+inputURL+" -> "+value);
							output+="CNAME record is: "+inputURL+" -> "+value;
							res=runQueries(value);

						}
						else {

							System.out.println("Querying for Subdomains");
							output+="Querying for Subdomains";
							int n=Sub_domain_helper(temp,n1-3,value);
							if(n==-1) {
								System.out.println("Some Error");
							}
							else {
								res=d._LEVEL3_Helper(n, inputURL);
							}

						}

					}

				}
			}
			else{
				System.out.println("Level2 : No DNS records found for "+inputURL);
				output+="Level2 : No DNS records found for "+inputURL;
			}
		}

		catch (Exception e1) {
			e1.printStackTrace();
		}
		myapp.driver.Driver.answer.add(output);
		return res;
	}
	
	///SUB DOMAIN RESOLVER
	
	public static int Sub_domain_helper(String[] sk,int len,String s) {
      // System.out.println(s);
        if(s.length()==3){
            return Character.getNumericValue(s.charAt(1));
        }
        if(len==-1){
            sk[0]="Default";
            return Sub_domain_helper(sk,0,s);
        }

		int count=0,start=0,end=1,flag=0;
		String key=null,value=null;
		for(int i=1;i<s.length()-1;i++){
		   if(count==0 && s.charAt(i)==':'){
		       start=i+1;
		      key=s.substring(end,i);
		      if(key.equals(sk[len])){
		         // System.out.println("key--"+key);
		          flag=1;
		      }
		   }
		   else if(s.charAt(i)=='{')
		       count++;
		   else if(s.charAt(i)=='}')
		    count--;
		   if(count==0 && s.charAt(i)=='?'){
		       
		       value=s.substring(start,i);
		       end=i+1;
		       if(flag==1){
		         //  System.out.println("value--"+value);
		        return   Sub_domain_helper(sk,len-1,value);
		       }
		   }
		   
		}
		return -1;
	}

	/**
	 * Method to randomly select data that will be queried.
	 * 
	 * @return {@link DNS}
	 */
	public DNS randomlySelectData() {
		Random rand = new Random();
		return dnsDataList.get(rand.nextInt(dnsDataList.size()));
	}

	/**
	 * Method to randomly select a node on which lookup request will be sent.
	 * 
	 * @return {@link Chord}
	 */
	public Chord randomlySelectChordNode() {

		if (allNodes.size() >= 1) {
			int randomNumber = rand.nextInt(allNodes.size());
			int i = 0;
			for (Chord chord : allNodes) {
				if (i != randomNumber) {   //== removed != instead
					//System.out.println("Selected node: " + chord.getURL());
					//System.out.println("Selected node data----- : " + chord.getID().toString()); //debug krishna
					return chord;
				}
			}
		}
		// It can't return null though.
		return bootStrapNode;
	}

	/**
	 * Method to fail and make temporary failed node alive based on passed
	 * probability.
	 * 
	 * @param probFailure
	 *            double
	 */
	public void probabilityFailure(double probFailure) {
		// Starting a failed node node randomly.
		Iterator failedNodesIt = failedChordNodes.iterator();
		while (failedNodesIt.hasNext()) {
			Chord chord = (Chord) failedNodesIt.next();
			if (Math.random() > probFailure) {
				System.out.println("Started the node: " + chord.getID());
				chord.setTempFailure(false);
				failedNodesIt.remove();
				runningNodes.add(chord);
			}
		}
		// Failing a running node randomly.
		Iterator it = runningNodes.iterator();
		while (it.hasNext()) {
			Chord chord = (Chord) it.next();
			if (Math.random() < probFailure) {
				System.out.println("Failed the node: " + chord.getID());
				failedChordNodes.add(chord);
				chord.setTempFailure(true);
				it.remove();
			}

		}
	}


	public int print_livenodes(){
		int count=0;
		Iterator it = runningNodes.iterator();
			while (it.hasNext()) {
				Chord chord = (Chord) it.next();
					//System.out.println("live nodes the node: " + chord.getID());	
					count++;
				}
	return count;
			}
public void delete_activenode(){
	
	try{
	Iterator it = runningNodes.iterator();
		while (it.hasNext()) {
		Chord chord = (Chord) it.next();
			if (Math.random() < 0.5) {
				System.out.println("Failed the node: " + chord.getID());
				failedChordNodes.add(chord);
				chord.setTempFailure(true);
				chord.leave();
				it.remove();
				break;
			}
		
		}
	}
	catch(Exception e){
				e.printStackTrace();
		}			
		
		
		
	}
	
	

		

	/**
	 * Method to save all key value pairs in a local variable. This is used to
	 * calculate the storage count when the shutdown command is fired.
	 * 
	 * @param mp
	 *            Map
	 * @param entries
	 *            Set<Entry>
	 */
	public void saveEntries(Map mp, Set<Entry> entries) {
		Iterator it = mp.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			// System.out.println(pair.getKey() + " = " + pair.getValue());
			Set<Entry> entries1 = (Set<Entry>) pair.getValue();
			if (entries1 != null) {
				for (Entry entry1 : entries1) {
					Entry entry = new Entry(new ID(entry1.getId().id), entry1.getValue());
					entries.add(entry);
				}
			}

		}
	}

	/**
	 * Method to shutdown bootstrap node.
	 * 
	 * @param runNumber
	 * @return StringBuilder which contains information about storage and query
	 *         load to be logged in file.
	 */
	public StringBuilder shutDownBootStrapNode(int runNumber) {
		Entries entries = bootStrapNode.getEntries();
		Map<ID, Set<Entry>> entriesMap = entries.getEntries();
		Set<Entry> entriesSaved = new HashSet<Entry>();
		saveEntries(entriesMap, entriesSaved);
		int storageLoad = entriesSaved.size();
		int queryLoad = bootStrapNode.getLoadCount();
		try {
			bootStrapNode.leave();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		sb.append(runNumber);
		sb.append(",");
		sb.append(storageLoad);
		sb.append(",");
		sb.append(queryLoad);
		return sb;
	}


	/**
	 * Method to shut down all the nodes.
	 * 
	 * @param runNumber
	 *            int
	 */
	public void shutDownAllNodes(int runNumber) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter("./results_2_java.txt", true);
			// Running nodes apart from bootstrap node.
			Iterator it = runningNodes.iterator();
			while (it.hasNext()) {
				Chord chord = (Chord) it.next();
				Entries entries = chord.getEntries();
				Map<ID, Set<Entry>> entriesMap = entries.getEntries();
				Set<Entry> entriesSaved = new HashSet<Entry>();
				saveEntries(entriesMap, entriesSaved);
				int storageLoad = entriesSaved.size();
				int queryLoad = chord.getLoadCount();
				chord.leave();
				it.remove();
				StringBuilder sb = new StringBuilder();
				sb.append(runNumber);
				sb.append(",");
				sb.append(storageLoad);
				sb.append(",");
				sb.append(queryLoad);
				fileWriter.write(sb.toString());
				fileWriter.write(separator);
			}
			// failedNodes
			Iterator failedNodesIt = failedChordNodes.iterator();
			while (failedNodesIt.hasNext()) {
				Chord chord = (Chord) failedNodesIt.next();
				Entries entries = chord.getEntries();
				Map<ID, Set<Entry>> entriesMap = entries.getEntries();
				Set<Entry> entriesSaved = new HashSet<Entry>();
				saveEntries(entriesMap, entriesSaved);
				int storageLoad = entriesSaved.size();
				int queryLoad = chord.getLoadCount();
				chord.leave();
				failedNodesIt.remove();
				StringBuilder sb = new StringBuilder();
				sb.append(runNumber);
				sb.append(",");
				sb.append(storageLoad);
				sb.append(",");
				sb.append(queryLoad);
				fileWriter.write(sb.toString());
				fileWriter.write(separator);
			}
			// bootStrapNode
			StringBuilder bootStrapSb = shutDownBootStrapNode(runNumber);
			fileWriter.write(bootStrapSb.toString());
			fileWriter.write(separator);

		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileWriter != null) {
					fileWriter.flush();
					fileWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Method to test the correctness of the chord ring. It checks for the
	 * scenarios when a key-value pair exists in the ring then value returned
	 * should be correct. If the key is not present, the ring should not return
	 * any value.
	 */
	public void testCorrectness() {
		correctnessTestDataList = CSVReader.read("./CorrectnessData.csv");
		List<DNS> correctnessViolated = new ArrayList<DNS>();
		for (DNS data : correctnessTestDataList) {
			System.out.println("Data: " + data.getIpAddress() + "Value: " + data.getWebsiteName());
			_in.driver.StringKey myKey = new _in.driver.StringKey(data.getWebsiteName());
			data.setMyKey(myKey);
			try {
				Set<Serializable> values = bootStrapNode.retrieve(myKey);
				if (values != null && values.size() >= 1) {
					for (Serializable k : values) {
						String value = k.toString();
						if (value.equals(data.getIpAddress())) {
							System.out.println("Correctness Verified for: " + data.getWebsiteName());
							break;
						}
					}
				} else if (data.getIpAddress().equals("-1") && values != null && values.size() == 0) {
					System.out.println("Correctness Verified for: " + data.getWebsiteName());
				} else {
					correctnessViolated.add(data);
				}

			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		// If there are any correctness violation it will write that to the
		// file.
		if (correctnessViolated.size() > 0) {
			FileWriter fileWriter = null;
			try {
				fileWriter = new FileWriter("./correctnessVerificationJava.txt", true);
				StringBuilder sb = new StringBuilder();
				sb.append("Correctness Violated");
				fileWriter.write(sb.toString());
				fileWriter.write(separator);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (fileWriter != null) {
						fileWriter.flush();
						fileWriter.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
