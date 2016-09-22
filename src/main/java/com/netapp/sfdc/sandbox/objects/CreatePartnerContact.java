package com.netapp.sfdc.sandbox.objects;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.soap.partner.QueryResult;
import com.sforce.soap.partner.sobject.SObject;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

	public class CreatePartnerContact {
	    private static PartnerConnection partnerConnection;
	    private static BufferedReader reader =
	        new BufferedReader(new InputStreamReader(System.in));
	   
	   /* public static void main(String[] args) {
	       CreatePartnerContact samples = new CreatePartnerContact();
	        if (samples.login()) {
	        querySample();
	       // createSample();
	       // updateSample();
	        }
	    }*/
	    
	    public static PartnerConnection getPartnerConnection() {
			return partnerConnection;
		}

		public static void setPartnerConnection(PartnerConnection partnerConnection) {
			CreatePartnerContact.partnerConnection = partnerConnection;
		}

		private String getUserInput(String prompt) {
	        String result = "";
	        try {
	          System.out.print(prompt);
	          result = reader.readLine();
	        } catch (IOException ioe) {
	          ioe.printStackTrace();
	        }
	        return result;
	    }
	   
	    public boolean login() {
	        boolean success = false;
	       /* String username = "intgpdb@netapp.com.esit2";
	        String password = "netapp123q2MfV3s2xDg3JLe1pMC2Mc8y";
	        String authEndPoint = "https://netapp--esit2.cs14.my.salesforce.com/services/Soap/u/31.0/";
	 */
	       /*String username = "intgpdb@netapp.com.sit";
	        String password = "Netapp7896NoEeBBzsG01h4EZ57JHf4lk";
	        String authEndPoint = "https://test.salesforce.com/services/Soap/u/33.0/0DFC0000000LGgA";*/
	       // System.setProperty("https.protocols", "TLS");
	        
	     /*  String username = "manisha.yadav@infogain.com";
	        String password = "Tingu@27kV65fLyGpj1uvpkspXxLRod3v";
	        String authEndPoint = "https://test.salesforce.com/services/Soap/u/37.0";*/
	       // System.setProperty("https.protocols", "TLS");
	        /*
	        String username = "sripriyv@netapp.com/soildfire.uat";
	        String password = "Minion123";
	        String authEndPoint = "https://test.salesforce.com/services/Soap/u/37.0";
	       */
	        String username = "deepak.manvati@infogain.com.devman";
	        String password = "infogainoct2016$tQTzKStg4GHTXbiuEz1DqfD01";
	        String authEndPoint = "https://test.salesforce.com/services/Soap/u/38.0";
	        
	        try {
	          ConnectorConfig config = new ConnectorConfig();
	          config.setUsername(username);
	          config.setPassword(password);
	         
	          config.setAuthEndpoint(authEndPoint);
	          config.setTraceFile("traceLogs.txt");
	          config.setTraceMessage(true);
	          config.setPrettyPrintXml(true);
	          System.out.println("1111111"+config.isManualLogin());
	         // System.out.println("2222222"+config.createTransport());
	          partnerConnection = new PartnerConnection(config);   
	         
	          System.out.println("bnbb"+config.getSessionId());
	          System.out.println("mmmmm"+config.getServiceEndpoint());
	          success = true;
	        } catch (Exception ce) {
	          ce.printStackTrace();
	        } /*catch (FileNotFoundException fnfe) {
	          fnfe.printStackTrace();
	        }*/
	 
	        return success;
	      }
	 
	    public  Job querySample(String id) {
	    	Job job = new Job();
	        try {
	        	
	        	/*job.setId("12345");
				job.setName("Test");
				job.setStatus("active");*/
			String soqlQuery = "SELECT Id,Name,Status__c FROM Job__C where id='"+id+"'";
			
			System.out.println(soqlQuery.toString());

			QueryResult qr = partnerConnection.query(soqlQuery.toString());
			SObject[] sobj = null;// getRecords();

			System.out
					.println("In PartnerUpdateObject ------------------------------- soqlQuery is:  "
							+ soqlQuery);
			System.out
					.println("In PartnerUpdateObject ------------------------------- QueryResult count is:  "
							+ qr.getSize());
			sobj = qr.getRecords();
			System.out
					.println("In PartnerUpdateObject -------------------------------- sobj count is: "
							+ sobj.length);

			System.out.println("contacts length:::" + sobj.length);
			
			if (sobj.length != 0){
				System.out.println(" id:::" + sobj[0].getField("Id"));
				System.out.println(" id:::" + sobj[0].getField("Name"));
				System.out.println(" id:::" + sobj[0].getField("Status__c"));
				
				job.setId(sobj[0].getField("Id").toString());
				job.setName(sobj[0].getField("Name").toString());
				job.setStatus(sobj[0].getField("Status__c").toString());
				
			}
		} catch (Exception ce) {
			ce.printStackTrace();
		}
		System.out.println("\nQuery execution completed.");
		return job;
	}
	    
	    
	    /*public static String updateSample() {
	        String result = null;
	        try {
	            // SOQL query to use
	            String soqlQuery = "SELECT id,FirstName, LastName, email, partner_user_id__c, cmat_id__C FROM Contact where id in ('003e000000Ov1YlAAJ', '003C000001Ao71S' )";
	            // Make the query call and get the query results
	            QueryResult qr = partnerConnection.query(soqlQuery);
	            boolean done = false;
	            int loopCount = 0;
	            int noOfRecords =0;
	           List<SObject> contactList = new ArrayList<SObject>();
	            
	            while (!done) {
	                System.out.println("Records in results set " + loopCount++
	                        + " - ");
	                SObject[] records = qr.getRecords();
	                noOfRecords = records.length;
	                // Process the query results
	                for (int i = 0; i < records.length; i++) {
	                    SObject contact = records[i];
	                    Object firstName = contact.getField("FirstName");
	                    Object lastName = contact.getField("LastName");
	                    if (firstName == null) {
	                        System.out.println("Contact " + (i + 1) +
	                                ": " + lastName
	                        );
	                    } else {
	                        System.out.println("Contact " + (i + 1) + ": " +
	                                firstName + " " + lastName);
	                    }
	                    contactList.add(contact);
	                }
	                if (qr.isDone()) {
	                    done = true;
	                } else {
	                    qr = partnerConnection.queryMore(qr.getQueryLocator());
	                }
	            }
	            
	            SObject[] contacts  = new SObject[noOfRecords];
	        
	            for  ( int i= 0; i <  contactList.size(); i++){
	              SObject  contact = contactList.get(i);
	                contact.setType("Contact");
	            
	                contact.setField("Id", contact.getField("id"));
	              contact.setField("cmat_id__C", "12345");
	              contacts[i] = contact;
	               
	            }
	           
	       
	           
	           
	            SaveResult[] results = partnerConnection.update(contacts);
	        
	            // Iterate through the results list
	            // and write the ID of the new sObject
	            // or the errors if the object creation failed.
	            // In this case, we only have one result
	            // since we created one contact.
	            for (int j = 0; j < results.length; j++) {
	                if (results[j].isSuccess()) {
	                    result = results[j].getId();
	                    System.out.println(
	                        "\nA contact with id  " + result + " has been updated successfully" 
	                    );
	                 } else {
	                    // There were errors during the create call,
	                    // go through the errors array and write
	                    // them to the console
	                    for (int i = 0; i < results[j].getErrors().length; i++) {
	                        com.sforce.soap.partner.Error err = results[j].getErrors()[i];
	                        System.out.println("Errors were found on item " + j);
	                        System.out.println("Error code: " + 
	                            err.getStatusCode().toString());
	                        System.out.println("Error message: " + err.getMessage());
	                    }
	                 }
	            }
	        } catch (ConnectionException ce) {
	            ce.printStackTrace();
	        }
	        return result;
	    }
	    */
	    
	    /*public static String createSample() {
	        String result = null;
	        try {
	            // Create a new sObject of type Contact
	               // and fill out its fields.
	        
	            SObject contact = new SObject();
	            contact.setType("Contact");
	            contact.setField("FirstName", "Otto");
	            contact.setField("LastName", "Jespersen");
	            contact.setField("Salutation", "Professor");
	            contact.setField("Phone", "(999) 555-1234");
	            contact.setField("Title", "Philologist");
	            contact.setField("recordtypeid", "012C0000000I3PG");
	            contact.setField("email", "ssubh@sri.com");
	        
	            // Add this sObject to an array 
	            SObject[] contacts = new SObject[1];
	            contacts[0] = contact;
	            // Make a create call and pass it the array of sObjects
	            SaveResult[] results = partnerConnection.create(contacts);
	        
	            // Iterate through the results list
	            // and write the ID of the new sObject
	            // or the errors if the object creation failed.
	            // In this case, we only have one result
	            // since we created one contact.
	            for (int j = 0; j < results.length; j++) {
	                if (results[j].isSuccess()) {
	                    result = results[j].getId();
	                    System.out.println(
	                        "\nA contact was created with an ID of: " + result
	                    );
	                 } else {
	                    // There were errors during the create call,
	                    // go through the errors array and write
	                    // them to the console
	                    for (int i = 0; i < results[j].getErrors().length; i++) {
	                        com.sforce.soap.partner.Error err = results[j].getErrors()[i];
	                        System.out.println("Errors were found on item " + j);
	                        System.out.println("Error code: " + 
	                            err.getStatusCode().toString());
	                        System.out.println("Error message: " + err.getMessage());
	                    }
	                 }
	            }
	        } catch (ConnectionException ce) {
	            ce.printStackTrace();
	        }
	        return result;
	    }
*/	    
	    
	    	
	}




