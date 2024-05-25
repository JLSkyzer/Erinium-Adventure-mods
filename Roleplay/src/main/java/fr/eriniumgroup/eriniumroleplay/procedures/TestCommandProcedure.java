package fr.eriniumgroup.eriniumroleplay.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

import fr.eriniumgroup.eriniumroleplay.network.EriniumroleplayModVariables;

public class TestCommandProcedure {
	public static void execute(double x, double z, Entity entity) {
		if (entity == null)
			return;
		Entity entityTemp = null;
		ItemStack itemStack = ItemStack.EMPTY;
		if (entity.getData(EriniumroleplayModVariables.PLAYER_VARIABLES).isDead) {
			{
				EriniumroleplayModVariables.PlayerVariables _vars = entity.getData(EriniumroleplayModVariables.PLAYER_VARIABLES);
				_vars.isDead = false;
				_vars.syncPlayerVariables(entity);
			}
		}
		if (entity.getY() < -64) {
			{
				Entity _ent = entity;
				_ent.teleportTo(x, 0, z);
				if (_ent instanceof ServerPlayer _serverPlayer)
					_serverPlayer.connection.teleport(x, 0, z, _ent.getYRot(), _ent.getXRot());
			}
		}
	}
}
