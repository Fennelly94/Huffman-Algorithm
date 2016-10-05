package textfiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * @author Gavin Fennelly
 * @version 1
 * Created: 6/05/2016
 * @Last edited: 18/05/2016
 */


public class ReadFile {

	// This is the word we will be taking into the programme
	
	static String Word = "";
	
	public static void main(String[] args) {
		
		
		// initalised as null
		BufferedReader br = null; 
		
		//Just in case of an error it will catch it
		
		try {

			
		//Reads whatever is in the input.txt file 
			
			br = new BufferedReader(new FileReader("input.txt"));

		 Word = br.readLine();
			
		 System.out.println(Word);
		 Counter.counter(Word);
		 
		 // in the case of an error it will be caught here
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
}