
package fr.eriniumgroup.eriniumadventure.base.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class StarshipItemItem extends Item {
	public StarshipItemItem() {
		super(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.COMMON));
	}
}
