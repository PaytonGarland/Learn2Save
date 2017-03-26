package net.minecraft.entity.passive;

import javax.annotation.Nullable;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.AbstractChestHorse;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMule;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class EntityDonkey extends AbstractChestHorse {
   public EntityDonkey(World p_i47298_1_) {
      super(p_i47298_1_);
   }

   public static void func_190699_b(DataFixer p_190699_0_) {
      AbstractChestHorse.func_190694_b(p_190699_0_, EntityDonkey.class);
   }

   @Nullable
   protected ResourceLocation func_184647_J() {
      return LootTableList.field_191190_H;
   }

   protected SoundEvent func_184639_G() {
      super.func_184639_G();
      return SoundEvents.field_187580_av;
   }

   protected SoundEvent func_184615_bR() {
      super.func_184615_bR();
      return SoundEvents.field_187586_ay;
   }

   protected SoundEvent func_184601_bQ() {
      super.func_184601_bQ();
      return SoundEvents.field_187588_az;
   }

   public boolean func_70878_b(EntityAnimal p_70878_1_) {
      return p_70878_1_ == this?false:(!(p_70878_1_ instanceof EntityDonkey) && !(p_70878_1_ instanceof EntityHorse)?false:this.func_110200_cJ() && ((AbstractHorse)p_70878_1_).func_110200_cJ());
   }

   public EntityAgeable func_90011_a(EntityAgeable p_90011_1_) {
      AbstractHorse abstracthorse = (AbstractHorse)(p_90011_1_ instanceof EntityHorse?new EntityMule(this.field_70170_p):new EntityDonkey(this.field_70170_p));
      this.func_190681_a(p_90011_1_, abstracthorse);
      return abstracthorse;
   }
}
