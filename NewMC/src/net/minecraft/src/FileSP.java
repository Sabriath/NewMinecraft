package net.minecraft.src;

import net.minecraft.api.*;
import java.io.*;

public class FileSP
	implements IFile
{
	private File configFile;
	private File mapFile;

	public FileSP(String dname)
	{
		if(configFile == null)
		{
			configFile = new File(dname.concat("level.cfg"));
			mapFile = new File(dname.concat("level.map"));
		}
	}
	
	@Override
	public String readCFGString(String key, String deflt)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int readCFGInt(String key, int deflt)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeCFGString(String key, String dat)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeCFGInt(String key, int dat)
	{
		// TODO Auto-generated method stub
		
	}

}
