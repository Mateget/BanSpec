package banspec.permission;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

import banspec.BanSpec;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.server.permission.DefaultPermissionLevel;
import net.minecraftforge.server.permission.PermissionAPI;

public class PermissionHandler {
	
	public static final String BASE =BanSpec.MODID+".";
	public static final String ADMIN = "admin.";
	
	
	public static final String RELOAD = BASE + ADMIN +"reload";
		
	public static void init() {
		PermissionAPI.registerNode(RELOAD, DefaultPermissionLevel.OP, "Reload config");
	}
	
	public static boolean playerHasPermission(CommandSource source, String node) {
		 try {
			return PermissionAPI.hasPermission(source.getPlayerOrException(), node);
		} catch (CommandSyntaxException e) {
			
		}
		 return true;
	}
	

	public static boolean playerHasReloadPermission(PlayerEntity player) {
		return playerHasPermission(player,RELOAD);
	}
	
	public static boolean playerHasReloadPermission(CommandSource player) {
		return playerHasPermission(player,RELOAD);
	}

	private static boolean playerHasPermission(PlayerEntity player, String node) {
		return PermissionAPI.hasPermission(player, node);
	}

}
