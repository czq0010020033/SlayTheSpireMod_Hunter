/**      
 * 项目名称：PetMod<br> 
 */
package com.czq.mod.pet.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.czq.mod.pet.cards.AbstractComboCard;
import com.czq.mod.pet.cards.pet.PetCall;
import com.czq.mod.pet.monsters.pets.Pet;
import com.czq.mod.pet.powers.DeathRattleExplodePower;
import com.czq.mod.pet.powers.DeathRattleHealPower;
import com.czq.mod.pet.powers.DeathRattleStrengthPower;
import com.czq.mod.pet.weapon.Bow;
import com.czq.mod.pet.weapon.Weapon;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

/**
 * 描述: TODO(描述这个类的作用)<br>
 * <br>
 * 作者： Administrator/850530595@qq.com<br>
 * 创建时间：2018年12月27日/下午12:31:23<br>
 * 修改人：Administrator/850530595@qq.com<br>
 * 修改时间：2018年12月27日/下午12:31:23<br>
 * 修改备注：<br>
 * 版本：1.0
 */
public class GiantModHelper {

	public static ArrayList<AbstractMonster> pets = new ArrayList<AbstractMonster>();
	public static Weapon weapon = new Bow();

	/*
	 * public static void addGiantCards(ArrayList<AbstractCard> tmpPool) { //
	 * logger.info("[INFO] Adding blue cards into card pool."); AbstractCard
	 * card = null; for (Map.Entry<String, AbstractCard> c :
	 * CardLibrary.cards.entrySet()) { card = (AbstractCard) c.getValue(); if
	 * ((card.color == AbstractCardEnum.GIANT_COLOR) && (card.rarity !=
	 * AbstractCard.CardRarity.BASIC) && ((!UnlockTracker.isCardLocked((String)
	 * c.getKey())) || (Settings .treatEverythingAsUnlocked()))) {
	 * tmpPool.add(card); } } }
	 */
	public static AbstractCard returnTrulyRandomComboCardInCombat() {
		final ArrayList<AbstractCard> list = new ArrayList<AbstractCard>();
		for (final AbstractCard c : AbstractDungeon.srcCommonCardPool.group) {
			if (c instanceof AbstractComboCard
					&& !c.hasTag(AbstractCard.CardTags.HEALING)) {
				list.add(c);
			}
		}
		for (final AbstractCard c : AbstractDungeon.srcUncommonCardPool.group) {
			if (c instanceof AbstractComboCard
					&& !c.hasTag(AbstractCard.CardTags.HEALING)) {
				list.add(c);
			}
		}
		for (final AbstractCard c : AbstractDungeon.srcRareCardPool.group) {
			if (c instanceof AbstractComboCard
					&& !c.hasTag(AbstractCard.CardTags.HEALING)) {
				list.add(c);
			}
		}
		if (list.size() == 0) {
			AbstractCard card = null;
			for (Map.Entry<String, AbstractCard> c : CardLibrary.cards
					.entrySet()) {
				card = (AbstractCard) c.getValue();
				if ((card instanceof AbstractComboCard)
						&& (card.rarity != AbstractCard.CardRarity.BASIC)
						&& ((!UnlockTracker.isCardLocked((String) c.getKey())) || (Settings
								.treatEverythingAsUnlocked()))) {
					list.add(card.makeCopy());
				}
			}
		}
		return list.get(AbstractDungeon.cardRandomRng.random(list.size() - 1));
	}

	public static AbstractCard returnTrulyRandomPetCardInCombat() {
		final ArrayList<AbstractCard> list = new ArrayList<AbstractCard>();
		for (final AbstractCard c : AbstractDungeon.srcCommonCardPool.group) {
			if (c instanceof PetCall
					&& !c.hasTag(AbstractCard.CardTags.HEALING)) {
				list.add(c);

			}
		}
		for (final AbstractCard c : AbstractDungeon.srcUncommonCardPool.group) {
			if (c instanceof PetCall
					&& !c.hasTag(AbstractCard.CardTags.HEALING)) {
				list.add(c);
			}
		}
		for (final AbstractCard c : AbstractDungeon.srcRareCardPool.group) {
			if (c instanceof PetCall
					&& !c.hasTag(AbstractCard.CardTags.HEALING)) {
				list.add(c);
			}
		}
		if (list.size() == 0) {
			AbstractCard card = null;
			for (Map.Entry<String, AbstractCard> c : CardLibrary.cards
					.entrySet()) {
				card = (AbstractCard) c.getValue();
				if ((card instanceof PetCall)
						&& (card.rarity != AbstractCard.CardRarity.BASIC)
						&& ((!UnlockTracker.isCardLocked((String) c.getKey())) || (Settings
								.treatEverythingAsUnlocked()))) {
					list.add(card.makeCopy());
				}
			}
		}
		return list.get(AbstractDungeon.cardRandomRng.random(list.size() - 1));
	}

	public static void setRandomDeathRattle(AbstractMonster m, int magicNumber) {
		if (m == null || (m != null && (!(m instanceof Pet))))
			return;
		Random random = new Random();
		int temp = random.random(2);
		if (temp == 1)
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,
					m,
					new DeathRattleExplodePower(m, m.maxHealth * magicNumber),
					m.maxHealth * magicNumber));
		else if (temp == 2)
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,
					m, new DeathRattleHealPower(m, magicNumber * 6),
					magicNumber * 6));
		else
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,
					m, new DeathRattleStrengthPower(m, magicNumber * 2),
					magicNumber * 2));
	}

	public static void setRandomDeathRattlemmediately(AbstractMonster m,
			int magicNumber) {
		if (m == null || (m != null && (!(m instanceof Pet))))
			return;
		Random random = new Random();
		int temp = random.random(2);
		if (temp == 1)
			m.addPower(new DeathRattleExplodePower(m, m.maxHealth * magicNumber));
		else if (temp == 2)
			m.addPower(new DeathRattleHealPower(m, magicNumber * 6));
		else
			m.addPower(new DeathRattleStrengthPower(m, magicNumber * 2));
	}

	/*
	 * private static void refreshPets() { AbstractMonster pet;
	 * Iterator<AbstractMonster> itr = pets.iterator(); while (itr.hasNext()) {
	 * pet = itr.next(); if ((pet == null) || (pet != null && ((pet.isDead) ||
	 * (pet.isDying)))) { itr.remove(); } } }
	 */
	public static boolean hasPet() {
		for (int i = 0; i < pets.size(); i++) {
			if ((pets.get(i) != null) && (!pets.get(i).isDying)
					&& (!pets.get(i).isDead)) {
				return true;
			}
		}
		return false;
	}

	public static AbstractMonster getRandomTauntMinion() {
		List<AbstractMonster> list = new ArrayList();
		for (int i = 0; i < pets.size(); i++) {
			if (PetHelper.isAlive(pets.get(i)) && isTaunt(pets.get(i))) {
				list.add(pets.get(i));
			}
		}
		if (list.size() > 0)
			return list.get(AbstractDungeon.aiRng.random(list.size() - 1));
		else
			return null;
	}

	public static boolean hasTauntMinion() {
		for (int i = 0; i < pets.size(); i++) {
			if (PetHelper.isAlive(pets.get(i)) && isTaunt(pets.get(i))) {
				return true;
			}
		}
		return false;
	}

	private static boolean isTaunt(AbstractMonster m) {
		if ((m != null) && (m instanceof Pet) && (((Pet) m).taunt)) {
			return true;
		}
		return false;
	}

}
