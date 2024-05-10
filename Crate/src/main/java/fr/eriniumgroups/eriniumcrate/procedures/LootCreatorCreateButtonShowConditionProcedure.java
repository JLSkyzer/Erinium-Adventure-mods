package fr.eriniumgroups.eriniumcrate.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.client.gui.components.EditBox;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

public class LootCreatorCreateButtonShowConditionProcedure {
	public static boolean execute(Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return false;
		return !((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Blocks.AIR.asItem()) && new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert(guistate.containsKey("text:count") ? ((EditBox) guistate.get("text:count")).getValue() : "") > 0 && new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert(guistate.containsKey("text:chance") ? ((EditBox) guistate.get("text:chance")).getValue() : "") > 0 && new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert(guistate.containsKey("text:chance") ? ((EditBox) guistate.get("text:chance")).getValue() : "") <= 100 && (guistate.containsKey("text:eventname") ? ((EditBox) guistate.get("text:eventname")).getValue() : "").length() > 0;
	}
}
