package io.github.qspdmc.mixin;

import io.github.qspdmc.ServerEnchantment;
import net.minecraft.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Enchantment.class)
public class EnchantmentMixin {
	@ModifyArg(method = "getName", at = @At(value = "INVOKE", target = "Lnet/minecraft/text/TranslatableText;<init>(Ljava/lang/String;[Ljava/lang/Object;)V"), index = 0)
	private String translate(String translatable) {
		if(this instanceof ServerEnchantment) {
			return ((ServerEnchantment) this).name();
		}
		return translatable;
	}
}
