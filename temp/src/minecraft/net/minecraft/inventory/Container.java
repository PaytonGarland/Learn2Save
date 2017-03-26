package net.minecraft.inventory;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;

public abstract class Container {
   public NonNullList<ItemStack> field_75153_a = NonNullList.<ItemStack>func_191196_a();
   public List<Slot> field_75151_b = Lists.<Slot>newArrayList();
   public int field_75152_c;
   private short field_75150_e;
   private int field_94535_f = -1;
   private int field_94536_g;
   private final Set<Slot> field_94537_h = Sets.<Slot>newHashSet();
   protected List<IContainerListener> field_75149_d = Lists.<IContainerListener>newArrayList();
   private final Set<EntityPlayer> field_75148_f = Sets.<EntityPlayer>newHashSet();

   protected Slot func_75146_a(Slot p_75146_1_) {
      p_75146_1_.field_75222_d = this.field_75151_b.size();
      this.field_75151_b.add(p_75146_1_);
      this.field_75153_a.add(ItemStack.field_190927_a);
      return p_75146_1_;
   }

   public void func_75132_a(IContainerListener p_75132_1_) {
      if(this.field_75149_d.contains(p_75132_1_)) {
         throw new IllegalArgumentException("Listener already listening");
      } else {
         this.field_75149_d.add(p_75132_1_);
         p_75132_1_.func_71110_a(this, this.func_75138_a());
         this.func_75142_b();
      }
   }

   public void func_82847_b(IContainerListener p_82847_1_) {
      this.field_75149_d.remove(p_82847_1_);
   }

   public NonNullList<ItemStack> func_75138_a() {
      NonNullList<ItemStack> nonnulllist = NonNullList.<ItemStack>func_191196_a();

      for(int i = 0; i < this.field_75151_b.size(); ++i) {
         nonnulllist.add(((Slot)this.field_75151_b.get(i)).func_75211_c());
      }

      return nonnulllist;
   }

   public void func_75142_b() {
      for(int i = 0; i < this.field_75151_b.size(); ++i) {
         ItemStack itemstack = ((Slot)this.field_75151_b.get(i)).func_75211_c();
         ItemStack itemstack1 = (ItemStack)this.field_75153_a.get(i);
         if(!ItemStack.func_77989_b(itemstack1, itemstack)) {
            itemstack1 = itemstack.func_190926_b()?ItemStack.field_190927_a:itemstack.func_77946_l();
            this.field_75153_a.set(i, itemstack1);

            for(int j = 0; j < this.field_75149_d.size(); ++j) {
               ((IContainerListener)this.field_75149_d.get(j)).func_71111_a(this, i, itemstack1);
            }
         }
      }

   }

   public boolean func_75140_a(EntityPlayer p_75140_1_, int p_75140_2_) {
      return false;
   }

   @Nullable
   public Slot func_75147_a(IInventory p_75147_1_, int p_75147_2_) {
      for(int i = 0; i < this.field_75151_b.size(); ++i) {
         Slot slot = (Slot)this.field_75151_b.get(i);
         if(slot.func_75217_a(p_75147_1_, p_75147_2_)) {
            return slot;
         }
      }

      return null;
   }

   public Slot func_75139_a(int p_75139_1_) {
      return (Slot)this.field_75151_b.get(p_75139_1_);
   }

   public ItemStack func_82846_b(EntityPlayer p_82846_1_, int p_82846_2_) {
      Slot slot = (Slot)this.field_75151_b.get(p_82846_2_);
      return slot != null?slot.func_75211_c():ItemStack.field_190927_a;
   }

