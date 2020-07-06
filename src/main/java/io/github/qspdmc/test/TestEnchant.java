package io.github.qspdmc.test;

import io.github.qspdmc.ServerEnchantment;
import net.minecraft.enchantment.SilkTouchEnchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class TestEnchant extends SilkTouchEnchantment implements ServerEnchantment {
	protected TestEnchant() {
		super(Rarity.COMMON, EquipmentSlot.OFFHAND);
	}

	@Override
	public int getMaxLevel() {
		return 10;
	}

	@Override
	public Text name() {
		return new LiteralText("hahano").formatted(Formatting.GREEN);
	}
}
