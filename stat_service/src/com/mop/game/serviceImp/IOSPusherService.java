/*********************************************************************************
 *	(C) Copyright 2011 - mailto:w.l.hikaru  <inuyashabuaa@126.com>	             *
 *********************************************************************************
 *********************************************************************************
 *																		                                                *
 *	 This program is free software; you can redistribute it and/or modif     *
 *	 it under the terms of the GNU General Public License as published by  *
 *	 the Free Software Foundation; either version 2 of the License, or	    *
 *	 (at your option) any later version.								                                *
 *																		                                                *
 ********************************************************************************/
package com.mop.game.serviceImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.mop.game.connection.IStatRequest;
import com.mop.game.connection.pns.PNSConfig;
import com.mop.game.constant.StatRuntime;
import com.mop.game.service.AbstractStatAsynProcessor;
import com.mop.game.utils.ConnectionUtils;

public class IOSPusherService extends AbstractStatAsynProcessor
{
	//游戏发送过来的某服务器全部ios用户消息
	
	private PNSConfig pnsConfig;
	
	public PNSConfig getPnsConfig() {
		return pnsConfig;
	}

	public void setPnsConfig(PNSConfig pnsConfig) {
		this.pnsConfig = pnsConfig;
	}

	@Override
	public void query(IStatRequest request) {
		//查找DB中符合条件的项
		
		StringBuilder b = new StringBuilder();
		b.append("select * from ios_info_report where 1=1 ");
		Iterator<Entry<String,Object>> params = request.getParams();
		while(params.hasNext())
		{
			Entry<String,Object> entry = params.next();
			if(entry.getValue() == null)continue;
			if("msg".equals(entry.getKey())) continue;
			b.append("and "+entry.getKey()+"='"+entry.getValue()+"'");
		}
		InnerDao dao = new InnerDao();
		final List<String> temp = dao.query(b.toString());
		
		//推送消息 
		StringBuffer sb = new StringBuffer();
		sb.append("pns:").append(ConnectionUtils.SEP);
		sb.append(pnsConfig.getKeystore()).append(ConnectionUtils.SEP);
		sb.append(pnsConfig.getPassword()).append(ConnectionUtils.SEP);
		sb.append(pnsConfig.getProduction()).append(ConnectionUtils.SEP);
		sb.append(request.getParam("msg")).append(ConnectionUtils.SEP);
		sb.append(pnsConfig.getThread()).append(ConnectionUtils.SEP);
		ConnectionUtils.exec(sb.toString(), "send pns error!",temp.toArray(new String[temp.size()]));
				
	}
	
}



class InnerDao extends JdbcDaoSupport
{
	public InnerDao()
	{
		DriverManagerDataSource source = (DriverManagerDataSource)StatRuntime.getInstance().getApplicationAvaliable("IOSSource");
		this.setDataSource(source);
	}

