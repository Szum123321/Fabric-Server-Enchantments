package io.github.qspdmc.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static net.fabricmc.fabric.api.util.NbtType.STRING;

public class LoreUtil {
	public static void addLore(ItemStack stack, Enchantment enchantment, int level) {
		CompoundTag display = stack.getOrCreateSubTag("display");

		ListTag lore = display.getList("Lore", 8);

		int oldLevel = EnchantmentHelper.getLevel(enchantment, stack);
		if (oldLevel != 0) {
			String text = text(enchantment, oldLevel);
			for (int i = lore.size() - 1; i >= 0; i--) {
				Tag line = lore.get(i);
				if (line.getType() == STRING) {
					if (line.asString().equals(text))
						lore.remove(i);
				}
			}
		}

		lore.add(0, StringTag.of(text(enchantment, level)));
		display.put("Lore", lore);
	}

	private static String text(Enchantment enchantment, int level) {
		return Text.Serializer.toJson(enchantment.getName(level));
	}
}
