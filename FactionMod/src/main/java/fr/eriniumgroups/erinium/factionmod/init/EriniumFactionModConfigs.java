package fr.eriniumgroups.erinium.factionmod.init;

import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;

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
