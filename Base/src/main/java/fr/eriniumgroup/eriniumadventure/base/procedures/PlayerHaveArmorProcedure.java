package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;

public class PlayerHaveArmorProcedure {
	public static double execute(Entity entity) {
		if (entity == null)
			return 0;
		boolean returnLogic = false;
		double slot = 0;
		double ReturnDec = 0;
		slot = 0;
		ReturnDec = 0;
		for (int index0 = 0; index0 < 4; index0++) {
			if (!((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) slot)) : ItemStack.EMPTY).getItem() == Blocks.AIR.asItem())) {
				ReturnDec = 10;
				break;
			} else {
				slot = slot + 1;
			}
		}
		return ReturnDec;
	}
}
