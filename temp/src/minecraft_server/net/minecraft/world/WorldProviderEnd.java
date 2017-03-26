package net.minecraft.world;

import javax.annotation.Nullable;
import net.minecraft.init.Biomes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.end.DragonFightManager;
import net.minecraft.world.gen.ChunkProviderEnd;

public class WorldProviderEnd extends WorldProvider {
   private DragonFightManager field_186064_g;

   public void func_76572_b() {
      this.field_76578_c = new BiomeProviderSingle(Biomes.field_76779_k);
      NBTTagCompound nbttagcompound = this.field_76579_a.func_72912_H().func_186347_a(DimensionType.THE_END);
      this.field_186064_g = this.field_76579_a instanceof WorldServer?new DragonFightManager((WorldServer)this.field_76579_a, nbttagcompound.func_74775_l("DragonFight")):null;
   }

   public IChunkGenerator func_186060_c() {
      return new ChunkProviderEnd(this.field_76579_a, this.field_76579_a.func_72912_H().func_76089_r(), this.field_76579_a.func_72905_C(), this.func_177496_h());
   }

   public float func_76563_a(long p_76563_1_, float p_76563_3_) {
      return 0.0F;
   }

   public boolean func_76567_e() {
      return false;
   }

   public boolean func_76569_d() {
      return false;
   }

   public boolean func_76566_a(int p_76566_1_, int p_76566_2_) {
      return this.field_76579_a.func_184141_c(new BlockPos(p_76566_1_, 0, p_76566_2_)).func_185904_a().func_76230_c();
   }

   public BlockPos func_177496_h() {
      return new BlockPos(100, 50, 0);
   }

   public int func_76557_i() {
      return 50;
   }

   public DimensionType func_186058_p() {
      return DimensionType.THE_END;
   }

   public void func_186057_q() {
      NBTTagCompound nbttagcompound = new NBTTagCompound();
      if(this.field_186064_g != null) {
         nbttagcompound.func_74782_a("DragonFight", this.field_186064_g.func_186088_a());
      }

      this.field_76579_a.func_72912_H().func_186345_a(DimensionType.THE_END, nbttagcompound);
   }

   public void func_186059_r() {
      if(this.field_186064_g != null) {
         this.field_186064_g.func_186105_b();
      }

   }

   @Nullable
   public DragonFightManager func_186063_s() {
      return this.field_186064_g;
   }
}
