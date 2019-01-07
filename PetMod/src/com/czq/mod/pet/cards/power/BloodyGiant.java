package com.czq.mod.pet.cards.power;

import com.czq.mod.pet.cards.AbstractModCard;
import com.czq.mod.pet.cards.status.BloodGiant;
import com.czq.mod.pet.powers.BloodyPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BloodyGiant
  extends AbstractModCard
{
  public static final String ID = "Bloody Giant";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
  private static final int COST = 1;
  private int DRAW = 1;
  
  public BloodyGiant()
  {
    super(ID, NAME, "red/power/evolve", 1, DESCRIPTION, AbstractCard.CardType.POWER, AbstractCard.CardColor.RED, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
    
    this.baseMagicNumber = this.DRAW;
    this.magicNumber = this.baseMagicNumber;
  }
  
  public void use(AbstractPlayer p, AbstractMonster m)
  {
	  
    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BloodyPower(p, this.magicNumber), this.magicNumber));
    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(CardLibrary.getCard(BloodGiant.ID).makeCopy(), 2, true, true));
  }
  
  public AbstractCard makeCopy()
  {
    return new BloodyGiant();
  }
  
  public void upgrade()
  {
    if (!this.upgraded)
    {
      upgradeName();
      upgradeMagicNumber(1);
      this.rawDescription = UPGRADE_DESCRIPTION;
      initializeDescription();
    }
  }
}
