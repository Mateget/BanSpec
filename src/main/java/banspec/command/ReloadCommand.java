package banspec.command;

import com.mojang.brigadier.CommandDispatcher;

import banspec.config.ConfigHandler;
import banspec.permission.PermissionHandler;
import banspec.utils.ChatUtils;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;

public class ReloadCommand {
		
	public ReloadCommand(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.literal("banspecreload")	.requires((source)->PermissionHandler.playerHasReloadPermission(source))			
				.executes((command)->{
					ConfigHandler.readAllConfigs();
					command.getSource().getPlayerOrException().sendMessage(new StringTextComponent(ChatUtils.chat("&bConfig reloaded check console for errors !")), command.getSource().getPlayerOrException().getUUID());
					return 1;
				}));
	}
	

}
