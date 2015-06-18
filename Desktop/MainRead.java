package mpkg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;

public class MainRead {
	public static void main(String[] args) throws Exception 
	{
		String method = args[0];
		if ( Integer.parseInt(method) == 1 )
			readOne( args);
		else if ( Integer.parseInt(method) == 2 )
			readTwo(args);
		else if ( Integer.parseInt(method) == 3 )
			Compare(args);
		System.out.println(new Date() + " Programs end");
	}
	
	public static void readOne(String args[]) throws Exception
	{
		String afile = args[1];
		File af = new File(afile);
	    	FileReader afr = new FileReader(af);
		BufferedReader abr = new BufferedReader(afr);
		String aline = abr.readLine();
		int i=0;
		while(aline != null)
		{
			aline = abr.readLine();
			if ( i%20000 == 0 )
			{
				System.out.println(new Date() + " Reading line " + i);
			}
			i++;
		}
		abr.close();
	}
	
	public static void readTwo(String args[]) throws Exception
	{
		String afile = args[1];
		File af = new File(afile);
	    	FileReader afr = new FileReader(af);
		BufferedReader abr = new BufferedReader(afr);
		String aline = abr.readLine();
		String bfile = args[2];
		File bf = new File(bfile);
	    	FileReader bfr = new FileReader(bf);
		BufferedReader bbr = new BufferedReader(bfr);
		String bline = bbr.readLine();
		int i=0;
		while(aline != null && bline != null)
		{
			if ( i%20000 == 0 )
			{
				System.out.println(new Date() + " Reading two lines " + i);
			}
			i++;
			aline = abr.readLine();
			bline = bbr.readLine();
		}
		abr.close();
		afr.close();
		bbr.close();
		bfr.close();
	}
	public static void Compare(String args[]) throws Exception
	{
		String afile = args[1];
		String bfile = args[2];
		String tocomparefields = args[3];
		String[] cmpfieldtk = tocomparefields.split(",");
		int[] cmpfields=new int[cmpfieldtk.length];
		for (int i=0;i<cmpfieldtk.length;i++) cmpfields[i] = Integer.parseInt(cmpfieldtk[i]);
		File af = new File(afile);
	    	FileReader afr = new FileReader(af);
		BufferedReader abr = new BufferedReader(afr);
		File bf = new File(bfile);
	    	FileReader bfr = new FileReader(bf);
		BufferedReader bbr = new BufferedReader(bfr);
		String aline = abr.readLine();
		String bline = bbr.readLine();
		while(aline != null && bline != null)
		{
			String[] atk = aline.split("\\|");
			String[] btk = bline.split("\\|");
			int custcmp = atk[0].compareTo(btk[0]);
			boolean readnextaline = false;
			boolean readnextbline = false;
			if ( custcmp == 0 )
			{
				int ofrcmp = atk[2].compareTo(btk[2]);
				if ( ofrcmp== 0 )
				{
					readnextaline=true;
					readnextbline=true;
					boolean changed=false;
					for (int j=0;j<cmpfields.length;j++)
					{
						if ( !atk[cmpfields[j]].equals(btk[cmpfields[j]]) )
						{
							changed = true;
							break;
						}
					}
					if (changed)
					{
						System.out.println("Changed " + bline );
					}	
				}
				else if ( ofrcmp < 0 )
				{
					readnextaline=true;
					System.out.println("Deleted " + aline );
				}
				else
				{
					readnextbline=true;
					System.out.println("Added " + bline );
				}
			}
			else if ( custcmp < 0 )
			{
				readnextaline=true;
				System.out.println("Deleted " + aline );
				//deleted list
			}
			else
			{
				readnextbline=true;
				System.out.println("Added " + bline );
			}

			if (readnextaline)
			{
				aline = abr.readLine();
			}
			if(readnextbline)
			{
				bline = bbr.readLine();
			}
		}
		
		if ( aline != null )
		{
			System.out.println("All the below lines are to be deleted");
			
			while(aline != null)
			{
				System.out.println("All Deleted " + aline );
				aline = abr.readLine();
			}
		}
		if (bline != null)
		{
			System.out.println("All the below lines are to be added");
			
			while(bline != null)
			{
				System.out.println("All to be added " + bline );
				bline = bbr.readLine();
			}
		}
		abr.close();
		afr.close();
		bbr.close();
		bfr.close();

	}

}
