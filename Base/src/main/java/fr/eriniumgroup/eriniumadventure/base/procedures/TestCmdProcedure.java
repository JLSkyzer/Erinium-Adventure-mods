package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import java.io.File;

public class TestCmdProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		File File = new File("");
		ItemStack item = ItemStack.EMPTY;
		ItemStack item2 = ItemStack.EMPTY;
		double number = 0;
		number = 123.456789;
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("" + number)), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal((new java.text.DecimalFormat("##.##").format(number))), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("" + Math.round(number * 100) / 100)), false);
	}
}
