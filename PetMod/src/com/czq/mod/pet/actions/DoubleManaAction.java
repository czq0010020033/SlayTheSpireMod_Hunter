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

public class DoubleManaAction
  extends AbstractGameAction
{
  private AbstractCreature creatrue;
  
  public DoubleManaAction(AbstractCreature creatrue, int amount)
  {
    this.actionType = AbstractGameAction.ActionType.WAIT;
    this.creatrue = creatrue;
    this.duration = Settings.ACTION_DUR_XFAST;
    this.amount = amount;
  }
  
  public void update()
  {
	  if ((this.duration == Settings.ACTION_DUR_XFAST) && (this.creatrue.hasPower(ManaPower.POWER_ID)))
    {
      int strAmt = this.creatrue.getPower(ManaPower.POWER_ID).amount * amount;
      AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.creatrue, this.creatrue, new ManaPower(this.creatrue, strAmt), strAmt));
    }
    tickDuration();
  }
}
