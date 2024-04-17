
package fr.eriniumgroup.eriniumadventure.automation.network;

import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import java.util.HashMap;

import fr.eriniumgroup.eriniumadventure.automation.world.inventory.FarmerJsonBuilderGuiMainMenu;
import fr.eriniumgroup.eriniumadventure.automation.procedures.OpenOneBlockCropGuiProcedure;
import fr.eriniumgroup.eriniumadventure.automation.procedures.OpenMultipleBlocksCropsProcedure;
import fr.eriniumgroup.eriniumadventure.automation.EriniumAutomationMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record FarmerJsonBuilderGuiMainButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final ResourceLocation ID = new ResourceLocation(EriniumAutomationMod.MODID, "farmer_json_builder_gui_main_buttons");
	public FarmerJsonBuilderGuiMainButtonMessage(FriendlyByteBuf buffer) {
		this(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt());
	}

	@Override
	public void write(final FriendlyByteBuf buffer) {
		buffer.writeInt(buttonID);
		buffer.writeInt(x);
		buffer.writeInt(y);
		buffer.writeInt(z);
	}

	@Override
	public ResourceLocation id() {
		return ID;
	}

	public static void handleData(final FarmerJsonBuilderGuiMainButtonMessage message, final PlayPayloadContext context) {
		if (context.flow() == PacketFlow.SERVERBOUND) {
			context.workHandler().submitAsync(() -> {
				Player entity = context.player().get();
				int buttonID = message.buttonID;
				int x = message.x;
				int y = message.y;
				int z = message.z;
				handleButtonAction(entity, buttonID, x, y, z);
			}).exceptionally(e -> {
				context.packetHandler().disconnect(Component.literal(e.getMessage()));
				return null;
			});
		}
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
		EriniumAutomationMod.addNetworkMessage(FarmerJsonBuilderGuiMainButtonMessage.ID, FarmerJsonBuilderGuiMainButtonMessage::new, FarmerJsonBuilderGuiMainButtonMessage::handleData);
	}
}
