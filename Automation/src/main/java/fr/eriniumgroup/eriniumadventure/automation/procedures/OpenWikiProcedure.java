package fr.eriniumgroup.eriniumadventure.automation.procedures;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.glfw.GLFW;

import fr.eriniumgroup.eriniumadventure.automation.WikiPage;

public class OpenWikiProcedure {

	private static final Minecraft minecraft = Minecraft.getInstance();

	@OnlyIn(Dist.CLIENT)
	public static void execute() {
		if (!(minecraft.screen instanceof WikiPage)) {
            // Display the ExampleScreen web browser
            minecraft.setScreen(new WikiPage(
                    Component.literal("Wiki")
            ));
        }
	}
}
