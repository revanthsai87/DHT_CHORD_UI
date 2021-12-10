package _net.driver;
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
		//int m = Integer.parseInt(args[0]);
		//In this implementation, m bits are converted into bytes array
		// and passing an arbitrary number of bits results in this implementation to fail.
		//System.out.println("Enter number of nodes: ");
		//int nodes = Integer.parseInt(args[1]);
		//System.out.println("Enter successor List Size: ");
		int successor_list_size =1;
		//int successor_list_size = Integer.parseInt(args[2]);
		//System.out.println("Enter No. of Queries: ");
		//int queries = 1;
		//int queries = Integer.parseInt(args[3]);
		//System.out.println("stabalization_delay_float");
		double stabalization_delay_float = 2; //sc.nextDouble();
		//double stabalization_delay_float = Double.parseDouble(args[4]);
		//System.out.println("fix_finger_delay_float");
		double fix_finger_delay_float = 2; //sc.nextDouble();
		//double fix_finger_delay_float = Double.parseDouble(args[5]);
		//System.out.println("check_pre_delay_float");
		double check_pre_delay_float = 1; //sc.nextDouble();
		//double check_pre_delay_float = Double.parseDouble(args[6]);
		//System.out.println("prob_failure");
	//	double prob_failure = 0.5; //sc.nextDouble();
		//double prob_failure = Double.parseDouble(args[7]);
		//System.out.println("run_number");
	//	int run_number = 1; //sc.nextInt();
		//int run_number = Integer.parseInt(args[8]);
		// Setup System parameters here.
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
//		Driver d=new Driver();
//		Scanner sc=
//		DriverHelper helper = new DriverHelper(args);
//		
//		helper.createBootStrapNode(d.m);
//		// Create N nodes
//		helper.createNNodes(d.nodes - 1);
//		// Insert Data
//		helper.insertData();
//		int choice;
//		 boolean b1=true;  
//		while(b1){
//			System.out.println("Option -1 : Add Node:::::::::::\n");
//    System.out.println("Option -2 : Delete Node:::::::::\n");
//     System.out.println("Option -3 : Query the Domain:::::::::\n");
//	 System.out.println("Option -4 : print live nodes and deleted nodes:::::::::\n");
//	 System.out.println("Option -5 : Shutdown and exit \n");
//			choice=sc.nextInt();
//			
//			
//			if(choice==1){
//				helper.createAdditionalNode();
//			}
//			
//			else if(choice ==2){
//				helper.delete_activenode();
//			}
//			
//			else if(choice == 3){
//		//		helper.runQueries(queries, prob_failure, run_number);
//			}
//			else if(choice == 4){
//				helper.print_livenodes();
//			}
//			else{
//				helper.shutDownAllNodes(run_number);
//		System.out.println("Process Exited");
//		     b1=false;
//				
		//	}
			
			
		}
			
			
			
		
		
		
		
		//Correctness Testing
		//helper.testCorrectness();
		// LookUp Keys
		//helper.runQueries(queries, prob_failure, run_number);
		// ShutDown Nodes
		//helper.shutDownAllNodes(run_number);
		//System.out.println("Process Exited");
	//}
}