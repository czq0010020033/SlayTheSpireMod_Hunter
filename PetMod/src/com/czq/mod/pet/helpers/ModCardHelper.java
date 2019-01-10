/**      
 * 项目名称：PetMod<br> 
 */
package com.czq.mod.pet.helpers;

import basemod.BaseMod;

import com.czq.mod.pet.cards.attack.BloodAttack;
import com.czq.mod.pet.cards.attack.ComboConnect;
import com.czq.mod.pet.cards.attack.ComboRecycle;
import com.czq.mod.pet.cards.attack.CooperationAttack;
import com.czq.mod.pet.cards.attack.EagleFirst;
import com.czq.mod.pet.cards.attack.EagleSecond;
import com.czq.mod.pet.cards.attack.EagleThird;
import com.czq.mod.pet.cards.attack.EchoAttack;
import com.czq.mod.pet.cards.attack.PummelGiant;
import com.czq.mod.pet.cards.attack.RevengeAttack;
import com.czq.mod.pet.cards.attack.Strike_Giant;
import com.czq.mod.pet.cards.attack.StruggleAttack;
import com.czq.mod.pet.cards.attack.TheEndGiant;
import com.czq.mod.pet.cards.power.BeastMaster;
import com.czq.mod.pet.cards.power.BloodyGiant;
import com.czq.mod.pet.cards.power.ComboForm;
import com.czq.mod.pet.cards.power.CultistForm;
import com.czq.mod.pet.cards.power.InfiniteCombo;
import com.czq.mod.pet.cards.power.RecoverHP;
import com.czq.mod.pet.cards.power.RetainCombo;
import com.czq.mod.pet.cards.power.RuptureGiant;
import com.czq.mod.pet.cards.power.SnakeForm;
import com.czq.mod.pet.cards.power.SpiritOfBeast;
import com.czq.mod.pet.cards.power.TimeTravel;
import com.czq.mod.pet.cards.power.UnlimitedCombo;
import com.czq.mod.pet.cards.skill.BackflipGiant;
import com.czq.mod.pet.cards.skill.Backward;
import com.czq.mod.pet.cards.skill.BloodDefend;
import com.czq.mod.pet.cards.skill.BurningPactGiant;
import com.czq.mod.pet.cards.skill.ComboDefend;
import com.czq.mod.pet.cards.skill.DeathExplode;
import com.czq.mod.pet.cards.skill.DeathProphecy;
import com.czq.mod.pet.cards.skill.Defend_Giant;
import com.czq.mod.pet.cards.skill.DiscoverCombo;
import com.czq.mod.pet.cards.skill.DiscoverPet;
import com.czq.mod.pet.cards.skill.DoubleMana;
import com.czq.mod.pet.cards.skill.DuplicateHand;
import com.czq.mod.pet.cards.skill.EchoDefend;
import com.czq.mod.pet.cards.skill.FanaticCultist;
import com.czq.mod.pet.cards.skill.HealWind;
import com.czq.mod.pet.cards.skill.ManaGrowth;
import com.czq.mod.pet.cards.skill.MeditateGiant;
import com.czq.mod.pet.cards.skill.OneBlood;
import com.czq.mod.pet.cards.skill.Prayer;
import com.czq.mod.pet.cards.skill.RandomDeathRattle;
import com.czq.mod.pet.cards.skill.SacrificeSummons;
import com.czq.mod.pet.cards.skill.SeekCombo;
import com.czq.mod.pet.cards.skill.SeekPet;
import com.czq.mod.pet.cards.skill.StormOfBlood;
import com.czq.mod.pet.cards.skill.UpUpUp;
import com.czq.mod.pet.cards.status.BloodGiant;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

/**
 * 描述: TODO(描述这个类的作用)<br>
 * <br>
 * 作者： Administrator/850530595@qq.com<br>
 * 创建时间：2018年10月16日/下午9:03:13<br>
 * 修改人：Administrator/850530595@qq.com<br>
 * 修改时间：2018年10月16日/下午9:03:13<br>
 * 修改备注：<br>
 * 版本：1.0
 */
public class ModCardHelper {

