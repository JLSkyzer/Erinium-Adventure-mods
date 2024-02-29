package fr.eriniumgroups.erinium.ericonomy.init;

import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;

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
