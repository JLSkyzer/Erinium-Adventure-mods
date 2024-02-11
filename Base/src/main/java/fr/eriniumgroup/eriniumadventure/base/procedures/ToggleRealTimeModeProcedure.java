package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import fr.eriniumgroup.eriniumadventure.base.network.EriniumAdventureModVariables;

public class ToggleRealTimeModeProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (!EriniumAdventureModVariables.MapVariables.get(world).toggle_realtime) {
			EriniumAdventureModVariables.MapVariables.get(world).toggle_realtime = true;
			EriniumAdventureModVariables.MapVariables.get(world).syncData(world);
			world.getLevelData().getGameRules().getRule(GameRules.RULE_DAYLIGHT).set(false, world.getServer());
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("\u00A7aON"), false);
		} else {
			EriniumAdventureModVariables.MapVariables.get(world).toggle_realtime = false;
			EriniumAdventureModVariables.MapVariables.get(world).syncData(world);
			world.getLevelData().getGameRules().getRule(GameRules.RULE_DAYLIGHT).set(true, world.getServer());
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("\u00A74OFF"), false);
		}
	}
}
