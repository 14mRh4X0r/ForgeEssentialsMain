package com.forgeessentials.teleport;

import java.util.List;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

import com.forgeessentials.api.APIRegistry;
import com.forgeessentials.api.permissions.IPermRegisterEvent;
import com.forgeessentials.api.permissions.RegGroup;
import com.forgeessentials.api.permissions.query.PermQueryPlayer;
import com.forgeessentials.core.PlayerInfo;
import com.forgeessentials.core.commands.ForgeEssentialsCommandBase;
import com.forgeessentials.util.ChatUtils;
import com.forgeessentials.util.OutputHandler;
import com.forgeessentials.util.TeleportCenter;
import com.forgeessentials.util.AreaSelector.WarpPoint;

public class CommandHome extends ForgeEssentialsCommandBase
{
	@Override
	public String getCommandName()
	{
		return "home";
	}

	@Override
	public void processCommandPlayer(EntityPlayer sender, String[] args)
	{
		if (args.length == 0)
		{
			WarpPoint home = PlayerInfo.getPlayerInfo(sender.username).home;
			if (home == null)
			{
				OutputHandler.chatError(sender, "No home set. Try this: [here|x, y, z]");
			}
			else
			{
				EntityPlayerMP player = (EntityPlayerMP) sender;
				PlayerInfo playerInfo = PlayerInfo.getPlayerInfo(player.username);
				playerInfo.back = new WarpPoint(player);
				CommandBack.justDied.remove(player.username);
				TeleportCenter.addToTpQue(home, player);
			}
		}
		else if (APIRegistry.perms.checkPermAllowed(new PermQueryPlayer(sender, getCommandPerm() + ".set")))
		{
			if (args.length >= 1 && (args[0].equals("here") || args[0].equals("set")))
			{
				WarpPoint p = new WarpPoint(sender);
				PlayerInfo.getPlayerInfo(sender.username).home = p;
				ChatUtils.sendMessage(sender, String.format("Home set to: %1$d, %2$d, %3$d", p.x, p.y, p.z));
			}
		}
	}

	@Override
	public String getCommandPerm()
	{
		return "ForgeEssentials.BasicCommands." + getCommandName();
	}

	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args)
	{
		if (args.length == 1)
			return getListOfStringsMatchingLastWord(args, "here");
		else
			return null;
	}

	@Override
	public boolean canConsoleUseCommand()
	{
		return false;
	}

	@Override
	public void registerExtraPermissions(IPermRegisterEvent event)
	{
		event.registerPermissionLevel(getCommandPerm() + ".set", getReggroup());
	}

	@Override
	public RegGroup getReggroup()
	{
		return RegGroup.MEMBERS;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		if (sender instanceof EntityPlayer)
			return "/home [here|x, y, z] Set your home location.";
		else return null;
	}
}
