package com.czq.mod.pet.cards.skill;

import com.czq.mod.pet.cards.AbstractEchoCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class EchoDefend extends AbstractEchoCard {
	public static final String ID = "Echo Defend Giant";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack
			.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
	private static final int COST = 1;
	private static final int BLOCK_AMT = 5;

	public EchoDefend() {
		super(ID, NAME, "blue/skill/defend", 1, DESCRIPTION,
				AbstractCard.CardType.SKILL, AbstractCard.CardColor.BLUE,
				AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);

		this.baseBlock = 9;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p,
				this.block));
		super.use(p, m);
	}

	public AbstractCard makeCopy() {
		AbstractCard c = new EchoDefend();
		return makeEchoCopy(c);
	}

	

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(3);
		}
	}

	/**
	* 描述： 
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
