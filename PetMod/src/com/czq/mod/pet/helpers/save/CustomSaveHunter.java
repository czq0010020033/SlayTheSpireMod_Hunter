/**      
 * 项目名称：PetMod<br> 
 */    
package com.czq.mod.pet.helpers.save;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import basemod.abstracts.CustomSavable;

import com.czq.mod.pet.cards.attack.EagleFirst;
import com.czq.mod.pet.monsters.pets.DefectPet;
import com.google.gson.JsonElement;

/**
 * 描述: TODO(描述这个类的作用)<br><br>
 * 作者： Administrator/850530595@qq.com<br>    
 * 创建时间：2019年1月9日/下午5:12:35<br>    
 * 修改人：Administrator/850530595@qq.com<br>    
 * 修改时间：2019年1月9日/下午5:12:35<br>    
 * 修改备注：<br>
 * 版本：1.0
 */
public class CustomSaveHunter implements CustomSavable<SaveBean> {
	private static final Logger logger = LogManager
			.getLogger(CustomSaveHunter.class.getName());

	/**
	* 描述： 
	* @see basemod.abstracts.CustomSavable#onLoad(java.lang.Object)
	*/ 
	@Override
	public void onLoad(SaveBean save) {
		logger.info("load hunter file.");
		if(save != null){
			DefectPet.seenDefect = save.seenDefect;
		}
		else{
			logger.info("error. hunter save is null.");
		}
	}

	/**
	* 描述： 
	* @see basemod.abstracts.CustomSavable#onSave()
	*/ 
	@Override
	public SaveBean onSave() {
		logger.info("save hunter file.");
		SaveBean save = new SaveBean();
		save.seenDefect = DefectPet.seenDefect;
		return save;
	}

}
