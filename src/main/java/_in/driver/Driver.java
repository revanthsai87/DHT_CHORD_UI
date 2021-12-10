package _in.driver;
/**
 * This is the driver program which will be called by monitor
 * program by passing different parameters.
 * 
 *
 */
 import java.util.*;
 
public class Driver {
	/**
	 * Driver main program.

	 */

public Driver(){
	
}
	public void  Initialization() {
		de.uniba.wiai.lspi.chord.service.PropertiesLoader.loadPropertyFile();
		int successor_list_size =1;
		double stabalization_delay_float = 2;
		double check_pre_delay_float = 1; 
		double fix_finger_delay_float = 2; 
		System.setProperty("de.uniba.wiai.lspi.chord.service.impl.ChordImpl.StabilizeTask.interval",
				"" + (int)(stabalization_delay_float*10));
		System.setProperty("de.uniba.wiai.lspi.chord.service.impl.ChordImpl.FixFingerTask.interval",
				"" + (int)(fix_finger_delay_float*10));
		System.setProperty("de.uniba.wiai.lspi.chord.service.impl.ChordImpl.CheckPredecessorTask.interval",
				"" + (int)(check_pre_delay_float*10));
		System.setProperty("de.uniba.wiai.lspi.chord.service.impl.ChordImpl.successors", "" + successor_list_size);
		// Create BootStrap Node
	}
	public String _IP1_Helper(String s) {
		return myapp.driver.Driver.ip1.runQueries(s);
	}
public String _IP2_Helper(String s) {
		return myapp.driver.Driver.ip2.runQueries(s);
	}
public String _IP3_Helper(String s) {
	return myapp.driver.Driver.ip3.runQueries(s);
}
public String _IP4_Helper(String s) {
	 return myapp.driver.Driver.ip4.runQueries(s);
}
public String _IP5_Helper(String s) {
	return myapp.driver.Driver.ip5.runQueries(s);
}
	public String _LEVEL3_Helper(int n,String s) {
		String ans=null;
		if(n==1) {
	//		_IP1.driver.DriverHelper helper = new _IP1.driver.DriverHelper();
			//_IP1.driver.Driver d=new _IP1.driver.Driver();
			//d.Initialization();
//			helper.createBootStrapNode(16);
//			helper.createNNodes(8);
//			helper.insertData();
			return _IP1_Helper( s);
		//	helper.runQueries(s);
		}
		else if(n==2)
		{
	//		_IP2.driver.DriverHelper help=new _IP2.driver.DriverHelper();
		//	_in.driver.Driver d=new _in.driver.Driver();
			//d.Initialization();
	//		help.createBootStrapNode(16);
	//		help.createNNodes(8);
	//		help.insertData();
			return _IP2_Helper(s);
			//help.runQueries(s);
			//System.out.println("NEED TO IMPLEMENT");
	
	}
		else if(n==3) {
//		_IP3.driver.DriverHelper help=new _IP3.driver.DriverHelper();
		
//		help.createBootStrapNode(16);
//		help.createNNodes(8);
//		help.insertData();
		return _IP3_Helper(s);
		
		}
		else if(n==4) {
	//	_IP4.driver.DriverHelper help=new _IP4.driver.DriverHelper();
		
	//	help.createBootStrapNode(16);
	//	help.createNNodes(8);
	//	help.insertData();
		return _IP4_Helper(s);
		
		}
		else if(n==5) {
	//	_IP5.driver.DriverHelper help=new _IP5.driver.DriverHelper();
		
	//	help.createBootStrapNode(16);
	//	help.createNNodes(8);
	//	help.insertData();
		return _IP5_Helper(s);
		
		}

		else {
			System.out.println("NEED TO IMPLEMENT THIS AGAIN");
			return ans;
			
		}

	}						   
	
	public static void main(String[] args) {
		}
			
			
			
		
		
	
}