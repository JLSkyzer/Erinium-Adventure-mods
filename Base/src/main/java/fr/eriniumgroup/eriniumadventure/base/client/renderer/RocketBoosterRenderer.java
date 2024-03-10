
package fr.eriniumgroup.eriniumadventure.base.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import fr.eriniumgroup.eriniumadventure.base.entity.RocketBoosterEntity;
import fr.eriniumgroup.eriniumadventure.base.client.model.Modelrocket_booster;

public class RocketBoosterRenderer extends MobRenderer<RocketBoosterEntity, Modelrocket_booster<RocketBoosterEntity>> {
	public RocketBoosterRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelrocket_booster(context.bakeLayer(Modelrocket_booster.LAYER_LOCATION)), 3f);
	}

	@Override
	public ResourceLocation getTextureLocation(RocketBoosterEntity entity) {
		return new ResourceLocation("erinium_adventure:textures/entities/rocket_booster_texture.png");
	}
}
