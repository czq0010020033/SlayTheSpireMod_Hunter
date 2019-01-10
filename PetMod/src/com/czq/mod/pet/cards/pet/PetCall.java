package com.czq.mod.pet.cards.pet;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.czq.mod.pet.actions.pet.SpawnPetAction;
import com.czq.mod.pet.cards.AbstractCardEnum;
import com.czq.mod.pet.cards.AbstractModCustomCard;
import com.czq.mod.pet.helpers.GiantModHelper;
import com.czq.mod.pet.helpers.ImageHelper;
import com.czq.mod.pet.helpers.PetHelper;
import com.czq.mod.pet.helpers.receive.ReceiveInBattle;
import com.czq.mod.pet.monsters.pets.Pet;
import com.megacrit.cardcrawl.actions.animations.AnimateShakeAction;
import com.megacrit.cardcrawl.actions.common.SuicideAction;
import com.megacrit.cardcrawl.actions.utility.HideHealthBarAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class PetCall extends AbstractModCustomCard {
	private static final Logger logger = LogManager.getLogger(PetCall.class
			.getName());
	public static int PET_MAX_SIZE = 3;

	// private ArrayList<AbstractMonster> pets = GiantModHelper.pets;
	public PetCall(String id, String name, String description, int cost,
			AbstractCard.CardColor color, AbstractCard.CardRarity rarity) {
		super(id, name, ImageHelper.getCardImageUrl(id,
				AbstractCard.CardType.SKILL), cost, description,
				AbstractCard.CardType.SKILL, AbstractCardEnum.GIANT_COLOR,
				rarity, AbstractCard.CardTarget.NONE);

		this.baseMagicNumber = 4;
		this.magicNumber = this.baseMagicNumber;
		this.exhaust = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		logger.info("use card");

		CallPet();

	}

	public AbstractMonster CallPet() {
		ArrayList<AbstractMonster> monsters = AbstractDungeon.getCurrRoom().monsters.monsters;
		if (monsters == null)
			return null;
		/*
		 * ArrayList<AbstractMonster> pets = GiantModHelper.pets;
		 * AbstractMonster pet; // logger.info("after judge Darkling");
		 * Iterator<AbstractMonster> itr = pets.iterator(); while
		 * (itr.hasNext()) { pet = itr.next(); if ((pet == null) || (pet != null
		 * && ((pet.isDead) || (pet.isDying)))) { itr.remove(); } }
		 * logger.info("after remove pet"); if (pets.size() == 0) { return
		 * createPet(this.cardID, Pet.LEFT_X);
		 * 
		 * } else if (pets.size() == 1) {
		 * 
		 * if (pets.get(0) != null && (pets.get(0).drawX < Settings.WIDTH *
		 * 0.25F)) return createPet(this.cardID, Pet.RIGHT_X); else return
		 * createPet(this.cardID, Pet.LEFT_X); } else if (pets.size() >= 2) { if
		 * (pets.get(0) != null && (pets.get(0).drawX < Settings.WIDTH * 0.25F))
		 * { // AbstractDungeon.actionManager.addToBottom(new
		 * CannotLoseAction()); AbstractDungeon.actionManager.addToBottom(new
		 * AnimateShakeAction(pets.get(0), 1.0F, 0.1F));
		 * AbstractDungeon.actionManager.addToBottom(new
		 * HideHealthBarAction(pets.get(0)));
		 * AbstractDungeon.actionManager.addToBottom(new WaitAction(1.0F));
		 * AbstractDungeon.actionManager.addToBottom(new
		 * SuicideAction(pets.get(0))); pets.remove(0); return
		 * createPet(this.cardID, Pet.LEFT_X); } else if(pets.get(1) != null &&
		 * (pets.get(1).drawX < Settings.WIDTH * 0.25F)) { //
		 * AbstractDungeon.actionManager.addToBottom(new CannotLoseAction());
		 * AbstractDungeon.actionManager.addToBottom(new
		 * AnimateShakeAction(pets.get(1), 1.0F, 0.1F));
		 * AbstractDungeon.actionManager.addToBottom(new
		 * HideHealthBarAction(pets.get(1)));
		 * AbstractDungeon.actionManager.addToBottom(new WaitAction(1.0F));
		 * AbstractDungeon.actionManager.addToBottom(new
		 * SuicideAction(pets.get(1))); pets.remove(1); return
		 * createPet(this.cardID, Pet.LEFT_X); }
		 * 
		 * }
		 * 
		 * return null;
		 */
		ArrayList<AbstractMonster> pets = GiantModHelper.pets;
		int num = identifySlot(pets);
		if (num == 100) {
			int replace = 0;
			if (ReceiveInBattle.tempSeenDefect && (pets.get(0) != null)
					&& (!pets.get(0).isDead) && (!pets.get(0).isDead)) {
				replace = 1;
			}
			AbstractDungeon.actionManager.addToBottom(new AnimateShakeAction(
					pets.get(replace), 0.5F, 0.15F));
			AbstractDungeon.actionManager.addToBottom(new HideHealthBarAction(
					pets.get(replace)));
			AbstractDungeon.actionManager.addToBottom(new WaitAction(0.5F));
			AbstractDungeon.actionManager.addToBottom(new SuicideAction(pets
					.get(replace)));
			AbstractDungeon.actionManager.addToBottom(new WaitAction(0.5F));
			num = replace;
		}
		Slot slot = getSlot(num);
	
		return createPet(this.cardID, num, slot);
	}

	private AbstractMonster createPet(String CardId, int num, Slot slot) {

		ArrayList<AbstractMonster> pets = GiantModHelper.pets;
		String petId = CardId.replace("Call", "");

		AbstractMonster pet = PetHelper.getPetInstance(petId, slot.x, slot.y);

		if (pet == null) {
			logger.info("pet == null");
		}

		if (pet != null && pet instanceof Pet) {
			if (num < pets.size()) {
				pets.set(num, pet);
			} else {
				pets.add(pet);
			}

			beforeSpawn(pet);
			AbstractDungeon.actionManager.addToBottom(new SpawnPetAction(
					(Pet) pet, this.magicNumber, false));
		}
		logger.info("after SpawnPetAction");

		return pet;
	}

	public void beforeSpawn(AbstractMonster pet) {

	}

	public AbstractCard makePetCopy(AbstractCard c) {
		if (exhaust && isEthereal) {
			c.isEthereal = true;
			c.exhaust = true;
			refreshDescription();
		}
		return c;
	}

	public void refreshDescription() {
	}

	private int identifySlot(List<AbstractMonster> pets) {
		int num = -1;
		for (int i = 0; i < pets.size(); i++) {
			if ((pets.get(i) == null) || (pets.get(i).isDying)
					|| (pets.get(i).isDead)) {
				num = i;
				break;
			}
		}
		if (num == -1) {
			int maxsize = 3;
			/*
			 * if(AbstractDungeon.player.hasRelic(SummonerMedal.ID)) { maxsize =
			 * 3; }
			 */
			if (pets.size() < maxsize) {
				num = pets.size();
			} else {
				num = 100;
			}
		}

		return num;
	}

	// public static float LEFT_X = 0.13F;
	// public static float RIGHT_X = 0.38F;
	// this.drawX = (Settings.WIDTH * x);
	// this.drawY = AbstractDungeon.floorY - 20F * Settings.scale;
	private Slot getSlot(int num) {
		// int num = identifySlot(pets);
		Slot slot = new Slot();

		switch (num) {
		case 0:
			slot.x = Settings.WIDTH * 0.13F;
			slot.y = AbstractDungeon.floorY - 20F * Settings.scale;
			break;
		case 1:
			slot.x = Settings.WIDTH * 0.38F;
			slot.y = AbstractDungeon.floorY - 20F * Settings.scale;
			break;
		case 2:
			slot.x = Settings.WIDTH * 0.48F;
			slot.y = AbstractDungeon.floorY - 20F * Settings.scale;
			break;
		case 100:
			slot.x = Settings.WIDTH * 0.13F;
			slot.y = AbstractDungeon.floorY - 20F * Settings.scale;
			break;
		default:
			slot.x = Settings.WIDTH * 0.13F;
			slot.y = AbstractDungeon.floorY - 20F * Settings.scale;
			break;
		}
		return slot;
	}

	/*
	 * public boolean canUse(AbstractPlayer p, AbstractMonster m) { boolean
	 * canUse = super.canUse(p, m); if (!canUse) { return false; }
	 * 
	 * ArrayList<AbstractMonster> pets = GiantModHelper.pets; if(pets.size() >=2
	 * ) { for(AbstractMonster pet : pets) { if ((pet == null) || (pet != null
	 * && ((pet.isDead) || (pet.isDying)))) { return true; } } } else return
	 * true; this.cantUseMessage = StringConstant.PetCallcantUse; return false;
	 * }
	 */
	/*
	 * public void upgrade() { if (!this.upgraded) { upgradeName();
	 * upgradeMagicNumber(3); this.exhaust = false; //this.rawDescription =
	 * "召唤一只" + petName + ",最多两只宠物。再使用变成所有宠物加3力量。"; initializeDescription(); } }
	 */

	private class Slot {
		float x;
		float y;
	}
}
