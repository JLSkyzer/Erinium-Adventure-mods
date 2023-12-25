
package fr.eriniumgroup.eriniumadventure.automation.network;

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

import fr.eriniumgroup.eriniumadventure.automation.world.inventory.FarmerJsonBuilderGuiMainMenu;
import fr.eriniumgroup.eriniumadventure.automation.procedures.OpenOneBlockCropGuiProcedure;
import fr.eriniumgroup.eriniumadventure.automation.procedures.OpenMultipleBlocksCropsProcedure;
import fr.eriniumgroup.eriniumadventure.automation.EriniumAutomationMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class FarmerJsonBuilderGuiMainButtonMessage {
	private final int buttonID, x, y, z;

	public FarmerJsonBuilderGuiMainButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public FarmerJsonBuilderGuiMainButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(FarmerJsonBuilderGuiMainButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(FarmerJsonBuilderGuiMainButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
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
		HashMap guistate = FarmerJsonBuilderGuiMainMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			OpenOneBlockCropGuiProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 1) {

			OpenMultipleBlocksCropsProcedure.execute(world, x, y, z, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		EriniumAutomationMod.addNetworkMessage(FarmerJsonBuilderGuiMainButtonMessage.class, FarmerJsonBuilderGuiMainButtonMessage::buffer, FarmerJsonBuilderGuiMainButtonMessage::new, FarmerJsonBuilderGuiMainButtonMessage::handler);
	}
}
