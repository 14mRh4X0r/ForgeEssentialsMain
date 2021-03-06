package com.forgeessentials.auth.lists;

import net.minecraft.command.ICommandSender;

import com.forgeessentials.api.APIRegistry;
import com.forgeessentials.core.commands.ForgeEssentialsCommandBase;

public class CommandVIP extends ForgeEssentialsCommandBase{

	@Override
	public String getCommandName() {
		return "vip";
	}
	
	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if(args.length >= 2 && args[0].equalsIgnoreCase("add")){
			APIRegistry.perms.setPlayerPermission(args[1], "ForgeEssentials.Auth.isVIP", true, "_GLOBAL_");
		}else if(args.length >= 2 && args[0].equalsIgnoreCase("remove")){
			APIRegistry.perms.setPlayerPermission(args[1], "ForgeEssentials.Auth.isVIP", false, "_GLOBAL_");
		}
	}

	@Override
	public boolean canConsoleUseCommand() {
		return true;
	}

	@Override
	public String getCommandPerm() {
		return "ForgeEssentials.Auth.vipcmd";
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "/vip [add|remove} <player> Adds or removes a player from the VIP list";
	}

}
