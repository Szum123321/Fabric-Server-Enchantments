package io.github.qspdmc.mixin;

import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import java.util.function.Supplier;

@Mixin (Registry.class)
public interface RegistryAccess {
	@Invoker
	static <T> Registry<T> callCreate(String id, Supplier<T> supplier) { throw new UnsupportedOperationException(); }
}
