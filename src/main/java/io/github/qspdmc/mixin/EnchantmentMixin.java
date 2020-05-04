package io.github.qspdmc.mixin;

import io.github.qspdmc.ServerEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin (Enchantment.class)
public class EnchantmentMixin {
	@ModifyVariable (method = "getName",
	                 at = @At (value = "INVOKE",
	                           target = "Lnet/minecraft/text/TranslatableText;<init>(Ljava/lang/String;[Ljava/lang/Object;)V",
	                           shift = At.Shift.AFTER),
	                 index = 2)
	private Text translate(Text translatable) {
		if (this instanceof ServerEnchantment) {
			return ((ServerEnchantment) this).name();
		}
		return translatable;
	}
}
