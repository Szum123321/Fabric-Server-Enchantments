package io.github.qspdmc.test;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.registry.Registry;

import static net.minecraft.util.registry.Registry.ENCHANTMENT;

public class Main implements ModInitializer {
	@Override
	public void onInitialize() {
		Registry.register(ENCHANTMENT, "fse:test", new TestEnchant());
	}
}
