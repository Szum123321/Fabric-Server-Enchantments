package io.github.qspdmc.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class LoreUtil {
	public static void addLore(ItemStack stack, Enchantment enchantment, int level) {
		if (!stack.hasTag()) stack.setTag(new CompoundTag());
		CompoundTag tag = stack.getTag();
		if(!tag.contains("display", 10))
			tag.put("display", new CompoundTag());

		CompoundTag display = tag.getCompound("display");
		if (!display.contains("Lore")) display.put("Lore", new ListTag());
		ListTag lore = display.getList("Lore", 8);

		lore.add(0, StringTag.of(Text.Serializer.toJson(enchantment.getName(level).formatted(Formatting.RESET, Formatting.GRAY))));
	}
}
