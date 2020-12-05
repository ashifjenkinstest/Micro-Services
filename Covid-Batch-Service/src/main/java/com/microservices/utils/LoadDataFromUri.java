package com.microservices.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

import com.microservices.models.CovidCsvHeader;
import com.opencsv.CSVWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;


@Configuration
@Component
@ComponentScan(basePackages = "com.microservices")
@Scope("singleton")
public class LoadDataFromUri {
	
	
	@Value("${csv_data_dir}")
	private String csv_data_dir;
	
	@Value("${csv_file_date_time_format}")
	private String csv_file_date_time_format;
	
	public static  File csv_file = null;
	
	public static int day_count;
	

	private String headerLine[];
	
	private String fileName;
	
	
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String[] getHeaderLine() {
		return headerLine;
	}

	public void  setHeaderLine(String[] headerLine) {
		this.headerLine = headerLine;
	
	}
	
	/**
	 * Loads the URL and returns the content of the URL .
	 * @return  String
	 */
	@SuppressWarnings("unused")
	public  String getDataFromGit() {
		System.out.println("Enter getDataFromGitNEW");
		int status = 0;
		URL url = null;
		try {
			url = new URL(CovidConstants.CORONA_VIRUS_URL);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpURLConnection urlConnection = null;
		BufferedReader in = null;
		StringBuffer content = new StringBuffer();
		String inputLine;
		try {
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setConnectTimeout(5000);
			urlConnection.setReadTimeout(3000);
			urlConnection.setRequestMethod("GET");
			try {
				status = urlConnection.getResponseCode();
			} catch (Exception e) {
				urlConnection.disconnect();
				//this.coronaLocationStats = coronaLocationStats;
				return null;

			}

			status = urlConnection.getResponseCode();
			if (status > 299) {
				// streamReader = new InputStreamReader(con.getErrorStream());
				System.out.println("RESPONSE CODE: " + status);
				in = new BufferedReader(new InputStreamReader(urlConnection.getErrorStream()));
			} else {
				System.out.println("RESPONSE CODE: " + status);
				// streamReader = new InputStreamReader(con.getInputStream());
				in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
				// System.out.println(in);
				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine + "\n");
				}
				in.close();
			}
			urlConnection.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println(content.toString());
		return content.toString();
	}
	
	/**
	 * Converts String to CSV
	 * @param String
	 * @return Iterable<CSVRecord>
	 */
	
	public Iterable<CSVRecord> converStringToCSVV1(String data) {
		System.out.println("Enter converStringToCSVNEW");
		if (data!=null) {
			
			StringReader csvBodyReader = new StringReader(data);
			CSVParser records = null;
			try {
				
				records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
			} catch (IOException e) {
				e.printStackTrace();
			}System.out.println("Exit converStringToCSVNEW");
			return records;
			
		}
		System.out.println("Exit converStringToCSVNEW");
		return null;
		

	}
	

	
	public void writeToCSVFile(Iterable<CSVRecord> cSVRecords) throws IOException {
		
		System.out.println("Data Write Directory = " + csv_data_dir);
		File directory = new File(csv_data_dir);
		
		SimpleDateFormat formatter = new SimpleDateFormat(csv_file_date_time_format);  
	    Date date = new Date();  
	    //System.out.println(formatter.format(date).toString()); 
		String csv_file_name = "Covid_".concat(formatter.format(date).replace("\"", ""));
		
		
	    File file = File.createTempFile(csv_file_name, ".csv", directory);
	    System.out.println("AbsolutePath " + file.getAbsolutePath());
	    setFileName(file.getName());
	    CSVWriter writer = new CSVWriter(new FileWriter(file.getAbsolutePath()));
	    
		String headerLineLocal[] = null;//new String[record.size()];
		//"Province/State,Country/Region,Lat,Long";
		boolean readHeader = false;
		
	
		//System.out.println("Before looping readHeader" + readHeader);
		for (CSVRecord record : cSVRecords) {
			int recordLength = record.size();
			day_count = recordLength - 4;
			String line[] = new String[recordLength];
			
			if(!readHeader) {
				headerLineLocal = new String[recordLength];
				headerLineLocal[0] = "Province/State";headerLineLocal[1] = "Country/Region";headerLineLocal[2] = "Lat";headerLineLocal[3] = "Long";
				
				for(int i = 4;i<recordLength;i++) {
					headerLineLocal[i] = String.valueOf(i - 3);
					//System.out.println(headerLineLocal[i]);
					//if(i == recordLength - 1) {headerLineLocal[i] = String.valueOf(i - 3).concat("");}
				}
				
				readHeader = true;
				//System.out.println(headerLineLocal.toString());
				writer.writeNext(headerLineLocal);
				for (String str : headerLineLocal) {
					//System.out.print(str);
					
				}
				System.out.println();
				/*covidCsvHeader.*/setHeaderLine(headerLineLocal);
				//System.out.println("AAA---" + toString());
				//System.out.println("After looping readHeader" + readHeader);
			}
			
			
			for(int i = 0 ; i < recordLength ; i++) {
				line[i] = record.get(i);
			 }
		
			writer.writeNext(line);
			writer.flush();
			
		 }
		
		writer.close();
		
		
	}

	@Override
	public String toString() {
		return "LoadDataFromUri [headerLine=" + Arrays.toString(headerLine) + "]";
	}

}
