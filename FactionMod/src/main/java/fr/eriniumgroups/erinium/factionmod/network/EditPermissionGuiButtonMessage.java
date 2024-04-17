
package fr.eriniumgroups.erinium.factionmod.network;

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

import fr.eriniumgroups.erinium.factionmod.world.inventory.EditPermissionGuiMenu;
import fr.eriniumgroups.erinium.factionmod.procedures.ValidateBtnProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.ChangeWarpProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.ChangeUnclaimProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.ChangeSetwarpProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.ChangeSethomeProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.ChangePromoteProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.ChangePoseProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.ChangeModifyProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.ChangeKickProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.ChangeInviteProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.ChangeInteractProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.ChangeHomeProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.ChangeDemoteProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.ChangeDelwarpProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.ChangeDelhomeProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.ChangeClaimProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.ChangeBreakProcedure;
import fr.eriniumgroups.erinium.factionmod.EriniumFactionMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record EditPermissionGuiButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final ResourceLocation ID = new ResourceLocation(EriniumFactionMod.MODID, "edit_permission_gui_buttons");
	public EditPermissionGuiButtonMessage(FriendlyByteBuf buffer) {
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

	public static void handleData(final EditPermissionGuiButtonMessage message, final PlayPayloadContext context) {
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
		HashMap guistate = EditPermissionGuiMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			ValidateBtnProcedure.execute(entity);
		}
		if (buttonID == 1) {

			ChangeInteractProcedure.execute(entity);
		}
		if (buttonID == 2) {

			ChangeBreakProcedure.execute(entity);
		}
		if (buttonID == 3) {

			ChangePoseProcedure.execute(entity);
		}
		if (buttonID == 4) {

			ChangeKickProcedure.execute(entity);
		}
		if (buttonID == 5) {

			ChangeInviteProcedure.execute(entity);
		}
		if (buttonID == 6) {

			ChangePromoteProcedure.execute(entity);
		}
		if (buttonID == 7) {

			ChangeDemoteProcedure.execute(entity);
		}
		if (buttonID == 8) {

			ChangeClaimProcedure.execute(entity);
		}
		if (buttonID == 9) {

			ChangeUnclaimProcedure.execute(entity);
		}
		if (buttonID == 10) {

			ChangeSetwarpProcedure.execute(entity);
		}
		if (buttonID == 11) {

			ChangeDelwarpProcedure.execute(entity);
		}
		if (buttonID == 12) {

			ChangeSethomeProcedure.execute(entity);
		}
		if (buttonID == 13) {

			ChangeDelhomeProcedure.execute(entity);
		}
		if (buttonID == 14) {

			ChangeModifyProcedure.execute(entity);
		}
		if (buttonID == 15) {

			ChangeHomeProcedure.execute(entity);
		}
		if (buttonID == 16) {

			ChangeWarpProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		EriniumFactionMod.addNetworkMessage(EditPermissionGuiButtonMessage.ID, EditPermissionGuiButtonMessage::new, EditPermissionGuiButtonMessage::handleData);
	}
}
