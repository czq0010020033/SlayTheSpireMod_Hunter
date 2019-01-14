package com.czq.mod.pet.actions;

import com.czq.mod.pet.helpers.GiantModHelper;
import com.czq.mod.pet.powers.ManaPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;

public class IncreaseWeaponDamageAction extends AbstractGameAction {
	public IncreaseWeaponDamageAction(int amount) {
		this.actionType = AbstractGameAction.ActionType.WAIT;
		this.duration = Settings.ACTION_DUR_XFAST;
		this.amount = amount;
	}

	public void update() {
		if ((this.duration == Settings.ACTION_DUR_XFAST)) {
			GiantModHelper.weapon.damage += amount;
			this.isDone = true;
			return;
		}
		tickDuration();
	}
}
