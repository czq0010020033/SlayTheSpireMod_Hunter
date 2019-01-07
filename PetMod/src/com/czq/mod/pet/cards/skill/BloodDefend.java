package com.czq.mod.pet.cards.skill;

import com.czq.mod.pet.cards.AbstractEchoCard;
import com.czq.mod.pet.cards.status.BloodGiant;
import com.czq.mod.pet.helpers.StringConstant;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BloodDefend
  extends AbstractEchoCard
{
  public static final String ID = "Blood Defend";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = 1;
  private static final int BLOCK_AMT = 5;
  
  public BloodDefend()
  {
    super(ID, NAME, "blue/skill/defend", 1, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
    
    this.baseBlock = 13;
  }
  
  public void use(AbstractPlayer p, AbstractMonster m)
  {
    AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, 2));
    AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new BloodGiant(), 1, true, true));
      
    super.use(p, m);
  }
  
  public AbstractCard makeCopy()
  {
    return makeEchoCopy(new BloodDefend());
  }
  
  public void upgrade()
  {
    if (!this.upgraded)
    {
      upgradeName();
      upgradeBlock(3);
    }
  }

/**
* 描述： 
* @see com.czq.mod.pet.cards.AbstractEchoCard#refreshDescription()
*/ 
@Override
public void refreshDescription() {
	if (exhaust && isEthereal) {
		this.rawDescription = DESCRIPTION + StringConstant.Ethereal + StringConstant.Exhaust;
		this.initializeDescription();
		}
}
}
