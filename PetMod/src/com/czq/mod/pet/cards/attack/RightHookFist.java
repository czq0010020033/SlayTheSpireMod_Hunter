package com.czq.mod.pet.cards.attack;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.math.MathUtils;
import com.czq.mod.pet.actions.pet.SpawnPetAction;
import com.czq.mod.pet.cards.AbstractCardEnum;
import com.czq.mod.pet.helpers.PetHelper;
import com.czq.mod.pet.monsters.pets.ChampPet;
import com.czq.mod.pet.monsters.pets.CultistPet;
import com.czq.mod.pet.monsters.pets.GremlinFatPet;
import com.czq.mod.pet.monsters.pets.GremlinNobPet;
import com.czq.mod.pet.monsters.pets.Pet;
import com.czq.mod.pet.powers.ComboPower;
import com.czq.mod.pet.powers.FistPower;
import com.czq.mod.pet.powers.UnlimitedComboPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.red.HeavyBlade;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class RightHookFist extends AbstractCard {
	private static final Logger logger = LogManager
			.getLogger(RightHookFist.class.getName());
	public static final String ID = "RightHookFist";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack
			.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;

	public RightHookFist() {
		super(ID, NAME, "colorless/skill/finesse", "colorless/skill/finesse",
				1, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.GIANT_COLOR, AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.ENEMY);
		this.baseDamage = 6;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_VERTICAL));

	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.exhaust = false;
			// this.rawDescription = "召唤一只" + petName +
			// ",最多两只宠物。再使用变成所有宠物加3力量。";
			initializeDescription();
		}
	}

	/**
	 * 描述：
	 * 
	 * @see com.megacrit.cardcrawl.cards.AbstractCard#makeCopy()
	 */
	@Override
	public AbstractCard makeCopy() {
		return new RightHookFist();
	}

	public void calculateCardDamage(AbstractMonster mo) {
			for (AbstractPower p : mo.powers) {
				if ((p instanceof ComboPower && p.amount == 1) ) {
					((FistPower) p).isCombo = true;
				}
			}

		super.calculateCardDamage(mo);
		if (mo != null && (mo.hasPower(FistPower.POWER_ID))) {
			this.rawDescription = DESCRIPTION + " NL Combo";
			initializeDescription();
		} else {
			this.rawDescription = DESCRIPTION;
			initializeDescription();
		}
	}

}
