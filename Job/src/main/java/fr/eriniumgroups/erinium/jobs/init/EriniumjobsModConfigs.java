package fr.eriniumgroups.erinium.jobs.init;

import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;

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
