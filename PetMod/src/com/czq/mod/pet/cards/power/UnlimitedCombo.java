/**      
 * 项目名称：PetMod<br> 
 */    
package com.czq.mod.pet.cards.power;

import com.czq.mod.pet.cards.AbstractCardEnum;
import com.czq.mod.pet.cards.AbstractModCustomCard;
import com.czq.mod.pet.helpers.PowerHelper;
import com.czq.mod.pet.powers.ComboPower;
import com.czq.mod.pet.powers.EaglePower;
import com.czq.mod.pet.powers.UnlimitedComboPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * 描述: TODO(描述这个类的作用)<br><br>
 * 作者： Administrator/850530595@qq.com<br>    
 * 创建时间：2018年12月24日/下午4:17:30<br>    
 * 修改人：Administrator/850530595@qq.com<br>    
 * 修改时间：2018年12月24日/下午4:17:30<br>    
 * 修改备注：<br>
 * 版本：1.0
 */
/**      
 * 项目名称：PetMod<br> 
 */    






public class UnlimitedCombo
  extends AbstractModCustomCard
{
  public static final String ID = "Unlimited Combo";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
  private static final int COST = 1;
  private static final int BLOCK_AMT = 13;
  
  public UnlimitedCombo()
  {
    super(ID, NAME, "blue/skill/undo", COST, DESCRIPTION, AbstractCard.CardType.POWER,  AbstractCardEnum.GIANT_COLOR, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
    
    this.baseMagicNumber = 1;
    this.magicNumber = this.baseMagicNumber;
  }
  
  public void use(AbstractPlayer p, AbstractMonster m)
  {
	 
	 if(!PowerHelper.hasPower(EaglePower.POWER_ID))
	 {
		 AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new EaglePower(p)));
	 }
    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new UnlimitedComboPower(p, magicNumber)));
  }
  
  public AbstractCard makeCopy()
  {
    return new UnlimitedCombo();
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

