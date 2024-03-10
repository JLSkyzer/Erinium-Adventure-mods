package fr.eriniumgroup.eriniumadventure.base.client.model;

import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class Modelrocket_booster<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("erinium_adventure", "modelrocket_booster"), "main");
	public final ModelPart hexadecagon1;
	public final ModelPart hexadecagon3;
	public final ModelPart hexadecagon4;
	public final ModelPart hexadecagon5;
	public final ModelPart hexadecagon2;

	public Modelrocket_booster(ModelPart root) {
		this.hexadecagon1 = root.getChild("hexadecagon1");
		this.hexadecagon3 = root.getChild("hexadecagon3");
		this.hexadecagon4 = root.getChild("hexadecagon4");
		this.hexadecagon5 = root.getChild("hexadecagon5");
		this.hexadecagon2 = root.getChild("hexadecagon2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition hexadecagon1 = partdefinition.addOrReplaceChild("hexadecagon1",
				CubeListBuilder.create().texOffs(114, 41).addBox(-3.9782F, 0.0F, -20.0F, 7.9565F, 0.0F, 40.0F, new CubeDeformation(0.0F)).texOffs(244, 127).addBox(-20.0F, 0.0F, -3.9782F, 40.0F, 0.0F, 7.9565F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition hexadecagon_r1 = hexadecagon1.addOrReplaceChild("hexadecagon_r1",
				CubeListBuilder.create().texOffs(244, 118).addBox(-20.0F, 0.0F, -3.9782F, 40.0F, 0.0F, 7.9565F, new CubeDeformation(0.0F)).texOffs(114, 0).addBox(-3.9782F, 0.0F, -20.0F, 7.9565F, 0.0F, 40.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));
		PartDefinition hexadecagon_r2 = hexadecagon1.addOrReplaceChild("hexadecagon_r2",
				CubeListBuilder.create().texOffs(244, 136).addBox(-20.0F, 0.0F, -3.9782F, 40.0F, 0.0F, 7.9565F, new CubeDeformation(0.0F)).texOffs(114, 82).addBox(-3.9782F, 0.0F, -20.0F, 7.9565F, 0.0F, 40.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
		PartDefinition hexadecagon_r3 = hexadecagon1.addOrReplaceChild("hexadecagon_r3", CubeListBuilder.create().texOffs(57, 83).addBox(-3.9782F, 0.0F, -20.0F, 7.9565F, 0.0F, 40.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));
		PartDefinition hexadecagon_r4 = hexadecagon1.addOrReplaceChild("hexadecagon_r4", CubeListBuilder.create().texOffs(0, 123).addBox(-3.9782F, 0.0F, -20.0F, 7.9565F, 0.0F, 40.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
		PartDefinition hexadecagon3 = partdefinition.addOrReplaceChild("hexadecagon3",
				CubeListBuilder.create().texOffs(57, 1).addBox(-3.9782F, -96.0F, -20.0F, 7.9565F, 0.0F, 40.0F, new CubeDeformation(0.0F)).texOffs(244, 100).addBox(-20.0F, -96.0F, -3.9782F, 40.0F, 0.0F, 7.9565F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition hexadecagon_r5 = hexadecagon3.addOrReplaceChild("hexadecagon_r5",
				CubeListBuilder.create().texOffs(242, 91).addBox(-20.0F, -96.0F, -3.9782F, 40.0F, 0.0F, 7.9565F, new CubeDeformation(0.0F)).texOffs(0, 41).addBox(-3.9782F, -96.0F, -20.0F, 7.9565F, 0.0F, 40.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));
		PartDefinition hexadecagon_r6 = hexadecagon3.addOrReplaceChild("hexadecagon_r6",
				CubeListBuilder.create().texOffs(244, 109).addBox(-20.0F, -96.0F, -3.9782F, 40.0F, 0.0F, 7.9565F, new CubeDeformation(0.0F)).texOffs(57, 42).addBox(-3.9782F, -96.0F, -20.0F, 7.9565F, 0.0F, 40.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
		PartDefinition hexadecagon_r7 = hexadecagon3.addOrReplaceChild("hexadecagon_r7", CubeListBuilder.create().texOffs(0, 0).addBox(-3.9782F, -96.0F, -20.0F, 7.9565F, 0.0F, 40.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));
		PartDefinition hexadecagon_r8 = hexadecagon3.addOrReplaceChild("hexadecagon_r8", CubeListBuilder.create().texOffs(0, 82).addBox(-3.9782F, -96.0F, -20.0F, 7.9565F, 0.0F, 40.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
		PartDefinition hexadecagon4 = partdefinition.addOrReplaceChild("hexadecagon4",
				CubeListBuilder.create().texOffs(223, 99).addBox(-3.9782F, -96.0F, -20.0F, 7.9565F, 96.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(181, 123).addBox(-3.9782F, -96.0F, 18.0F, 7.9565F, 96.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(0, 164).addBox(18.0F, -96.0F, -3.9782F, 2.0F, 96.0F, 7.9565F, new CubeDeformation(0.0F)).texOffs(118, 124).addBox(-20.0F, -96.0F, -3.9782F, 2.0F, 96.0F, 7.9565F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition hexadecagon_r9 = hexadecagon4.addOrReplaceChild("hexadecagon_r9",
				CubeListBuilder.create().texOffs(97, 124).addBox(-20.0F, -96.0F, -3.9782F, 2.0F, 96.0F, 7.9565F, new CubeDeformation(0.0F)).texOffs(160, 123).addBox(18.0F, -96.0F, -3.9782F, 2.0F, 96.0F, 7.9565F, new CubeDeformation(0.0F))
						.texOffs(63, 164).addBox(-3.9782F, -96.0F, 18.0F, 7.9565F, 96.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(181, 222).addBox(-3.9782F, -96.0F, -20.0F, 7.9565F, 96.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));
		PartDefinition hexadecagon_r10 = hexadecagon4.addOrReplaceChild("hexadecagon_r10",
				CubeListBuilder.create().texOffs(139, 124).addBox(-20.0F, -96.0F, -3.9782F, 2.0F, 96.0F, 7.9565F, new CubeDeformation(0.0F)).texOffs(21, 164).addBox(18.0F, -96.0F, -3.9782F, 2.0F, 96.0F, 7.9565F, new CubeDeformation(0.0F))
						.texOffs(202, 123).addBox(-3.9782F, -96.0F, 18.0F, 7.9565F, 96.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(158, 228).addBox(-3.9782F, -96.0F, -20.0F, 7.9565F, 96.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
		PartDefinition hexadecagon_r11 = hexadecagon4.addOrReplaceChild("hexadecagon_r11",
				CubeListBuilder.create().texOffs(42, 164).addBox(-3.9782F, -96.0F, 18.0F, 7.9565F, 96.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(221, 220).addBox(-3.9782F, -96.0F, -20.0F, 7.9565F, 96.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));
		PartDefinition hexadecagon_r12 = hexadecagon4.addOrReplaceChild("hexadecagon_r12",
				CubeListBuilder.create().texOffs(211, 0).addBox(-3.9782F, -96.0F, 18.0F, 7.9565F, 96.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(84, 229).addBox(-3.9782F, -96.0F, -20.0F, 7.9565F, 96.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
		PartDefinition hexadecagon5 = partdefinition.addOrReplaceChild(
				"hexadecagon5", CubeListBuilder.create().texOffs(35, 269).addBox(-2.9223F, -96.0F, -20.5F, 2.0F, 96.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 269).addBox(0.9223F, -96.0F, 19.5F, 2.0F, 96.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(239, 0).addBox(19.5F, -96.0F, 1.0777F, 1.0F, 96.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(140, 229).addBox(-20.5F, -96.0F, 0.9223F, 1.0F, 96.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition hexadecagon_r13 = hexadecagon5.addOrReplaceChild("hexadecagon_r13",
				CubeListBuilder.create().texOffs(133, 229).addBox(-20.5F, -96.0F, 0.9223F, 1.0F, 96.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(232, 0).addBox(19.5F, -96.0F, 1.0777F, 1.0F, 96.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(263, 243)
						.addBox(0.9223F, -96.0F, 19.5F, 2.0F, 96.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(28, 269).addBox(-2.9223F, -96.0F, -20.5F, 2.0F, 96.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));
		PartDefinition hexadecagon_r14 = hexadecagon5.addOrReplaceChild("hexadecagon_r14",
				CubeListBuilder.create().texOffs(147, 229).addBox(-20.5F, -96.0F, 0.9223F, 1.0F, 96.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(242, 196).addBox(19.5F, -96.0F, 1.0777F, 1.0F, 96.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(7, 269)
						.addBox(0.9223F, -96.0F, 19.5F, 2.0F, 96.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(270, 145).addBox(-2.9223F, -96.0F, -20.5F, 2.0F, 96.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
		PartDefinition hexadecagon_r15 = hexadecagon5.addOrReplaceChild("hexadecagon_r15",
				CubeListBuilder.create().texOffs(263, 145).addBox(0.9223F, -96.0F, 19.5F, 2.0F, 96.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(21, 269).addBox(-2.9223F, -96.0F, -20.5F, 2.0F, 96.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));
		PartDefinition hexadecagon_r16 = hexadecagon5.addOrReplaceChild("hexadecagon_r16",
				CubeListBuilder.create().texOffs(14, 269).addBox(0.9223F, -96.0F, 19.5F, 2.0F, 96.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(270, 243).addBox(-2.9223F, -96.0F, -20.5F, 2.0F, 96.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
		PartDefinition hexadecagon2 = partdefinition.addOrReplaceChild(
				"hexadecagon2", CubeListBuilder.create().texOffs(63, 263).addBox(1.0777F, -96.0F, -20.5F, 2.0F, 96.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(256, 145).addBox(-3.0777F, -96.0F, 19.5F, 2.0F, 96.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(119, 229).addBox(19.5F, -96.0F, -2.9223F, 1.0F, 96.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(209, 222).addBox(-20.5F, -96.0F, -3.0777F, 1.0F, 96.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition hexadecagon_r17 = hexadecagon2.addOrReplaceChild("hexadecagon_r17",
				CubeListBuilder.create().texOffs(202, 222).addBox(-20.5F, -96.0F, -3.0777F, 1.0F, 96.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(112, 229).addBox(19.5F, -96.0F, -2.9223F, 1.0F, 96.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(249, 243).addBox(-3.0777F, -96.0F, 19.5F, 2.0F, 96.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(56, 263).addBox(1.0777F, -96.0F, -20.5F, 2.0F, 96.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));
		PartDefinition hexadecagon_r18 = hexadecagon2.addOrReplaceChild("hexadecagon_r18",
				CubeListBuilder.create().texOffs(105, 229).addBox(-20.5F, -96.0F, -3.0777F, 1.0F, 96.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(126, 229).addBox(19.5F, -96.0F, -2.9223F, 1.0F, 96.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(256, 243).addBox(-3.0777F, -96.0F, 19.5F, 2.0F, 96.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(70, 263).addBox(1.0777F, -96.0F, -20.5F, 2.0F, 96.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3927F, 0.0F));
		PartDefinition hexadecagon_r19 = hexadecagon2.addOrReplaceChild("hexadecagon_r19",
				CubeListBuilder.create().texOffs(249, 145).addBox(-3.0777F, -96.0F, 19.5F, 2.0F, 96.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(49, 263).addBox(1.0777F, -96.0F, -20.5F, 2.0F, 96.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));
		PartDefinition hexadecagon_r20 = hexadecagon2.addOrReplaceChild("hexadecagon_r20",
				CubeListBuilder.create().texOffs(42, 263).addBox(-3.0777F, -96.0F, 19.5F, 2.0F, 96.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(77, 263).addBox(1.0777F, -96.0F, -20.5F, 2.0F, 96.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
		return LayerDefinition.create(meshdefinition, 512, 512);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		hexadecagon1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		hexadecagon3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		hexadecagon4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		hexadecagon5.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		hexadecagon2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
