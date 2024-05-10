package fr.eriniumgroups.eriniumcrate.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.MenuProvider;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import io.netty.buffer.Unpooled;

import fr.eriniumgroups.eriniumcrate.world.inventory.LootListMenu;
import fr.eriniumgroups.eriniumcrate.network.EriniumcrateModVariables;

public class BasiccratePlayerStartsToDestroyProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (!(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
			{
				EriniumcrateModVariables.PlayerVariables _vars = entity.getData(EriniumcrateModVariables.PLAYER_VARIABLES);
				_vars.lootfor = "basic";
				_vars.syncPlayerVariables(entity);
			}
			{
				EriniumcrateModVariables.PlayerVariables _vars = entity.getData(EriniumcrateModVariables.PLAYER_VARIABLES);
				_vars.page = 0;
				_vars.syncPlayerVariables(entity);
			}
			{
				EriniumcrateModVariables.PlayerVariables _vars = entity.getData(EriniumcrateModVariables.PLAYER_VARIABLES);
				_vars.page_initialised = false;
				_vars.syncPlayerVariables(entity);
			}
			if (entity instanceof ServerPlayer _ent) {
				BlockPos _bpos = BlockPos.containing(x, y, z);
				_ent.openMenu(new MenuProvider() {
					@Override
					public Component getDisplayName() {
						return Component.literal("LootList");
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						return new LootListMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
					}
				}, _bpos);
			}
		}
	}
}
