package net.minecraft.tileentity;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBanner;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.BannerPattern;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IWorldNameable;

public class TileEntityBanner extends TileEntity implements IWorldNameable {
   private String field_190617_a;
   private EnumDyeColor field_175120_a = EnumDyeColor.BLACK;
   private NBTTagList field_175118_f;
   private boolean field_175119_g;
   private List<BannerPattern> field_175122_h;
   private List<EnumDyeColor> field_175123_i;
   private String field_175121_j;

   public void func_175112_a(ItemStack p_175112_1_, boolean p_175112_2_) {
      this.field_175118_f = null;
      NBTTagCompound nbttagcompound = p_175112_1_.func_179543_a("BlockEntityTag");
      if(nbttagcompound != null && nbttagcompound.func_150297_b("Patterns", 9)) {
         this.field_175118_f = nbttagcompound.func_150295_c("Patterns", 10).func_74737_b();
      }

      this.field_175120_a = p_175112_2_?func_190616_d(p_175112_1_):ItemBanner.func_179225_h(p_175112_1_);
      this.field_175122_h = null;
      this.field_175123_i = null;
      this.field_175121_j = "";
      this.field_175119_g = true;
      this.field_190617_a = p_175112_1_.func_82837_s()?p_175112_1_.func_82833_r():null;
   }

   public String func_70005_c_() {
      return this.func_145818_k_()?this.field_190617_a:"banner";
   }

   public boolean func_145818_k_() {
      return this.field_190617_a != null && !this.field_190617_a.isEmpty();
   }

   public ITextComponent func_145748_c_() {
      return (ITextComponent)(this.func_145818_k_()?new TextComponentString(this.func_70005_c_()):new TextComponentTranslation(this.func_70005_c_(), new Object[0]));
   }

   public NBTTagCompound func_189515_b(NBTTagCompound p_189515_1_) {
      super.func_189515_b(p_189515_1_);
      p_189515_1_.func_74768_a("Base", this.field_175120_a.func_176767_b());
      if(this.field_175118_f != null) {
         p_189515_1_.func_74782_a("Patterns", this.field_175118_f);
      }

      if(this.func_145818_k_()) {
         p_189515_1_.func_74778_a("CustomName", this.field_190617_a);
      }

      return p_189515_1_;
   }

   public void func_145839_a(NBTTagCompound p_145839_1_) {
      super.func_145839_a(p_145839_1_);
      if(p_145839_1_.func_150297_b("CustomName", 8)) {
         this.field_190617_a = p_145839_1_.func_74779_i("CustomName");
      }

      this.field_175120_a = EnumDyeColor.func_176766_a(p_145839_1_.func_74762_e("Base"));
      this.field_175118_f = p_145839_1_.func_150295_c("Patterns", 10);
      this.field_175122_h = null;
      this.field_175123_i = null;
      this.field_175121_j = null;
      this.field_175119_g = true;
   }

   @Nullable
   public SPacketUpdateTileEntity func_189518_D_() {
      return new SPacketUpdateTileEntity(this.field_174879_c, 6, this.func_189517_E_());
   }

   public NBTTagCompound func_189517_E_() {
      return this.func_189515_b(new NBTTagCompound());
   }

   public static int func_175113_c(ItemStack p_175113_0_) {
      NBTTagCompound nbttagcompound = p_175113_0_.func_179543_a("BlockEntityTag");
      return nbttagcompound != null && nbttagcompound.func_74764_b("Patterns")?nbttagcompound.func_150295_c("Patterns", 10).func_74745_c():0;
   }

   public static void func_175117_e(ItemStack p_175117_0_) {
      NBTTagCompound nbttagcompound = p_175117_0_.func_179543_a("BlockEntityTag");
      if(nbttagcompound != null && nbttagcompound.func_150297_b("Patterns", 9)) {
         NBTTagList nbttaglist = nbttagcompound.func_150295_c("Patterns", 10);
         if(!nbttaglist.func_82582_d()) {
            nbttaglist.func_74744_a(nbttaglist.func_74745_c() - 1);
            if(nbttaglist.func_82582_d()) {
               p_175117_0_.func_77978_p().func_82580_o("BlockEntityTag");
               if(p_175117_0_.func_77978_p().func_82582_d()) {
                  p_175117_0_.func_77982_d((NBTTagCompound)null);
               }
            }

         }
      }
   }

   public ItemStack func_190615_l() {
      ItemStack itemstack = ItemBanner.func_190910_a(this.field_175120_a, this.field_175118_f);
      if(this.func_145818_k_()) {
         itemstack.func_151001_c(this.func_70005_c_());
      }

      return itemstack;
   }

   public static EnumDyeColor func_190616_d(ItemStack p_190616_0_) {
      NBTTagCompound nbttagcompound = p_190616_0_.func_179543_a("BlockEntityTag");
      return nbttagcompound != null && nbttagcompound.func_74764_b("Base")?EnumDyeColor.func_176766_a(nbttagcompound.func_74762_e("Base")):EnumDyeColor.BLACK;
   }
}
