package com.czq.mod.pet.cards.attack;

import com.badlogic.gdx.graphics.Color;
import com.czq.mod.pet.helpers.StringConstant;
import com.czq.mod.pet.powers.EaglePower;
import com.czq.mod.pet.powers.UnlimitedComboPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.PummelDamageAction;
import com.megacrit.cardcrawl.actions.utility.ShakeScreenAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.DieDieDieEffect;

public class TheEndGiant extends AbstractComboAttackCard {
	public static final String ID = "The End Giant";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack
			.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	public static final int LEVEL = 5;

	public TheEndGiant() {
		super(ID, NAME, "red/attack/pummel", 2, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCard.CardColor.RED,
				AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ALL_ENEMY, LEVEL);

		this.baseDamage = 8;
		this.baseBlock= 50;
		this.baseMagicNumber = 30;
		this.magicNumber = this.baseMagicNumber;
		  this.isMultiDamage = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		  AbstractDungeon.actionManager.addToBottom(new VFXAction(new BorderLongFlashEffect(Color.LIGHT_GRAY.cpy())));
		    AbstractDungeon.actionManager.addToBottom(new VFXAction(new DieDieDieEffect(), 1.0F));
		    AbstractDungeon.actionManager.addToBottom(new ShakeScreenAction(0.0F, ScreenShake.ShakeDur.MED, ScreenShake.ShakeIntensity.HIGH));
		
		    AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
		    AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

	}

	public void applyCombo() {
		super.applyCombo();
		if(this.combo){
		      this.rawDescription = (DESCRIPTION + StringConstant.Available);
		      initializeDescription();
		}
		else{
			  this.rawDescription = DESCRIPTION;
		      initializeDescription();
		}
	}

	public AbstractCard makeCopy() {
		return new TheEndGiant();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(4);
			upgradeDamage(20);
		}
	}
}
