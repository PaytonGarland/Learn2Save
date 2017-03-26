package net.minecraft.stats;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import net.minecraft.scoreboard.IScoreCriteria;
import net.minecraft.scoreboard.ScoreCriteriaStat;
import net.minecraft.stats.IStatType;
import net.minecraft.stats.StatList;
import net.minecraft.util.IJsonSerializable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.HoverEvent;

public class StatBase {
   public final String field_75975_e;
   private final ITextComponent field_75978_a;
   public boolean field_75972_f;
   private final IStatType field_75976_b;
   private final IScoreCriteria field_150957_c;
   private Class<? extends IJsonSerializable> field_150956_d;
   private static final NumberFormat field_75977_c = NumberFormat.getIntegerInstance(Locale.US);
   public static IStatType field_75980_h = new IStatType() {
   };
   private static final DecimalFormat field_75974_d = new DecimalFormat("########0.00");
   public static IStatType field_75981_i = new IStatType() {
   };
   public static IStatType field_75979_j = new IStatType() {
   };
   public static IStatType field_111202_k = new IStatType() {
   };

   public StatBase(String p_i45307_1_, ITextComponent p_i45307_2_, IStatType p_i45307_3_) {
      this.field_75975_e = p_i45307_1_;
      this.field_75978_a = p_i45307_2_;
      this.field_75976_b = p_i45307_3_;
      this.field_150957_c = new ScoreCriteriaStat(this);
      IScoreCriteria.field_96643_a.put(this.field_150957_c.func_96636_a(), this.field_150957_c);
   }

   public StatBase(String p_i45308_1_, ITextComponent p_i45308_2_) {
      this(p_i45308_1_, p_i45308_2_, field_75980_h);
   }

   public StatBase func_75966_h() {
      this.field_75972_f = true;
      return this;
   }

   public StatBase func_75971_g() {
      if(StatList.field_188093_a.containsKey(this.field_75975_e)) {
         throw new RuntimeException("Duplicate stat id: \"" + ((StatBase)StatList.field_188093_a.get(this.field_75975_e)).field_75978_a + "\" and \"" + this.field_75978_a + "\" at id " + this.field_75975_e);
      } else {
         StatList.field_75940_b.add(this);
         StatList.field_188093_a.put(this.field_75975_e, this);
         return this;
      }
   }

   public boolean func_75967_d() {
      return false;
   }

   public ITextComponent func_150951_e() {
      ITextComponent itextcomponent = this.field_75978_a.func_150259_f();
      itextcomponent.func_150256_b().func_150238_a(TextFormatting.GRAY);
      itextcomponent.func_150256_b().func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_ACHIEVEMENT, new TextComponentString(this.field_75975_e)));
      return itextcomponent;
   }

   public ITextComponent func_150955_j() {
      ITextComponent itextcomponent = this.func_150951_e();
      ITextComponent itextcomponent1 = (new TextComponentString("[")).func_150257_a(itextcomponent).func_150258_a("]");
      itextcomponent1.func_150255_a(itextcomponent.func_150256_b());
      return itextcomponent1;
   }

   public boolean equals(Object p_equals_1_) {
      if(this == p_equals_1_) {
         return true;
      } else if(p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
         StatBase statbase = (StatBase)p_equals_1_;
         return this.field_75975_e.equals(statbase.field_75975_e);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.field_75975_e.hashCode();
   }

   public String toString() {
      return "Stat{id=" + this.field_75975_e + ", nameId=" + this.field_75978_a + ", awardLocallyOnly=" + this.field_75972_f + ", formatter=" + this.field_75976_b + ", objectiveCriteria=" + this.field_150957_c + '}';
   }

   public IScoreCriteria func_150952_k() {
      return this.field_150957_c;
   }

   public Class<? extends IJsonSerializable> func_150954_l() {
      return this.field_150956_d;
   }

   public StatBase func_150953_b(Class<? extends IJsonSerializable> p_150953_1_) {
      this.field_150956_d = p_150953_1_;
      return this;
   }
}
