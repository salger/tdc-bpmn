package br.com.tdc.bpmn.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadCSV {

	public static void main(String[] args) throws IOException {
		String dir = "C:\\Users\\salger.oliveira\\Downloads\\Seazone\\";
		
		BufferedWriter bwr = new BufferedWriter(new FileWriter(dir + "Hosts_ids_Itapema_novo.csv"));
	

		List<List<String>> records = new ArrayList<>();
		String append = "";
		try (BufferedReader br = new BufferedReader(new FileReader(dir + "Hosts_ids_Itapema.csv"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	System.out.println(line);
//		    	System.out.println("---");
		        String[] values = line.split(";");
		        append = append + (line.toString().replaceAll("\n", "").replaceAll("\r", "").replaceAll("\r\n", "").replaceAll("<br>", ""));
		        try{
		        	System.out.println(values[0]);
		        	//System.out.println(Long.parseLong(values[0].replace("\"", "")));
		        	append = append + "\n";
		        }catch(Exception e) {
		        	System.out.println(line);
		        	System.out.println(e);
		        }
		        
		        append = append + values[0] + ";" + values[1] + ";" + values[2] + ";" + values[3] + ";" + values[4] + ";" + values[5] + ";" 
		        		 + values[8] + ";" + values[9] + ";" + values[10] + ";" + values[11] + "\n";
		        
		        records.add(Arrays.asList(values));
		    }
		    
		    System.out.println("Finishing");
		    
		    System.out.println(append);
			bwr.write(append);
			bwr.flush();
			bwr.close();
		}
		
		
	}

	

}
