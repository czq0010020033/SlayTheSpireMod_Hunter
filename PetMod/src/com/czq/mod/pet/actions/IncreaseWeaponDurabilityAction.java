package com.czq.mod.pet.actions;

import com.czq.mod.pet.helpers.GiantModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;

public class IncreaseWeaponDurabilityAction extends AbstractGameAction {

	public IncreaseWeaponDurabilityAction(int amount) {
		this.actionType = AbstractGameAction.ActionType.WAIT;
		this.duration = Settings.ACTION_DUR_XFAST;
		this.amount = amount;
	}

	public void update() {
		if ((this.duration == Settings.ACTION_DUR_XFAST)) {
			GiantModHelper.weapon.durability += amount;
			if (amount > 0)
				GiantModHelper.weapon.flash(true);
			else
				GiantModHelper.weapon.flash(false);
		
			this.isDone = true;
			return;
		}
		tickDuration();
	}
}
