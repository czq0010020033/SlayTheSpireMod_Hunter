package com.czq.mod.pet.cards.status;

import basemod.abstracts.CustomCard;

import com.czq.mod.pet.helpers.ImageHelper;
import com.czq.mod.pet.powers.BloodyPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class BloodGiant
  extends CustomCard
{
  public static final String ID = "Blood Giant";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = -2;
  
  public BloodGiant()
  {
    super(ID, NAME, ImageHelper.getCardImageUrl(ID, AbstractCard.CardType.STATUS), -2, DESCRIPTION, AbstractCard.CardType.STATUS, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.NONE);
    this.isEthereal = true;
  }
  
  public void triggerWhenDrawn()
  {
    if ((AbstractDungeon.player.hasPower("Evolve")) && (!AbstractDungeon.player.hasPower("No Draw")))
    {
      AbstractDungeon.player.getPower("Evolve").flash();
      AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 
      
        AbstractDungeon.player.getPower("Evolve").amount));
    }
    if (AbstractDungeon.player.hasPower(BloodyPower.POWER_ID))
    {
      AbstractDungeon.player.getPower(BloodyPower.POWER_ID).flash();
      int temp  =  AbstractDungeon.player.getPower(BloodyPower.POWER_ID).amount;
      AbstractDungeon.actionManager.addToBottom(new HealAction(AbstractDungeon.player, AbstractDungeon.player,  3));
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, temp), temp));
      if((!AbstractDungeon.player.hasPower("No Draw")))
      {
    	  AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, temp));
      }
   }
  }
  
  public void use(AbstractPlayer p, AbstractMonster m)
  {
    if (p.hasRelic("Medical Kit")) {
      useMedicalKit(p);
    } else {
      AbstractDungeon.actionManager.addToBottom(new UseCardAction(this));
    }
  }
  
  public AbstractCard makeCopy()
  {
    return new BloodGiant();
  }
  
  public void upgrade() {}
}
