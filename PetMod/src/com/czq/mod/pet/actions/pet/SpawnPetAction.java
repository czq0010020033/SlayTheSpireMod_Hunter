package com.czq.mod.pet.actions.pet;

import java.util.ArrayList;
import java.util.List;

import com.czq.mod.pet.helpers.GiantModHelper;
import com.czq.mod.pet.monsters.pets.Pet;
import com.czq.mod.pet.powers.GiantSpiritPower;
import com.czq.mod.pet.powers.ManaPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.powers.SlowPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class SpawnPetAction
  extends AbstractGameAction
{
  private boolean used = false;
  private static final float DURATION = 0.1F;
  private Pet m;
  private boolean always;
  private int mana;
  public SpawnPetAction(Pet m, int mana,boolean always)
  {
    this.actionType = AbstractGameAction.ActionType.SPECIAL;
    this.duration = 0.1F;
    this.m = m;
    this.mana =mana;
    this.always = always;
    if (AbstractDungeon.player.hasRelic("Philosopher's Stone")) {
      m.addPower(new StrengthPower(m, 1));
      
    }
    if(!always)
    	m.addPower(new ManaPower(m, mana));
    m.beforeSpawn();
    if (AbstractDungeon.player.hasPower(GiantSpiritPower.POWER_ID))
  		((Pet) m).applySpirit(AbstractDungeon.player
  				.getPower(GiantSpiritPower.POWER_ID).amount);
    AbstractDungeon.onModifyPower();
  }
  
  public void update()
  {
    if (!this.used)
    {
      this.m.init();
     
      this.m.createIntent();
      this.m.applyPowers();
      
      AbstractDungeon.getCurrRoom().monsters.addSpawnedMonster(this.m);
      this.m.showHealthBar();
      this.m.onSpawn();
      if (ModHelper.isModEnabled("Lethality")) {
          AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.m, this.m, new StrengthPower(this.m, 3), 3));
        }
        if (ModHelper.isModEnabled("Time Dilation")) {
          AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.m, this.m, new SlowPower(this.m, 0)));
        }
  /*    if (this.minion) {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.m, this.m, new MinionPower(this.m)));
      }
    */  
      this.used = true;
    }
    tickDuration();
  }
  

}
