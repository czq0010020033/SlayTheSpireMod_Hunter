package com.czq.mod.pet.cards.power;

import com.czq.mod.pet.cards.AbstractModCustomCard;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RupturePower;

public class RuptureGiant
  extends AbstractModCustomCard
{
  public static final String ID = "Rupture Giant";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Rupture");
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = 1;
  private int buffAmt = 1;
  
  public RuptureGiant()
  {
    super(ID, NAME, "red/power/rupture", 1, DESCRIPTION, AbstractCard.CardType.POWER, AbstractCard.CardColor.RED, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
  }
  
  public void use(AbstractPlayer p, AbstractMonster m)
  {
    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RupturePower(p, this.buffAmt), this.buffAmt));
  }
  
  public void upgrade()
  {
    if (!this.upgraded)
    {
      upgradeName();
      upgradeBaseCost(0);
    }
  }
  
  public AbstractCard makeCopy()
  {
    return new RuptureGiant();
  }
}