   public ItemStack func_184996_a(int p_184996_1_, int p_184996_2_, ClickType p_184996_3_, EntityPlayer p_184996_4_) {
      ItemStack itemstack = ItemStack.field_190927_a;
      InventoryPlayer inventoryplayer = p_184996_4_.field_71071_by;
      if(p_184996_3_ == ClickType.QUICK_CRAFT) {
         int i = this.field_94536_g;
         this.field_94536_g = func_94532_c(p_184996_2_);
         if((i != 1 || this.field_94536_g != 2) && i != this.field_94536_g) {
            this.func_94533_d();
         } else if(inventoryplayer.func_70445_o().func_190926_b()) {
            this.func_94533_d();
         } else if(this.field_94536_g == 0) {
            this.field_94535_f = func_94529_b(p_184996_2_);
            if(func_180610_a(this.field_94535_f, p_184996_4_)) {
               this.field_94536_g = 1;
               this.field_94537_h.clear();
            } else {
               this.func_94533_d();
            }
         } else if(this.field_94536_g == 1) {
            Slot slot = (Slot)this.field_75151_b.get(p_184996_1_);
            ItemStack itemstack1 = inventoryplayer.func_70445_o();
            if(slot != null && func_94527_a(slot, itemstack1, true) && slot.func_75214_a(itemstack1) && (this.field_94535_f == 2 || itemstack1.func_190916_E() > this.field_94537_h.size()) && this.func_94531_b(slot)) {
               this.field_94537_h.add(slot);
            }
         } else if(this.field_94536_g == 2) {
            if(!this.field_94537_h.isEmpty()) {
               ItemStack itemstack5 = inventoryplayer.func_70445_o().func_77946_l();
               int l = inventoryplayer.func_70445_o().func_190916_E();

               for(Slot slot1 : this.field_94537_h) {
                  ItemStack itemstack2 = inventoryplayer.func_70445_o();
                  if(slot1 != null && func_94527_a(slot1, itemstack2, true) && slot1.func_75214_a(itemstack2) && (this.field_94535_f == 2 || itemstack2.func_190916_E() >= this.field_94537_h.size()) && this.func_94531_b(slot1)) {
                     ItemStack itemstack3 = itemstack5.func_77946_l();
                     int j = slot1.func_75216_d()?slot1.func_75211_c().func_190916_E():0;
                     func_94525_a(this.field_94537_h, this.field_94535_f, itemstack3, j);
                     int k = Math.min(itemstack3.func_77976_d(), slot1.func_178170_b(itemstack3));
                     if(itemstack3.func_190916_E() > k) {
                        itemstack3.func_190920_e(k);
                     }

                     l -= itemstack3.func_190916_E() - j;
                     slot1.func_75215_d(itemstack3);
                  }
               }

               itemstack5.func_190920_e(l);
               inventoryplayer.func_70437_b(itemstack5);
            }

            this.func_94533_d();
         } else {
            this.func_94533_d();
         }
      } else if(this.field_94536_g != 0) {
         this.func_94533_d();
      } else if((p_184996_3_ == ClickType.PICKUP || p_184996_3_ == ClickType.QUICK_MOVE) && (p_184996_2_ == 0 || p_184996_2_ == 1)) {
         if(p_184996_1_ == -999) {
            if(!inventoryplayer.func_70445_o().func_190926_b()) {
               if(p_184996_2_ == 0) {
                  p_184996_4_.func_71019_a(inventoryplayer.func_70445_o(), true);
                  inventoryplayer.func_70437_b(ItemStack.field_190927_a);
               }

               if(p_184996_2_ == 1) {
                  p_184996_4_.func_71019_a(inventoryplayer.func_70445_o().func_77979_a(1), true);
               }
            }
         } else if(p_184996_3_ == ClickType.QUICK_MOVE) {
            if(p_184996_1_ < 0) {
               return ItemStack.field_190927_a;
            }

            Slot slot6 = (Slot)this.field_75151_b.get(p_184996_1_);
            if(slot6 != null && slot6.func_82869_a(p_184996_4_)) {
               ItemStack itemstack10 = this.func_82846_b(p_184996_4_, p_184996_1_);
               if(!itemstack10.func_190926_b()) {
                  Item item = itemstack10.func_77973_b();
                  itemstack = itemstack10.func_77946_l();
                  if(slot6.func_75211_c().func_77973_b() == item) {
                     this.func_75133_b(p_184996_1_, p_184996_2_, true, p_184996_4_);
                  }
               }
            }
         } else {
            if(p_184996_1_ < 0) {
               return ItemStack.field_190927_a;
            }

            Slot slot7 = (Slot)this.field_75151_b.get(p_184996_1_);
            if(slot7 != null) {
               ItemStack itemstack11 = slot7.func_75211_c();
               ItemStack itemstack13 = inventoryplayer.func_70445_o();
               if(!itemstack11.func_190926_b()) {
                  itemstack = itemstack11.func_77946_l();
               }

               if(itemstack11.func_190926_b()) {
                  if(!itemstack13.func_190926_b() && slot7.func_75214_a(itemstack13)) {
                     int l2 = p_184996_2_ == 0?itemstack13.func_190916_E():1;
                     if(l2 > slot7.func_178170_b(itemstack13)) {
                        l2 = slot7.func_178170_b(itemstack13);
                     }

                     slot7.func_75215_d(itemstack13.func_77979_a(l2));
                  }
               } else if(slot7.func_82869_a(p_184996_4_)) {
                  if(itemstack13.func_190926_b()) {
                     if(itemstack11.func_190926_b()) {
                        slot7.func_75215_d(ItemStack.field_190927_a);
                        inventoryplayer.func_70437_b(ItemStack.field_190927_a);
                     } else {
                        int k2 = p_184996_2_ == 0?itemstack11.func_190916_E():(itemstack11.func_190916_E() + 1) / 2;
                        inventoryplayer.func_70437_b(slot7.func_75209_a(k2));
                        if(itemstack11.func_190926_b()) {
                           slot7.func_75215_d(ItemStack.field_190927_a);
                        }

                        slot7.func_190901_a(p_184996_4_, inventoryplayer.func_70445_o());
                     }
                  } else if(slot7.func_75214_a(itemstack13)) {
                     if(itemstack11.func_77973_b() == itemstack13.func_77973_b() && itemstack11.func_77960_j() == itemstack13.func_77960_j() && ItemStack.func_77970_a(itemstack11, itemstack13)) {
                        int j2 = p_184996_2_ == 0?itemstack13.func_190916_E():1;
                        if(j2 > slot7.func_178170_b(itemstack13) - itemstack11.func_190916_E()) {
                           j2 = slot7.func_178170_b(itemstack13) - itemstack11.func_190916_E();
                        }

                        if(j2 > itemstack13.func_77976_d() - itemstack11.func_190916_E()) {
                           j2 = itemstack13.func_77976_d() - itemstack11.func_190916_E();
                        }

                        itemstack13.func_190918_g(j2);
                        itemstack11.func_190917_f(j2);
                     } else if(itemstack13.func_190916_E() <= slot7.func_178170_b(itemstack13)) {
                        slot7.func_75215_d(itemstack13);
                        inventoryplayer.func_70437_b(itemstack11);
                     }
                  } else if(itemstack11.func_77973_b() == itemstack13.func_77973_b() && itemstack13.func_77976_d() > 1 && (!itemstack11.func_77981_g() || itemstack11.func_77960_j() == itemstack13.func_77960_j()) && ItemStack.func_77970_a(itemstack11, itemstack13) && !itemstack11.func_190926_b()) {
                     int i2 = itemstack11.func_190916_E();
                     if(i2 + itemstack13.func_190916_E() <= itemstack13.func_77976_d()) {
                        itemstack13.func_190917_f(i2);
                        itemstack11 = slot7.func_75209_a(i2);
                        if(itemstack11.func_190926_b()) {
                           slot7.func_75215_d(ItemStack.field_190927_a);
                        }

                        slot7.func_190901_a(p_184996_4_, inventoryplayer.func_70445_o());
                     }
                  }
               }

               slot7.func_75218_e();
            }
         }
      } else if(p_184996_3_ == ClickType.SWAP && p_184996_2_ >= 0 && p_184996_2_ < 9) {
         Slot slot5 = (Slot)this.field_75151_b.get(p_184996_1_);
         ItemStack itemstack9 = inventoryplayer.func_70301_a(p_184996_2_);
         ItemStack itemstack12 = slot5.func_75211_c();
         if(!itemstack9.func_190926_b() || !itemstack12.func_190926_b()) {
            if(itemstack9.func_190926_b()) {
               if(slot5.func_82869_a(p_184996_4_)) {
                  inventoryplayer.func_70299_a(p_184996_2_, itemstack12);
                  slot5.func_190900_b(itemstack12.func_190916_E());
                  slot5.func_75215_d(ItemStack.field_190927_a);
                  slot5.func_190901_a(p_184996_4_, itemstack12);
               }
            } else if(itemstack12.func_190926_b()) {
               if(slot5.func_75214_a(itemstack9)) {
                  int k1 = slot5.func_178170_b(itemstack9);
                  if(itemstack9.func_190916_E() > k1) {
                     slot5.func_75215_d(itemstack9.func_77979_a(k1));
                  } else {
                     slot5.func_75215_d(itemstack9);
                     inventoryplayer.func_70299_a(p_184996_2_, ItemStack.field_190927_a);
                  }
               }
            } else if(slot5.func_82869_a(p_184996_4_) && slot5.func_75214_a(itemstack9)) {
               int l1 = slot5.func_178170_b(itemstack9);
               if(itemstack9.func_190916_E() > l1) {
                  slot5.func_75215_d(itemstack9.func_77979_a(l1));
                  slot5.func_190901_a(p_184996_4_, itemstack12);
                  if(!inventoryplayer.func_70441_a(itemstack12)) {
                     p_184996_4_.func_71019_a(itemstack12, true);
                  }
               } else {
                  slot5.func_75215_d(itemstack9);
                  inventoryplayer.func_70299_a(p_184996_2_, itemstack12);
                  slot5.func_190901_a(p_184996_4_, itemstack12);
               }
            }
         }
      } else if(p_184996_3_ == ClickType.CLONE && p_184996_4_.field_71075_bZ.field_75098_d && inventoryplayer.func_70445_o().func_190926_b() && p_184996_1_ >= 0) {
         Slot slot4 = (Slot)this.field_75151_b.get(p_184996_1_);
         if(slot4 != null && slot4.func_75216_d()) {
            ItemStack itemstack8 = slot4.func_75211_c().func_77946_l();
            itemstack8.func_190920_e(itemstack8.func_77976_d());
            inventoryplayer.func_70437_b(itemstack8);
         }
      } else if(p_184996_3_ == ClickType.THROW && inventoryplayer.func_70445_o().func_190926_b() && p_184996_1_ >= 0) {
         Slot slot3 = (Slot)this.field_75151_b.get(p_184996_1_);
         if(slot3 != null && slot3.func_75216_d() && slot3.func_82869_a(p_184996_4_)) {
            ItemStack itemstack7 = slot3.func_75209_a(p_184996_2_ == 0?1:slot3.func_75211_c().func_190916_E());
            slot3.func_190901_a(p_184996_4_, itemstack7);
            p_184996_4_.func_71019_a(itemstack7, true);
         }
      } else if(p_184996_3_ == ClickType.PICKUP_ALL && p_184996_1_ >= 0) {
         Slot slot2 = (Slot)this.field_75151_b.get(p_184996_1_);
         ItemStack itemstack6 = inventoryplayer.func_70445_o();
         if(!itemstack6.func_190926_b() && (slot2 == null || !slot2.func_75216_d() || !slot2.func_82869_a(p_184996_4_))) {
            int i1 = p_184996_2_ == 0?0:this.field_75151_b.size() - 1;
            int j1 = p_184996_2_ == 0?1:-1;

            for(int i3 = 0; i3 < 2; ++i3) {
               for(int j3 = i1; j3 >= 0 && j3 < this.field_75151_b.size() && itemstack6.func_190916_E() < itemstack6.func_77976_d(); j3 += j1) {
                  Slot slot8 = (Slot)this.field_75151_b.get(j3);
                  if(slot8.func_75216_d() && func_94527_a(slot8, itemstack6, true) && slot8.func_82869_a(p_184996_4_) && this.func_94530_a(itemstack6, slot8)) {
                     ItemStack itemstack14 = slot8.func_75211_c();
                     if(i3 != 0 || itemstack14.func_190916_E() != itemstack14.func_77976_d()) {
                        int k3 = Math.min(itemstack6.func_77976_d() - itemstack6.func_190916_E(), itemstack14.func_190916_E());
                        ItemStack itemstack4 = slot8.func_75209_a(k3);
                        itemstack6.func_190917_f(k3);
                        if(itemstack4.func_190926_b()) {
                           slot8.func_75215_d(ItemStack.field_190927_a);
                        }

                        slot8.func_190901_a(p_184996_4_, itemstack4);
                     }
                  }
               }
            }
         }

         this.func_75142_b();
      }

      return itemstack;
   }

