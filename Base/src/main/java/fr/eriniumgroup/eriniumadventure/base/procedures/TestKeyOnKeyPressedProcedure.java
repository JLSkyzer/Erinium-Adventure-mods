package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import fr.eriniumgroup.eriniumadventure.base.network.EriniumAdventureModVariables;

public class TestKeyOnKeyPressedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			EriniumAdventureModVariables.PlayerVariables _vars = entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES);
			_vars.movement_type = "DRIVE";
			_vars.syncPlayerVariables(entity);
		}
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("\u00A7cTOUCHE APPUYER = acc\u00E9leration"), true);
	}
}
