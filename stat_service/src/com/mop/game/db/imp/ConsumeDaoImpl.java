//package com.mop.game.db.imp;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.jdbc.core.PreparedStatementSetter;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.support.JdbcDaoSupport;
//
//import com.obm.passport.bean.ConsumeLog;
//import com.obm.passport.bean.Game;
//import com.obm.passport.core.GameManager;
//import com.obm.passport.db.ConsumeDao;
//import com.obm.passport.util.DateUtil;
//
//public class ConsumeDaoImpl extends JdbcDaoSupport implements ConsumeDao {
//
//	public static final String DB_NAME = "consumelog";
//
//	public void save(final ConsumeLog log) {
//		this
//				.getJdbcTemplate()
//				.update(
//						"INSERT INTO "
//								+ DB_NAME
//								+ " (passport,game,subgame,chanId,time,type,value,userid,code,name,count,cat) VALUES(?,?, ?,?, ?,?,?,?,?,?,?,?)",
//						new PreparedStatementSetter() {
//							public void setValues(PreparedStatement ps)
//									throws SQLException {
//								ps.setString(1, log.getPassport());
//								ps.setString(2, log.getGame());
//								ps.setString(3, log.getSubgame());
//								ps.setString(4, log.getChanId());
//								ps.setString(5, log.getTime());
//								ps.setString(6, log.getType());
//								ps.setString(7, log.getValue());
//								ps.setString(8, log.getUserid());
//								ps.setString(9, log.getCode());
//								ps.setString(10, log.getName());
//								ps.setInt(11, log.getCount());
//								ps.setString(12, log.getCat());
//							}
//						});
//	}
//
//	public ConsumeLog[] query(final String type, String game, String subgame,
//			String passport, String startDate, String endDate,
//			int startSequence, int recordCount) {
//		final Game g = GameManager.getGame(game);
//		if (g == null)
//			return null;
//		String filter = " where type = '" + type + "' and game = '" + game
//				+ "' and passport='" + passport + "' and time >= '" + startDate
//				+ "' and time <= '" + endDate + "' ";
//		if (subgame != null) {
//			filter += " and subgame='" + subgame + "' ";
//		}
//		String sql = "select time,name,code,value from " + DB_NAME + filter
//				+ " order by id DESC limit " + (startSequence - 1) + ","
//				+ recordCount;
//		List<ConsumeLog> list = getJdbcTemplate().query(sql, new RowMapper() {
//			public ConsumeLog mapRow(ResultSet arg0, int arg1)
//					throws SQLException {
//				ConsumeLog log = new ConsumeLog();
//				log.setType(type);
//				log.setTime(DateUtil.getLogFormat().format(
//						arg0.getTimestamp("time").getTime()));
//				log.setName(arg0.getString("name"));
//				log.setCode(arg0.getString("code"));
//				log.setValue(arg0.getString("value"));
//				return log;
//			}
//		});
//		return list.toArray(new ConsumeLog[list.size()]);
//	}
//
//	/**
//	 * 
//	 */
//	public Date getLastExchangeTime(String userId, String passport) {
//		String sql = "select max(time) from consumelog where cat = 'exchange' and type='A' and (userid =? or passport = ?)";
//		try {
//			return (Date) this.getJdbcTemplate().queryForObject(sql,
//					new Object[] { userId, passport }, Date.class);
//		} catch (Exception e) {
//			return null;
//		}
//	}
//
//	public int getExchangeOfDay(String userId, String passport) {
//		String sql = "select sum(value) from consumelog where cat = 'exchange' and type='A' and (userid =? or passport = ?) and date(time)=date(now())";
//		try {
//			return this.getJdbcTemplate().queryForInt(sql,
//					new Object[] { userId, passport });
//		} catch (Exception e) {
//			return 0;
//		}
//	}
//}
