package com.czq.mod.pet.cards.skill;

import com.czq.mod.pet.cards.AbstractCardEnum;
import com.czq.mod.pet.cards.AbstractModCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;

public class OneBlood
  extends AbstractModCard
{
  public static final String ID = "One Blood";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = 1;
  
  public OneBlood()
  {
    super(ID, NAME, "red/skill/secondwind", "red/skill/secondWind", COST, DESCRIPTION, AbstractCard.CardType.SKILL,  AbstractCardEnum.GIANT_COLOR, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
    this.baseBlock = 0;
    this.baseMagicNumber = 3;
    this.magicNumber = this.baseMagicNumber;
    this.exhaust = true;
  }
  
  public void use(AbstractPlayer p, AbstractMonster m)
  {
	  int temp  = AbstractDungeon.player.currentHealth - 1 ;
	  
	  this.baseBlock = temp * this.magicNumber;
	  AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, temp));
	  AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
	  AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BlurPower(p, 4), 4));
  }
  
  
  public void applyPowers()
  {
	  this.baseBlock = (AbstractDungeon.player.currentHealth - 1)*this.magicNumber;
      super.applyPowers();
  }

  
  public AbstractCard makeCopy()
  {
    return new OneBlood();
  }
  
  public void upgrade()
  {
    if (!this.upgraded)
    {
      upgradeName();
      upgradeMagicNumber(2);
      upgradeBaseCost(0);
    }
  }
  
  
}
