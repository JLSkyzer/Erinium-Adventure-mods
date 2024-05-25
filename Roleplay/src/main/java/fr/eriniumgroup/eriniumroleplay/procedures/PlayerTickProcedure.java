package fr.eriniumgroup.eriniumroleplay.procedures;

import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;

import fr.eriniumgroup.eriniumroleplay.network.EriniumroleplayModVariables;

@Mod.EventBusSubscriber
public class PlayerTickProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player);
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (entity.getData(EriniumroleplayModVariables.PLAYER_VARIABLES).isDead) {
			entity.setInvulnerable(true);
			if (entity.getData(EriniumroleplayModVariables.PLAYER_VARIABLES).TimeToRespawn > 0) {
				{
					EriniumroleplayModVariables.PlayerVariables _vars = entity.getData(EriniumroleplayModVariables.PLAYER_VARIABLES);
					_vars.TimeToRespawn = entity.getData(EriniumroleplayModVariables.PLAYER_VARIABLES).TimeToRespawn - 1;
					_vars.syncPlayerVariables(entity);
				}
			}
			if (!(entity.getX() == entity.getData(EriniumroleplayModVariables.PLAYER_VARIABLES).DeathX) || !(entity.getY() == entity.getData(EriniumroleplayModVariables.PLAYER_VARIABLES).DeathY)
					|| !(entity.getZ() == entity.getData(EriniumroleplayModVariables.PLAYER_VARIABLES).DeathZ)) {
				{
					Entity _ent = entity;
					_ent.teleportTo(entity.getData(EriniumroleplayModVariables.PLAYER_VARIABLES).DeathX, entity.getData(EriniumroleplayModVariables.PLAYER_VARIABLES).DeathY, entity.getData(EriniumroleplayModVariables.PLAYER_VARIABLES).DeathZ);
					if (_ent instanceof ServerPlayer _serverPlayer)
						_serverPlayer.connection.teleport(entity.getData(EriniumroleplayModVariables.PLAYER_VARIABLES).DeathX, entity.getData(EriniumroleplayModVariables.PLAYER_VARIABLES).DeathY,
								entity.getData(EriniumroleplayModVariables.PLAYER_VARIABLES).DeathZ, _ent.getYRot(), _ent.getXRot());
				}
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal((Component.translatable("death.move").getString())), true);
			}
			entity.setDeltaMovement(new Vec3(0, 0, 0));
		} else if (entity.isInvulnerable()) {
			entity.setInvulnerable(false);
		}
	}
}
