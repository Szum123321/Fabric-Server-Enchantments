package io.github.qspdmc.mixin;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.minecraft.command.arguments.EntityArgumentType;
import net.minecraft.command.arguments.ItemEnchantmentArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.CommandSource;
import net.minecraft.server.command.EnchantCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(CommandManager.class)
public abstract class CommandManagerMixin {
    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/EnchantCommand;register(Lcom/mojang/brigadier/CommandDispatcher;)V"))
    public void updateSuggestions(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandManager.literal("enchant").requires((serverCommandSource) -> {
            return serverCommandSource.hasPermissionLevel(2);
        })).then(CommandManager.argument("targets", EntityArgumentType.entities()).then(((RequiredArgumentBuilder)CommandManager.argument("enchantment", ItemEnchantmentArgumentType.itemEnchantment())
                //Thanks to this line, even if client does not have mod installed they still can see our enchant popup in suggestions.
                .suggests((commandContext, suggestionsBuilder) -> CommandSource.suggestIdentifiers((Iterable)Registry.ENCHANTMENT.getIds(), suggestionsBuilder))
                .executes((commandContext) -> {
            return EnchantCommand.execute((ServerCommandSource)commandContext.getSource(), EntityArgumentType.getEntities(commandContext, "targets"), ItemEnchantmentArgumentType.getEnchantment(commandContext, "enchantment"), 1);
        })).then(CommandManager.argument("level", IntegerArgumentType.integer(0)).executes((commandContext) -> {
            return EnchantCommand.execute((ServerCommandSource)commandContext.getSource(), EntityArgumentType.getEntities(commandContext, "targets"), ItemEnchantmentArgumentType.getEnchantment(commandContext, "enchantment"), IntegerArgumentType.getInteger(commandContext, "level"));
        })))));
    }
}
