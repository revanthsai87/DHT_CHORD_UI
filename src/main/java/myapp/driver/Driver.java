package myapp.driver;
/**
 * This is the driver program which will be called by monitor
 * program by passing different parameters.
 * 
 *
 */
 import java.util.*;

import _cache.driver.LRU;
 //import _com.driver.DriverHelper;
// import _in.driver.*;
public class Driver {
	 /**
	  * Driver main program.
	  *
	  * @param args
	  */

	 //For supporting Cname Reord
	 // Write separate function for content in main
	 //
	 public static ArrayList<String> answer=new ArrayList<>();
	 public static _com.driver.DriverHelper h1 = new _com.driver.DriverHelper();
	 public static _in.driver.DriverHelper h2 = new _in.driver.DriverHelper();
	 public static _org.driver.DriverHelper h3 = new _org.driver.DriverHelper();
	 public static _edu.driver.DriverHelper h4 = new _edu.driver.DriverHelper();
	 public static _net.driver.DriverHelper h5 = new _net.driver.DriverHelper();
	 public static _MX.driver.DriverHelper h6 = new _MX.driver.DriverHelper();
	 public static _IP1.driver.DriverHelper ip1 = new _IP1.driver.DriverHelper();
	 public static _IP2.driver.DriverHelper ip2 = new _IP2.driver.DriverHelper();
	 public static _IP3.driver.DriverHelper ip3 = new _IP3.driver.DriverHelper();
	 public static _IP4.driver.DriverHelper ip4 = new _IP4.driver.DriverHelper();
	 public static _IP5.driver.DriverHelper ip5 = new _IP5.driver.DriverHelper();

	 //public static _cache.driver.LRU cache;
	 public Driver() {

	 }

	 public static void _in_helper(String s) {

		 String re = h2.runQueries(s);
		 if (re == null || re=="")
			 return;

		 LRU.insertToCache(s, re);


	 }

	 public static void _com_helper(String s) {
		 String re = h1.runQueries(s);
		// System.out.println("return value for ebay--"+re);
		 if (re == null || re=="")
			 return;

		 LRU.insertToCache(s, re);

	 }

	 public static void _org_helper(String s) {
		 String re = h3.runQueries(s);
		 if (re == null || re=="")
			 return;

		 LRU.insertToCache(s, re);


	 }

	 public static void _edu_helper(String s) {
		 String re = h4.runQueries(s);
		 if (re == null || re=="")
			 return;

		 LRU.insertToCache(s, re);


	 }

	 public static void _net_helper(String s) {
		 String re = h5.runQueries(s);
		 if (re == null || re=="")
			 return;

		 LRU.insertToCache(s, re);

	 }

	 public static void Initalize() {
	//	 myapp.driver.Driver d1=new myapp.driver.Driver();
		 //IP1 INITIALIZATION
		 System.out.println("Starting All Servers");
		 _IP1.driver.Driver d11 = new _IP1.driver.Driver();
		 d11.Initialization();
		 ip1.createBootStrapNode(16);
		 ip1.createNNodes(8);
		 ip1.insertData();
		 //IP2 INITIALIZATION
		 _IP2.driver.Driver d12 = new _IP2.driver.Driver();
		 d12.Initialization();
		 ip2.createBootStrapNode(16);
		 ip2.createNNodes(8);
		 ip2.insertData();
		 //IP3 INITIALIZATION
		 _IP3.driver.Driver d13 = new _IP3.driver.Driver();
		 d13.Initialization();
		 ip3.createBootStrapNode(16);
		 ip3.createNNodes(8);
		 ip3.insertData();
		 //IP4 INITIALIZATION
		 _IP4.driver.Driver d14 = new _IP4.driver.Driver();
		 d14.Initialization();
		 ip4.createBootStrapNode(16);
		 ip4.createNNodes(8);
		 ip4.insertData();
		 //IP5 INITIALIZATION
		 _IP5.driver.Driver d15 = new _IP5.driver.Driver();
		 d15.Initialization();
		 ip5.createBootStrapNode(16);
		 ip5.createNNodes(8);
		 ip5.insertData();
		 //COM TLD Initialization
		 _com.driver.Driver d = new _com.driver.Driver();
		 d.Initialization();
		 h1.createBootStrapNode(16);
		 h1.createNNodes(8);
		 h1.insertData();
		 //MX record initialization
		 _MX.driver.Driver d1 = new _MX.driver.Driver();
		 d1.Initialization();
		 h6.createBootStrapNode(16);
		 h6.createNNodes(8);
		 h6.insertData();

		 // IN TLD Initialization
		 _in.driver.Driver d2 = new _in.driver.Driver();
		 d2.Initialization();
		 h2.createBootStrapNode(16);
		 h2.createNNodes(8);
		 h2.insertData();

		 //ORG TLD Initialization
		 _org.driver.Driver d3 = new _org.driver.Driver();
		 d3.Initialization();
		 h3.createBootStrapNode(16);
		 h3.createNNodes(8);
		 h3.insertData();

		 //EDU TLD Initialization
		 _edu.driver.Driver d4 = new _edu.driver.Driver();
		 d4.Initialization();
		 h4.createBootStrapNode(16);
		 h4.createNNodes(8);
		 h4.insertData();

		 //NET TLD INITALIZATION
		 _net.driver.Driver d5 = new _net.driver.Driver();
		 d5.Initialization();
		 h5.createBootStrapNode(16);
		 h5.createNNodes(8);
		 h5.insertData();


	 }

