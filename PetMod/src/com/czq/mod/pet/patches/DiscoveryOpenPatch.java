/**      
 * 项目名称：PetMod<br> 
 */    
package com.czq.mod.pet.patches;

import java.util.ArrayList;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

/**
 * 描述: TODO(描述这个类的作用)<br><br>
 * 作者： Administrator/850530595@qq.com<br>    
 * 创建时间：2018年12月27日/下午1:17:58<br>    
 * 修改人：Administrator/850530595@qq.com<br>    
 * 修改时间：2018年12月27日/下午1:17:58<br>    
 * 修改备注：<br>
 * 版本：1.0
 */
/*@SpirePatch(cls="com.megacrit.cardcrawl.screens.CardRewardScreen", method="discoveryOpen",paramtypez={
		AbstractCard.CardType.class})
public class DiscoveryOpenPatch
{
/*  @SpireInsertPatch(rloc=17, localvars={"tmp"})
  public static void Insert(CardRewardScreen screen, AbstractCard.CardType type, AbstractCard tmp)
  {
	  if(type == CardTypeEnum.COMBO)
		  tmp = ModHelper.returnTrulyRandomCardInCombat();
  }
  public static void Replace(AbstractCard.CardType type)
  {
	 
  }
}*/