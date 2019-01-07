/**      
 * 项目名称：PetMod<br> 
 */    
package com.czq.mod.pet.cards.power;

import com.czq.mod.pet.cards.AbstractCardEnum;
import com.czq.mod.pet.cards.AbstractModCustomCard;
import com.czq.mod.pet.powers.RetainComboCardPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
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



public class RetainCombo
  extends AbstractModCustomCard
{
  public static final String ID = "Retain Combo";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  private static final int COST = 1;
  private static final int BLOCK_AMT = 13;
  
  public RetainCombo()
  {
    super(ID, NAME, null, "blue/skill/undo", COST, DESCRIPTION, AbstractCard.CardType.POWER,  AbstractCardEnum.GIANT_COLOR, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
    
  }
  
  public void use(AbstractPlayer p, AbstractMonster m)
  {
    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RetainComboCardPower(p)));
  }
  
  public AbstractCard makeCopy()
  {
    return new RetainCombo();
  }
  
  public void upgrade()
  {
    if (!this.upgraded)
    {
      upgradeName();
      upgradeBaseCost(0);
    }
  }
}