	 public static void Exit() {
		 ip1.shutDownAllNodes(1);
		 ip2.shutDownAllNodes(1);
		 ip3.shutDownAllNodes(1);
		 ip4.shutDownAllNodes(1);
		 ip5.shutDownAllNodes(1);
		 h1.shutDownAllNodes(1);
		 h2.shutDownAllNodes(1);
		 h3.shutDownAllNodes(1);
		 h4.shutDownAllNodes(1);
		 h5.shutDownAllNodes(1);
		 h6.shutDownAllNodes(1);
	 }

	 public static ArrayList< String>user(String s) throws InterruptedException {
		 String ans = "";
		 answer.clear();
		 ans = LRU.checkCache(s);
		 if (ans != null) {
			answer.add("Record Fetched from Cache");
			 answer.add("IP is:" + ans);
		 } else if (s.toLowerCase().indexOf("mail.") == 0) {
			 h6.runQueries(s);
		 } else if (s.toLowerCase().contains(".com")) {
			 _com_helper(s);
		 } else if (s.toLowerCase().contains(".in")) {
			 _in_helper(s);
		 } else if (s.toLowerCase().contains(".org")) {
			 _org_helper(s);
		 } else if (s.toLowerCase().contains(".edu")) {

			 _edu_helper(s);

		 } else if (s.toLowerCase().contains(".net")) {
			 _net_helper(s);
		 } else {
			 answer.add("No Records Found Improper domain Name");
		 }
		 return answer;
	 }

