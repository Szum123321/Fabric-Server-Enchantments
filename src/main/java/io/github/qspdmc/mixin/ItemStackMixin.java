package io.github.qspdmc.mixin;

import io.github.qspdmc.ServerEnchantment;
import io.github.qspdmc.util.LoreUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public class ItemStackMixin {
	@Inject(method = "addEnchantment", at = @At("RETURN"))
	private void addLore(Enchantment enchantment, int level, CallbackInfo ci) {
		if(enchantment instanceof ServerEnchantment)
			LoreUtil.addLore((ItemStack) (Object) (this), enchantment, level);
	}
}
