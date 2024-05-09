
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.eriniumadventure.base.init;

import org.lwjgl.glfw.GLFW;

import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import fr.eriniumgroup.eriniumadventure.base.network.TestKeyMessage;
import fr.eriniumgroup.eriniumadventure.base.network.LaunchMessage;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class EriniumAdventureModKeyMappings {
	public static final KeyMapping LAUNCH = new KeyMapping("key.erinium_adventure.launch", GLFW.GLFW_KEY_SPACE, "key.categories.misc") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				PacketDistributor.SERVER.noArg().send(new LaunchMessage(0, 0));
				LaunchMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping TEST_KEY = new KeyMapping("key.erinium_adventure.test_key", GLFW.GLFW_KEY_K, "key.categories.misc") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				PacketDistributor.SERVER.noArg().send(new TestKeyMessage(0, 0));
				TestKeyMessage.pressAction(Minecraft.getInstance().player, 0, 0);
				TEST_KEY_LASTPRESS = System.currentTimeMillis();
			} else if (isDownOld != isDown && !isDown) {
				int dt = (int) (System.currentTimeMillis() - TEST_KEY_LASTPRESS);
				PacketDistributor.SERVER.noArg().send(new TestKeyMessage(1, dt));
				TestKeyMessage.pressAction(Minecraft.getInstance().player, 1, dt);
			}
			isDownOld = isDown;
		}
	};
	private static long TEST_KEY_LASTPRESS = 0;

	@SubscribeEvent
	public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
		event.register(LAUNCH);
		event.register(TEST_KEY);
	}

	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(TickEvent.ClientTickEvent event) {
			if (Minecraft.getInstance().screen == null) {
				LAUNCH.consumeClick();
				TEST_KEY.consumeClick();
			}
		}
	}
}
