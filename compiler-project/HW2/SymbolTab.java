                 /*thomas raphel*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;

//Function to get the token and return it. 
public class SymbolTab {
	public String gettoken(String str,String[] k,String[] m)
	{ 
		//Remove the case sensitivity
		String i_str = str.toUpperCase();
		char[] temp = str.toCharArray();
		int n = k.length;
		int i = 0;
		while(i<n)
		{
			// keyword or token operator
			if(i_str.equals(k[i]))
			{
				return m[i];
			}
			else
				i++;
		}
		// tokword
		if((temp[0]>=65 && temp[0]<=90) || (temp[0]>=97 && temp[0]<=122))
			return "tokidentifier";
		// toknumber
		else if(temp[0]>=48 && temp[0]<=57)
			return "toknumber";
		// tokop
		else
			return "tokerror";

	}

public static void main (String[] args) throws FileNotFoundException{
		// Output file
		PrintStream out=new PrintStream(new BufferedOutputStream(new FileOutputStream("D://output.txt")));
		SymbolTab lexs = new SymbolTab();
		BufferedReader reader = null;
		try {

			String in_str;
			String i_str="";
			int count=0;
			// Operators and Keywords array
			String[] res_words={
					"AND","ARRAY","BEGIN","CASE","CONST","DIV","DO","DOWNTO","ELSE","END","FILE","FOR","FUNCTION","GOTO","IF","IN","LABEL","NIL","NOT","OF","OR","PACKED","PROCEDURE","PROGRAM","RECORD","REPEAT","SET","THEN","TO","TYPE","UNTIL","VAR","WHILE","WITH",".", ",", ";", "[", "]", "\"", "'", ":",  "(", ")", "=", "<>", "<", "<=", ">", ">=", "+", "-", "*", "/", "MOD",  ":=", "INTEGER", "REAL", "WRITE", "WRITELN", "READLN"
			};
			// Token Class array
			String[] token_class={
					"tokand","tokarray","tokbegin","tokcase","tokconst","tokdiv","tokdo","tokdownto","tokelse","tokend","tokfile","tokfor","tokfunction","tokgoto","tokif","tokin","toklabel","toknil","toknot","tokof","tokor","tokpacked","tokprocedure","tokprogram","tokrecord","tokrepeat","tokset","tokthen","tokto","toktype","tokuntil","tokvar","tokwhile","tokwith","tokperiod","tokcomma","toksemicolon","tokopenbracket","tokclosebracket","tokdoublequote","tokquote","tokcolon","tokopenparen","tokcloseparen","tokequals","toknotequals","tokless","toklessequal","tokgreater","tokgreaterequal","tokplus","tokminus","tokstar","tokslash","tokmod","tokassignment", "datatypeinteger", "datatypereal", "tokwrite", "tokwriteln","tokreadln"
			};
			// Read the input file
			reader = new BufferedReader(new FileReader("D:\\input.txt"));

			while ((in_str = reader.readLine()) != null) 
			{
				i_str = i_str + in_str + "\n";
			}
			
			char[] char_array = i_str.toCharArray();
			int n = i_str.length();
			int i = 0;
			String str1 = "";
			int[] arr=new int[n];
			for(i=0; i<n; i++)
			{
				arr[i]=(int)char_array[i];
			}
			int j,k,temp=0,temp1=0,temp2=0;
			String[] array = new String[1000];
			for(i=0; i<n; i++)
			{
				str1 = "";
				if(arr[i]==123)// checks whether the comments are within braces
				{
					k=i;
					temp = temp + 1;
					while(k<n)
					{
						if(arr[k] == 125)
						{
							temp = temp + 1;
							i = k + 1;
							break;
						}
						else
						{
							k++;
						}
					}
					if(k>=n)
					{
						out.println("Error: Comment missing end brace");
						break;
					}
				}
				else if(arr[i]==58)// store "="
				{
					if(arr[i+1]==61)
					{
						str1 = str1 + char_array[i];
						i=i+1;
					}
					str1 = str1 + char_array[i];
					array[count] = str1;
					count++;
				}
				else if((arr[i]>=65 && arr[i]<=90) || (arr[i]>=97 && arr[i]<=122))//Check for letter
				{
					j = i;
					str1 = str1 + char_array[j];
					j++;
					while((arr[j]>=65 && arr[j]<=90) || (arr[j]>=97 && arr[j]<=122) || (arr[j]>=48 && arr[j]<=57) || (arr[j]==95))
					{
						str1 = str1 + char_array[j];
						j = j + 1;
					}//store the characters into a string-token
					i = j - 1;
					array[count] = str1;
					count++;

				}
				else if (arr[i]==91)//Check for the array bound conditions
				{
					j = i;
					str1 = str1 + char_array[j];
					array[count] = str1;
					count++;
					str1 = "";
					if((arr[j+1]>=48 && arr[j+1]<=57) && (arr[j+2]==46 && arr[j+3]==46))
					{
						str1 = str1 + char_array[j+1];
						i = j + 1;
						array[count] = str1;
						count++;
					}

				}
				//check for digit
				else if(arr[i]>=48 && arr[i]<=57)
				{
					j = i;
					while((arr[j]>=48 && arr[j]<=57) || (arr[j]==46))
					{
						str1 = str1 + char_array[j];
						j = j + 1;
					}
					//Check for ExpoNum.
					if(arr[j]==69)
					{
						str1 = str1 + char_array[j];
						j = j + 1;
						if(arr[j]==43 || arr[j]==45 || (arr[j]>=48 && arr[j]<=57))
						{
							str1 = str1 + char_array[j];
							j++;
						}
						else
						{
							out.println("Error: Incorrect Exponential Number");
							temp2 = 1;

						}
					}
					i=j-1;
					array[count] = str1;
					count++;
				}
				//not to include space, \n and \t
				else if(arr[i]==32 || arr[i]==10 ||arr[i]==9)
				{
					// do nothing
				}
				else
				{
					str1 = str1 + char_array[i];
					array[count] = str1;
					count++;
				}
			}
			// Array name with lower and upper bound
			String[][] aux_tab = new String[count][3];
			j = 0;
			for(i = 0; i < count; i++)
			{
				if(array[i].equals('.') && array[i+1].equals('.'))
						{
							aux_tab[j][0] = array[i-5];// Name
							aux_tab[j][1] = array[i-1];// Lower bound value
							aux_tab[j][2] = array[i+2];// Upper bound value
							j++;
						}
			}
			if(temp%2!=0)
			{
				out.println("Error: Write comments within braces");
			}
			String[] flag = new String[count];
			for(i=0; i<count && (temp%2==0) && temp1==0 && temp2==0; i++)
			{
				flag[i]=lexs.gettoken(array[i],res_words,token_class);
				out.println(array[i]+" : "+flag[i]);
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
}