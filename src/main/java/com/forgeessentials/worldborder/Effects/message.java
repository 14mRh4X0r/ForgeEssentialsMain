package com.forgeessentials.worldborder.Effects;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.Configuration;

import com.forgeessentials.util.ChatUtils;
import com.forgeessentials.util.FunctionHelper;
import com.forgeessentials.worldborder.WorldBorder;

public class message implements IEffect
{
	private String	message	= "You passed the world border!";

	@Override
	public void registerConfig(Configuration config, String category)
	{
		message = config.get(category, "Message", message, "Message to send to the player. You can use color codes.").getString();
	}

	@Override
	public void execute(WorldBorder wb, EntityPlayerMP player)
	{
		ChatUtils.sendMessage(player, FunctionHelper.formatColors(message));
	}
}
