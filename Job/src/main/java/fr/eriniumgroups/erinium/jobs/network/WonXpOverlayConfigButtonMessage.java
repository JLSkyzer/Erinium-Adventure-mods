
package fr.eriniumgroups.erinium.jobs.network;

import fr.eriniumgroups.erinium.jobs.procedures.TestProcedure;
import net.minecraft.client.Minecraft;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import java.util.function.Supplier;
import java.util.HashMap;

import fr.eriniumgroups.erinium.jobs.world.inventory.WonXpOverlayConfigMenu;
import fr.eriniumgroups.erinium.jobs.procedures.TempProcProcedure;
import fr.eriniumgroups.erinium.jobs.EriniumjobsMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class WonXpOverlayConfigButtonMessage {
	private final int buttonID, x, y, z;

	public WonXpOverlayConfigButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public WonXpOverlayConfigButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(WonXpOverlayConfigButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(WonXpOverlayConfigButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			Player entity = context.getSender();
			int buttonID = message.buttonID;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			handleButtonAction(entity, buttonID, x, y, z);
		});
		context.setPacketHandled(true);
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level();
		HashMap guistate = WonXpOverlayConfigMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability ->{
			int w = (int) ((Minecraft.getInstance().getWindow().getGuiScaledWidth() * capability.won_xp_x) / 100);
			int h = (int) ((Minecraft.getInstance().getWindow().getGuiScaledHeight() * capability.won_xp_y) / 100);
			// Down
			if (buttonID == 0) {
				if ((capability.won_xp_y + 1) <= 100){
					capability.won_xp_y = capability.won_xp_y + 1;
				}
			}
			// Reset
			if (buttonID == 1) {

				capability.won_xp_y = 15;
				capability.won_xp_x = 70;
			}
			// Left
			if (buttonID == 2) {
				if ((capability.won_xp_x - 1) >= 0){
					capability.won_xp_x = capability.won_xp_x - 1;
				}
			}
			// Right
			if (buttonID == 3) {
				if ((capability.won_xp_x + 1) <= 100){
					capability.won_xp_x = capability.won_xp_x + 1;
				}
			}
			// Up
			if (buttonID == 4) {
				if ((capability.won_xp_y - 1) >= 0){
					capability.won_xp_y = capability.won_xp_y - 1;
				}
			}

			// Test
			if (buttonID == 5) {
				TestProcedure.execute(entity);
			}

			capability.syncPlayerVariables(entity);
		});
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		EriniumjobsMod.addNetworkMessage(WonXpOverlayConfigButtonMessage.class, WonXpOverlayConfigButtonMessage::buffer, WonXpOverlayConfigButtonMessage::new, WonXpOverlayConfigButtonMessage::handler);
	}
}
