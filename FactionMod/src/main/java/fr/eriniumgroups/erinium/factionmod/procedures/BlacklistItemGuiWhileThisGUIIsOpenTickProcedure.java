package fr.eriniumgroups.erinium.factionmod.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.function.Supplier;
import java.util.Map;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

public class BlacklistItemGuiWhileThisGUIIsOpenTickProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		String BlackListItem = "";
		ItemStack tempItem = ItemStack.EMPTY;
		double Count = 0;
		double slot_count = 0;
		double whilecount = 0;
		if (TargetEntityIsAdminProcedure.execute(entity)) {
			if (!entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).BL_Item_page_initialised) {
				BlackListItem = ReturnBlackListItemProcedure.execute();
				Count = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).blacklist_item_page * 18;
				whilecount = 0;
				if (Count > 0) {
					for (int index0 = 0; index0 < (int) Count; index0++) {
						if (!((BlackListItem).length() == 0)) {
							BlackListItem = BlackListItem.replace(new Object() {
								private String split(String text, String space, int index) {
									String s = text.split(space)[index];
									return s;
								}
							}.split(BlackListItem, ", ", (int) whilecount) + ", ", "");
						} else {
							break;
						}
					}
				}
				slot_count = 0;
				for (int index1 = 0; index1 < 18; index1++) {
					if ((BlackListItem).length() > 0) {
						if (!(BuiltInRegistries.ITEM.get(new ResourceLocation((new Object() {
							private String split(String text, String space, int index) {
								String s = text.split(space)[index];
								return s;
							}
						}.split(BlackListItem, ", ", (int) 0)).toLowerCase(java.util.Locale.ENGLISH))) == Blocks.AIR.asItem())) {
							tempItem = new ItemStack(BuiltInRegistries.ITEM.get(new ResourceLocation((new Object() {
								private String split(String text, String space, int index) {
									String s = text.split(space)[index];
									return s;
								}
							}.split(BlackListItem, ", ", (int) 0)).toLowerCase(java.util.Locale.ENGLISH))));
							tempItem.setHoverName(Component.literal(new Object() {
								private String split(String text, String space, int index) {
									String s = text.split(space)[index];
									return s;
								}
							}.split(BlackListItem, ", ", (int) 0)));
							if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
								ItemStack _setstack = tempItem.copy();
								_setstack.setCount(1);
								((Slot) _slots.get((int) slot_count)).set(_setstack);
								_player.containerMenu.broadcastChanges();
							}
						} else {
							if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
								ItemStack _setstack = new ItemStack(Blocks.AIR).copy();
								_setstack.setCount(1);
								((Slot) _slots.get((int) slot_count)).set(_setstack);
								_player.containerMenu.broadcastChanges();
							}
						}
						BlackListItem = BlackListItem.replace(new Object() {
							private String split(String text, String space, int index) {
								String s = text.split(space)[index];
								return s;
							}
						}.split(BlackListItem, ", ", (int) 0) + ", ", "");
					} else {
						if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
							ItemStack _setstack = new ItemStack(Blocks.AIR).copy();
							_setstack.setCount(1);
							((Slot) _slots.get((int) slot_count)).set(_setstack);
							_player.containerMenu.broadcastChanges();
						}
					}
					slot_count = slot_count + 1;
				}
				{
					EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
					_vars.BL_Item_page_initialised = true;
					_vars.syncPlayerVariables(entity);
				}
			}
		}
	}
}
