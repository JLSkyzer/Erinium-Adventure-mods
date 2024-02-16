package fr.eriniumgroups.erinium.jobs.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;

import fr.eriniumgroups.erinium.jobs.configuration.CommonConfigConfiguration;

public class ReachLevelProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		String item_list = "";
		double item_number = 0;
		if (entity instanceof Player || entity instanceof ServerPlayer) {
			item_list = CommonConfigConfiguration.ITEM_GIVE.get();
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal((Component.translatable("jobs.levelup.gift.message").getString())), false);
			while (!(new Object() {
				private String split(String text, String space, int index) {
					String s = text.split(space)[index];
					return s;
				}
			}.split(item_list, ", ", (int) 0)).isEmpty()) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("" + new Object() {
						private String split(String text, String space, int index) {
							String s = text.split(space)[index];
							return s;
						}
					}.split(item_list, ", ", (int) 0))), false);
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("" + new Object() {
						private String split(String text, String space, int index) {
							String s = text.split(space)[index];
							return s;
						}
					}.split(new Object() {
						private String split(String text, String space, int index) {
							String s = text.split(space)[index];
							return s;
						}
					}.split(item_list, ", ", (int) 0), " ", (int) 0))), false);
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("" + new Object() {
						private String split(String text, String space, int index) {
							String s = text.split(space)[index];
							return s;
						}
					}.split(new Object() {
						private String split(String text, String space, int index) {
							String s = text.split(space)[index];
							return s;
						}
					}.split(item_list, ", ", (int) 0), " ", (int) 1))), false);
				item_list = item_list.replace(new Object() {
					private String split(String text, String space, int index) {
						String s = text.split(space)[index];
						return s;
					}
				}.split(item_list, ", ", (int) 0), "");
			}
		}
	}
}
