package com.czq.mod.pet.cards.weapon;

import com.czq.mod.pet.actions.IncreaseWeaponDurabilityAction;
import com.czq.mod.pet.cards.AbstractCardEnum;
import com.czq.mod.pet.cards.AbstractModCard;
import com.czq.mod.pet.helpers.GiantModHelper;
import com.czq.mod.pet.powers.PoisonWeaponPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Quiver extends AbstractModCard {
	public static final String ID = "Quiver Giant";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack
			.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;

	public Quiver() {
		super(ID, NAME, "red/skill/secondwind", "red/skill/secondWind", COST,
				DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.GIANT_COLOR, AbstractCard.CardRarity.SPECIAL,
				AbstractCard.CardTarget.NONE);
		this.baseBlock = 5;
		this.baseMagicNumber = 2;
		this.magicNumber = this.baseMagicNumber;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p,
				this.block));
		AbstractDungeon.actionManager
				.addToBottom(new IncreaseWeaponDurabilityAction(
						this.magicNumber));
	}

	public AbstractCard makeCopy() {
		return new Quiver();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(3);
			upgradeMagicNumber(1);
		}
	}
}
