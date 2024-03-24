package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;

public class TestProcedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		double slot = 0;
		for (int index0 = 0; index0 < 4; index0++) {
			{
				ItemStack _ist = (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, (int) slot)) : ItemStack.EMPTY);
				if (_ist.hurt(1, RandomSource.create(), null)) {
					_ist.shrink(1);
					_ist.setDamageValue(0);
				}
			}
			slot = slot + 1;
		}
	}
}
