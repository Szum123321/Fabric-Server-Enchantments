package io.github.qspdmc;

import io.github.qspdmc.enchants.TestEnchant;
import net.fabricmc.api.ModInitializer;

import static net.minecraft.util.registry.Registry.ENCHANTMENT;
import static net.minecraft.util.registry.Registry.register;

public class ServerEnchants implements ModInitializer {
	public static final TestEnchant TEST_ENCHANT = new TestEnchant();

	@Override
	public void onInitialize() {
		register(ENCHANTMENT, "fse:test", TEST_ENCHANT);
	}
}
