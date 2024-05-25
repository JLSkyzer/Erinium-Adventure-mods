package fr.eriniumgroup.eriniumroleplay.procedures;

import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

import javax.annotation.Nullable;

import fr.eriniumgroup.eriniumroleplay.network.EriniumroleplayModVariables;

@Mod.EventBusSubscriber
public class PlayerDeathEventProcedure {
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity());
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof ServerPlayer) {
			if (event instanceof ICancellableEvent _cancellable) {
				_cancellable.setCanceled(true);
			}
			if (entity instanceof LivingEntity _entity)
				_entity.setHealth(2);
			{
				EriniumroleplayModVariables.PlayerVariables _vars = entity.getData(EriniumroleplayModVariables.PLAYER_VARIABLES);
				_vars.DeathX = entity.getX();
				_vars.syncPlayerVariables(entity);
			}
			{
				EriniumroleplayModVariables.PlayerVariables _vars = entity.getData(EriniumroleplayModVariables.PLAYER_VARIABLES);
				_vars.DeathY = entity.getY();
				_vars.syncPlayerVariables(entity);
			}
			{
				EriniumroleplayModVariables.PlayerVariables _vars = entity.getData(EriniumroleplayModVariables.PLAYER_VARIABLES);
				_vars.DeathZ = entity.getZ();
				_vars.syncPlayerVariables(entity);
			}
			{
				EriniumroleplayModVariables.PlayerVariables _vars = entity.getData(EriniumroleplayModVariables.PLAYER_VARIABLES);
				_vars.isDead = true;
				_vars.syncPlayerVariables(entity);
			}
			entity.setInvulnerable(true);
		}
	}
}
