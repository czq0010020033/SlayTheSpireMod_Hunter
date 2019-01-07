/**      
 * 项目名称：PetMod<br> 
 */    
package com.czq.mod.pet.patches.monstergroup;

import java.util.ArrayList;

import com.badlogic.gdx.math.MathUtils;
import com.czq.mod.pet.helpers.PetHelper;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.random.Random;

/**
 * 描述: TODO(描述这个类的作用)<br><br>
 * 作者： Administrator/850530595@qq.com<br>    
 * 创建时间：2018年12月29日/下午5:05:25<br>    
 * 修改人：Administrator/850530595@qq.com<br>    
 * 修改时间：2018年12月29日/下午5:05:25<br>    
 * 修改备注：<br>
 * 版本：1.0
 */
@SpirePatch(cls="com.megacrit.cardcrawl.monsters.MonsterGroup", method="getRandomMonster",
paramtypez = {AbstractMonster.class,boolean.class})
public class GetRandomMonsterPatch2 {
	public static AbstractMonster Replace(MonsterGroup group,AbstractMonster exception,
			boolean aliveOnly) {
		if (group.areMonstersBasicallyDead()) {
			return null;
		}
		if (exception == null) {
			if (aliveOnly) {
				ArrayList<AbstractMonster> tmp = new ArrayList();
				for (AbstractMonster m : group.monsters) {
					if ((!m.halfDead) && (!m.isDying) && (!m.isEscaping)
							&& (!PetHelper.isPet(m))) {
						tmp.add(m);
					}
				}
				if (tmp.size() <= 0) {
					return null;
				}
				return (AbstractMonster) tmp.get(MathUtils.random(0,
						tmp.size() - 1));
			}
			ArrayList<AbstractMonster> tmp = new ArrayList();
			for (AbstractMonster m : group.monsters) {
				if (!PetHelper.isPet(m)) {
					tmp.add(m);
				}
			}
			if (tmp.size() <= 0) {
				return null;
			}
			return (AbstractMonster) tmp
					.get(MathUtils.random(0, tmp.size() - 1));
			// return (AbstractMonster)group.monsters.get(MathUtils.random(0,
			// group.monsters.size() - 1));
		}
		if (group.monsters.size() == 1) {
			return (AbstractMonster) group.monsters.get(0);
		}
		if (aliveOnly) {
			ArrayList<AbstractMonster> tmp = new ArrayList();
			for (AbstractMonster m : group.monsters) {
				if ((!m.halfDead) && (!m.isDying) && (!m.isEscaping)
						&& (!exception.equals(m) && (!PetHelper.isPet(m)))) {
					tmp.add(m);
				}
			}
			if (tmp.size() == 0) {
				return null;
			}
			return (AbstractMonster) tmp
					.get(MathUtils.random(0, tmp.size() - 1));
		}
		ArrayList<AbstractMonster> tmp = new ArrayList();
		for (AbstractMonster m : group.monsters) {
			if (!exception.equals(m) && (!PetHelper.isPet(m))) {
				tmp.add(m);
			}
		}
		return (AbstractMonster) tmp.get(MathUtils.random(0, tmp.size() - 1));
	}
}