/**      
 * 项目名称：PetMod<br> 
 */    
package com.czq.mod.pet.cards.power;

import javax.swing.JFormattedTextField.AbstractFormatter;

import com.czq.mod.pet.characters.Giant;
import com.czq.mod.pet.powers.RetainComboCardPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConfusionPower;
import com.megacrit.cardcrawl.powers.DrawPower;
import com.megacrit.cardcrawl.powers.EquilibriumPower;

/**
 * 描述: TODO(描述这个类的作用)<br><br>
 * 作者： Administrator/850530595@qq.com<br>    
 * 创建时间：2018年12月24日/下午3:56:07<br>    
 * 修改人：Administrator/850530595@qq.com<br>    
 * 修改时间：2018年12月24日/下午3:56:07<br>    
 * 修改备注：<br>
 * 版本：1.0
 */



public class SnakeForm
  extends AbstractForm
{
  public static final String ID = "SnakeForm";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
  private static final int COST = 2;
  private static final int BLOCK_AMT = 13;
  
  public SnakeForm()
  {
    super(ID, NAME, null, "blue/skill/undo", COST, DESCRIPTION, AbstractCard.CardType.POWER, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
    this.baseMagicNumber = 1;
    this.magicNumber = this.baseMagicNumber;
   
  }
  
  public void use(AbstractPlayer p, AbstractMonster m)
  {
	/*  if(p instanceof Giant)
	  {
		  ((Giant) p).changeState(Giant.BeastStatus.NORMAL);
	  }
	  */
	  AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ConfusionPower(AbstractDungeon.player)));
    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DrawPower(p, this.magicNumber)));
  }
  
  public AbstractCard makeCopy()
  {
    return new SnakeForm();
  }
  
  public void upgrade()
  {
    if (!this.upgraded)
    {
      upgradeName();
      upgradeMagicNumber(1);
      this.isInnate = true;
      this.rawDescription = UPGRADE_DESCRIPTION;
      this.initializeDescription();
    }
  }
}

