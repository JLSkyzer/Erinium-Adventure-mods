package fr.eriniumgroups.erinium.auctionhouse.procedures;

import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.MenuProvider;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import java.util.function.Supplier;
import java.util.Map;

import io.netty.buffer.Unpooled;

import fr.eriniumgroups.erinium.auctionhouse.world.inventory.DeleteItemsMenu;
import fr.eriniumgroups.erinium.auctionhouse.world.inventory.BuyGuiMenu;
import fr.eriniumgroups.erinium.auctionhouse.network.EriniumAhModVariables;

public class Line0BuyProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (!((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Blocks.AIR.asItem())) {
			if (!(((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getOrCreateTag().getString("uuid"))
					.equals(entity.getUUID().toString()))) {
				{
					ItemStack _setval = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY);
					entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.ah_temp_item = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if (entity instanceof ServerPlayer _ent) {
					BlockPos _bpos = BlockPos.containing(x, y, z);
					NetworkHooks.openScreen((ServerPlayer) _ent, new MenuProvider() {
						@Override
						public Component getDisplayName() {
							return Component.literal("BuyGui");
						}

						@Override
						public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
							return new BuyGuiMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
						}
					}, _bpos);
				}
			} else {
				{
					ItemStack _setval = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY);
					entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.ah_temp_item = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if (entity instanceof ServerPlayer _ent) {
					BlockPos _bpos = BlockPos.containing(x, y, z);
					NetworkHooks.openScreen((ServerPlayer) _ent, new MenuProvider() {
						@Override
						public Component getDisplayName() {
							return Component.literal("DeleteItems");
						}

						@Override
						public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
							return new DeleteItemsMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
						}
					}, _bpos);
				}
			}
		}
	}
}
