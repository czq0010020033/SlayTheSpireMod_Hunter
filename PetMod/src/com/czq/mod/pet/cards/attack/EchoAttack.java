package com.czq.mod.pet.cards.attack;

import com.czq.mod.pet.cards.AbstractEchoCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class EchoAttack extends AbstractEchoCard {
	public static final String ID = "Echo Attack";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack
			.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
	private static final int COST = 1;
	private static final int BLOCK_AMT = 5;

	public EchoAttack() {
		super(ID, NAME, "blue/skill/defend", 1, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCard.CardColor.BLUE,
				AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY);

		this.baseDamage = 12;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.BLUNT_LIGHT));
		super.use(p, m);
	}

	public AbstractCard makeCopy() {
		AbstractCard c = new EchoAttack();
		return makeEchoCopy(c);
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(4);
		}
	}

	/**
	 * 描述：
	 * 
	 * @see com.czq.mod.pet.cards.AbstractEchoCard#refreshDesription()
	 */
	@Override
	public void refreshDescription() {
		if (exhaust && isEthereal) {
			this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[0];
			this.initializeDescription();
		}
	}
}
