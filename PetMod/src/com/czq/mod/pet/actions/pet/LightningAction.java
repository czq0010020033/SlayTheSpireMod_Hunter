package com.czq.mod.pet.actions.pet;

import com.czq.mod.pet.monsters.pets.DefectPet;
import com.czq.mod.pet.monsters.pets.Pet;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

public class LightningAction extends AbstractGameAction {
	private DamageInfo info;

	public LightningAction(DamageInfo info) {
		this.info = info;
		this.actionType = AbstractGameAction.ActionType.DAMAGE;
		this.attackEffect = AbstractGameAction.AttackEffect.NONE;
	}

	public void update() {

		float speedTime = 0.1F;
		if (Settings.FAST_MODE) {
			speedTime = 0.0F;
		}
		if (info.owner instanceof DefectPet) {
			AbstractDungeon.actionManager.addToTop(new DamageAction(
					AbstractDungeon.player, this.info,
					AbstractGameAction.AttackEffect.NONE, true));
			AbstractDungeon.actionManager.addToTop(new VFXAction(
					new LightningEffect(AbstractDungeon.player.drawX,
							AbstractDungeon.player.drawY), speedTime));
			AbstractDungeon.actionManager.addToTop(new SFXAction(
					"ORB_LIGHTNING_EVOKE"));
		} else {
			AbstractDungeon.actionManager.addToTop(new DamageAllEnemiesAction(
					AbstractDungeon.player, DamageInfo.createDamageMatrix(
							this.info.base, true, true),
					DamageInfo.DamageType.THORNS,
					AbstractGameAction.AttackEffect.NONE));
			for (AbstractMonster m3 : AbstractDungeon.getMonsters().monsters) {
				if ((!m3.isDeadOrEscaped()) && (!m3.halfDead)
						&& (!(m3 instanceof Pet))) {
					AbstractDungeon.actionManager
							.addToTop(new VFXAction(new LightningEffect(
									m3.drawX, m3.drawY), speedTime));
				}
			}
			AbstractDungeon.actionManager.addToTop(new SFXAction(
					"ORB_LIGHTNING_EVOKE"));
		}
		this.isDone = true;
	}

}
