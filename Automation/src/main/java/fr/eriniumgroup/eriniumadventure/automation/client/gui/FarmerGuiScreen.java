package fr.eriniumgroup.eriniumadventure.automation.client.gui;

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

import fr.eriniumgroup.eriniumadventure.automation.world.inventory.FarmerGuiMenu;
import fr.eriniumgroup.eriniumadventure.automation.procedures.GetEnergyProcedure;
import fr.eriniumgroup.eriniumadventure.automation.network.FarmerGuiButtonMessage;

import com.mojang.blaze3d.systems.RenderSystem;

public class FarmerGuiScreen extends AbstractContainerScreen<FarmerGuiMenu> {
	private final static HashMap<String, Object> guistate = FarmerGuiMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_help;

	public FarmerGuiScreen(FarmerGuiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("erinium_automation:textures/screens/farmer_gui.png");

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
		guiGraphics.drawString(this.font, Component.translatable("gui.erinium_automation.farmer_gui.label_farmer"), 69, 7, -16777216, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.erinium_automation.farmer_gui.label_proc_get_energy"), 6, 16, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.erinium_automation.farmer_gui.label_seeds"), 6, 34, -10066432, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.erinium_automation.farmer_gui.label_items"), 141, 34, -16751002, false);
		guiGraphics.drawString(this.font,

				GetEnergyProcedure.execute(world, x, y, z), 6, 16, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_help = Button.builder(Component.translatable("gui.erinium_automation.farmer_gui.button_help"), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new FarmerGuiButtonMessage(0, x, y, z));
				FarmerGuiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 123, this.topPos + 7, 46, 20).build();
		guistate.put("button:button_help", button_help);
		this.addRenderableWidget(button_help);
	}
}
