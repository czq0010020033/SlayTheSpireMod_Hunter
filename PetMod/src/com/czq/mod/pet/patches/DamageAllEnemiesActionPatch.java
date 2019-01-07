/**      
 * 项目名称：PetMod<br> 
 */
package com.czq.mod.pet.patches;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.graphics.Color;
import com.czq.mod.pet.cards.attack.FirstCombo;
import com.czq.mod.pet.monsters.pets.Pet;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

/**
 * 描述: TODO(描述这个类的作用)<br>
 * <br>
 * 作者： Administrator/850530595@qq.com<br>
 * 创建时间：2018年12月27日/下午2:02:20<br>
 * 修改人：Administrator/850530595@qq.com<br>
 * 修改时间：2018年12月27日/下午2:02:20<br>
 * 修改备注：<br>
 * 版本：1.0
 */
@SpirePatch(cls = "com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction", method = "update")
public class DamageAllEnemiesActionPatch {

	public static void Replace(DamageAllEnemiesAction action) {
		Logger logger = LogManager.getLogger(FirstCombo.class.getName());
		Field cardField = null;
		boolean firstFrame = false;
		try {
			cardField = action.getClass().getDeclaredField("firstFrame");
			cardField.setAccessible(true);
			firstFrame = (boolean) cardField.get(action);
		} catch (NoSuchFieldException | SecurityException
				| IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
		}

		boolean playedMusic;
		if (firstFrame) {
			try {
				logger.info("first time firstFrame :" + firstFrame
						+ ", this.firstFrame: "
						+ (boolean) cardField.get(action));
			} catch (IllegalArgumentException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
			playedMusic = false;
			int temp = AbstractDungeon.getCurrRoom().monsters.monsters.size();
			for (int i = 0; i < temp; i++) {
				if ((!((AbstractMonster) AbstractDungeon.getCurrRoom().monsters.monsters
						.get(i)).isDying)
						&& (((AbstractMonster) AbstractDungeon.getCurrRoom().monsters.monsters
								.get(i)).currentHealth > 0)
						&& (!((AbstractMonster) AbstractDungeon.getCurrRoom().monsters.monsters
								.get(i)).isEscaping)

						&& (!(((AbstractMonster) AbstractDungeon.getCurrRoom().monsters.monsters
								.get(i)) instanceof Pet))) {
					if (playedMusic) {
						AbstractDungeon.effectList
								.add(new FlashAtkImgEffect(

										((AbstractMonster) AbstractDungeon
												.getCurrRoom().monsters.monsters
												.get(i)).hb.cX,
										((AbstractMonster) AbstractDungeon
												.getCurrRoom().monsters.monsters
												.get(i)).hb.cY,
										action.attackEffect, true));
					} else {
						playedMusic = true;
						AbstractDungeon.effectList
								.add(new FlashAtkImgEffect(

										((AbstractMonster) AbstractDungeon
												.getCurrRoom().monsters.monsters
												.get(i)).hb.cX,
										((AbstractMonster) AbstractDungeon
												.getCurrRoom().monsters.monsters
												.get(i)).hb.cY,
										action.attackEffect));
					}
				}
			}
			try {
				cardField.set(action, false);
				logger.info("firstFrame :" + firstFrame + ", this.firstFrame: "
						+ (boolean) cardField.get(action));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}

		}
		try {

			Method privateStringMethod;
			privateStringMethod = AbstractGameAction.class
					.getDeclaredMethod("tickDuration");
			privateStringMethod.setAccessible(true);
			privateStringMethod.invoke(action);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		if (action.isDone) {
			logger.info("action.isDone .");

			for (AbstractPower p : AbstractDungeon.player.powers) {
				p.onDamageAllEnemies(action.damage);
			}
			int temp = AbstractDungeon.getCurrRoom().monsters.monsters.size();
			if (temp != action.damage.length) {
				logger.info("出现异常，怪物数量：" + temp + ", 伤害数量: "
						+ action.damage.length);
			} else {
				for (int i = 0; i < temp; i++) {
					if (!((AbstractMonster) AbstractDungeon.getCurrRoom().monsters.monsters
							.get(i)).isDeadOrEscaped()
							&& (!(((AbstractMonster) AbstractDungeon
									.getCurrRoom().monsters.monsters.get(i)) instanceof Pet))) {
						if (action.attackEffect == AbstractGameAction.AttackEffect.POISON) {
							((AbstractMonster) AbstractDungeon.getCurrRoom().monsters.monsters
									.get(i)).tint.color = Color.CHARTREUSE
									.cpy();
							((AbstractMonster) AbstractDungeon.getCurrRoom().monsters.monsters
									.get(i)).tint
									.changeColor(Color.WHITE.cpy());
						} else if (action.attackEffect == AbstractGameAction.AttackEffect.FIRE) {
							((AbstractMonster) AbstractDungeon.getCurrRoom().monsters.monsters
									.get(i)).tint.color = Color.RED.cpy();
							((AbstractMonster) AbstractDungeon.getCurrRoom().monsters.monsters
									.get(i)).tint
									.changeColor(Color.WHITE.cpy());
						}
						((AbstractMonster) AbstractDungeon.getCurrRoom().monsters.monsters
								.get(i)).damage(new DamageInfo(action.source,
								action.damage[i], action.damageType));
					}
					logger.info("action.isDone . mid" + ", size: " + temp
							+ ",action.damage.length:" +

							action.damage.length + ",i:" + i);
				}
			}
			if (AbstractDungeon.getCurrRoom().monsters
					.areMonstersBasicallyDead()) {
				AbstractDungeon.actionManager.clearPostCombatActions();
			}
			if (!Settings.FAST_MODE) {
				AbstractDungeon.actionManager.addToTop(new WaitAction(0.1F));
			}
			logger.info("action.isDone . last");
		}
	}
}
