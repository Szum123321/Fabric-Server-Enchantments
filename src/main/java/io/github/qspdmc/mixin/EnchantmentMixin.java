package io.github.qspdmc.mixin;

import io.github.qspdmc.ServerEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Enchantment.class)
public class EnchantmentMixin {
	//@ModifyVariable(method = "getName", at = @At(value = "INVOKE", target = "Lnet/minecraft/text/MutableText;formatted(Lnet/minecraft/util/Formatting;)Lnet/minecraft/text/MutableText;"))
	@ModifyVariable (method = "getName", index = 2, at = @At (value = "INVOKE_ASSIGN", target = "Lnet/minecraft/enchantment/Enchantment;getTranslationKey()Ljava/lang/String;", shift = At.Shift.BY, by = 2))
	private MutableText translate(MutableText mutableText) {
		if (this instanceof ServerEnchantment) {
			MutableText mutableText2 = ((ServerEnchantment) this).name().copy();

			if(mutableText2.getStyle().getColor() == null)
				mutableText2.formatted(Formatting.GRAY);

			return mutableText2;
		}

		return mutableText;
	}
}
