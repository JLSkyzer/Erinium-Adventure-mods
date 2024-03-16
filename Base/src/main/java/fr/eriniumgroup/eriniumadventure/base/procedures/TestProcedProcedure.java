package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.CombatRules;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

public class TestProcedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		double slot = 0;
		net.minecraft.world.damagesource.DamageSource damageSource = null;
		double damage = 0;

		{
			for (int index0 = 0; index0 < 4; index0++) {
				{
					ItemStack _ist = (entity instanceof net.minecraft.world.entity.LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.byTypeAndIndex(net.minecraft.world.entity.EquipmentSlot.Type.ARMOR, (int) slot)) : ItemStack.EMPTY);
					if (_ist.hurt((int) new Object(){
						private double getDamageAfterArmorAbsorb(DamageSource eridamageSource, double eridamage){
							if (!eridamageSource.is(net.minecraft.tags.DamageTypeTags.BYPASSES_ARMOR)) {
								eridamage = net.minecraft.world.damagesource.CombatRules.getDamageAfterAbsorb((float) eridamage, (float)net.minecraft.util.Mth.floor(net.minecraft.world.entity.ai.attributes.Attributes.ARMOR.getDefaultValue()), (float)net.minecraft.world.entity.ai.attributes.Attributes.ARMOR_TOUGHNESS.getDefaultValue());
							}

							return eridamage;
						}
					}.getDamageAfterArmorAbsorb(damageSource, damage), RandomSource.create(), null)) {
						_ist.shrink(1);
						_ist.setDamageValue(0);
					}
				}
				slot = slot + 1;
			}
		}

	}
}
