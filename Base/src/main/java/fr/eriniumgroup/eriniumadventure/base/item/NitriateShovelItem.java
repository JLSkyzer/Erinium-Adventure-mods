
package fr.eriniumgroup.eriniumadventure.base.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.network.chat.Component;

import java.util.List;

import fr.eriniumgroup.eriniumadventure.base.init.EriniumAdventureModItems;

public class NitriateShovelItem extends ShovelItem {
	public NitriateShovelItem() {
		super(new Tier() {
			public int getUses() {
				return 46;
			}

			public float getSpeed() {
				return 3f;
			}

			public float getAttackDamageBonus() {
				return -1f;
			}

			public int getLevel() {
				return 1;
			}

			public int getEnchantmentValue() {
				return 4;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(EriniumAdventureModItems.NITRIATE_GEM.get()));
			}
		}, 1, -3f, new Item.Properties());
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
	}
}
