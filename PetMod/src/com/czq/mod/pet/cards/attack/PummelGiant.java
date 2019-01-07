package com.czq.mod.pet.cards.attack;

import com.czq.mod.pet.powers.EaglePower;
import com.czq.mod.pet.powers.UnlimitedComboPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.PummelDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PummelGiant extends AbstractComboAttackCard {
	public static final String ID = "Pummel Giant";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack
			.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
	private static final int COST = 2;
	private static final int ATTACK_DMG = 2;
	public static final int LEVEL = 2;

	public PummelGiant() {
		super(ID, NAME, "red/attack/pummel", 2, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCard.CardColor.RED,
				AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.ENEMY, LEVEL);

		this.baseDamage = 6;
		this.baseMagicNumber = 3;
		this.magicNumber = this.baseMagicNumber;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new PummelDamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn)));

		AbstractDungeon.actionManager.addToBottom(new PummelDamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn)));
		if (this.combo) {
			AbstractDungeon.actionManager.addToBottom(new PummelDamageAction(m,
					new DamageInfo(p, this.damage, this.damageTypeForTurn)));
			AbstractDungeon.actionManager.addToBottom(new PummelDamageAction(m,
					new DamageInfo(p, this.damage, this.damageTypeForTurn)));
		}

		if (this.upgraded) {
			AbstractDungeon.actionManager.addToBottom(new PummelDamageAction(m,
					new DamageInfo(p, this.damage, this.damageTypeForTurn)));
		}
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.BLUNT_HEAVY));
	}

	public void applyCombo() {
		boolean combo = false;
		for (AbstractPower p : AbstractDungeon.player.powers) {
			if ((p instanceof EaglePower && p.amount == level - 1)
					|| (p instanceof UnlimitedComboPower)) {
				// this.isDamageModified = true;
				combo = true;
				break;
			}
		}
		this.combo = combo;
		if (this.combo) {
			if (this.upgraded)
			{
				this.baseMagicNumber = 6;
				this.magicNumber = this.baseMagicNumber;
			}
				
			else
			{
				this.baseMagicNumber = 5;
				this.magicNumber = this.baseMagicNumber;
			}
			this.rawDescription = (DESCRIPTION + EXTENDED_DESCRIPTION[0]);
			initializeDescription();
		} else {
			if (this.upgraded)
			{
				this.baseMagicNumber = 4;
				this.magicNumber = this.baseMagicNumber;
			}
			else
			{
				this.baseMagicNumber = 3;
				this.magicNumber = this.baseMagicNumber;
			}
			this.rawDescription = DESCRIPTION;
			initializeDescription();
		}

	}

	public AbstractCard makeCopy() {
		return new PummelGiant();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(1);
		}
	}
}
