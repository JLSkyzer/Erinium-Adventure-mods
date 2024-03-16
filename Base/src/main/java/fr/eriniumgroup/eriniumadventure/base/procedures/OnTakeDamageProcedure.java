package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerPlayer;

import javax.annotation.Nullable;

import fr.eriniumgroup.eriniumadventure.base.network.EriniumAdventureModVariables;

@Mod.EventBusSubscriber
public class OnTakeDamageProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getSource(), event.getEntity(), event.getAmount());
		}
	}

	public static void execute(DamageSource damagesource, Entity entity, double amount) {
		execute(null, damagesource, entity, amount);
	}

	private static void execute(@Nullable Event event, DamageSource damagesource, Entity entity, double amount) {
		if (damagesource == null || entity == null)
			return;
		if (entity instanceof Player || entity instanceof ServerPlayer) {
			if (!(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
				if (event != null && event.isCancelable()) {
					event.setCanceled(true);
				}
				if ((entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAdventureModVariables.PlayerVariables())).health_damage == 0) {
					if (damagesource.is(DamageTypes.LAVA) || damagesource.is(DamageTypes.IN_FIRE) || damagesource.is(DamageTypes.ON_FIRE)) {
						{
							double _setval = (entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAdventureModVariables.PlayerVariables())).health
									- amount * (1 - (entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAdventureModVariables.PlayerVariables())).fire_reduction);
							entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.health = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
					} else {
						{
							double _setval = (entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAdventureModVariables.PlayerVariables())).health - amount;
							entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.health = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
					}
					{
						double _setval = 20;
						entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.health_damage = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				}
			}
		}
	}
}
