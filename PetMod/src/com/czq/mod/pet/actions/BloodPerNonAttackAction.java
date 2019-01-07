package com.czq.mod.pet.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BloodPerNonAttackAction
  extends AbstractGameAction
{
  private int BloodGAIN_AMOUNT = 0;
  
  public BloodPerNonAttackAction(int bloodAmount)
  {
    this.BloodGAIN_AMOUNT = bloodAmount;
    setValues(AbstractDungeon.player, AbstractDungeon.player);
    this.actionType = AbstractGameAction.ActionType.HEAL;
  }
  
  public void update()
  {
    if (!this.isDone)
    {
      this.isDone = true;
      int total = 0;
      for (AbstractCard c : AbstractDungeon.player.hand.group) {
        if (c.type != AbstractCard.CardType.ATTACK)
        {
          AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
          
          total += this.BloodGAIN_AMOUNT;
        }
      }
      AbstractDungeon.actionManager.addToTop(new HealAction(AbstractDungeon.player, AbstractDungeon.player, total));
    }
  }
}
