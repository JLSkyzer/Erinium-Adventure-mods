
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroups.erinium.jobs.init;

import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import fr.eriniumgroups.erinium.jobs.network.OpenOverlaySettingsPageMessage;
import fr.eriniumgroups.erinium.jobs.EriniumjobsMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class EriniumjobsModKeyMappings {
	public static final KeyMapping OPEN_OVERLAY_SETTINGS_PAGE = new KeyMapping("key.eriniumjobs.open_overlay_settings_page", GLFW.GLFW_KEY_M, "key.categories.eriniumjobs") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				EriniumjobsMod.PACKET_HANDLER.sendToServer(new OpenOverlaySettingsPageMessage(0, 0));
				OpenOverlaySettingsPageMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};

	@SubscribeEvent
	public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
		event.register(OPEN_OVERLAY_SETTINGS_PAGE);
	}

	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(TickEvent.ClientTickEvent event) {
			if (Minecraft.getInstance().screen == null) {
				OPEN_OVERLAY_SETTINGS_PAGE.consumeClick();
			}
		}
	}
}
