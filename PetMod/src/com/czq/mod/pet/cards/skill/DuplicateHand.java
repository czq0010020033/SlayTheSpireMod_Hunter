package com.czq.mod.pet.cards.skill;

import java.awt.List;
import java.util.ArrayList;

import com.czq.mod.pet.cards.AbstractModCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DuplicateHand
  extends AbstractModCard
{
  public static final String ID = "Duplicate Hand";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
  private static final int COST = 1;
  
  public DuplicateHand()
  {
    super(ID, NAME, "colorless/skill/discovery", 0, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.NONE);
    
    this.exhaust = true;
  }
  
  public void use(AbstractPlayer p, AbstractMonster m)
  {
    ArrayList<AbstractCard> cards = new ArrayList<AbstractCard>();
    for(AbstractCard c : AbstractDungeon.player.hand.group)
    {
    	if(c != this)
    	{
    		cards.add(c);
    	}
    }
    for(AbstractCard c : cards)
    {
    	AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(c.makeStatEquivalentCopy(), 1, true, true));
    }
    			
    
  }
  
  public AbstractCard makeCopy()
  {
    return new DuplicateHand();
  }
  
  public void upgrade()
  {
    if (!this.upgraded)
    {
      upgradeName();
      this.exhaust = false;
      this.rawDescription = UPGRADE_DESCRIPTION;
      initializeDescription();
    }
  }
}
