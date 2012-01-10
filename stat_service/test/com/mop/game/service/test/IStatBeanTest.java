package com.mop.game.service.test;

import org.junit.Test;

import com.mop.game.service.bean.ChargeBean;

public class IStatBeanTest 
{
	@Test
	public void testBean()
	{
		try
		{
			ChargeBean bean = new ChargeBean(12L,"userName","lstx","1.0.1","duowan",12321L,100,(byte)1);
			ChargeBean bean2 = new ChargeBean(13L,"userName","lstx1","1.1.1","duowan",12321L,100,(byte)1);
			byte [] data = bean2.deserialize();
			bean.serialize(data);
			System.out.println(bean.toString());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
