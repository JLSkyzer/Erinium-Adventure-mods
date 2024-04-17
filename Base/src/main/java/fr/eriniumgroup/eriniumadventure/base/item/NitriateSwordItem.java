
package fr.eriniumgroup.eriniumadventure.base.item;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

import fr.eriniumgroup.eriniumadventure.base.init.EriniumAdventureModItems;

public class NitriateSwordItem extends SwordItem {
	public NitriateSwordItem() {
		super(new Tier() {
			public int getUses() {
				return 46;
			}

			public float getSpeed() {
				return 3f;
			}

			public float getAttackDamageBonus() {
				return -3f;
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
		}, 3, -3f, new Item.Properties());
	}
}
