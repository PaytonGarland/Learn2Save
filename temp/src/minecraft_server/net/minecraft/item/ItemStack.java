package net.minecraft.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.text.DecimalFormat;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDurability;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.BlockEntityTag;
import net.minecraft.util.datafix.walkers.EntityTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public final class ItemStack {
   public static final ItemStack field_190927_a = new ItemStack((Item)null);
   public static final DecimalFormat field_111284_a = new DecimalFormat("#.##");
   private int field_77994_a;
   private int field_77992_b;
   private final Item field_151002_e;
   private NBTTagCompound field_77990_d;
   private boolean field_190928_g;
   private int field_77991_e;
   private EntityItemFrame field_82843_f;
   private Block field_179552_h;
   private boolean field_179553_i;
   private Block field_179550_j;
   private boolean field_179551_k;

   public ItemStack(Block p_i1876_1_) {
      this((Block)p_i1876_1_, 1);
   }

   public ItemStack(Block p_i1877_1_, int p_i1877_2_) {
      this((Block)p_i1877_1_, p_i1877_2_, 0);
   }

   public ItemStack(Block p_i1878_1_, int p_i1878_2_, int p_i1878_3_) {
      this(Item.func_150898_a(p_i1878_1_), p_i1878_2_, p_i1878_3_);
   }

   public ItemStack(Item p_i1879_1_) {
      this((Item)p_i1879_1_, 1);
   }

   public ItemStack(Item p_i1880_1_, int p_i1880_2_) {
      this((Item)p_i1880_1_, p_i1880_2_, 0);
   }

   public ItemStack(Item p_i1881_1_, int p_i1881_2_, int p_i1881_3_) {
      this.field_151002_e = p_i1881_1_;
      this.field_77991_e = p_i1881_3_;
      this.field_77994_a = p_i1881_2_;
      if(this.field_77991_e < 0) {
         this.field_77991_e = 0;
      }

      this.func_190923_F();
   }

   private void func_190923_F() {
      this.field_190928_g = this.func_190926_b();
   }

   public ItemStack(NBTTagCompound p_i47263_1_) {
      this.field_151002_e = Item.func_111206_d(p_i47263_1_.func_74779_i("id"));
      this.field_77994_a = p_i47263_1_.func_74771_c("Count");
      this.field_77991_e = Math.max(0, p_i47263_1_.func_74765_d("Damage"));
      if(p_i47263_1_.func_150297_b("tag", 10)) {
         this.field_77990_d = p_i47263_1_.func_74775_l("tag");
         if(this.field_151002_e != null) {
            this.field_151002_e.func_179215_a(p_i47263_1_);
         }
      }

      this.func_190923_F();
   }

   public boolean func_190926_b() {
      return this == field_190927_a?true:(this.field_151002_e != null && this.field_151002_e != Item.func_150898_a(Blocks.field_150350_a)?(this.field_77994_a <= 0?true:this.field_77991_e < -32768 || this.field_77991_e > '\uffff'):true);
   }

   public static void func_189868_a(DataFixer p_189868_0_) {
      p_189868_0_.func_188258_a(FixTypes.ITEM_INSTANCE, new BlockEntityTag());
      p_189868_0_.func_188258_a(FixTypes.ITEM_INSTANCE, new EntityTag());
   }

   public ItemStack func_77979_a(int p_77979_1_) {
      int i = Math.min(p_77979_1_, this.field_77994_a);
      ItemStack itemstack = this.func_77946_l();
      itemstack.func_190920_e(i);
      this.func_190918_g(i);
      return itemstack;
   }

   public Item func_77973_b() {
      return this.field_190928_g?Item.func_150898_a(Blocks.field_150350_a):this.field_151002_e;
   }

   public EnumActionResult func_179546_a(EntityPlayer p_179546_1_, World p_179546_2_, BlockPos p_179546_3_, EnumHand p_179546_4_, EnumFacing p_179546_5_, float p_179546_6_, float p_179546_7_, float p_179546_8_) {
      EnumActionResult enumactionresult = this.func_77973_b().func_180614_a(p_179546_1_, p_179546_2_, p_179546_3_, p_179546_4_, p_179546_5_, p_179546_6_, p_179546_7_, p_179546_8_);
      if(enumactionresult == EnumActionResult.SUCCESS) {
         p_179546_1_.func_71029_a(StatList.func_188057_b(this.field_151002_e));
      }

      return enumactionresult;
   }

   public float func_150997_a(IBlockState p_150997_1_) {
      return this.func_77973_b().func_150893_a(this, p_150997_1_);
   }

   public ActionResult<ItemStack> func_77957_a(World p_77957_1_, EntityPlayer p_77957_2_, EnumHand p_77957_3_) {
      return this.func_77973_b().func_77659_a(p_77957_1_, p_77957_2_, p_77957_3_);
   }

   public ItemStack func_77950_b(World p_77950_1_, EntityLivingBase p_77950_2_) {
      return this.func_77973_b().func_77654_b(this, p_77950_1_, p_77950_2_);
   }

   public NBTTagCompound func_77955_b(NBTTagCompound p_77955_1_) {
      ResourceLocation resourcelocation = (ResourceLocation)Item.field_150901_e.func_177774_c(this.field_151002_e);
      p_77955_1_.func_74778_a("id", resourcelocation == null?"minecraft:air":resourcelocation.toString());
      p_77955_1_.func_74774_a("Count", (byte)this.field_77994_a);
      p_77955_1_.func_74777_a("Damage", (short)this.field_77991_e);
      if(this.field_77990_d != null) {
         p_77955_1_.func_74782_a("tag", this.field_77990_d);
      }

      return p_77955_1_;
   }

   public int func_77976_d() {
      return this.func_77973_b().func_77639_j();
   }

   public boolean func_77985_e() {
      return this.func_77976_d() > 1 && (!this.func_77984_f() || !this.func_77951_h());
   }

   public boolean func_77984_f() {
      return this.field_190928_g?false:(this.field_151002_e.func_77612_l() <= 0?false:!this.func_77942_o() || !this.func_77978_p().func_74767_n("Unbreakable"));
   }

   public boolean func_77981_g() {
      return this.func_77973_b().func_77614_k();
   }

   public boolean func_77951_h() {
      return this.func_77984_f() && this.field_77991_e > 0;
   }

   public int func_77952_i() {
      return this.field_77991_e;
   }

   public int func_77960_j() {
      return this.field_77991_e;
   }

   public void func_77964_b(int p_77964_1_) {
      this.field_77991_e = p_77964_1_;
      if(this.field_77991_e < 0) {
         this.field_77991_e = 0;
      }

   }

   public int func_77958_k() {
      return this.func_77973_b().func_77612_l();
   }

   public boolean func_96631_a(int p_96631_1_, Random p_96631_2_) {
      if(!this.func_77984_f()) {
         return false;
      } else {
         if(p_96631_1_ > 0) {
            int i = EnchantmentHelper.func_77506_a(Enchantments.field_185307_s, this);
            int j = 0;

            for(int k = 0; i > 0 && k < p_96631_1_; ++k) {
               if(EnchantmentDurability.func_92097_a(this, i, p_96631_2_)) {
                  ++j;
               }
            }

            p_96631_1_ -= j;
            if(p_96631_1_ <= 0) {
               return false;
            }
         }

         this.field_77991_e += p_96631_1_;
         return this.field_77991_e > this.func_77958_k();
      }
   }

   public void func_77972_a(int p_77972_1_, EntityLivingBase p_77972_2_) {
      if(!(p_77972_2_ instanceof EntityPlayer) || !((EntityPlayer)p_77972_2_).field_71075_bZ.field_75098_d) {
         if(this.func_77984_f()) {
            if(this.func_96631_a(p_77972_1_, p_77972_2_.func_70681_au())) {
               p_77972_2_.func_70669_a(this);
               this.func_190918_g(1);
               if(p_77972_2_ instanceof EntityPlayer) {
                  EntityPlayer entityplayer = (EntityPlayer)p_77972_2_;
                  entityplayer.func_71029_a(StatList.func_188059_c(this.field_151002_e));
               }

               this.field_77991_e = 0;
            }

         }
      }
   }

   public void func_77961_a(EntityLivingBase p_77961_1_, EntityPlayer p_77961_2_) {
      boolean flag = this.field_151002_e.func_77644_a(this, p_77961_1_, p_77961_2_);
      if(flag) {
         p_77961_2_.func_71029_a(StatList.func_188057_b(this.field_151002_e));
      }

   }

   public void func_179548_a(World p_179548_1_, IBlockState p_179548_2_, BlockPos p_179548_3_, EntityPlayer p_179548_4_) {
      boolean flag = this.func_77973_b().func_179218_a(this, p_179548_1_, p_179548_2_, p_179548_3_, p_179548_4_);
      if(flag) {
         p_179548_4_.func_71029_a(StatList.func_188057_b(this.field_151002_e));
      }

   }

   public boolean func_150998_b(IBlockState p_150998_1_) {
      return this.func_77973_b().func_150897_b(p_150998_1_);
   }

   public boolean func_111282_a(EntityPlayer p_111282_1_, EntityLivingBase p_111282_2_, EnumHand p_111282_3_) {
      return this.func_77973_b().func_111207_a(this, p_111282_1_, p_111282_2_, p_111282_3_);
   }

   public ItemStack func_77946_l() {
      ItemStack itemstack = new ItemStack(this.field_151002_e, this.field_77994_a, this.field_77991_e);
      if(this.field_77990_d != null) {
         itemstack.field_77990_d = this.field_77990_d.func_74737_b();
      }

      return itemstack;
   }

   public static boolean func_77970_a(ItemStack p_77970_0_, ItemStack p_77970_1_) {
      return p_77970_0_.func_190926_b() && p_77970_1_.func_190926_b()?true:(!p_77970_0_.func_190926_b() && !p_77970_1_.func_190926_b()?(p_77970_0_.field_77990_d == null && p_77970_1_.field_77990_d != null?false:p_77970_0_.field_77990_d == null || p_77970_0_.field_77990_d.equals(p_77970_1_.field_77990_d)):false);
   }

   public static boolean func_77989_b(ItemStack p_77989_0_, ItemStack p_77989_1_) {
      return p_77989_0_.func_190926_b() && p_77989_1_.func_190926_b()?true:(!p_77989_0_.func_190926_b() && !p_77989_1_.func_190926_b()?p_77989_0_.func_77959_d(p_77989_1_):false);
   }

   private boolean func_77959_d(ItemStack p_77959_1_) {
      return this.field_77994_a != p_77959_1_.field_77994_a?false:(this.func_77973_b() != p_77959_1_.func_77973_b()?false:(this.field_77991_e != p_77959_1_.field_77991_e?false:(this.field_77990_d == null && p_77959_1_.field_77990_d != null?false:this.field_77990_d == null || this.field_77990_d.equals(p_77959_1_.field_77990_d))));
   }

   public static boolean func_179545_c(ItemStack p_179545_0_, ItemStack p_179545_1_) {
      return p_179545_0_ == p_179545_1_?true:(!p_179545_0_.func_190926_b() && !p_179545_1_.func_190926_b()?p_179545_0_.func_77969_a(p_179545_1_):false);
   }

   public static boolean func_185132_d(ItemStack p_185132_0_, ItemStack p_185132_1_) {
      return p_185132_0_ == p_185132_1_?true:(!p_185132_0_.func_190926_b() && !p_185132_1_.func_190926_b()?p_185132_0_.func_185136_b(p_185132_1_):false);
   }

   public boolean func_77969_a(ItemStack p_77969_1_) {
      return !p_77969_1_.func_190926_b() && this.field_151002_e == p_77969_1_.field_151002_e && this.field_77991_e == p_77969_1_.field_77991_e;
   }

   public boolean func_185136_b(ItemStack p_185136_1_) {
      return !this.func_77984_f()?this.func_77969_a(p_185136_1_):!p_185136_1_.func_190926_b() && this.field_151002_e == p_185136_1_.field_151002_e;
   }

   public String func_77977_a() {
      return this.func_77973_b().func_77667_c(this);
   }

   public String toString() {
      return this.field_77994_a + "x" + this.func_77973_b().func_77658_a() + "@" + this.field_77991_e;
   }

   public void func_77945_a(World p_77945_1_, Entity p_77945_2_, int p_77945_3_, boolean p_77945_4_) {
      if(this.field_77992_b > 0) {
         --this.field_77992_b;
      }

      if(this.field_151002_e != null) {
         this.field_151002_e.func_77663_a(this, p_77945_1_, p_77945_2_, p_77945_3_, p_77945_4_);
      }

   }

   public void func_77980_a(World p_77980_1_, EntityPlayer p_77980_2_, int p_77980_3_) {
      p_77980_2_.func_71064_a(StatList.func_188060_a(this.field_151002_e), p_77980_3_);
      this.func_77973_b().func_77622_d(this, p_77980_1_, p_77980_2_);
   }

   public int func_77988_m() {
      return this.func_77973_b().func_77626_a(this);
   }

   public EnumAction func_77975_n() {
      return this.func_77973_b().func_77661_b(this);
   }

   public void func_77974_b(World p_77974_1_, EntityLivingBase p_77974_2_, int p_77974_3_) {
      this.func_77973_b().func_77615_a(this, p_77974_1_, p_77974_2_, p_77974_3_);
   }

   public boolean func_77942_o() {
      return !this.field_190928_g && this.field_77990_d != null;
   }

   @Nullable
   public NBTTagCompound func_77978_p() {
      return this.field_77990_d;
   }

   public NBTTagCompound func_190925_c(String p_190925_1_) {
      if(this.field_77990_d != null && this.field_77990_d.func_150297_b(p_190925_1_, 10)) {
         return this.field_77990_d.func_74775_l(p_190925_1_);
      } else {
         NBTTagCompound nbttagcompound = new NBTTagCompound();
         this.func_77983_a(p_190925_1_, nbttagcompound);
         return nbttagcompound;
      }
   }

   @Nullable
   public NBTTagCompound func_179543_a(String p_179543_1_) {
      return this.field_77990_d != null && this.field_77990_d.func_150297_b(p_179543_1_, 10)?this.field_77990_d.func_74775_l(p_179543_1_):null;
   }

   public void func_190919_e(String p_190919_1_) {
      if(this.field_77990_d != null && this.field_77990_d.func_150297_b(p_190919_1_, 10)) {
         this.field_77990_d.func_82580_o(p_190919_1_);
      }

   }

   @Nullable
   public NBTTagList func_77986_q() {
      return this.field_77990_d == null?null:this.field_77990_d.func_150295_c("ench", 10);
   }

   public void func_77982_d(@Nullable NBTTagCompound p_77982_1_) {
      this.field_77990_d = p_77982_1_;
   }

   public String func_82833_r() {
      NBTTagCompound nbttagcompound = this.func_179543_a("display");
      if(nbttagcompound != null) {
         if(nbttagcompound.func_150297_b("Name", 8)) {
            return nbttagcompound.func_74779_i("Name");
         }

         if(nbttagcompound.func_150297_b("LocName", 8)) {
            return I18n.func_74838_a(nbttagcompound.func_74779_i("LocName"));
         }
      }

      return this.func_77973_b().func_77653_i(this);
   }

   public ItemStack func_190924_f(String p_190924_1_) {
      this.func_190925_c("display").func_74778_a("LocName", p_190924_1_);
      return this;
   }

   public ItemStack func_151001_c(String p_151001_1_) {
      this.func_190925_c("display").func_74778_a("Name", p_151001_1_);
      return this;
   }

   public void func_135074_t() {
      NBTTagCompound nbttagcompound = this.func_179543_a("display");
      if(nbttagcompound != null) {
         nbttagcompound.func_82580_o("Name");
         if(nbttagcompound.func_82582_d()) {
            this.func_190919_e("display");
         }
      }

      if(this.field_77990_d != null && this.field_77990_d.func_82582_d()) {
         this.field_77990_d = null;
      }

   }

   public boolean func_82837_s() {
      NBTTagCompound nbttagcompound = this.func_179543_a("display");
      return nbttagcompound != null && nbttagcompound.func_150297_b("Name", 8);
   }

   public EnumRarity func_77953_t() {
      return this.func_77973_b().func_77613_e(this);
   }

   public boolean func_77956_u() {
      return !this.func_77973_b().func_77616_k(this)?false:!this.func_77948_v();
   }

   public void func_77966_a(Enchantment p_77966_1_, int p_77966_2_) {
      if(this.field_77990_d == null) {
         this.func_77982_d(new NBTTagCompound());
      }

      if(!this.field_77990_d.func_150297_b("ench", 9)) {
         this.field_77990_d.func_74782_a("ench", new NBTTagList());
      }

      NBTTagList nbttaglist = this.field_77990_d.func_150295_c("ench", 10);
      NBTTagCompound nbttagcompound = new NBTTagCompound();
      nbttagcompound.func_74777_a("id", (short)Enchantment.func_185258_b(p_77966_1_));
      nbttagcompound.func_74777_a("lvl", (short)((byte)p_77966_2_));
      nbttaglist.func_74742_a(nbttagcompound);
   }

   public boolean func_77948_v() {
      return this.field_77990_d != null && this.field_77990_d.func_150297_b("ench", 9)?!this.field_77990_d.func_150295_c("ench", 10).func_82582_d():false;
   }

   public void func_77983_a(String p_77983_1_, NBTBase p_77983_2_) {
      if(this.field_77990_d == null) {
         this.func_77982_d(new NBTTagCompound());
      }

      this.field_77990_d.func_74782_a(p_77983_1_, p_77983_2_);
   }

   public boolean func_82835_x() {
      return this.func_77973_b().func_82788_x();
   }

   public boolean func_82839_y() {
      return this.field_82843_f != null;
   }

   public void func_82842_a(EntityItemFrame p_82842_1_) {
      this.field_82843_f = p_82842_1_;
   }

   @Nullable
   public EntityItemFrame func_82836_z() {
      return this.field_190928_g?null:this.field_82843_f;
   }

   public int func_82838_A() {
      return this.func_77942_o() && this.field_77990_d.func_150297_b("RepairCost", 3)?this.field_77990_d.func_74762_e("RepairCost"):0;
   }

   public void func_82841_c(int p_82841_1_) {
      if(!this.func_77942_o()) {
         this.field_77990_d = new NBTTagCompound();
      }

      this.field_77990_d.func_74768_a("RepairCost", p_82841_1_);
   }

   public Multimap<String, AttributeModifier> func_111283_C(EntityEquipmentSlot p_111283_1_) {
      Multimap<String, AttributeModifier> multimap;
      if(this.func_77942_o() && this.field_77990_d.func_150297_b("AttributeModifiers", 9)) {
         multimap = HashMultimap.<String, AttributeModifier>create();
         NBTTagList nbttaglist = this.field_77990_d.func_150295_c("AttributeModifiers", 10);

         for(int i = 0; i < nbttaglist.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound = nbttaglist.func_150305_b(i);
            AttributeModifier attributemodifier = SharedMonsterAttributes.func_111259_a(nbttagcompound);
            if(attributemodifier != null && (!nbttagcompound.func_150297_b("Slot", 8) || nbttagcompound.func_74779_i("Slot").equals(p_111283_1_.func_188450_d())) && attributemodifier.func_111167_a().getLeastSignificantBits() != 0L && attributemodifier.func_111167_a().getMostSignificantBits() != 0L) {
               multimap.put(nbttagcompound.func_74779_i("AttributeName"), attributemodifier);
            }
         }
      } else {
         multimap = this.func_77973_b().func_111205_h(p_111283_1_);
      }

      return multimap;
   }

   public void func_185129_a(String p_185129_1_, AttributeModifier p_185129_2_, @Nullable EntityEquipmentSlot p_185129_3_) {
      if(this.field_77990_d == null) {
         this.field_77990_d = new NBTTagCompound();
      }

      if(!this.field_77990_d.func_150297_b("AttributeModifiers", 9)) {
         this.field_77990_d.func_74782_a("AttributeModifiers", new NBTTagList());
      }

      NBTTagList nbttaglist = this.field_77990_d.func_150295_c("AttributeModifiers", 10);
      NBTTagCompound nbttagcompound = SharedMonsterAttributes.func_111262_a(p_185129_2_);
      nbttagcompound.func_74778_a("AttributeName", p_185129_1_);
      if(p_185129_3_ != null) {
         nbttagcompound.func_74778_a("Slot", p_185129_3_.func_188450_d());
      }

      nbttaglist.func_74742_a(nbttagcompound);
   }

   public ITextComponent func_151000_E() {
      TextComponentString textcomponentstring = new TextComponentString(this.func_82833_r());
      if(this.func_82837_s()) {
         textcomponentstring.func_150256_b().func_150217_b(Boolean.valueOf(true));
      }

      ITextComponent itextcomponent = (new TextComponentString("[")).func_150257_a(textcomponentstring).func_150258_a("]");
      if(!this.field_190928_g) {
         NBTTagCompound nbttagcompound = this.func_77955_b(new NBTTagCompound());
         itextcomponent.func_150256_b().func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_ITEM, new TextComponentString(nbttagcompound.toString())));
         itextcomponent.func_150256_b().func_150238_a(this.func_77953_t().field_77937_e);
      }

      return itextcomponent;
   }

   public boolean func_179544_c(Block p_179544_1_) {
      if(p_179544_1_ == this.field_179552_h) {
         return this.field_179553_i;
      } else {
         this.field_179552_h = p_179544_1_;
         if(this.func_77942_o() && this.field_77990_d.func_150297_b("CanDestroy", 9)) {
            NBTTagList nbttaglist = this.field_77990_d.func_150295_c("CanDestroy", 8);

            for(int i = 0; i < nbttaglist.func_74745_c(); ++i) {
               Block block = Block.func_149684_b(nbttaglist.func_150307_f(i));
               if(block == p_179544_1_) {
                  this.field_179553_i = true;
                  return true;
               }
            }
         }

         this.field_179553_i = false;
         return false;
      }
   }

   public boolean func_179547_d(Block p_179547_1_) {
      if(p_179547_1_ == this.field_179550_j) {
         return this.field_179551_k;
      } else {
         this.field_179550_j = p_179547_1_;
         if(this.func_77942_o() && this.field_77990_d.func_150297_b("CanPlaceOn", 9)) {
            NBTTagList nbttaglist = this.field_77990_d.func_150295_c("CanPlaceOn", 8);

            for(int i = 0; i < nbttaglist.func_74745_c(); ++i) {
               Block block = Block.func_149684_b(nbttaglist.func_150307_f(i));
               if(block == p_179547_1_) {
                  this.field_179551_k = true;
                  return true;
               }
            }
         }

         this.field_179551_k = false;
         return false;
      }
   }

   public void func_190915_d(int p_190915_1_) {
      this.field_77992_b = p_190915_1_;
   }

   public int func_190916_E() {
      return this.field_190928_g?0:this.field_77994_a;
   }

   public void func_190920_e(int p_190920_1_) {
      this.field_77994_a = p_190920_1_;
      this.func_190923_F();
   }

   public void func_190917_f(int p_190917_1_) {
      this.func_190920_e(this.field_77994_a + p_190917_1_);
   }

   public void func_190918_g(int p_190918_1_) {
      this.func_190917_f(-p_190918_1_);
   }
}
