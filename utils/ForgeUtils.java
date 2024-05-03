package resetshrine.forge.utils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

public class ForgeUtils {

	public static String getPlayerDimensionName(ServerPlayerEntity player){
		String dimName = "";
		 for (ServerWorld serverWorld : ServerLifecycleHooks.getCurrentServer().getAllLevels()) {
				World world = serverWorld;
				if(world.equals(player.level)) {
					dimName = serverWorld.dimension().location().getPath();
				}
	     }
		 return dimName.toLowerCase();
	}
	
	public static String getPlayerDimensionName(PlayerEntity player){
		String dimName = "";
		 for (ServerWorld serverWorld : ServerLifecycleHooks.getCurrentServer().getAllLevels()) {
				World world = serverWorld;
				if(world.equals(player.level)) {
					dimName = serverWorld.dimension().location().getPath();
				}
	     }
		 return dimName.toLowerCase();
	}
	
	public static ServerWorld getWorldFromName(String worldName){
		ServerWorld serverWorld = null;
		 for (ServerWorld world : ServerLifecycleHooks.getCurrentServer().getAllLevels()) {
			if( world.dimension().location().getPath().equals(worldName.toLowerCase())) {
				serverWorld = world;
			}
	     }
		 return serverWorld;
	}
	
}
