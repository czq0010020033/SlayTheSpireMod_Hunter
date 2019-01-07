package com.czq.mod.pet.powers;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.common.SuicideAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;

public class ManaPower
  extends AbstractPower
{
  public static final String POWER_ID = "Mana Power";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
  private boolean justApplied = false;
  
  public ManaPower(AbstractCreature owner, int amount)
  {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = amount;
    this.justApplied = true;
    updateDescription();
    loadRegion("fading");
  }
  
  
  /*public void atEndOfRound()
  {
    if (this.justApplied)
    {
      this.justApplied = false;
      return;
    }
    if (this.amount <= 0) {
    	
      AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    } else {
      AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
    }
  }*/
  public void reducePower(int reduceAmount)
  {
    if (this.amount - reduceAmount <= 0)
    {
      this.fontScale = 8.0F;
      this.amount = 0;
      AbstractDungeon.actionManager.addToBottom(new VFXAction(new ExplosionSmallEffect(this.owner.hb.cX, this.owner.hb.cY), 0.3F));
      AbstractDungeon.actionManager.addToBottom(new SuicideAction((AbstractMonster)this.owner));
    }
    else
    {
      this.fontScale = 8.0F;
      this.amount -= reduceAmount;
    }
  }
  
  
  @Override
  public void duringTurn()
  {
    if ((this.amount == 1) && (!this.owner.isDying))
    {
      AbstractDungeon.actionManager.addToBottom(new VFXAction(new ExplosionSmallEffect(this.owner.hb.cX, this.owner.hb.cY), 0.3F));
      
      AbstractDungeon.actionManager.addToBottom(new SuicideAction((AbstractMonster)this.owner));
    }
    else
    {
      AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
      updateDescription();
    }
  }
  
  public void updateDescription()
  {
	  this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
  }
}
