/**      
 * 项目名称：PetMod<br> 
 */    
package com.czq.mod.pet.patches.monster;

import com.czq.mod.pet.helpers.GiantModHelper;
import com.czq.mod.pet.helpers.MonsterHelper;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


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
