package com.forgeessentials.chat;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.ForgeSubscribe;

import com.forgeessentials.util.ChatUtils;
import com.forgeessentials.util.FunctionHelper;

import cpw.mods.fml.common.FMLCommonHandler;

public class CommandMuter
{
    public static ArrayList<String> mutedCommands = new ArrayList<String>();

    @ForgeSubscribe
    public void commandEvent(CommandEvent e)
    {
        if (FMLCommonHandler.instance().getEffectiveSide().isClient())
            return;

        if (e.sender instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) e.sender;
            if (player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).getBoolean("mute"))
            {
                if (mutedCommands.contains(e.command.getCommandName()))
                {
					ChatUtils.sendMessage(player, "You are currently muted.");
                    e.setCanceled(true);
                    return;
                }
                else
                {
                    for (Object obj : e.command.getCommandAliases())
                    {
                        if(mutedCommands.contains(obj.toString()))
                        {
							ChatUtils.sendMessage(player, "You are currently muted.");
                            e.setCanceled(true);
                            return;
                        }
                    }
                }
            }
        }

        if (ConfigChat.logcmd && ModuleChat.cmdLog != null)
        {
            ModuleChat.cmdLog.println(FunctionHelper.getCurrentDateString() + " " + FunctionHelper.getCurrentTimeString() + "[" + e.sender.getCommandSenderName() + "] /" + e.command.getCommandName() + " " + join(e.parameters));
        }
        if (e.command.getCommandName().equalsIgnoreCase("me")){
        	IRCHelper.postIRC(e.sender.getCommandSenderName() + " " + e.parameters.toString());
        }

    }

    public String join(String[] args)
    {
        StringBuilder sb = new StringBuilder();
        for (String agr : args)
            sb.append(agr + " ");
        return sb.toString();
    }
}
