/**      
 * 项目名称：PetMod<br> 
 */    
package com.czq.mod.pet.patches.relics;

import java.lang.reflect.Field;
import java.util.Iterator;

import com.czq.mod.pet.cards.attack.EagleFirst;
import com.czq.mod.pet.cards.attack.Strike_Giant;
import com.czq.mod.pet.cards.skill.Defend_Giant;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.PandorasBox;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

/**
 * 描述: TODO(描述这个类的作用)<br><br>
 * 作者： Administrator/850530595@qq.com<br>    
 * 创建时间：2019年1月6日/上午2:30:12<br>    
 * 修改人：Administrator/850530595@qq.com<br>    
 * 修改时间：2019年1月6日/上午2:30:12<br>    
 * 修改备注：<br>
 * 版本：1.0
 */
@SpirePatch(cls = "com.megacrit.cardcrawl.relics.PandorasBox", method = "onEquip")
public class PandorasBoxPatch {
	public static void Replace(PandorasBox box) {
		Field cardField = null;
		boolean calledTransformTemp = false;
		Field countField = null;
		int countTemp = 0;
		try {
			cardField = box.getClass().getDeclaredField("calledTransform");
			cardField.setAccessible(true);
			cardField.set(box, false);
			calledTransformTemp = (boolean) cardField.get(box);
			
			countField = box.getClass().getDeclaredField("count");
			countField.setAccessible(true);
		
			countTemp = (int) countField.get(box);
		} catch (NoSuchFieldException | SecurityException
				| IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
		}
	
		    for (Iterator<AbstractCard> i = AbstractDungeon.player.masterDeck.group.iterator(); i.hasNext();)
		    {
		      AbstractCard e = (AbstractCard)i.next();
		      if ((e.cardID.equals("Strike_R")) || (e.cardID.equals("Strike_G")) || (e.cardID.equals("Strike_B")) || 
		        (e.cardID.equals("Defend_R")) || (e.cardID.equals("Defend_G")) || (e.cardID.equals("Defend_B"))
		    ||  (e.cardID.equals(Strike_Giant.ID)) || (e.cardID.equals(Defend_Giant.ID)) || (e.cardID.equals(EagleFirst.ID)))
		      {
		        i.remove();
		    	
		    	countTemp += 1;
		      }
		    }
		    try {
				countField.set(box, countTemp);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		    if (countTemp > 0)
		    {
		      CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
		      for (int i = 0; i < countTemp; i++)
		      {
		        AbstractCard card = AbstractDungeon.returnTrulyRandomCard().makeCopy();
		        UnlockTracker.markCardAsSeen(card.cardID);
		        card.isSeen = true;
		        group.addToBottom(card);
		      }
		      AbstractDungeon.gridSelectScreen.openConfirmationGrid(group, box.DESCRIPTIONS[1]);
		    }
	}
	}