	 public static String admin(int k, int l, int p) {
		 String ans = "";
		 if(k==1){
			 if(l==1){
				 if(p==1){

					 ans="Live Nodes count  before addition  "+String.valueOf(h1.print_livenodes())+"\n";
					 h1.createAdditionalNode();

					 ans+="Live Nodes count  after addition  "+String.valueOf(h1.print_livenodes());
				 }
				 else if(p==2){
					 ans="Live Nodes count  before addition  "+String.valueOf(h2.print_livenodes())+"\n";
					 h2.createAdditionalNode();

					 ans+="Live Nodes count  after addition  "+String.valueOf(h2.print_livenodes());
				 }
				 else if(p==3){
					 ans="Live Nodes count  before addition  "+String.valueOf(h3.print_livenodes())+"\n";
					 h3.createAdditionalNode();

					 ans+="Live Nodes count  after addition  "+String.valueOf(h3.print_livenodes());
				 }
				 else if(p==4){

					 ans="Live Nodes count  before addition  "+String.valueOf(h4.print_livenodes())+"\n";
					 h4.createAdditionalNode();

					 ans+="Live Nodes count  after addition  "+String.valueOf(h4.print_livenodes());
				 }
				 else if(p==5){
					 ans="Live Nodes count  before addition  "+String.valueOf(h5.print_livenodes())+"\n";
					 h5.createAdditionalNode();

					 ans+="Live Nodes count  after addition  "+String.valueOf(h5.print_livenodes());
				 }
				 else {

					 ans="Live Nodes count  before addition  "+String.valueOf(h6.print_livenodes())+"\n";
					 h6.createAdditionalNode();

					 ans+="Live Nodes count  after addition  "+String.valueOf(h6.print_livenodes());

				 }
			 }
			 else{
				 if(p==1){

					 ans="Live Nodes count  before addition  "+String.valueOf(ip1.print_livenodes())+"\n";
					 ip1.createAdditionalNode();
					ans+="Live Nodes count  after addition  "+String.valueOf(ip1.print_livenodes());
				 }
				 else if(p==2){
					 ans="Live Nodes count  before addition  "+String.valueOf(ip2.print_livenodes())+"\n";
					 ip2.createAdditionalNode();
					 ans+="Live Nodes count  after addition  "+String.valueOf(ip2.print_livenodes());
				 }
				 else if(p==3){
					 ans="Live Nodes count  before addition  "+String.valueOf(ip3.print_livenodes())+"\n";
					 ip3.createAdditionalNode();
					 ans+="Live Nodes count  after addition  "+String.valueOf(ip3.print_livenodes());
				 }
				 else if(p==4){
					 ans="Live Nodes count  before addition  "+String.valueOf(ip4.print_livenodes())+"\n";
					 ip4.createAdditionalNode();
					 ans+="Live Nodes count  after addition  "+String.valueOf(ip4.print_livenodes());
				 }
				 else if(p==5){
					 ans="Live Nodes count  before addition  "+String.valueOf(ip5.print_livenodes())+"\n";
					 ip5.createAdditionalNode();
					 ans+="Live Nodes count  after addition  "+String.valueOf(ip5.print_livenodes());
				 }
				 else {
					 System.out.println("// Enter proper input ");
				 }
			 }
		 }
		 else{
			 if(l==1){
				 if(p==1){

					 ans="Live Nodes count  before deletion  "+String.valueOf(h1.print_livenodes())+"\n";
					 h1.delete_activenode();

					 ans+="Live Nodes count  after deletion  "+String.valueOf(h1.print_livenodes());
				 }
				 else if(p==2){
					 ans="Live Nodes count  before deletion  "+String.valueOf(h2.print_livenodes())+"\n";
					 h2.delete_activenode();

					 ans+="Live Nodes count  after deletion  "+String.valueOf(h2.print_livenodes());
				 }
				 else if(p==3){
					 ans="Live Nodes count  before deletion  "+String.valueOf(h3.print_livenodes())+"\n";
					 h3.delete_activenode();

					 ans+="Live Nodes count  after deletion  "+String.valueOf(h3.print_livenodes());
				 }
				 else if(p==4){

					 ans="Live Nodes count  before deletion  "+String.valueOf(h4.print_livenodes())+"\n";
					 h4.delete_activenode();

					 ans+="Live Nodes count  after deletion  "+String.valueOf(h4.print_livenodes());
				 }
				 else if(p==5){
					 ans="Live Nodes count  before deletion  "+String.valueOf(h5.print_livenodes())+"\n";
					 h5.delete_activenode();

					 ans+="Live Nodes count  after deletion  "+String.valueOf(h5.print_livenodes());
				 }
				 else {

					 ans="Live Nodes count  before deletion  "+String.valueOf(h6.print_livenodes())+"\n";
					 h6.delete_activenode();

					 ans+="Live Nodes count  after deletion  "+String.valueOf(h6.print_livenodes());

				 }
			 }
			 else{
				 if(p==1){

					 ans="Live Nodes count  before deletion  "+String.valueOf(ip1.print_livenodes())+"\n";
					 ip1.delete_activenode();
					 ans+="Live Nodes count  after deletion  "+String.valueOf(ip1.print_livenodes());
				 }
				 else if(p==2){
					 ans="Live Nodes count  before deletion  "+String.valueOf(ip2.print_livenodes())+"\n";
					 ip2.delete_activenode();
					 ans+="Live Nodes count  after deletion  "+String.valueOf(ip2.print_livenodes());
				 }
				 else if(p==3){
					 ans="Live Nodes count  before deletion  "+String.valueOf(ip3.print_livenodes())+"\n";
					 ip3.delete_activenode();
					 ans+="Live Nodes count  after deletion  "+String.valueOf(ip3.print_livenodes());
				 }
				 else if(p==4){
					 ans="Live Nodes count  before deletion  "+String.valueOf(ip4.print_livenodes())+"\n";
					 ip4.delete_activenode();
					 ans+="Live Nodes count  after deletion  "+String.valueOf(ip4.print_livenodes());
				 }
				 else if(p==5){
					 ans="Live Nodes count  before deletion  "+String.valueOf(ip5.print_livenodes())+"\n";
					 ip5.delete_activenode();
					 ans+="Live Nodes count  after deletion  "+String.valueOf(ip5.print_livenodes());
				 }
				 else {
					 System.out.println("// Enter proper input ");
				 }

			 }
		 }

		 return ans;
	 }

public static void main(String[] args){

}



	 //Class
 }

