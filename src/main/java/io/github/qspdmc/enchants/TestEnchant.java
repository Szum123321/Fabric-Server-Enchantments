package io.github.qspdmc.enchants;

import io.github.qspdmc.ServerEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

public class TestEnchant extends Enchantment implements ServerEnchantment {
	public TestEnchant() {
		super(Weight.COMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
	}

	@Override
	public void onTargetDamaged(LivingEntity user, Entity target, int level) {
		target.setVelocity(user.getRotationVector().multiply(-1));
	}

	@Override
	public String name() {
		return "Test Enchantment";
	}
}
