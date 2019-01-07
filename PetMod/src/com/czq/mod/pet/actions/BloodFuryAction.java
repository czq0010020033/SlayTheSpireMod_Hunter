package com.czq.mod.pet.actions;

import com.czq.mod.pet.cards.attack.BloodAttack;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.colorless.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BloodFuryAction
  extends AbstractGameAction
{
  private boolean upgrade;
  
  public BloodFuryAction(boolean upgraded)
  {
    this.duration = Settings.ACTION_DUR_FAST;
    this.upgrade = upgraded;
  }
  
  public void update()
  {
    if (this.duration == Settings.ACTION_DUR_FAST)
    {
      int theSize = AbstractDungeon.player.hand.size();
      
      AbstractDungeon.actionManager.addToBottom(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player, theSize, false));
      if (this.upgrade)
      {
        AbstractCard s = new BloodAttack().makeCopy();
        s.upgrade();
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(s, theSize));
      }
      else
      {
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new BloodAttack(), theSize));
      }
    }
    tickDuration();
  }
}
