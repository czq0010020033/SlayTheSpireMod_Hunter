package com.czq.mod.pet.cards.pet;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.math.MathUtils;
import com.czq.mod.pet.actions.pet.SpawnPetAction;
import com.czq.mod.pet.helpers.PetHelper;
import com.czq.mod.pet.monsters.pets.CultistPet;
import com.czq.mod.pet.monsters.pets.GremlinFatPet;
import com.czq.mod.pet.monsters.pets.GremlinNobPet;
import com.czq.mod.pet.monsters.pets.Pet;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
import com.megacrit.cardcrawl.actions.common.SuicideAction;
import com.megacrit.cardcrawl.actions.utility.HideHealthBarAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;

public class PetCapture extends AbstractCard {
	private static final Logger logger = LogManager.getLogger(PetCapture.class
			.getName());
	public static String ID = "PetCapture";
	  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("PetCapture");
	  public static final String NAME = cardStrings.NAME;
	  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public PetCapture() {

		super(ID, NAME, "status/void", "status/void",
				1, DESCRIPTION,
				AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS,
				AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY);
		this.baseDamage = 6;
		this.exhaust = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		if (m == null)
			return;
		boolean capture = false;
		if (m != null && PetHelper.canCapture(m) &&(m.maxHealth != 0)) {
			float percent = (float) m.currentHealth / (float) m.maxHealth;
			if (m.currentHealth < 8 || percent <= 0.2F ) {
				String cardID = m.id + "PetCall";
				if (CardLibrary.cards.containsKey(cardID)) {
					AbstractCard card = CardLibrary.getCopy(cardID);
					AbstractDungeon.player.masterDeck.addToTop(card);
					capture = true;
				}
			}
		}

		if (!capture) {
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
					new DamageInfo(AbstractDungeon.player, this.damage),
					AttackEffect.BLUNT_LIGHT));
		} else {
			AbstractDungeon.actionManager.addToBottom(new SuicideAction(m,
					false));
		}
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(0);
			upgradeDamage(3);

		}
	}

	/**
	 * 描述：
	 * 
	 * @see com.megacrit.cardcrawl.cards.AbstractCard#makeCopy()
	 */
	@Override
	public AbstractCard makeCopy() {
		return new PetCapture();
	}

}
