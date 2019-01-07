/**      
 * 项目名称：PetMod<br> 
 */
package com.czq.mod.pet.patches;

import java.util.ArrayList;

import com.czq.mod.pet.helpers.GiantModHelper;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

/**
 * 描述: TODO(描述这个类的作用)<br>
 * <br>
 * 作者： Administrator/850530595@qq.com<br>
 * 创建时间：2018年12月27日/下午2:02:20<br>
 * 修改人：Administrator/850530595@qq.com<br>
 * 修改时间：2018年12月27日/下午2:02:20<br>
 * 修改备注：<br>
 * 版本：1.0
 */
@SpirePatch(cls = "com.megacrit.cardcrawl.dungeons.AbstractDungeon", method = "returnTrulyRandomCardInCombat", paramtypez = { AbstractCard.CardType.class })
public class ReturnCardPatch {
	public static AbstractCard Replace(AbstractCard.CardType type) {
		if (type == CardTypeEnum.COMBO)
			return GiantModHelper.returnTrulyRandomComboCardInCombat();
		else if(type == CardTypeEnum.PET)
		{
			return GiantModHelper.returnTrulyRandomPetCardInCombat();
		}
		final ArrayList<AbstractCard> list = /* EL:1206 */new ArrayList<AbstractCard>();
		/* SL:1207 */for (final AbstractCard c : AbstractDungeon.srcCommonCardPool.group) {
			/* SL:1208 */if (c.type == type
					&& !c.hasTag(AbstractCard.CardTags.HEALING)) {
				/* SL:1209 */list.add(c);
			}
		}
		/* SL:1212 */for (final AbstractCard c : AbstractDungeon.srcUncommonCardPool.group) {
			/* SL:1213 */if (c.type == type
					&& !c.hasTag(AbstractCard.CardTags.HEALING)) {
				/* SL:1214 */list.add(c);
			}
		}
		/* SL:1217 */for (final AbstractCard c : AbstractDungeon.srcRareCardPool.group) {
			/* SL:1218 */if (c.type == type
					&& !c.hasTag(AbstractCard.CardTags.HEALING)) {
				/* SL:1219 */list.add(c);
			}
		}
		/* SL:1222 */return list.get(AbstractDungeon.cardRandomRng.random(list
				.size() - 1));
	}
}
