/**      
 * 项目名称：PetMod<br> 
 */    
/*package com.czq.mod.pet.patches.monster;

import com.czq.mod.pet.helpers.GiantModHelper;
import com.czq.mod.pet.helpers.MonsterHelper;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

**
 * 描述: TODO(描述这个类的作用)<br><br>
 * 作者： Administrator/850530595@qq.com<br>    
 * 创建时间：2019年1月10日/下午6:05:07<br>    
 * 修改人：Administrator/850530595@qq.com<br>    
 * 修改时间：2019年1月10日/下午6:05:07<br>    
 * 修改备注：<br>
 * 版本：1.0
 *
@SpirePatch(cls = "com.megacrit.cardcrawl.monsters.AbstractMonster", method = "createIntent")
public class CreateIntentPatch
{
	@SpirePrefixPatch
	public static void Prefix(final AbstractMonster monster) {
		AbstractMonster pet = GiantModHelper.getRandomTauntMinion();
		if(monster != null && pet != null)
		MonsterHelper.setTarget(monster, pet);
    }
    
}
*/