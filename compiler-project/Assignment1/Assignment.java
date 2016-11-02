package DSPack;


import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader; 
import java.io.FileWriter;
import java.io.FileReader;

/*
 * Description:
 * This java program takes a function gettoken to make tokens of the corresponding lexemes
 * from inputfile.txt and writes the corresponding tokens to outputfile.txt
 *
 */


    public class Assignment {
    	
    	/*
    	 * the function getToken() accepts string result as an input.
    	 * It recognizes lexemes of a language and outputs tokens corresponding
    	 * to that lexemes in the outputfile.txt.
    	 */
    	
    	
       public static void main(String[] args) throws IOException {
             
    	        
         	  String returnVal = "";
         	  FileReader file = null;
         	  
         	  //code to convert the input.txt file to a single line
         	  
         	  try {
         	    file = new FileReader("inputfile.txt");
         	    BufferedReader reader = new BufferedReader(file);
         	    String line = "";
         	    while ((line = reader.readLine()) != null) {
         	      returnVal += line + " ";
         	    }
         	    
         	  //Changing all spaces to a single space
         	    
                String result=returnVal.replaceAll("[\\s]+", " ");
                
                //calling the getToken funtion
                 getToken(result);
                 reader.close();
         	  } catch (Exception e) {
         	      throw new RuntimeException(e);
         	  } finally {
         	    if (file != null) {
         	      try {
         	        file.close();
         	      } catch (IOException e) {
         	        
         	      }
         	    }
         	  }
         	} 

    	
    	
    	
    	
    	//the gettoken function running code
    	
    	public static void getToken(String result)
    	{ 
    		
    		FileWriter outputfile = null;
    	    try {
    	      outputfile = new FileWriter("outputfile.txt");
    	      BufferedWriter writer = new BufferedWriter(outputfile);
    	      
    	    
    		
    		int i=0;
    		
    		while(i<result.length())
    		{
    			
    			if(result.charAt(i)!=' ')
    			{
    				
    				StringBuffer buff=new StringBuffer("");
    				
    				//To find Words
    				if(Character.toString(result.charAt(i)).matches("[A-Za-z]+"))   					
    				{
    					
    					while(result.charAt(i)!=' '&& Character.toString(result.charAt(i)).matches("[A-Za-z0-9]+"))
    					{
    						buff.append(Character.toString(result.charAt(i)));
    						i++;
    					}
    					writer.write(buff + "\t" + "TOKWORD" + "\n");
    					writer.newLine();
    				}
    				
    				//To find Numbers
    				else if(Character.toString(result.charAt(i)).matches("[0-9]+"))
    				{
    					
    					while(result.charAt(i)!=' ' && Character.toString(result.charAt(i)).matches("[0-9]+") )
    					{
    						buff.append(Character.toString(result.charAt(i)));
    						i++;
    						
    						//To find Decimal Numbers
    						if (result.charAt(i)=='.' && Character.toString(result.charAt(i+1)).matches("[0-9]+"))
    						{
    							while(Character.toString(result.charAt(i+1)).matches("[0-9]+"))
    							{	
    							buff.append(Character.toString(result.charAt(i)));
    							i++;
    							}
    						}
    					}
    					writer.write(buff + "\t" + "TOKNUMBER"+ "\n");
    					writer.newLine();
    				}
    				
    				//To find Operators
    				else
    				{
    					
    					writer.write(result.charAt(i) + "\t" + "TOKOP" + "\n");
    					writer.newLine();
    					i++;
    					
    				}
    			}
    			if (result.charAt(i)== ' ')
    			i++;
    				
    		}
    		writer.close();
    	    }
    		catch (Exception e) {
      	      throw new RuntimeException(e);
      	    } finally {
      	      if (outputfile != null) {
      	        try {
      	          outputfile.close();
      	        } catch (IOException e) {
      	          
      	        }
      	      }
      	    }
    	}
    		
    
    


       
        }   