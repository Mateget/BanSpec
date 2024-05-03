package banspec.spec;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;
import com.pixelmonmod.api.pokemon.requirement.AbstractStringPokemonRequirement;
import com.pixelmonmod.api.requirement.Requirement;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.species.Species;
import com.pixelmonmod.pixelmon.api.registries.PixelmonSpecies;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;

import banspec.BanSpec;
import banspec.config.ConfigHandler;

public class PokeBanSpec extends AbstractStringPokemonRequirement  {
	
	private static final Set<String> KEYS = Sets.newHashSet("banspec");
	private static final String NONE = "none";
	
	public PokeBanSpec() {
		super(KEYS, NONE);
	}
	
	public PokeBanSpec(String arg0) {
		super(KEYS, NONE,arg0);
	}	

	@Override
	public void applyData(Pokemon arg0) {
		List<String> matchGroup = ConfigHandler.config.getBanGroups().get(this.value);
		if(matchGroup == null || matchGroup.isEmpty()) return;
		int count = 0;
		Species species = arg0.getSpecies();
		while(matchGroup.contains(species.getName().toLowerCase()) || count > 1000) {
			count++;
			species = PixelmonSpecies.getRandomSpecies();
		}
		arg0.setSpecies(species, true);
		BanSpec.LOGGER.error("Could not find a valid species after 1000 try, final species picked : " + species.getName());
	}

	@Override
	public Requirement<Pokemon, PixelmonEntity, String> createInstance(String arg0) {
		return new PokeBanSpec(arg0);
	}

	@Override
	public boolean isDataMatch(Pokemon arg0) {
		List<String> matchGroup = ConfigHandler.config.getBanGroups().get(this.value);
		if(matchGroup == null || matchGroup.isEmpty()) return false;
		for(String speciesName : matchGroup) {
			if(speciesName.equals(arg0.getSpecies().getName())) {
				return false;
			}
		}
		return true;
	}

}
