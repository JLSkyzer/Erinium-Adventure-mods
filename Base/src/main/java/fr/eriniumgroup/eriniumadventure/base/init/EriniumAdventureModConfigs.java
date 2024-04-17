package fr.eriniumgroup.eriniumadventure.base.init;

import net.neoforged.fml.event.lifecycle.FMLConstructModEvent;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.bus.api.SubscribeEvent;

import fr.eriniumgroup.eriniumadventure.base.configuration.ServerConfigConfiguration;
import fr.eriniumgroup.eriniumadventure.base.EriniumAdventureMod;

@Mod.EventBusSubscriber(modid = EriniumAdventureMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EriniumAdventureModConfigs {
	@SubscribeEvent
	public static void register(FMLConstructModEvent event) {
		event.enqueueWork(() -> {
			ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ServerConfigConfiguration.SPEC, "eriniumadventure-common.toml");
		});
	}
}
