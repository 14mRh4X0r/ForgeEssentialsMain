package com.ForgeEssentials.permissions;

import java.io.File;
import java.io.Serializable;

import net.minecraftforge.common.Configuration;

import com.ForgeEssentials.core.data.DataDriver;
import com.ForgeEssentials.core.data.filesystem.FileSystemDataAdapter;
import com.ForgeEssentials.core.data.filesystem.FileSystemDataDriver;

public class ZoneDataSaver extends FileSystemDataAdapter<Zone, String> implements Serializable
{

	@Override
	public boolean saveData(Zone object)
	{
		//Configuration config = new Configuration(new File((FileSystemDataDriver)DataDriver.getInstance()) + "/Zone/"+object.getZoneID());
		
		
		return false;
	}

	@Override
	public boolean loadData(String uniqueObjectKey, Zone object)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteData(String uniqueObjectKey)
	{
		// TODO Auto-generated method stub
		return false;
	}

}