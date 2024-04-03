package br.com.tdc.bpmn.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrintCSV {

	public static void main(String[] args) throws IOException {
		String dir = "C:\\Users\\salger.oliveira\\Downloads\\Seazone\\";
		
		List<List<String>> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(dir + "Hosts_ids_Itapema_novo.csv"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	System.out.println(line);
		    	System.out.println("---");
		    }
		}
		
	}

	

}
