package com.czq.mod.pet.patches;

import com.czq.mod.pet.helpers.GiantModHelper;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.common.EnableEndTurnButtonAction;

/**
 * 项目名称：PetMod<br>
 */
/*
 * package com.czq.mod.pet.patches;
 * 
 * import com.czq.mod.pet.helpers.GiantModHelper; import
 * com.evacipated.cardcrawl.modthespire.lib.SpirePatch; import
 * com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch; import
 * com.megacrit.cardcrawl.actions.common.EnableEndTurnButtonAction;
 * 
 * /** 描述: TODO(描述这个类的作用)<br><br> 作者： Administrator/850530595@qq.com<br>
 * 创建时间：2019年1月11日/上午11:41:41<br> 修改人：Administrator/850530595@qq.com<br>
 * 修改时间：2019年1月11日/上午11:41:41<br> 修改备注：<br> 版本：1.0
 */
@SpirePatch(cls = "com.megacrit.cardcrawl.actions.common.EnableEndTurnButtonAction", method = "update")
public class EnableEndTurnButtonActionPatch {
	@SpirePrefixPatch
	public static void Prefix(EnableEndTurnButtonAction action) {
		if (GiantModHelper.weapon != null)
			GiantModHelper.weapon.enable();
	}
}
