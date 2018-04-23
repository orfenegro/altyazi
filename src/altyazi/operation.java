package altyazi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class operation{
	public static int howmany =0;
	public static int howmanysmalli=0;
	public static double ratio;
	public static boolean changed;
	public static boolean utf;
	public static boolean ansii;
	public static String[] returnvalues;
	File myFile;
    public String koddegýstýr(String myfile) throws IOException{
    	returnvalues = new String[2];
    	/*english-turkish
    	 *ansii-utf8 
    	 * 
    	 * 
    	 * 
    	 * 
    	 * 
    	 * 
    	 */
    	int changedFiles = 0;
    	File file = new File(myfile);
    	byte[] bytesArray = new byte[(int) file.length()];
    	FileInputStream fis = new FileInputStream(file);
		fis.read(bytesArray);
		fis.close();
    	int[] freqs = new int[256];
		  for(byte b: bytesArray){
			  freqs[b&0x0ff]++;
		  }
		  howmany = freqs[107]+freqs[75];
		  howmanysmalli=freqs[253];
		  //System.out.println("Character \"k\" appears " + howmany +" times in the text "+myfile);
		  ratio = (double)howmany/(double)bytesArray.length;
		  /*
		  System.out.println("How many: "+howmany);
		  System.out.println("Length: "+bytesArray.length);
		  System.out.println("Ratio: "+ratio);
		  */
		  //Cp1254
		  if(ratio<0.01){
			  System.out.println("Text file is probably not turkish");
		  }else{
			  System.out.println("Text file is probably turkish");
			  if(howmanysmalli>20){
				  String line;
				  BufferedReader br = new BufferedReader(new InputStreamReader(
				            new FileInputStream(myfile),
				            "ISO-8859-9"));
				  Writer out = new BufferedWriter(
				            new OutputStreamWriter(new FileOutputStream(
				            		myfile+"-new"), "UTF-8"));
				  try {		
				        while ((line = br.readLine()) != null) {
				
				            out.write(line);
				            out.write("\n");
				        }
				        changedFiles=changedFiles+2;
				        
				        	        
				    } finally {
				    	br.close();
				        out.close();
				        if(file.delete())
				        {
				            //System.out.println("File deleted successfully");
				        }
				        else
				        {
				            //System.out.println("Failed to delete the file");
				        }
				        File file2 = new File(myfile+"-new");
				        File file3 = new File(myfile);
				        file2.renameTo(file3);	
				    }
			  }else{
				  changedFiles++;
				  //System.out.println("Passed as utf-8");
			  }
		  }
		  if(changedFiles>1){
			  String status =myfile+" deðiþtirildi !";
			  return status;
		  }else if(changedFiles>0){
			  String status =myfile+" ansii olmadýðý için atlandý!";
			  return status;
		  }else{
			  String status =myfile+" Türkçe olmadýðý için atlandý!";
			  return status;
		  }
	}
}

	

