/**      
 * 项目名称：PetMod<br> 
 */    
package com.czq.mod.pet.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.czq.mod.pet.cards.attack.EagleFirst;

/**
 * 描述: TODO(描述这个类的作用)<br><br>
 * 作者： Administrator/850530595@qq.com<br>    
 * 创建时间：2019年1月11日/上午1:07:09<br>    
 * 修改人：Administrator/850530595@qq.com<br>    
 * 修改时间：2019年1月11日/上午1:07:09<br>    
 * 修改备注：<br>
 * 版本：1.0
 */
public class LogHelper {
	private static final Logger logger = LogManager
			.getLogger(LogHelper.class.getName());
	public static boolean log = true;
	public static void  info(String msg){
		if(log)
			logger.info(msg);
	}
}
