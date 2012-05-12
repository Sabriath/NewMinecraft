package net.minecraft.src;

import net.minecraft.api.*;
import java.io.*;
import java.util.StringTokenizer;

public class FileSP
	implements IFile
{
	@Override
	public boolean readCFGs(String fname, ITag host)
	{
		File f = new File(fname);
		String lookahead = "";
		ITag[] lasttags = new ITag[16];
		ITag up1 = host;
		ITag cur = null;
		int looksz = 0;
		int look1;
		int lt = 0;
		
		try
		{
			BufferedReader b = new BufferedReader(new FileReader(f));
			for(;;)
			{
				look1 = b.read();
				if(look1 < 33)
				{
					if(looksz > 0)
					{
						cur = up1.FindOrAdd(lookahead);
						lookahead = "";
						looksz = 0;
					}
					if(look1 == -1)
						break;
					continue;
				}
				if(look1 == 123)
				{
					if(looksz > 0)
					{
						if(up1.Find(lookahead) == null)
							cur = up1.FindOrAdd(lookahead);
						lookahead = "";
						looksz = 0;
					}
					if(cur == null)
						//TODO throw something meaningful
						throw new IllegalArgumentException("'{' in cfg file out of place");
					if(lt == 15)
						throw new IllegalArgumentException("'{' in cfg file exceeds nesting limit");
					lasttags[lt++] = up1;
					up1 = cur;
					cur = null;
					continue;
				}
				if(look1 == 125)
				{
					if(looksz > 0)
					{
						if(up1.Find(lookahead) == null)
							cur = up1.FindOrAdd(lookahead);
						lookahead = "";
						looksz = 0;
					}
					if(lt == 0)
						//TODO throw something meaningful
						throw new IllegalArgumentException("'}' in cfg file out of sync");
					up1 = lasttags[--lt];
					cur = null;
					continue;
				}
				if(look1 == 61)
				{
					if(looksz > 0)
					{
						if(up1.Find(lookahead) == null)
							cur = up1.FindOrAdd(lookahead);
						looksz = 0;
					}
					if(cur == null)
						//TODO throw something meaningful
						throw new IllegalArgumentException("'=' in cfg file has no target");
					if((lookahead = b.readLine()) != null)
						cur.SetStr(lookahead);
					lookahead = "";
				}
				lookahead.concat(Character.toString(((char)(look1 & 255))));
				looksz++;
			}
			b.close();
		}
		catch (IllegalArgumentException e)
		{
			//TODO do something about it
		}
		catch (IOException e)
		{
			//TODO do something about it
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean writeCFGs(String fname, ITag host)
	{
		File f = new File(fname);
		String n;
		ITag[] nest = new ITag[16];
		ITag top;
		ITag cur;
		ITag chld;
		int inest = 0;
		
		try
		{
			BufferedWriter b = new BufferedWriter(new FileWriter(f));
			cur = host.GetF();
			top = host;
			if(cur == null)
			{
				b.close();
				return false;
			}
			for(;;)
			{
				if(inest > 0)
					b.write("                ".substring(0, inest));
				b.write(cur.GetK());
				if((n = cur.GetStr()) != null)
					b.write("=".concat(n));
				b.newLine();
				if((chld = cur.GetF()) != null)
				{
					if(inest == 15)
						throw new IllegalArgumentException("Attempt to exceed nest limit in tags");
					b.write("                ".substring(0, inest).concat("{"));
					nest[inest++] = top;
					top = cur;
					cur = chld;
					continue;
				}
				if((cur = cur.GetN()) != null)
					continue;
				while(inest > 0)
				{
					cur = top.GetN();
					top = nest[--inest];
					b.write("                ".substring(0, inest).concat("}"));
					if(cur != null)
						break;
				}
				if(inest == 0)
					break;
			}
		}
		catch (IllegalArgumentException e)
		{
			//do something about it
		}
		catch (IOException e)
		{
			//do something about it
			e.printStackTrace();
		}
		return false;
	}

}
