package com.czq.mod.pet.cards.power;

import com.czq.mod.pet.cards.AbstractModCard;
import com.czq.mod.pet.helpers.StringConstant;
import com.czq.mod.pet.powers.TimeTravelPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TimeTravel
  extends AbstractModCard
{
  public static final String ID = "Time Travel";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = 2;
  private static final int TURNS = 3;
  private static final int ATTACK_DMG = 30;
  private static final int UPGRADE_AMT = 10;
  
  public TimeTravel()
  {
    super(ID, NAME, "colorless/skill/the_bomb", COST, DESCRIPTION, AbstractCard.CardType.POWER, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
    
  }
  
  public void use(AbstractPlayer p, AbstractMonster m)
  {
    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new TimeTravelPower(p)));
  }
  
  public AbstractCard makeCopy()
  {
    return new TimeTravel();
  }
  
  public void upgrade()
  {
    if (!this.upgraded)
    {
      upgradeName();
     upgradeBaseCost(1);
      this.rawDescription = DESCRIPTION;
      initializeDescription();
    }
  }
}
