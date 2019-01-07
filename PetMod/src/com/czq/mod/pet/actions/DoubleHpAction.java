package com.czq.mod.pet.actions;

import com.czq.mod.pet.powers.ManaPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class DoubleHpAction
  extends AbstractGameAction
{
  private AbstractCreature creatrue;
  
  public DoubleHpAction(AbstractCreature creatrue, int amount)
  {
	  if (Settings.FAST_MODE) {
	      this.startDuration = Settings.ACTION_DUR_XFAST;
	    } else {
	      this.startDuration = Settings.ACTION_DUR_FAST;
	    }
	    this.duration = this.startDuration;
    this.actionType = AbstractGameAction.ActionType.WAIT;
    this.creatrue = creatrue;
    this.amount = amount;
    
  }
  
  public void update()
  {
	  if (this.duration == this.startDuration && this.creatrue != null) {
		  
	      this.creatrue.increaseMaxHp(this.creatrue.maxHealth * amount, true);
	    }
    tickDuration();
  }
}
