package net.minecraft.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class ItemEnchantedBook extends Item {
   public boolean func_77616_k(ItemStack p_77616_1_) {
      return false;
   }

   public EnumRarity func_77613_e(ItemStack p_77613_1_) {
      return this.func_92110_g(p_77613_1_).func_82582_d()?super.func_77613_e(p_77613_1_):EnumRarity.UNCOMMON;
   }

   public NBTTagList func_92110_g(ItemStack p_92110_1_) {
      NBTTagCompound nbttagcompound = p_92110_1_.func_77978_p();
      return nbttagcompound != null && nbttagcompound.func_150297_b("StoredEnchantments", 9)?(NBTTagList)nbttagcompound.func_74781_a("StoredEnchantments"):new NBTTagList();
   }

   public void func_92115_a(ItemStack p_92115_1_, EnchantmentData p_92115_2_) {
      NBTTagList nbttaglist = this.func_92110_g(p_92115_1_);
      boolean flag = true;

      for(int i = 0; i < nbttaglist.func_74745_c(); ++i) {
         NBTTagCompound nbttagcompound = nbttaglist.func_150305_b(i);
         if(Enchantment.func_185262_c(nbttagcompound.func_74765_d("id")) == p_92115_2_.field_76302_b) {
            if(nbttagcompound.func_74765_d("lvl") < p_92115_2_.field_76303_c) {
               nbttagcompound.func_74777_a("lvl", (short)p_92115_2_.field_76303_c);
            }

            flag = false;
            break;
         }
      }

      if(flag) {
         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
         nbttagcompound1.func_74777_a("id", (short)Enchantment.func_185258_b(p_92115_2_.field_76302_b));
         nbttagcompound1.func_74777_a("lvl", (short)p_92115_2_.field_76303_c);
         nbttaglist.func_74742_a(nbttagcompound1);
      }

      if(!p_92115_1_.func_77942_o()) {
         p_92115_1_.func_77982_d(new NBTTagCompound());
      }

      p_92115_1_.func_77978_p().func_74782_a("StoredEnchantments", nbttaglist);
   }

   public ItemStack func_92111_a(EnchantmentData p_92111_1_) {
      ItemStack itemstack = new ItemStack(this);
      this.func_92115_a(itemstack, p_92111_1_);
      return itemstack;
   }
}