   public boolean func_94530_a(ItemStack p_94530_1_, Slot p_94530_2_) {
      return true;
   }

   protected void func_75133_b(int p_75133_1_, int p_75133_2_, boolean p_75133_3_, EntityPlayer p_75133_4_) {
      this.func_184996_a(p_75133_1_, p_75133_2_, ClickType.QUICK_MOVE, p_75133_4_);
   }

   public void func_75134_a(EntityPlayer p_75134_1_) {
      InventoryPlayer inventoryplayer = p_75134_1_.field_71071_by;
      if(!inventoryplayer.func_70445_o().func_190926_b()) {
         p_75134_1_.func_71019_a(inventoryplayer.func_70445_o(), false);
         inventoryplayer.func_70437_b(ItemStack.field_190927_a);
      }

   }

   public void func_75130_a(IInventory p_75130_1_) {
      this.func_75142_b();
   }

   public void func_75141_a(int p_75141_1_, ItemStack p_75141_2_) {
      this.func_75139_a(p_75141_1_).func_75215_d(p_75141_2_);
   }

   public void func_190896_a(List<ItemStack> p_190896_1_) {
      for(int i = 0; i < p_190896_1_.size(); ++i) {
         this.func_75139_a(i).func_75215_d((ItemStack)p_190896_1_.get(i));
      }

   }

