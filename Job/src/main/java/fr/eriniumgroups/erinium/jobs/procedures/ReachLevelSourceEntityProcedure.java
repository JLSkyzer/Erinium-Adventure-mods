package fr.eriniumgroups.erinium.jobs.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import fr.eriniumgroups.erinium.jobs.configuration.CommonConfigConfiguration;

public class ReachLevelSourceEntityProcedure {
	public static void execute(Entity sourceentity) {
		if (sourceentity == null)
			return;
		String item_list = "";
		double item_number = 0;
		if (sourceentity instanceof Player || sourceentity instanceof ServerPlayer) {
			item_list = CommonConfigConfiguration.ITEM_GIVE.get();
			if (sourceentity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal((Component.translatable("jobs.levelup.gift.message").getString())), false);
			while ((item_list).length() > 0) {
				if (new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(new Object() {
					private String split(String text, String space, int index) {
						String s = text.split(space)[index];
						return s;
					}
				}.split(new Object() {
					private String split(String text, String space, int index) {
						String s = text.split(space)[index];
						return s;
					}
				}.split(item_list, ", ", (int) 0), " ", (int) 1)) > 1) {
					if (sourceentity instanceof Player _player) {
						ItemStack _setstack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation((new Object() {
							private String split(String text, String space, int index) {
								String s = text.split(space)[index];
								return s;
							}
						}.split(new Object() {
							private String split(String text, String space, int index) {
								String s = text.split(space)[index];
								return s;
							}
						}.split(item_list, ", ", (int) 0), " ", (int) 0)).toLowerCase(java.util.Locale.ENGLISH))));
						_setstack.setCount((int) new Object() {
							double convert(String s) {
								try {
									return Double.parseDouble(s.trim());
								} catch (Exception e) {
								}
								return 0;
							}
						}.convert(new Object() {
							private String split(String text, String space, int index) {
								String s = text.split(space)[index];
								return s;
							}
						}.split(new Object() {
							private String split(String text, String space, int index) {
								String s = text.split(space)[index];
								return s;
							}
						}.split(item_list, ", ", (int) 0), " ", (int) 1)));
						ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
					}
				} else {
					if (sourceentity instanceof Player _player) {
						ItemStack _setstack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation((new Object() {
							private String split(String text, String space, int index) {
								String s = text.split(space)[index];
								return s;
							}
						}.split(new Object() {
							private String split(String text, String space, int index) {
								String s = text.split(space)[index];
								return s;
							}
						}.split(item_list, ", ", (int) 0), " ", (int) 0)).toLowerCase(java.util.Locale.ENGLISH))));
						_setstack.setCount(1);
						ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
					}
				}
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
