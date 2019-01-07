/**      
 * 项目名称：PetMod<br> 
 */    
package com.czq.mod.pet.patches.monstergroup;

import com.czq.mod.pet.helpers.GiantModHelper;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.monsters.MonsterGroup;

/**
 * 描述: TODO(描述这个类的作用)<br><br>
 * 作者： Administrator/850530595@qq.com<br>    
 * 创建时间：2018年12月29日/下午4:51:33<br>    
 * 修改人：Administrator/850530595@qq.com<br>    
 * 修改时间：2018年12月29日/下午4:51:33<br>    
 * 修改备注：<br>
 * 版本：1.0
 */
@SpirePatch(cls="com.megacrit.cardcrawl.monsters.MonsterGroup", method="init")
public class InitPatch {
	@SpirePrefixPatch
	public static void Prefix(MonsterGroup group){
		 GiantModHelper.pets.clear();
	}
}
