package net.minecraft.world.storage;

import java.io.File;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.storage.ISaveHandler;

public interface ISaveFormat {
   ISaveHandler func_75804_a(String var1, boolean var2);

   boolean func_75801_b(String var1);

   boolean func_75805_a(String var1, IProgressUpdate var2);

   File func_186352_b(String var1, String var2);
}
