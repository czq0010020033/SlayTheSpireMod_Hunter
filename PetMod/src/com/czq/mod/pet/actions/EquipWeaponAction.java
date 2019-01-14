package com.czq.mod.pet.actions;

import com.czq.mod.pet.helpers.GiantModHelper;
import com.czq.mod.pet.powers.ManaPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;

public class EquipWeaponAction extends AbstractGameAction {
	private int durability;
	private int damage;

	public EquipWeaponAction(int durability, int damage) {
		this.actionType = AbstractGameAction.ActionType.WAIT;
		this.duration = Settings.ACTION_DUR_XFAST;
		this.damage = damage;
		this.durability = durability;
	}

	public void update() {
		if ((this.duration == Settings.ACTION_DUR_XFAST)) {
			GiantModHelper.weapon.init(durability, damage);
			this.isDone = true;
			return;
		}
		tickDuration();
	}
}
