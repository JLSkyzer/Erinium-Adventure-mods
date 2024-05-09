package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

import fr.eriniumgroup.eriniumadventure.base.network.EriniumAdventureModVariables;

@Mod.EventBusSubscriber
public class PlayerTickProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.getX(), event.player.getY(), event.player.getZ(), event.player);
		}
	}

	public static void execute(double x, double y, double z, Entity entity) {
		execute(null, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity.isAlive()) {
			if (entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).Health_regen_tick > 0) {
				{
					EriniumAdventureModVariables.PlayerVariables _vars = entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES);
					_vars.Health_regen_tick = entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).Health_regen_tick - 1;
					_vars.syncPlayerVariables(entity);
				}
			} else {
				if (entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).health < entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).max_health
						&& entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).Health_regen_cooldown == 0) {
					{
						EriniumAdventureModVariables.PlayerVariables _vars = entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES);
						_vars.health = entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).health + 0.5;
						_vars.syncPlayerVariables(entity);
					}
					{
						EriniumAdventureModVariables.PlayerVariables _vars = entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES);
						_vars.Health_regen_tick = 40;
						_vars.syncPlayerVariables(entity);
					}
				}
			}
			if (entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).health > entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).max_health) {
				{
					EriniumAdventureModVariables.PlayerVariables _vars = entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES);
					_vars.health = entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).max_health;
					_vars.syncPlayerVariables(entity);
				}
			}
			if (entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).health <= 0) {
				{
					EriniumAdventureModVariables.PlayerVariables _vars = entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES);
					_vars.health = 0;
					_vars.syncPlayerVariables(entity);
				}
				if (entity instanceof LivingEntity _entity)
					_entity.setHealth(0);
			}
			if (entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).health_damage > 0) {
				{
					EriniumAdventureModVariables.PlayerVariables _vars = entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES);
					_vars.health_damage = entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).health_damage - 1;
					_vars.syncPlayerVariables(entity);
				}
				{
					EriniumAdventureModVariables.PlayerVariables _vars = entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES);
					_vars.Health_regen_cooldown = 20;
					_vars.syncPlayerVariables(entity);
				}
			} else {
				if (entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).Health_regen_cooldown > 0) {
					{
						EriniumAdventureModVariables.PlayerVariables _vars = entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES);
						_vars.Health_regen_cooldown = entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).Health_regen_cooldown - 1;
						_vars.syncPlayerVariables(entity);
					}
				}
			}
			if (entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).synchronisation > 0) {
				{
					EriniumAdventureModVariables.PlayerVariables _vars = entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES);
					_vars.synchronisation = entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).synchronisation - 1;
					_vars.syncPlayerVariables(entity);
				}
			} else {
				SynchronisePlayerProcedure.execute(entity);
				{
					EriniumAdventureModVariables.PlayerVariables _vars = entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES);
					_vars.synchronisation = 6000;
					_vars.syncPlayerVariables(entity);
				}
			}
			if ((entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).movement_type).equals("DRIVE")) {
				if (entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).speed < 1) {
					{
						EriniumAdventureModVariables.PlayerVariables _vars = entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES);
						_vars.speed = Math.min(entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).speed + 0.0083333333, 1);
						_vars.syncPlayerVariables(entity);
					}
				}
				{
					double yawRadians = java.lang.Math.toRadians(entity.getYRot()); // Angle horizontal (yaw) du joueur en radians
					// Calcul des composantes du vecteur de direction dans le plan horizontal (XZ)
					double directionX = -java.lang.Math.sin(yawRadians); // Composante X du vecteur de direction
					double directionZ = java.lang.Math.cos(yawRadians); // Composante Z du vecteur de direction
					double speed = entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).speed; // Vitesse de déplacement (ajustez selon vos besoins)
					// Calcul du déplacement dans le plan horizontal (XZ) en fonction de la direction du joueur
					double deltaX = directionX * speed;
					double deltaZ = directionZ * speed;
					entity.setDeltaMovement(deltaX, 0, deltaZ);
				}
			} else if ((entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).movement_type).equals("HOLD")) {
				if (entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).speed > 0) {
					{
						EriniumAdventureModVariables.PlayerVariables _vars = entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES);
						_vars.speed = entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).speed - 0.01;
						_vars.syncPlayerVariables(entity);
					}
					{
						double yawRadians = java.lang.Math.toRadians(entity.getYRot()); // Angle horizontal (yaw) du joueur en radians
						// Calcul des composantes du vecteur de direction dans le plan horizontal (XZ)
						double directionX = -java.lang.Math.sin(yawRadians); // Composante X du vecteur de direction
						double directionZ = java.lang.Math.cos(yawRadians); // Composante Z du vecteur de direction
						double speed = entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).speed; // Vitesse de déplacement (ajustez selon vos besoins)
						// Calcul du déplacement dans le plan horizontal (XZ) en fonction de la direction du joueur
						double deltaX = directionX * speed;
						double deltaZ = directionZ * speed;
						entity.setDeltaMovement(deltaX, 0, deltaZ);
					}
				}
			} else if ((entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).movement_type).equals("")) {
				{
					EriniumAdventureModVariables.PlayerVariables _vars = entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES);
					_vars.movement_type = "HOLD";
					_vars.syncPlayerVariables(entity);
				}
			}
		}
	}
}
