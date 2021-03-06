package io.github.qspdmc.mixin;

import io.github.qspdmc.ServerEnchantment;
import net.fabricmc.fabric.impl.registry.sync.RegistrySyncManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = RegistrySyncManager.class, remap = false)
public class RegistrySyncManagerMixin {
	@Redirect(method = "toTag", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/registry/Registry;getId(Ljava/lang/Object;)Lnet/minecraft/util/Identifier;"))
	private static <T> Identifier getId(Registry<T> registry, T entry) {
		if (registry == Registry.ENCHANTMENT && entry instanceof ServerEnchantment) {
			return null;
		}
		return registry.getId(entry);
	}
}
