package com.czq.mod.pet.cards.skill;

import com.czq.mod.pet.cards.AbstractCardEnum;
import com.czq.mod.pet.cards.AbstractEchoCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Prayer
  extends AbstractEchoCard
{
  public static final String ID = "Giant Prayer";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
//  public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
  public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
  private static final int COST = 1;
  
  public Prayer()
  {
    super(ID, NAME, "blue/skill/white_noise", COST, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCardEnum.GIANT_COLOR, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.NONE);
    this.exhaust = true;
  }
  
  public void use(AbstractPlayer p, AbstractMonster m)
  {
	  AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
	  c.setCostForTurn(0);
		 AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, true));
		 if(this.upgraded)
		 {
			 super.use(p,m);
		 }
  }
  
  public AbstractCard makeCopy()
  {
    return makeEchoCopy(new Prayer());
  }
  
  public void upgrade()
  {
    if (!this.upgraded)
    {
      upgradeName();
      refreshDescription();
    }
  }

/**
* 描述： 
* @see com.czq.mod.pet.cards.AbstractEchoCard#refreshDescription()
*/ 
@Override
public void refreshDescription() {
	if(this.upgraded){
		if (exhaust && isEthereal) {
			this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[0] + EXTENDED_DESCRIPTION[1];
			this.initializeDescription();
		}
		else{
			this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[0];
			this.initializeDescription();
		}
	}
	else{
		this.rawDescription = DESCRIPTION;
		this.initializeDescription();
	}
}
}
