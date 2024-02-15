package fr.eriniumgroups.erinium.jobs.procedures;

import net.minecraftforge.network.NetworkHooks;

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
import net.minecraft.commands.CommandSourceStack;

import io.netty.buffer.Unpooled;

import fr.eriniumgroups.erinium.jobs.world.inventory.UnlockedGuiMenu;
import fr.eriniumgroups.erinium.jobs.network.EriniumjobsModVariables;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.StringArgumentType;

public class OpenUnlockedGuiProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		{
			double _setval = 0;
			entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.wonxp_page = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			boolean _setval = false;
			entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.wonxp_initialised = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			String _setval = StringArgumentType.getString(arguments, "jobid");
			entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.temp_job_id = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			if (entity instanceof ServerPlayer _ent) {
				BlockPos _bpos = BlockPos.containing(x, y, z);
				NetworkHooks.openScreen((ServerPlayer) _ent, new MenuProvider() {
					@Override
					public Component getDisplayName() {
						return Component.literal("UnlockedGui");
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						return new UnlockedGuiMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
					}
				}, _bpos);
			}
		}
	}
}
