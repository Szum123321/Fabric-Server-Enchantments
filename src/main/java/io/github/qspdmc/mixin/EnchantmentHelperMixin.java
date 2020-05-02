package io.github.qspdmc.mixin;

import io.github.qspdmc.ServerEnchantment;
import io.github.qspdmc.util.LoreUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.Map;

@Mixin (EnchantmentHelper.class)
public class EnchantmentHelperMixin {
	@Inject (method = "set",
	         at = @At ("HEAD"))
	private static void addLore(Map<Enchantment, Integer> enchantments, ItemStack stack, CallbackInfo ci) {
		for (Enchantment enchantment : enchantments.keySet()) {
			if (enchantment instanceof ServerEnchantment) {
				LoreUtil.addLore(stack, enchantment, enchantments.get(enchantment));
			}
		}
	}
}
