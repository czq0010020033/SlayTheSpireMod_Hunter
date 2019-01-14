/**      
 * 项目名称：PetMod<br> 
 */
package com.czq.mod.pet.helpers;

import java.util.ArrayList;

import com.czq.mod.pet.cards.weapon.PoisonWeapon;
import com.czq.mod.pet.cards.weapon.Quiver;
import com.czq.mod.pet.cards.weapon.VampireWeapon;
import com.czq.mod.pet.cards.weapon.WeaponUpgrade;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardLibrary;

/**
 * 描述: TODO(描述这个类的作用)<br>
 * <br>
 * 作者： Administrator/850530595@qq.com<br>
 * 创建时间：2019年1月13日/下午8:41:50<br>
 * 修改人：Administrator/850530595@qq.com<br>
 * 修改时间：2019年1月13日/下午8:41:50<br>
 * 修改备注：<br>
 * 版本：1.0
 */
public class WeaponHelper {

	public static final ArrayList<String> weaponCards = new ArrayList<String>();
	static {
		weaponCards.add(Quiver.ID);
		weaponCards.add(PoisonWeapon.ID);
		weaponCards.add(VampireWeapon.ID);
		weaponCards.add(WeaponUpgrade.ID);

	}

	public static ArrayList<AbstractCard> getRandomWeaponCards(int amount) {
		ArrayList<AbstractCard> list = new ArrayList<AbstractCard>();
		list.add(CardLibrary.getCard(Quiver.ID).makeCopy());
		for (int i = 0; i < weaponCards.size(); i++) {
			list.add(CardLibrary.getCard(weaponCards.get(i)).makeCopy());
		}
		return list;
	}
}
