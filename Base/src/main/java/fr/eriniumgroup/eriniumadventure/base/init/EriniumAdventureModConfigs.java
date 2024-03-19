package fr.eriniumgroup.eriniumadventure.base.init;

import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;

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
