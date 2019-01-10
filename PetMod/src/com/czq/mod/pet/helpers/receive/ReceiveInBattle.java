/**      
 * 项目名称：PetMod<br> 
 */
package com.czq.mod.pet.helpers.receive;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import basemod.BaseMod;
import basemod.interfaces.OnStartBattleSubscriber;
import basemod.interfaces.PostBattleSubscriber;
import basemod.interfaces.PostDeathSubscriber;

import com.czq.mod.pet.actions.pet.SpawnPetAction;
import com.czq.mod.pet.helpers.GiantModHelper;
import com.czq.mod.pet.helpers.PetHelper;
import com.czq.mod.pet.monsters.pets.DefectPet;
import com.czq.mod.pet.monsters.pets.Pet;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;

/**
 * 描述: TODO(描述这个类的作用)<br>
 * <br>
 * 作者： Administrator/850530595@qq.com<br>
 * 创建时间：2019年1月9日/下午5:48:21<br>
 * 修改人：Administrator/850530595@qq.com<br>
 * 修改时间：2019年1月9日/下午5:48:21<br>
 * 修改备注：<br>
 * 版本：1.0
 */
@SpireInitializer
public class ReceiveInBattle implements PostBattleSubscriber,
		OnStartBattleSubscriber,PostDeathSubscriber {
	private static final Logger logger = LogManager
			.getLogger(ReceiveInBattle.class.getName());
	
	public static boolean tempSeenDefect = false;

	public static void initialize() {
		new ReceiveInBattle();
	}
	
	public ReceiveInBattle() {
		BaseMod.subscribe(this);
		logger.info("subscribe receiveinbattle");
	}

	/**
	 * 描述：
	 * 
	 * @see basemod.interfaces.PostBattleSubscriber#receivePostBattle(com.megacrit.cardcrawl.rooms.AbstractRoom)
	 */
	@Override
	public void receivePostBattle(AbstractRoom room) {
		if ((room instanceof MonsterRoomElite) && (!DefectPet.seenDefect)
				&& tempSeenDefect) {
			tempSeenDefect = false;
			DefectPet.seenDefect = true;
			DefectPet.harmonious = true;
		}
	}

	/**
	 * 描述：
	 * 
	 * @see basemod.interfaces.OnStartBattleSubscriber#receiveOnBattleStart(com.megacrit.cardcrawl.rooms.AbstractRoom)
	 */
	@Override
	public void receiveOnBattleStart(AbstractRoom room) {
		GiantModHelper.pets.clear();
		logger.info("On battle start.");
		if ((room instanceof MonsterRoomElite) && (!DefectPet.seenDefect))

		{
			logger.info("create defect");
			tempSeenDefect = true;
			DefectPet.harmonious = true;
			AbstractMonster defect = PetHelper.getPetInstance(DefectPet.ID,
					Settings.WIDTH * 0.13F, AbstractDungeon.floorY - 20F
							* Settings.scale);
			GiantModHelper.pets.add(defect);
			if (defect instanceof Pet)
				AbstractDungeon.actionManager.addToBottom(new SpawnPetAction(
						(Pet) defect, 0, true));
		}
	}

	/**
	* 描述： 
	* @see basemod.interfaces.PostDeathSubscriber#receivePostDeath()
	*/ 
	@Override
	public void receivePostDeath() {
		logger.info("On Death.");
		DefectPet.seenDefect = false;
		DefectPet.harmonious = true;
	}
}
