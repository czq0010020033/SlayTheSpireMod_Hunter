/**      
 * 项目名称：PetMod<br> 
 */
package com.czq.mod.pet.helpers;

import java.util.ArrayList;

import com.czq.mod.pet.monsters.pets.Pet;
import com.czq.mod.pet.patches.monster.MonsterAddFieldsPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * 描述: TODO(描述这个类的作用)<br>
 * <br>
 * 作者： Administrator/850530595@qq.com<br>
 * 创建时间：2019年1月10日/下午6:14:30<br>
 * 修改人：Administrator/850530595@qq.com<br>
 * 修改时间：2019年1月10日/下午6:14:30<br>
 * 修改备注：<br>
 * 版本：1.0
 */
public class MonsterHelper {

	public static void setTarget(final AbstractMonster monster,
			final AbstractMonster target) {
		if (monster != null && target != null)
			MonsterAddFieldsPatch.attack_target.set(monster, target);
	}

	public static AbstractMonster getTarget(AbstractMonster monster) {
		if (monster != null)
			return MonsterAddFieldsPatch.attack_target.get(monster);
		return null;
	}
	
	public static int getMonstersAliveCount(){
		ArrayList<AbstractMonster> monsters = AbstractDungeon.getCurrRoom().monsters.monsters;
		if(monsters == null)
			return 0;
		int count = 0;
		for(int i = 0; i < monsters.size();i++){
			if((!monsters.get(i).isDeadOrEscaped()) && (!monsters.get(i).isDying) 
					&& (!(monsters.get(i) instanceof Pet)))
				count++;
		}
		return count;
	}
}
