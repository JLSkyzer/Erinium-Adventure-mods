package fr.eriniumgroup.eriniumroleplay.procedures;

import net.neoforged.neoforge.event.entity.living.LivingAttackEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

import javax.annotation.Nullable;

import fr.eriniumgroup.eriniumroleplay.network.EriniumroleplayModVariables;

@Mod.EventBusSubscriber
public class PlayerAttackEntityProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getSource().getDirectEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(Entity immediatesourceentity, Entity sourceentity) {
		execute(null, immediatesourceentity, sourceentity);
	}

	private static void execute(@Nullable Event event, Entity immediatesourceentity, Entity sourceentity) {
		if (immediatesourceentity == null || sourceentity == null)
			return;
		if (sourceentity instanceof ServerPlayer && sourceentity.getData(EriniumroleplayModVariables.PLAYER_VARIABLES).isDead) {
			if (event instanceof ICancellableEvent _cancellable) {
				_cancellable.setCanceled(true);
			}
		} else if (immediatesourceentity instanceof ServerPlayer && immediatesourceentity.getData(EriniumroleplayModVariables.PLAYER_VARIABLES).isDead) {
			if (event instanceof ICancellableEvent _cancellable) {
				_cancellable.setCanceled(true);
			}
		}
	}
}
