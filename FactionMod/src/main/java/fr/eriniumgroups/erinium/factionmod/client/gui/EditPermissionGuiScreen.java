package fr.eriniumgroups.erinium.factionmod.client.gui;

import net.neoforged.neoforge.network.PacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import java.util.HashMap;

import fr.eriniumgroups.erinium.factionmod.world.inventory.EditPermissionGuiMenu;
import fr.eriniumgroups.erinium.factionmod.procedures.ReturnTempFileProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.CansethomeProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.CaninteractProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.CanWarpProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.CanUnclaimProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.CanSetwarpProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.CanPromoteProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.CanPoseProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.CanModifyProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.CanKickProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.CanInviteProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.CanHomeProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.CanDemoteProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.CanDelwarpProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.CanDelhomeProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.CanClaimProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.CanBreakProcedure;
import fr.eriniumgroups.erinium.factionmod.network.EditPermissionGuiButtonMessage;

import com.mojang.blaze3d.systems.RenderSystem;

public class EditPermissionGuiScreen extends AbstractContainerScreen<EditPermissionGuiMenu> {
	private final static HashMap<String, Object> guistate = EditPermissionGuiMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_finish;
	Button button_x;
	Button button_x1;
	Button button_empty;
	Button button_x2;
	Button button_x3;
	Button button_x4;
	Button button_x5;
	Button button_x6;
	Button button_x7;
	Button button_x8;
	Button button_x9;
	Button button_x10;
	Button button_x11;
	Button button_x12;
	Button button_x13;
	Button button_x14;

	public EditPermissionGuiScreen(EditPermissionGuiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("erinium_faction:textures/screens/edit_permission_gui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		guiGraphics.blit(new ResourceLocation("erinium_faction:textures/screens/f_perm_gui.png"), this.leftPos + -126, this.topPos + -37, 0, 0, 428, 240, 428, 240);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font,

				ReturnTempFileProcedure.execute(entity), -124, -35, -6737152, false);
		guiGraphics.drawString(this.font,

				CaninteractProcedure.execute(entity), -122, -18, -1, false);
		guiGraphics.drawString(this.font,

				CanBreakProcedure.execute(entity), -122, 12, -1, false);
		guiGraphics.drawString(this.font,

				CanPoseProcedure.execute(entity), -122, 42, -1, false);
		guiGraphics.drawString(this.font,

				CanKickProcedure.execute(entity), -122, 72, -1, false);
		guiGraphics.drawString(this.font,

				CanInviteProcedure.execute(entity), -122, 102, -1, false);
		guiGraphics.drawString(this.font,

				CanPromoteProcedure.execute(entity), -122, 132, -1, false);
		guiGraphics.drawString(this.font,

				CanDemoteProcedure.execute(entity), -122, 162, -1, false);
		guiGraphics.drawString(this.font,

				CanClaimProcedure.execute(entity), -42, -18, -1, false);
		guiGraphics.drawString(this.font,

				CanUnclaimProcedure.execute(entity), -42, 12, -1, false);
		guiGraphics.drawString(this.font,

				CanSetwarpProcedure.execute(entity), -42, 42, -1, false);
		guiGraphics.drawString(this.font,

				CanDelwarpProcedure.execute(entity), -42, 72, -1, false);
		guiGraphics.drawString(this.font,

				CansethomeProcedure.execute(entity), -42, 102, -1, false);
		guiGraphics.drawString(this.font,

				CanDelhomeProcedure.execute(entity), -42, 132, -1, false);
		guiGraphics.drawString(this.font,

				CanModifyProcedure.execute(entity), -42, 162, -1, false);
		guiGraphics.drawString(this.font,

				CanHomeProcedure.execute(entity), 28, -18, -1, false);
		guiGraphics.drawString(this.font,

				CanWarpProcedure.execute(entity), 28, 12, -1, false);
	}

