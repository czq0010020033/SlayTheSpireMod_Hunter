/**      
 * 项目名称：PetMod<br> 
 */    
package com.czq.mod.pet.powers;

import com.czq.mod.pet.actions.pet.RetainComboAction;
import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * 描述: TODO(描述这个类的作用)<br><br>
 * 作者： Administrator/850530595@qq.com<br>    
 * 创建时间：2018年12月24日/下午2:59:19<br>    
 * 修改人：Administrator/850530595@qq.com<br>    
 * 修改时间：2018年12月24日/下午2:59:19<br>    
 * 修改备注：<br>
 * 版本：1.0
 */

public class RetainComboCardPower
  extends AbstractPower
{
  public static final String POWER_ID = "Retain Combo Cards";
  private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
  public static final String NAME = powerStrings.NAME;
  public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
  
  public RetainComboCardPower(AbstractCreature owner)
  {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    updateDescription();
    loadRegion("retain");
  }
  
  public void updateDescription()
  {
      this.description = DESCRIPTIONS[0];
  }
  
  public void atEndOfTurn(boolean isPlayer)
  {
    if ((isPlayer) && (!AbstractDungeon.player.hand.isEmpty()) && (!AbstractDungeon.player.hasRelic("Runic Pyramid")) && 
      (!AbstractDungeon.player.hasPower("Equilibrium"))) {
      AbstractDungeon.actionManager.addToBottom(new RetainComboAction(this.owner));
    }
  }
}