   public void func_75137_b(int p_75137_1_, int p_75137_2_) {
   }

   public short func_75136_a(InventoryPlayer p_75136_1_) {
      ++this.field_75150_e;
      return this.field_75150_e;
   }

   public boolean func_75129_b(EntityPlayer p_75129_1_) {
      return !this.field_75148_f.contains(p_75129_1_);
   }

   public void func_75128_a(EntityPlayer p_75128_1_, boolean p_75128_2_) {
      if(p_75128_2_) {
         this.field_75148_f.remove(p_75128_1_);
      } else {
         this.field_75148_f.add(p_75128_1_);
      }

   }

   public abstract boolean func_75145_c(EntityPlayer var1);

   protected boolean func_75135_a(ItemStack p_75135_1_, int p_75135_2_, int p_75135_3_, boolean p_75135_4_) {
      boolean flag = false;
      int i = p_75135_2_;
      if(p_75135_4_) {
         i = p_75135_3_ - 1;
      }

      if(p_75135_1_.func_77985_e()) {
         while(!p_75135_1_.func_190926_b()) {
            if(p_75135_4_) {
               if(i < p_75135_2_) {
                  break;
               }
            } else if(i >= p_75135_3_) {
               break;
            }

            Slot slot = (Slot)this.field_75151_b.get(i);
            ItemStack itemstack = slot.func_75211_c();
            if(!itemstack.func_190926_b() && itemstack.func_77973_b() == p_75135_1_.func_77973_b() && (!p_75135_1_.func_77981_g() || p_75135_1_.func_77960_j() == itemstack.func_77960_j()) && ItemStack.func_77970_a(p_75135_1_, itemstack)) {
               int j = itemstack.func_190916_E() + p_75135_1_.func_190916_E();
               if(j <= p_75135_1_.func_77976_d()) {
                  p_75135_1_.func_190920_e(0);
                  itemstack.func_190920_e(j);
                  slot.func_75218_e();
                  flag = true;
               } else if(itemstack.func_190916_E() < p_75135_1_.func_77976_d()) {
                  p_75135_1_.func_190918_g(p_75135_1_.func_77976_d() - itemstack.func_190916_E());
                  itemstack.func_190920_e(p_75135_1_.func_77976_d());
                  slot.func_75218_e();
                  flag = true;
               }
            }

            if(p_75135_4_) {
               --i;
            } else {
               ++i;
            }
         }
      }

      if(!p_75135_1_.func_190926_b()) {
         if(p_75135_4_) {
            i = p_75135_3_ - 1;
         } else {
            i = p_75135_2_;
         }

         while(true) {
            if(p_75135_4_) {
               if(i < p_75135_2_) {
                  break;
               }
            } else if(i >= p_75135_3_) {
               break;
            }

            Slot slot1 = (Slot)this.field_75151_b.get(i);
            ItemStack itemstack1 = slot1.func_75211_c();
            if(itemstack1.func_190926_b() && slot1.func_75214_a(p_75135_1_)) {
               if(p_75135_1_.func_190916_E() > slot1.func_75219_a()) {
                  slot1.func_75215_d(p_75135_1_.func_77979_a(slot1.func_75219_a()));
               } else {
                  slot1.func_75215_d(p_75135_1_.func_77979_a(p_75135_1_.func_190916_E()));
               }

               slot1.func_75218_e();
               flag = true;
               break;
            }

            if(p_75135_4_) {
               --i;
            } else {
               ++i;
            }
         }
      }

      return flag;
   }

