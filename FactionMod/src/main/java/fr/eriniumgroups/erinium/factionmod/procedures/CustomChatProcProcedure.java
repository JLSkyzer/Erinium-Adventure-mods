package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

public class CustomChatProcProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (EriniumFactionModVariables.MapVariables.get(world).custom_chat) {
			EriniumFactionModVariables.MapVariables.get(world).custom_chat = false;
			EriniumFactionModVariables.MapVariables.get(world).syncData(world);
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("\u00A74OFF"), false);
		} else {
			EriniumFactionModVariables.MapVariables.get(world).custom_chat = true;
			EriniumFactionModVariables.MapVariables.get(world).syncData(world);
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("\u00A7aON"), false);
		}
	}
}
