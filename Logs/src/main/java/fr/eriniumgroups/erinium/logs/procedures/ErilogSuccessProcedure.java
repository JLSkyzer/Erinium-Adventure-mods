package fr.eriniumgroups.erinium.logs.procedures;

import net.neoforged.fml.loading.FMLPaths;

import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.commands.CommandSourceStack;

import java.util.List;
import java.util.ArrayList;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.io.BufferedWriter;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.StringArgumentType;

public class ErilogSuccessProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments) {
		File file = new File("");
		String time = "";
		List<Object> array = new ArrayList<>();
		file = new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumLogs/" + StringArgumentType.getString(arguments, "type") + "/"), File.separator + (ReturnDateForFileProcedure.execute() + ".erilog"));
		if (!file.exists()) {
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			try {
				FileWriter filewriter = new FileWriter(file, true);
				BufferedWriter filebw = new BufferedWriter(filewriter);
				{
					filebw.write("========== Erinium Logs ==========");
					filebw.newLine();
				}
				filebw.close();
				filewriter.close();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
		try {
			FileWriter filewriter = new FileWriter(file, true);
			BufferedWriter filebw = new BufferedWriter(filewriter);
			{
				filebw.write(("[" + ReturnDateStringProcedure.execute() + "] [" + "SUCCESS" + "] " + (new Object() {
					public String getMessage() {
						try {
							return MessageArgument.getMessage(arguments, "message").getString();
						} catch (CommandSyntaxException ignored) {
							return "";
						}
					}
				}).getMessage()));
				filebw.newLine();
			}
			filebw.close();
			filewriter.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
