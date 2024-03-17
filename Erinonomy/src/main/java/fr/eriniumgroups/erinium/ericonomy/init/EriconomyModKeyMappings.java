
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroups.erinium.ericonomy.init;

import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import fr.eriniumgroups.erinium.ericonomy.network.InsertAllMoneyMessage;
import fr.eriniumgroups.erinium.ericonomy.EriconomyMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class EriconomyModKeyMappings {
	public static final KeyMapping INSERT_ALL_MONEY = new KeyMapping("key.ericonomy.insert_all_money", GLFW.GLFW_KEY_I, "key.categories.ericonomy") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				EriconomyMod.PACKET_HANDLER.sendToServer(new InsertAllMoneyMessage(0, 0));
				InsertAllMoneyMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};

	@SubscribeEvent
	public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
		event.register(INSERT_ALL_MONEY);
	}

	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(TickEvent.ClientTickEvent event) {
			if (Minecraft.getInstance().screen == null) {
				INSERT_ALL_MONEY.consumeClick();
			}
		}
	}
}
