package io.github.qspdmc.fse.impl.mixin;

import io.github.qspdmc.fse.api.ServerEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Enchantment.class)
public class EnchantmentMixin {
	@ModifyVariable(method = "getName", at = @At(value = "INVOKE", target = "Lnet/minecraft/text/Text;formatted(Lnet/minecraft/util/Formatting;)Lnet/minecraft/text/Text;"))
	private Text translate(Text translatable) {
		if (this instanceof ServerEnchantment) {
			return ((ServerEnchantment) this).name();
		}
		return translatable;
	}
}
