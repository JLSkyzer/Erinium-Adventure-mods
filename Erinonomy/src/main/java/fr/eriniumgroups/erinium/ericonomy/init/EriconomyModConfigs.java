package fr.eriniumgroups.erinium.ericonomy.init;

import net.neoforged.fml.event.lifecycle.FMLConstructModEvent;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.bus.api.SubscribeEvent;

import fr.eriniumgroups.erinium.ericonomy.configuration.ServerConfigConfiguration;
import fr.eriniumgroups.erinium.ericonomy.EriconomyMod;

@Mod.EventBusSubscriber(modid = EriconomyMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EriconomyModConfigs {
	@SubscribeEvent
	public static void register(FMLConstructModEvent event) {
		event.enqueueWork(() -> {
			ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ServerConfigConfiguration.SPEC, "ericonomy-common.toml");
		});
	}
}
