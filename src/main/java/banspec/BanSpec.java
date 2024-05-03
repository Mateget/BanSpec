package banspec;

import java.io.File;
import java.nio.file.FileSystems;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pixelmonmod.api.pokemon.PokemonSpecificationProxy;

import banspec.config.ConfigHandler;
import banspec.permission.PermissionHandler;
import banspec.spec.PokeBanSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.network.FMLNetworkConstants;

@Mod(BanSpec.MODID)
public class BanSpec {
	
	public static final String MODID = "banspec"; 
    public static final Logger LOGGER = LogManager.getLogger("BanSpec");
    public static File configDir;
	

    public BanSpec() {
    	
        MinecraftForge.EVENT_BUS.register(this);
    	
    	configDir = new File("config" + FileSystems.getDefault().getSeparator() + MODID + FileSystems.getDefault().getSeparator());
        configDir.mkdir();
        ConfigHandler.readAllConfigs();
        ConfigHandler.creationCheckConfig();
        ConfigHandler.writeConfig();
    	
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.<Supplier<String>, BiPredicate<String, Boolean>>of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
        
        
        PokemonSpecificationProxy.register(new PokeBanSpec());
        
    }
    
    @SubscribeEvent
    public void preInit(FMLServerAboutToStartEvent e) {
    	PermissionHandler.init();
	}

 
}
