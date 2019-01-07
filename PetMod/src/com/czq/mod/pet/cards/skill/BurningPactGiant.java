package com.czq.mod.pet.cards.skill;

import com.czq.mod.pet.cards.AbstractModCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BurningPactGiant
  extends AbstractModCard
{
  public static final String ID = "Burning Pact Giant";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = 1;
  private static final int DRAW = 2;
  private static final int BASE_EXHAUST = 1;
  
  public BurningPactGiant()
  {
    super(ID, NAME, "red/skill/burning_pact", 1, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.NONE);
    
    this.baseMagicNumber = 2;
    this.magicNumber = this.baseMagicNumber;
  }
  
  public void use(AbstractPlayer p, AbstractMonster m)
  {
    AbstractDungeon.actionManager.addToBottom(new ExhaustAction(p, p, 2, false));
    AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
  }
  
  public AbstractCard makeCopy()
  {
    return new BurningPactGiant();
  }
  
  public void upgrade()
  {
    if (!this.upgraded)
    {
      upgradeName();
      upgradeMagicNumber(1);
    }
  }
}
