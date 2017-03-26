package net.minecraft.world.storage;

import java.io.File;
import java.io.FileInputStream;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.SaveHandler;
import net.minecraft.world.storage.WorldInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SaveFormatOld implements ISaveFormat {
   private static final Logger field_151479_b = LogManager.getLogger();
   protected final File field_75808_a;
   protected final DataFixer field_186354_b;

   public SaveFormatOld(File p_i46647_1_, DataFixer p_i46647_2_) {
      this.field_186354_b = p_i46647_2_;
      if(!p_i46647_1_.exists()) {
         p_i46647_1_.mkdirs();
      }

      this.field_75808_a = p_i46647_1_;
   }

   @Nullable
   public WorldInfo func_75803_c(String p_75803_1_) {
      File file1 = new File(this.field_75808_a, p_75803_1_);
      if(!file1.exists()) {
         return null;
      } else {
         File file2 = new File(file1, "level.dat");
         if(file2.exists()) {
            WorldInfo worldinfo = func_186353_a(file2, this.field_186354_b);
            if(worldinfo != null) {
               return worldinfo;
            }
         }

         file2 = new File(file1, "level.dat_old");
         return file2.exists()?func_186353_a(file2, this.field_186354_b):null;
      }
   }

   @Nullable
   public static WorldInfo func_186353_a(File p_186353_0_, DataFixer p_186353_1_) {
      try {
         NBTTagCompound nbttagcompound = CompressedStreamTools.func_74796_a(new FileInputStream(p_186353_0_));
         NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("Data");
         return new WorldInfo(p_186353_1_.func_188257_a(FixTypes.LEVEL, nbttagcompound1));
      } catch (Exception exception) {
         field_151479_b.error("Exception reading {}", new Object[]{p_186353_0_, exception});
         return null;
      }
   }

   public ISaveHandler func_75804_a(String p_75804_1_, boolean p_75804_2_) {
      return new SaveHandler(this.field_75808_a, p_75804_1_, p_75804_2_, this.field_186354_b);
   }

   public boolean func_75801_b(String p_75801_1_) {
      return false;
   }

   public boolean func_75805_a(String p_75805_1_, IProgressUpdate p_75805_2_) {
      return false;
   }

   public File func_186352_b(String p_186352_1_, String p_186352_2_) {
      return new File(new File(this.field_75808_a, p_186352_1_), p_186352_2_);
   }
}