   public static int func_94529_b(int p_94529_0_) {
      return p_94529_0_ >> 2 & 3;
   }

   public static int func_94532_c(int p_94532_0_) {
      return p_94532_0_ & 3;
   }

   public static int func_94534_d(int p_94534_0_, int p_94534_1_) {
      return p_94534_0_ & 3 | (p_94534_1_ & 3) << 2;
   }

   public static boolean func_180610_a(int p_180610_0_, EntityPlayer p_180610_1_) {
      return p_180610_0_ == 0?true:(p_180610_0_ == 1?true:p_180610_0_ == 2 && p_180610_1_.field_71075_bZ.field_75098_d);
   }

   protected void func_94533_d() {
      this.field_94536_g = 0;
      this.field_94537_h.clear();
   }

   public static boolean func_94527_a(@Nullable Slot p_94527_0_, ItemStack p_94527_1_, boolean p_94527_2_) {
      boolean flag = p_94527_0_ == null || !p_94527_0_.func_75216_d();
      return !flag && p_94527_1_.func_77969_a(p_94527_0_.func_75211_c()) && ItemStack.func_77970_a(p_94527_0_.func_75211_c(), p_94527_1_)?p_94527_0_.func_75211_c().func_190916_E() + (p_94527_2_?0:p_94527_1_.func_190916_E()) <= p_94527_1_.func_77976_d():flag;
   }