	public static void addCardToLibrary() {
		PetHelper.addPetCardToLibrary();

/*		BaseMod.addCard(new LeftHookFist());
		UnlockTracker.unlockCard("LeftHookFist");
		BaseMod.addCard(new RightHookFist());
		UnlockTracker.unlockCard("RightHookFist");
		BaseMod.addCard(new FinalFist());
		UnlockTracker.unlockCard("FinalFist");
*/
		//attack
		BaseMod.addCard(new EagleFirst());
		UnlockTracker.unlockCard(EagleFirst.ID);
		BaseMod.addCard(new EagleSecond());
		UnlockTracker.unlockCard(EagleSecond.ID);
		BaseMod.addCard(new EagleThird());
		UnlockTracker.unlockCard(EagleThird.ID);
		BaseMod.addCard(new BloodAttack());
		UnlockTracker.unlockCard(BloodAttack.ID);
		BaseMod.addCard(new ComboConnect());
		UnlockTracker.unlockCard(ComboConnect.ID);
		BaseMod.addCard(new RevengeAttack());
		UnlockTracker.unlockCard(RevengeAttack.ID);
		BaseMod.addCard(new PummelGiant());
		UnlockTracker.unlockCard(PummelGiant.ID);
		BaseMod.addCard(new EchoAttack());
		UnlockTracker.unlockCard(EchoAttack.ID);
		BaseMod.addCard(new Strike_Giant());
		UnlockTracker.unlockCard(Strike_Giant.ID);
		BaseMod.addCard(new ComboRecycle());
		UnlockTracker.unlockCard(ComboRecycle.ID);
		BaseMod.addCard(new TheEndGiant());
		UnlockTracker.unlockCard(TheEndGiant.ID);
		BaseMod.addCard(new StruggleAttack());
		UnlockTracker.unlockCard(StruggleAttack.ID);
	//	BaseMod.addCard(new CooperationAttack());
//		UnlockTracker.unlockCard(CooperationAttack.ID);
		//power card
		BaseMod.addCard(new RetainCombo());
		UnlockTracker.unlockCard(RetainCombo.ID);
		BaseMod.addCard(new UnlimitedCombo());
		UnlockTracker.unlockCard(UnlimitedCombo.ID);
		BaseMod.addCard(new SnakeForm());
		UnlockTracker.unlockCard(SnakeForm.ID);
		BaseMod.addCard(new CultistForm());
		UnlockTracker.unlockCard(CultistForm.ID);
		BaseMod.addCard(new RecoverHP());
		UnlockTracker.unlockCard(RecoverHP.ID);
		BaseMod.addCard(new InfiniteCombo());
		UnlockTracker.unlockCard(InfiniteCombo.ID);
		BaseMod.addCard(new TimeTravel());
		UnlockTracker.unlockCard(TimeTravel.ID);
		BaseMod.addCard(new SpiritOfBeast());
		UnlockTracker.unlockCard(SpiritOfBeast.ID);
		BaseMod.addCard(new ComboForm());
		UnlockTracker.unlockCard(ComboForm.ID);
		BaseMod.addCard(new BeastMaster());
		UnlockTracker.unlockCard(BeastMaster.ID);
		BaseMod.addCard(new BloodyGiant());
		UnlockTracker.unlockCard(BloodyGiant.ID);
		BaseMod.addCard(new RuptureGiant());
		UnlockTracker.unlockCard(RuptureGiant.ID);
		//skill
		BaseMod.addCard(new HealWind());
		UnlockTracker.unlockCard(HealWind.ID);
		BaseMod.addCard(new Prayer());
		UnlockTracker.unlockCard(Prayer.ID);
		BaseMod.addCard(new OneBlood());
		UnlockTracker.unlockCard(OneBlood.ID);
		BaseMod.addCard(new SeekCombo());
		UnlockTracker.unlockCard(SeekCombo.ID);
		BaseMod.addCard(new DiscoverCombo());
		UnlockTracker.unlockCard(DiscoverCombo.ID);
		BaseMod.addCard(new DoubleMana());
		UnlockTracker.unlockCard(DoubleMana.ID);
		BaseMod.addCard(new DiscoverPet());
		UnlockTracker.unlockCard(DiscoverPet.ID);
		BaseMod.addCard(new UpUpUp());
		UnlockTracker.unlockCard(UpUpUp.ID);
	//	BaseMod.addCard(new DoubleHp());
	//	UnlockTracker.unlockCard(DoubleHp.ID);
		BaseMod.addCard(new DeathExplode());
		UnlockTracker.unlockCard(DeathExplode.ID);
		BaseMod.addCard(new RandomDeathRattle());
		UnlockTracker.unlockCard(RandomDeathRattle.ID);
		BaseMod.addCard(new SacrificeSummons());
		UnlockTracker.unlockCard(SacrificeSummons.ID);
		BaseMod.addCard(new BloodDefend());
		UnlockTracker.unlockCard(BloodDefend.ID);
		BaseMod.addCard(new DuplicateHand());
		UnlockTracker.unlockCard(DuplicateHand.ID);
		BaseMod.addCard(new ManaGrowth());
		UnlockTracker.unlockCard(ManaGrowth.ID);
		BaseMod.addCard(new BackflipGiant());
		UnlockTracker.unlockCard(BackflipGiant.ID);
		BaseMod.addCard(new DeathProphecy());
		UnlockTracker.unlockCard(DeathProphecy.ID);
		BaseMod.addCard(new BurningPactGiant());
		UnlockTracker.unlockCard(BurningPactGiant.ID);
		BaseMod.addCard(new EchoDefend());
		UnlockTracker.unlockCard(EchoDefend.ID);
		BaseMod.addCard(new ComboDefend());
		UnlockTracker.unlockCard(ComboDefend.ID);
		BaseMod.addCard(new Backward());
		UnlockTracker.unlockCard(Backward.ID);
		BaseMod.addCard(new FanaticCultist());
		UnlockTracker.unlockCard(FanaticCultist.ID);
		BaseMod.addCard(new SeekPet());
		UnlockTracker.unlockCard(SeekPet.ID);
		BaseMod.addCard(new Defend_Giant());
		UnlockTracker.unlockCard(Defend_Giant.ID);
		BaseMod.addCard(new MeditateGiant());
		UnlockTracker.unlockCard(MeditateGiant.ID);
		BaseMod.addCard(new StormOfBlood());
		UnlockTracker.unlockCard(StormOfBlood.ID);
		//status
		BaseMod.addCard(new BloodGiant());
		UnlockTracker.unlockCard(BloodGiant.ID);
		
	}
}
