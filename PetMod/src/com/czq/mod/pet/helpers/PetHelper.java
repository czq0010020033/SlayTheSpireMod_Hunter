/**      
 * 项目名称：SlayTheSpire<br> 
 */
package com.czq.mod.pet.helpers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;

import basemod.BaseMod;

import com.czq.mod.pet.cards.pet.ByrdPetCall;
import com.czq.mod.pet.cards.pet.ChampPetCall;
import com.czq.mod.pet.cards.pet.CultistPetCall;
import com.czq.mod.pet.cards.pet.DonuPetCall;
import com.czq.mod.pet.cards.pet.GremlinFatPetCall;
import com.czq.mod.pet.cards.pet.GremlinNobPetCall;
import com.czq.mod.pet.cards.pet.GremlinTsunderePetCall;
import com.czq.mod.pet.cards.pet.HealerPetCall;
import com.czq.mod.pet.cards.pet.PetCapture;
import com.czq.mod.pet.cards.pet.SneckoPetCall;
import com.czq.mod.pet.monsters.pets.ByrdPet;
import com.czq.mod.pet.monsters.pets.ChampPet;
import com.czq.mod.pet.monsters.pets.CultistPet;
import com.czq.mod.pet.monsters.pets.DonuPet;
import com.czq.mod.pet.monsters.pets.GremlinFatPet;
import com.czq.mod.pet.monsters.pets.GremlinNobPet;
import com.czq.mod.pet.monsters.pets.GremlinTsunderePet;
import com.czq.mod.pet.monsters.pets.HealerPet;
import com.czq.mod.pet.monsters.pets.Pet;
import com.czq.mod.pet.monsters.pets.SneckoPet;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

public class PetHelper {
	
	public static HashSet<String> pets = new HashSet<String>();
	public static HashSet<String> petCards = new HashSet<String>();
	private static String petPrefix = "com.czq.mod.pet.monsters.pets.";
	private static String cardPrefix = "com.czq.mod.pet.cards.pet.";
	static {
		pets.add(CultistPet.ID);
//	pets.add(GremlinFatPet.ID);
	//	pets.add(GremlinNobPet.ID);
//		pets.add(ChampPet.ID);
		pets.add(ByrdPet.ID);
		pets.add(GremlinTsunderePet.ID);
		pets.add(HealerPet.ID);
//		pets.add(DonuPet.ID);
//		pets.add(SneckoPet.ID);
		
		
		petCards.add(CultistPetCall.ID);
	//	petCards.add(GremlinFatPetCall.ID);
	//	petCards.add(GremlinNobPetCall.ID);
	//	petCards.add(ChampPetCall.ID);
		petCards.add(ByrdPetCall.ID);
		petCards.add(GremlinTsunderePetCall.ID);
		petCards.add(HealerPetCall.ID);
	//	petCards.add(DonuPetCall.ID);
	//	petCards.add(SneckoPetCall.ID);
	}

	public static boolean isPet(String petID) {
		if (petID != null && pets.contains(petID)) {
			return true;
		}

		return false;
	}

	public static boolean isPet(AbstractMonster m) {
		if (m != null && pets.contains(m.id)) {
			return true;
		}
		return false;
	}

	public static boolean isPetCard(AbstractCard c) {
		if (c != null && petCards.contains(c.cardID)) {
			return true;
		}
		return false;
	}

	public static boolean canCapture(AbstractMonster m) {
		if (m != null) {
			String id = m.id + "Pet";
			if (pets.contains(id)) {
				return true;
			}
		}

		return false;
	}

	public static AbstractCard getPetCardInstance(String cardId) {
		if (cardId == null || (!petCards.contains(cardId))) {
			return null;
		}
		try {
			Class clazz = Class.forName(cardPrefix + cardId);
			return (AbstractCard) clazz.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		return null;

	}

	public static AbstractMonster getPetInstance(String petId, int mana, float pos_x) {
		if (petId == null || (!pets.contains(petId))) {
			return null;
		}

		try {
			Class clazz = Class.forName(petPrefix + petId);
			Constructor constructor = clazz.getConstructor(int.class, float.class);
			return (AbstractMonster) constructor.newInstance(mana, pos_x);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void addPetCardToLibrary() {
		for (String cardId : petCards) {
			if (!CardLibrary.cards.containsKey(cardId)) {
				AbstractCard c = getPetCardInstance(cardId);
				if (c != null) {
					CardLibrary.add(c);
					addCardToPool(c.makeCopy());
				}
			}
		}

	/*	if (!CardLibrary.cards.containsKey(PetCapture.ID)) {
			CardLibrary.add(new PetCapture());
			addCardToPool(new PetCapture());
		}*/
	}

	public static void addCardToPool(AbstractCard c) {
		if (c == null || (CardLibrary.cards.containsKey(c.cardID)))
			return;
		
		   BaseMod.addCard(c);
		   UnlockTracker.unlockCard(c.cardID);
		/*
		switch (c.rarity) {
		case COMMON:
			AbstractDungeon.commonCardPool.addToTop(c);
			AbstractDungeon.srcCommonCardPool.addToTop(c);
			break;
		case UNCOMMON:
			AbstractDungeon.uncommonCardPool.addToTop(c);
			AbstractDungeon.srcUncommonCardPool.addToTop(c);
			break;
		case RARE:
			AbstractDungeon.rareCardPool.addToTop(c);
			AbstractDungeon.srcRareCardPool.addToTop(c);
			break;
		case CURSE:
			AbstractDungeon.curseCardPool.addToTop(c);
			AbstractDungeon.srcCurseCardPool.addToTop(c);
			break;
		default:

			break;
		}
		*/
	}
	
	public static boolean isAlive(AbstractMonster pet){
		if(!(pet instanceof Pet))
			return false;
		Pet p = (Pet)pet;
		if(p != null && (!p.isDead) && (!p.isDying) && (!p.isEscaping))
			return true;
		return false;
	}


}