	public static List<String> getDevices() throws Exception {
		List<String> devices = new ArrayList<String>();
		devices.add("0d9cb9de8c0c0de3600ed42ef18a95131274b2c222699b3fa52a48a2351d5247");
		devices.add("1dbd1bb2f77071b7fa27a48a4100b9562d1991da139b9bb62c747477fd5fffbe");
		devices.add("276c3f62f0676424ef06f8a7b312b88cac3106a2fe2a1c3e67597977ee9ca49a");
		devices.add("276c3f62f0676424ef06f8a7b312b88cac3106a2fe2a1c3e67597977ee9ca49a");
		devices.add("1dbd1bb2f77071b7fa27a48a4100b9562d1991da139b9bb62c747477fd5fffbe");
		devices.add("324cc6fd95f6f0ff1a9b54a46745fe58c6f5e8cce07fee596d0ec1361cb59f30");
		devices.add("10d7f1cd244ca780a70bea9e09927c762c355b5a4fb4fc53ccbbc5f483f2bf21");
		devices.add("b58dd52319a6568e29b7427d2b2b28db70b0b202b07ccf3f23e611c38cde1f5a");
		devices.add("674b9bb88a174c6e5b6bd0a9487d095607f33a71fa30ed582847c1986db6431c");
		devices.add("ee7e9d15f0ecd64b00833aa84b3eb4412cae7b3bccda06ab386c667827aea5ad");
		devices.add("0d36ffa6b8f10f860e0437372f85e2cff1ec77013fd58b27d9886cff09a80f0d");
		devices.add("5809fe2f23da82ecce3b801f4c8785a6c26b84312fd81759488f54176b4321ce");
		devices.add("7ca9b021d540e0de9f7cf33e86bdf6676fddc47572630f1257c69eba36f4fac0");
		devices.add("e8c72a4c6913711a8eeb050a2076e65aebec60071e503a8ce8d557ae0ada59e5");
		devices.add("4458db5519129714ee9573134ddacfb068e04a66d29db8fe4eb3af894885dad7");
		devices.add("9918bf944e9f5a4ee53be7b6c5ab5ece735ff43b81750d7ff3ece409ab363ed9");
		devices.add("4f1f3e589d2babb5586761870b7c46969841f1cfc6a6052b18d5443772efae3f");
		devices.add("32793685fffdc4bbf8e41cf4ceb2d4d51a7a4c166540aa9f712eef149b8cba08");
		devices.add("2c93026d8efdf3338c3cc9886b5d22e1800a2943ad1a551d378df74dae5a41c5");
		devices.add("68b13bd9c68b43c1c27899ee49018c818034bd25c16fee595aaafb16d3e896b4");
		devices.add("a868b88e85e7597c50e492ad0f9882a048de3761029073b4f731cffbca8f65f6");
		devices.add("c99589aea96e2bf8430f9306d1db8e1bbad715d238d0f3f26ace70ac8c070f8e");
		devices.add("12350e92bc1ec3eb83390eb0cef7067d75472f85d6d69419bcb2f8e700b75296");
		devices.add("8e2c7241d01b7372a2dea56c23da01abc15d8dec1b27a3aa3e8ba588c3500af9");
		devices.add("5a00c725c9e168b0a710604a9e4bfa0849600f31531200c72a35da0ea6c52ed7");
		devices.add("1cca1d6131795baecd214b316d8f2f20cd0cf5c2b28bc6881065d6fd48656ce7");
		devices.add("4427573837fe7001236af8ebd6760c657cfe5f5a96aea79aebded2e5b1689d4a");
		devices.add("8c84826d6f0c6d16c5767c23ef9b9b00ea50d086647518b42004f8a55ea768c6");
		devices.add("426b3dbabd769b8196433e49e546ae2c0f142bfc3a36d966197db1a79322b965");
		devices.add("06b946c2b99ef742a0ab2695bd19ce8dc79def31cd394a412cab0f47914ce10f");
		devices.add("19570cb2a0e426947b9ad7fa90679a331af6af42df6670554d2ae9b70778f804");
		devices.add("5eab9fa984aebc1a612669daa1f960416fbab938160c95ce3a12796543b6924b");
		devices.add("cfa96074a014a2ac66b84ad8c78771ded26251c32ca02d7bfecce66999dbd2b3");
		devices.add("25aa45392a3f8689b96819321e5c76bbb467a0bd3f9b12ced210b34978e84348");
		devices.add("31bd5ba0bfc6e030fa38605ab3f0c26391a43067b46aca4d8c58d73281a355aa");
		devices.add("51bce28e4262327533358437c97ebe51fda307e564d5b70aa87c236e28acf97e");
		devices.add("329d516cb74b0906b12c19b761e4e76583c9e769cd1ef6ad57626b83e3f23708");
		devices.add("751e251e7a7919b11963e53caf7a0cccf181315bf399be09eb2a2060429d6ded");
		devices.add("60c8ad7219b6490a1482797c3a855d831056a74bcc2d8d0323029ced365e0224");
		devices.add("9d32564ee3b13957c1797f581c19dc6240638112f979e925b984830d545782ce");
		devices.add("f0768715f6db6c368abaf71a124c82100f37c4d37eeda2d232355df0204fb4ae");
		devices.add("860698757243f634f57400057041cd1febd24f14645595148f75bb68b7285e26");
		devices.add("1f88fcd62df0580de18246aa3e17279a27a739050b908c078a688394000aab6f");
		devices.add("83099cba97899e983738f4b113a4689284d058ffcd1965747a2d3e24f1364fed");
		devices.add("e414dd76fcefcddfe5f9269631515831e9557f052927063cf9539d4040c78316");
		devices.add("761d16f674621fc3124807d0394d4c4a0505b1a7c1799e4149bfabef68c059e2");
		devices.add("772be0ea4c9526ba8a5eaa65ae4e7ecd2f1844517921e065ee6f5642e123d207");
		devices.add("a8b475d5753f728edb5b7651a98cca03e821614db872297358187c55fc2fc062");
		devices.add("c7f2b31884d43c5447929614ae04fcb25ac3f1ab8a0d53ad6bb229c99ae09948");
		devices.add("4b52fbec889a4c1fbeab4746e09bf5b7ef3900b9d94ca27fbe4539c445a3bf69");
		devices.add("587f4536a2e92b0b56a483a12987c20492c16f572bdb141d0d1d84da277219a3");
		devices.add("f62fa479d7e6ad2ef5e9a857f003c45cddf6ff3453378406bdd8632c184152e9");
		devices.add("bbbec750bf6fbef5ed0f9bb2a3a3018d4090c6c69c04ec3e8a734ed2217cbdf4");
		return devices;
	}
	/**
	 * 
	 * @param sql
	 * @return
	 */
	public List<String> query(String sql) {
		
		List<String> list = getJdbcTemplate().query(sql, new RowMapper() {

			@Override
			public Object mapRow(final java.sql.ResultSet result, final int arg1)
					throws SQLException {
//				ReportBean bean = new ReportBean();
//				bean.setAppid(result.getString("appid"));
				return result.getString("dtoken");
			}
			
		});
//		try {
//			list = getDevices();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return list;
	}
}
