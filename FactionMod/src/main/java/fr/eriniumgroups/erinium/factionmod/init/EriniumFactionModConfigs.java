package fr.eriniumgroups.erinium.factionmod.init;

import net.neoforged.fml.event.lifecycle.FMLConstructModEvent;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.bus.api.SubscribeEvent;

import fr.eriniumgroups.erinium.factionmod.configuration.ConfigConfiguration;
import fr.eriniumgroups.erinium.factionmod.EriniumFactionMod;

@Mod.EventBusSubscriber(modid = EriniumFactionMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EriniumFactionModConfigs {
	@SubscribeEvent
	public static void register(FMLConstructModEvent event) {
		event.enqueueWork(() -> {
			ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigConfiguration.SPEC, "erinium-faction-common.toml");
		});
	}
}
