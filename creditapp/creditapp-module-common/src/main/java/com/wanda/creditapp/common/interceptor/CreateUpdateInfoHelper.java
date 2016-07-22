package com.wanda.creditapp.common.interceptor;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import com.wanda.creditapp.common.domain.BaseDomain;
import com.wanda.creditapp.common.sercurity.User;

/**
 * Title：CreateUpdateInfoHelper <br/>
 * Description：统一处理创建者和修改者 <br/>
 * Company：wanda <br/>
 * 
 * @author Chad<br/>
 *         Date：2016年4月21日 上午10:39:14<br/>
 */
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class CreateUpdateInfoHelper implements Interceptor {

	@SuppressWarnings("unused")
	private Properties properties;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
		Object parameter = invocation.getArgs()[1];
		if (SqlCommandType.INSERT == sqlCommandType) {
			if (parameter instanceof BaseDomain) {
				setCreateUserAndTs(parameter);
			} else if (parameter instanceof Collection<?>) {
				Iterator<?> it = ((Collection<?>) parameter).iterator();
				while (it.hasNext()) {
					Object obj = it.next();
					if (obj instanceof BaseDomain) {
						setCreateUserAndTs(obj);
					}
				}
			}
		} else if (SqlCommandType.UPDATE == sqlCommandType) {
			if (parameter instanceof BaseDomain) {
				setUpdateUserAndTs(parameter);
			} else if (parameter instanceof Collection<?>) {
				Iterator<?> it = ((Collection<?>) parameter).iterator();
				while (it.hasNext()) {
					Object obj = it.next();
					if (obj instanceof BaseDomain) {
						setUpdateUserAndTs(obj);
					}
				}
			}
		}
		return invocation.proceed();
	}

	/**
	 * @param obj
	 */
	private void setUpdateUserAndTs(Object obj) {
		String userName = getUsernameFromContext();
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		((BaseDomain) obj).setUpdateUser(userName);
		((BaseDomain) obj).setUpdatedAt(ts);
	}

	private void setCreateUserAndTs(Object obj) {
		String userName = getUsernameFromContext();
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		((BaseDomain) obj).setCreateUser(userName);
		if (((BaseDomain) obj).getCreatedAt() == null) {
			((BaseDomain) obj).setCreatedAt(ts);
		}
		((BaseDomain) obj).setUpdateUser(userName);
		((BaseDomain) obj).setUpdatedAt(ts);
	}

	/**
	 * @return
	 */
	private String getUsernameFromContext() {
		UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication.getPrincipal() == null) {
			return "SYS";
		} else {
			// return ((User)(authentication.getPrincipal())).getUserName();
			return ((User) (authentication.getPrincipal())).getUserPhone();
		}
	}

	@Override
	public Object plugin(Object target) {
		if (target instanceof Executor) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	@Override
	public void setProperties(Properties properties) {

	}

}
