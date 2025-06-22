package com.humbertocaetano.fogless.command;

import com.humbertocaetano.fogless.util.FogLessHandler;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class FogLessCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("fogless")
                .then(literal("enabled")
                        .then(argument("value", BoolArgumentType.bool())
                                .executes(context -> {
                                    boolean enabled = BoolArgumentType.getBool(context, "value");
                                    FogLessHandler.setModEnabled(enabled);

                                    String status = enabled ? "§ahabilitado" : "§cdesabilitado";
                                    String emoji = enabled ? "✅" : "❌";
                                    context.getSource().sendMessage(Text.literal("§6[FogLess] §r" + emoji + " Mod foi " + status + "!"));

                                    return 1;
                                })
                        )
                        .executes(context -> {
                            // Se nenhum argumento for fornecido, mostra o status atual
                            boolean isEnabled = FogLessHandler.isModEnabled();
                            String status = isEnabled ? "§ahabilitado" : "§cdesabilitado";
                            String emoji = isEnabled ? "✅" : "❌";
                            context.getSource().sendMessage(Text.literal("§6[FogLess] §r" + emoji + " Mod está " + status));
                            return 1;
                        })
                )
                .then(literal("toggle")
                        .executes(context -> {
                            // Alterna o estado do mod
                            boolean newState = !FogLessHandler.isModEnabled();
                            FogLessHandler.setModEnabled(newState);

                            String status = newState ? "§ahabilitado" : "§cdesabilitado";
                            String emoji = newState ? "✅" : "❌";
                            context.getSource().sendMessage(Text.literal("§6[FogLess] §r" + emoji + " Mod " + status + "!"));

                            return 1;
                        })
                )
                .then(literal("status")
                        .executes(context -> {
                            boolean isEnabled = FogLessHandler.isModEnabled();
                            String status = isEnabled ? "§aATIVO" : "§cINATIVO";
                            String emoji = isEnabled ? "✅" : "❌";

                            context.getSource().sendMessage(Text.literal("§6[FogLess] Status: §r" + emoji + " " + status));
                            context.getSource().sendMessage(Text.literal("§7Comandos disponíveis:"));
                            context.getSource().sendMessage(Text.literal("§7• /fogless toggle §8- Alterna on/off"));
                            context.getSource().sendMessage(Text.literal("§7• /fogless enabled true/false §8- Define estado"));
                            context.getSource().sendMessage(Text.literal("§7• /fogless status §8- Mostra este status"));

                            return 1;
                        })
                )
        );
    }
}