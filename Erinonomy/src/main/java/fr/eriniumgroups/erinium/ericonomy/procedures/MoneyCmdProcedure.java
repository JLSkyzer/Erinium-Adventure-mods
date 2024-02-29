package fr.eriniumgroups.erinium.ericonomy.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

public class MoneyCmdProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("\u00A72" + "balance : \u00A77" + new java.text.DecimalFormat("#,###.##").format(GetEntityMoneyProcedure.execute(entity)) + "$")), false);
	}
}