	@Override
	public void init() {
		super.init();
		button_finish = Button.builder(Component.translatable("gui.erinium_faction.edit_permission_gui.button_finish"), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new EditPermissionGuiButtonMessage(0, x, y, z));
				EditPermissionGuiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 261, this.topPos + 179, 36, 20).build();
		guistate.put("button:button_finish", button_finish);
		this.addRenderableWidget(button_finish);
		button_x = Button.builder(Component.translatable("gui.erinium_faction.edit_permission_gui.button_x"), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new EditPermissionGuiButtonMessage(1, x, y, z));
				EditPermissionGuiButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + -122, this.topPos + -8, 20, 20).build();
		guistate.put("button:button_x", button_x);
		this.addRenderableWidget(button_x);
		button_x1 = Button.builder(Component.translatable("gui.erinium_faction.edit_permission_gui.button_x1"), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new EditPermissionGuiButtonMessage(2, x, y, z));
				EditPermissionGuiButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}).bounds(this.leftPos + -122, this.topPos + 22, 20, 20).build();
		guistate.put("button:button_x1", button_x1);
		this.addRenderableWidget(button_x1);
		button_empty = Button.builder(Component.translatable("gui.erinium_faction.edit_permission_gui.button_empty"), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new EditPermissionGuiButtonMessage(3, x, y, z));
				EditPermissionGuiButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		}).bounds(this.leftPos + -122, this.topPos + 52, 20, 20).build();
		guistate.put("button:button_empty", button_empty);
		this.addRenderableWidget(button_empty);
		button_x2 = Button.builder(Component.translatable("gui.erinium_faction.edit_permission_gui.button_x2"), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new EditPermissionGuiButtonMessage(4, x, y, z));
				EditPermissionGuiButtonMessage.handleButtonAction(entity, 4, x, y, z);
			}
		}).bounds(this.leftPos + -122, this.topPos + 82, 20, 20).build();
		guistate.put("button:button_x2", button_x2);
		this.addRenderableWidget(button_x2);
		button_x3 = Button.builder(Component.translatable("gui.erinium_faction.edit_permission_gui.button_x3"), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new EditPermissionGuiButtonMessage(5, x, y, z));
				EditPermissionGuiButtonMessage.handleButtonAction(entity, 5, x, y, z);
			}
		}).bounds(this.leftPos + -122, this.topPos + 112, 20, 20).build();
		guistate.put("button:button_x3", button_x3);
		this.addRenderableWidget(button_x3);
		button_x4 = Button.builder(Component.translatable("gui.erinium_faction.edit_permission_gui.button_x4"), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new EditPermissionGuiButtonMessage(6, x, y, z));
				EditPermissionGuiButtonMessage.handleButtonAction(entity, 6, x, y, z);
			}
		}).bounds(this.leftPos + -122, this.topPos + 142, 20, 20).build();
		guistate.put("button:button_x4", button_x4);
		this.addRenderableWidget(button_x4);
		button_x5 = Button.builder(Component.translatable("gui.erinium_faction.edit_permission_gui.button_x5"), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new EditPermissionGuiButtonMessage(7, x, y, z));
				EditPermissionGuiButtonMessage.handleButtonAction(entity, 7, x, y, z);
			}
		}).bounds(this.leftPos + -122, this.topPos + 172, 20, 20).build();
		guistate.put("button:button_x5", button_x5);
		this.addRenderableWidget(button_x5);
		button_x6 = Button.builder(Component.translatable("gui.erinium_faction.edit_permission_gui.button_x6"), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new EditPermissionGuiButtonMessage(8, x, y, z));
				EditPermissionGuiButtonMessage.handleButtonAction(entity, 8, x, y, z);
			}
		}).bounds(this.leftPos + -42, this.topPos + -8, 20, 20).build();
		guistate.put("button:button_x6", button_x6);
		this.addRenderableWidget(button_x6);
		button_x7 = Button.builder(Component.translatable("gui.erinium_faction.edit_permission_gui.button_x7"), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new EditPermissionGuiButtonMessage(9, x, y, z));
				EditPermissionGuiButtonMessage.handleButtonAction(entity, 9, x, y, z);
			}
		}).bounds(this.leftPos + -42, this.topPos + 22, 20, 20).build();
		guistate.put("button:button_x7", button_x7);
		this.addRenderableWidget(button_x7);
		button_x8 = Button.builder(Component.translatable("gui.erinium_faction.edit_permission_gui.button_x8"), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new EditPermissionGuiButtonMessage(10, x, y, z));
				EditPermissionGuiButtonMessage.handleButtonAction(entity, 10, x, y, z);
			}
		}).bounds(this.leftPos + -42, this.topPos + 52, 20, 20).build();
		guistate.put("button:button_x8", button_x8);
		this.addRenderableWidget(button_x8);
		button_x9 = Button.builder(Component.translatable("gui.erinium_faction.edit_permission_gui.button_x9"), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new EditPermissionGuiButtonMessage(11, x, y, z));
				EditPermissionGuiButtonMessage.handleButtonAction(entity, 11, x, y, z);
			}
		}).bounds(this.leftPos + -42, this.topPos + 82, 20, 20).build();
		guistate.put("button:button_x9", button_x9);
		this.addRenderableWidget(button_x9);
		button_x10 = Button.builder(Component.translatable("gui.erinium_faction.edit_permission_gui.button_x10"), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new EditPermissionGuiButtonMessage(12, x, y, z));
				EditPermissionGuiButtonMessage.handleButtonAction(entity, 12, x, y, z);
			}
		}).bounds(this.leftPos + -42, this.topPos + 112, 20, 20).build();
		guistate.put("button:button_x10", button_x10);
		this.addRenderableWidget(button_x10);
		button_x11 = Button.builder(Component.translatable("gui.erinium_faction.edit_permission_gui.button_x11"), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new EditPermissionGuiButtonMessage(13, x, y, z));
				EditPermissionGuiButtonMessage.handleButtonAction(entity, 13, x, y, z);
			}
		}).bounds(this.leftPos + -42, this.topPos + 142, 20, 20).build();
		guistate.put("button:button_x11", button_x11);
		this.addRenderableWidget(button_x11);
		button_x12 = Button.builder(Component.translatable("gui.erinium_faction.edit_permission_gui.button_x12"), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new EditPermissionGuiButtonMessage(14, x, y, z));
				EditPermissionGuiButtonMessage.handleButtonAction(entity, 14, x, y, z);
			}
		}).bounds(this.leftPos + -42, this.topPos + 172, 20, 20).build();
		guistate.put("button:button_x12", button_x12);
		this.addRenderableWidget(button_x12);
		button_x13 = Button.builder(Component.translatable("gui.erinium_faction.edit_permission_gui.button_x13"), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new EditPermissionGuiButtonMessage(15, x, y, z));
				EditPermissionGuiButtonMessage.handleButtonAction(entity, 15, x, y, z);
			}
		}).bounds(this.leftPos + 28, this.topPos + -8, 20, 20).build();
		guistate.put("button:button_x13", button_x13);
		this.addRenderableWidget(button_x13);
		button_x14 = Button.builder(Component.translatable("gui.erinium_faction.edit_permission_gui.button_x14"), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new EditPermissionGuiButtonMessage(16, x, y, z));
				EditPermissionGuiButtonMessage.handleButtonAction(entity, 16, x, y, z);
			}
		}).bounds(this.leftPos + 28, this.topPos + 22, 20, 20).build();
		guistate.put("button:button_x14", button_x14);
		this.addRenderableWidget(button_x14);
	}
}
