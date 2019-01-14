package com.czq.mod.pet.cards.skill;

import com.badlogic.gdx.math.RandomXS128;
import com.czq.mod.pet.actions.DoubleManaAction;
import com.czq.mod.pet.cards.AbstractCardEnum;
import com.czq.mod.pet.cards.AbstractModCard;
import com.czq.mod.pet.helpers.GiantModHelper;
import com.czq.mod.pet.helpers.StringConstant;
import com.czq.mod.pet.monsters.pets.Pet;
import com.czq.mod.pet.powers.DeathRattleExplodePower;
import com.czq.mod.pet.powers.DeathRattleHealPower;
import com.czq.mod.pet.powers.DeathRattleStrengthPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.random.Random;

public class RandomDeathRattle extends AbstractModCard {
	public static final String ID = "Random Death Rattle";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack
			.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;

	public RandomDeathRattle() {
		super(ID, NAME, "red/skill/secondwind", "red/skill/secondWind", COST,
				DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.GIANT_COLOR, AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ENEMY);
		this.baseMagicNumber = 1;
		this.magicNumber = this.baseMagicNumber;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,
				m,
				new DeathRattleExplodePower(m, m.maxHealth * magicNumber),
				m.maxHealth * magicNumber));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,
				m, new DeathRattleHealPower(m, magicNumber * 6),
				magicNumber * 6));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,
				m, new DeathRattleStrengthPower(m, magicNumber * 2),
				magicNumber * 2));
	}

	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if (!canUse) {
			return false;
		}
		if (m != null && m instanceof Pet)
			return true;
		else {
			this.cantUseMessage = StringConstant.cantUseMessage;
			return false;
		}
	}

	public AbstractCard makeCopy() {
		return new RandomDeathRattle();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
		}
	}
}
