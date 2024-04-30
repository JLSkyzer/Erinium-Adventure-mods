package fr.eriniumgroups.erinium.jobs.init;

import net.neoforged.fml.event.lifecycle.FMLConstructModEvent;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.bus.api.SubscribeEvent;

import fr.eriniumgroups.erinium.jobs.configuration.CommonConfigConfiguration;
import fr.eriniumgroups.erinium.jobs.EriniumjobsMod;

@Mod.EventBusSubscriber(modid = EriniumjobsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EriniumjobsModConfigs {
	@SubscribeEvent
	public static void register(FMLConstructModEvent event) {
		event.enqueueWork(() -> {
			ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfigConfiguration.SPEC, "erinium-ranking.toml");
		});
	}
}