   public static void func_94525_a(Set<Slot> p_94525_0_, int p_94525_1_, ItemStack p_94525_2_, int p_94525_3_) {
      switch(p_94525_1_) {
      case 0:
         p_94525_2_.func_190920_e(MathHelper.func_76141_d((float)p_94525_2_.func_190916_E() / (float)p_94525_0_.size()));
         break;
      case 1:
         p_94525_2_.func_190920_e(1);
         break;
      case 2:
         p_94525_2_.func_190920_e(p_94525_2_.func_77973_b().func_77639_j());
      }

      p_94525_2_.func_190917_f(p_94525_3_);
   }

   public boolean func_94531_b(Slot p_94531_1_) {
      return true;
   }

   public static int func_178144_a(@Nullable TileEntity p_178144_0_) {
      return p_178144_0_ instanceof IInventory?func_94526_b((IInventory)p_178144_0_):0;
   }

   public static int func_94526_b(@Nullable IInventory p_94526_0_) {
      if(p_94526_0_ == null) {
         return 0;
      } else {
         int i = 0;
         float f = 0.0F;

         for(int j = 0; j < p_94526_0_.func_70302_i_(); ++j) {
            ItemStack itemstack = p_94526_0_.func_70301_a(j);
            if(!itemstack.func_190926_b()) {
               f += (float)itemstack.func_190916_E() / (float)Math.min(p_94526_0_.func_70297_j_(), itemstack.func_77976_d());
               ++i;
            }
         }

         f = f / (float)p_94526_0_.func_70302_i_();
         return MathHelper.func_76141_d(f * 14.0F) + (i > 0?1:0);
      }
   }
}
