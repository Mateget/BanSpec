package banspec.command;

import banspec.BanSpec;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BanSpec.MODID)
public class CommandRegister {
		
	@SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
			new ReloadCommand(event.getDispatcher());

    }
}