
package fr.eriniumgroup.eriniumadventure.base.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import fr.eriniumgroup.eriniumadventure.base.entity.RocketHeadEntity;
import fr.eriniumgroup.eriniumadventure.base.client.model.Modelrocket_head;

public class RocketHeadRenderer extends MobRenderer<RocketHeadEntity, Modelrocket_head<RocketHeadEntity>> {
	public RocketHeadRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelrocket_head(context.bakeLayer(Modelrocket_head.LAYER_LOCATION)), 3f);
	}

	@Override
	public ResourceLocation getTextureLocation(RocketHeadEntity entity) {
		return new ResourceLocation("erinium_adventure:textures/entities/rocket_head.png");
	}
}
