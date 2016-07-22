package com.wanda.creditapp.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;

/**
 * 反射的Utils函数集合.扩展自Apache Commons BeanUtils
 */
@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
public class BeanUtil {
	public static final String FORMAT_OBJECT = "F-JSONTO-OBJ";
	public static final String FORMAT_MAP = "F-JSONTO-MAP";
	public static final String FORMAT_LIST = "F-JSONTO-LIST";
	public static final String FORMAT_GET = "F-GET";
	public static final String TO_JSON = "TO-JSON";

	private BeanUtil() {
	}

	public static Field getDeclaredField(Object object, String propertyName) throws NoSuchFieldException {
		return getDeclaredField(object.getClass(), propertyName);
	}

	public static Field getDeclaredField(Class clazz, String propertyName) {
		for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				Field[] s = superClass.getDeclaredFields();
				return superClass.getDeclaredField(propertyName);
			} catch (NoSuchFieldException e) {
			}
		}
		return null;
	}

	/**
	 * 直接获取属性值
	 * 
	 * @param object
	 * @param propertyName
	 * @return
	 */
	public static Object getNameProperty(Object object, String propertyName) {
		Field field = null;
		try {
			field = getDeclaredField(object, propertyName);
		} catch (NoSuchFieldException e1) {
		}
		Object result = null;
		if (null != field) {
			boolean accessible = field.isAccessible();
			field.setAccessible(true);
			try {
				result = field.get(object);
			} catch (Exception e) {
			}
			field.setAccessible(accessible);
		}
		return result;
	}

	/**
	 * 支持a.b.c
	 * 
	 * @param object
	 * @param propertyName
	 * @return
	 */
	public static Object newForceGetProperty(Object object, String propertyName) {
		if (null == object)
			return null;
		if (StringUtil.isBlank(propertyName))
			return null;
		Object obj = null;
		if (object instanceof Map) {
			obj = ((Map) object).get(propertyName);
		}
		if (obj != null)
			return obj;
		String[] s = propertyName.split("\\.");
		if (null == s)
			return null;
		for (int i = 0; i < s.length; i++) {
			if (s[i].indexOf("F-") >= 0) {
				object = formatProperty(object, s[i]);
			} else {
				object = forceGetProperty(object, s[i]);
			}
		}
		return object;
	}

	public static Object formatProperty(Object object, String propertyName) {
		if (FORMAT_OBJECT.equals(propertyName)) {
			return JsonUtil.buildNormalBinder().getJsonToObject(object.toString(), Object.class);
		} else if (FORMAT_MAP.equals(propertyName)) {
			return JsonUtil.buildNormalBinder().getJsonToMap(object.toString(), String.class, Object.class);
		} else if (FORMAT_LIST.equals(propertyName)) {
			List list = (List) JsonUtil.buildNormalBinder().getJsonToList(object.toString(), Object.class);
			return list.get(0);
		} else if (FORMAT_GET.equals(propertyName)) {
			if (object instanceof ArrayList) {
				List list = (List) object;
				return list.get(0);
			} else {
				String[] str = (String[]) object;
				return str[0];
			}
		}
		return null;
	}

	public static Object forceGetProperty(Object object, String propertyName) {
		Object result = null;
		try {
			if (object instanceof Map) {
				result = ((Map) object).get(propertyName);
			} else {
				result = getObjValue(object, propertyName, null);
			}
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 直接赋属性值
	 * 
	 * @param object
	 * @param propertyName
	 * @return
	 */
	public static void setNameProperty(Object object, String propertyName, Object newValue) throws NoSuchFieldException {

		Field field = getDeclaredField(object, propertyName);
		boolean accessible = field.isAccessible();
		field.setAccessible(true);
		try {
			field.set(object, newValue);
		} catch (Exception e) {
		}
		field.setAccessible(accessible);
	}

	/**
	 * 通过set方法赋值
	 * 
	 * @param object
	 * @param propertyName
	 * @param newValue
	 */
	public static void forceSetProperty(Object object, String propertyName, Object newValue) {
		if (null == object || StringUtil.isBlank(propertyName))
			return;
		String[] s = propertyName.split("\\.");
		if (null == s)
			return;
		for (int i = 0; i < s.length - 1; i++) {
			object = forceGetProperty(object, s[i]);
		}
		try {
			if (object instanceof Map) {
				((Map) object).put(propertyName, newValue);
			} else {
				setObjValue(object, propertyName, newValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Object invokePrivateMethod(Object object, String methodName, Object... params)
			throws NoSuchMethodException {
		Class[] types = new Class[params.length];
		for (int i = 0; i < params.length; i++) {
			types[i] = params[i].getClass();
		}

		Class clazz = object.getClass();
		Method method = null;
		for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				method = superClass.getDeclaredMethod(methodName, types);
				break;
			} catch (NoSuchMethodException e) {

			}
		}

		if (method == null)
			throw new NoSuchMethodException("No Such Method:" + clazz.getSimpleName() + methodName);

		boolean accessible = method.isAccessible();
		method.setAccessible(true);
		Object result = null;
		try {
			result = method.invoke(object, params);
		} catch (Exception e) {
		}
		method.setAccessible(accessible);
		return result;
	}

	/**
	 * 通过反射动态调用方法
	 * 
	 * @param classpath
	 *            类
	 * @param methodname
	 *            方法名称
	 * @param types
	 *            [] 方法参数数组
	 * @return
	 */
	public static Method transferMethoder(String classpath, String methodname, Class types[]) {
		Class clazz = null;
		try {
			clazz = Class.forName(classpath);
		} catch (Exception e) {
		}
		for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				return superClass.getMethod(methodname, types);
			} catch (Exception e) {
			}
		}
		return null;
	}

	/**
	 * 通过反射动态调用方法
	 * 
	 * @param classpath
	 *            类
	 * @param methodname
	 *            方法名称
	 * @param types
	 *            [] 方法参数数组
	 * @return
	 */
	public static Method transferMethoder(Object obj, String methodname, Class types[]) {
		Class clazz = obj.getClass();
		for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				return superClass.getMethod(methodname, types);
			} catch (Exception e) {
			}
		}
		return null;
	}

	/**
	 * 获取对象的属性(不包括继承的)
	 * 
	 * @param obj
	 * @return Field[]
	 */
	public static Field[] getObjProperty(Object obj) {
		Class c = obj.getClass();
		Field[] field = c.getDeclaredFields();
		return field;
	}

	/**
	 * 只拷贝超类里的数据
	 * 
	 * @param arg0
	 * @param arg1
	 * @throws Exception
	 */
	public static void copySupperPropertys(Object arg0, Object arg1) throws Exception {
		if (null != arg0 && null != arg1) {
			Object value = null;
			if (arg1 instanceof Map) {
				for (String key : ((Map<String, Object>) arg1).keySet()) {
					value = BeanUtil.forceGetProperty(arg1, key);
					BeanUtil.forceSetProperty(arg0, key, value);
				}
			} else {
				Field[] field = getObjSupperProperty(arg1);
				if (null != field) {
					for (int i = 0; i < field.length; i++) {
						value = BeanUtil.forceGetProperty(arg1, field[i].getName());
						BeanUtil.forceSetProperty(arg0, field[i].getName(), value);
					}
				}
			}
		} else {
			throw new Exception("参数为空");
		}
	}

	/**
	 * 拷贝类里所有的数据arg1中的属性值到arg0
	 * 
	 * @param arg0 dest
	 * @param arg1
	 * @throws Exception
	 */
	public static void copyAllPropertys(Object arg0, Object arg1) throws Exception {
		if (null != arg0 && null != arg1) {
			Object value = null;
			if (arg1 instanceof Map) {
				for (String key : ((Map<String, Object>) arg1).keySet()) {
					value = BeanUtil.forceGetProperty(arg1, key);
					BeanUtil.forceSetProperty(arg0, key, value);
				}
			} else {
				Field[] field = getObjAllProperty(arg1);
				if (null != field) {
					for (int i = 0; i < field.length; i++) {
						if ("serialVersionUID".equals(field[i].getName()))
							continue;
						value = BeanUtil.forceGetProperty(arg1, field[i].getName());
						BeanUtil.forceSetProperty(arg0, field[i].getName(), value);
					}
				}
			}
		} else {
			throw new Exception("参数为空");
		}
	}

	/**
	 * 拷贝的数据(不包括继承的)
	 * 
	 * @param arg0
	 * @param arg1
	 * @throws Exception
	 */
	public static void copyImplPropertys(Object arg0, Object arg1) throws Exception {
		if (null != arg0 && null != arg1) {
			Object value = null;
			if (arg1 instanceof Map) {
				for (String key : ((Map<String, Object>) arg1).keySet()) {
					value = BeanUtil.forceGetProperty(arg1, key);
					BeanUtil.forceSetProperty(arg0, key, value);
				}
			} else {
				Field[] field = getObjProperty(arg1);
				if (null != field) {
					for (int i = 0; i < field.length; i++) {
						value = BeanUtil.forceGetProperty(arg1, field[i].getName());
						BeanUtil.forceSetProperty(arg0, field[i].getName(), value);
					}
				}
			}
		} else {
			throw new Exception("参数为空");
		}
	}

	/**
	 * 获取对象祖先的属性
	 * 
	 * @param obj
	 * @return Field[]
	 */
	public static Field[] getObjSupperProperty(Object obj) {
		Class c = obj.getClass();
		Class supper = c.getSuperclass();
		List<Field> list = new ArrayList<Field>();
		if (null != supper) {
			for (Class superClass = supper; superClass != Object.class; superClass = superClass.getSuperclass()) {
				Field[] fieldchild = superClass.getDeclaredFields();
				if (null != fieldchild) {
					for (Field field2 : fieldchild) {
						list.add(field2);
					}
				}
			}
		}
		Field[] field = new Field[list.size()];
		field = list.toArray(field);
		return field;
	}

	/**
	 * 获取对象祖先的属性,不包括supperbean
	 * 
	 * @param obj
	 * @return Field[]
	 */
	public static Field[] getObjOpSupperProperty(Object obj) {
		Class c = obj.getClass();
		Class supper = c.getSuperclass();
		List<Field> list = new ArrayList<Field>();
		if (null != supper) {
			for (Class superClass = supper; superClass != Object.class; superClass = superClass.getSuperclass()) {
				Field[] fieldchild = superClass.getDeclaredFields();
				if (null != fieldchild) {
					for (Field field2 : fieldchild) {
						list.add(field2);
					}
				}
			}
		}
		Field[] field = new Field[list.size()];
		field = list.toArray(field);
		return field;
	}

	/**
	 * 获取对象所有的属性(包括继承的)
	 * 
	 * @param obj
	 * @return Field[]
	 */
	public static Field[] getObjAllProperty(Object obj) {
		List<Field> list = new ArrayList<Field>();
		for (Class superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
			Field[] fieldchild = superClass.getDeclaredFields();
			if (null != fieldchild) {
				for (Field field2 : fieldchild) {
					list.add(field2);
				}
			}
		}
		Field[] field = new Field[list.size()];
		field = list.toArray(field);
		return field;
	}

	/**
	 * 获取对象所有的属性(包括继承的),不包括supperbean
	 * 
	 * @param obj
	 * @return Field[]
	 */
	public static Field[] getObjAllOpProperty(Object obj) {
		List<Field> list = new ArrayList<Field>();
		for (Class superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
			Field[] fieldchild = superClass.getDeclaredFields();
			if (null != fieldchild) {
				for (Field field2 : fieldchild) {
					list.add(field2);
				}
			}
		}
		Field[] field = new Field[list.size()];
		field = list.toArray(field);
		return field;
	}

	/**
	 * 获取对应的名称的get方法
	 * 
	 * @param proName
	 * @return
	 */
	public static String getProNameMethod(String proName) {
		String methodName = "";
		if (StringUtil.isNotBlank(proName)) {
			methodName = "get" + StringUtil.getFirstUpper(proName);
		}
		return methodName;
	}

	/**
	 * 获取对应的名称的set方法
	 * 
	 * @param proName
	 * @return
	 */
	public static String getProSetNameMethod(String proName) {
		String methodName = "";
		if (StringUtil.isNotBlank(proName)) {
			methodName = "set" + StringUtil.getFirstUpper(proName);
		}
		return methodName;
	}

	/**
	 * 获取对象里对应的属性值(通过get方法)
	 * 
	 * @param obj
	 * @param name
	 * @param defObj
	 *            默认值
	 * @return
	 */
	public static Object getObjValue(Object obj, String name, Object defObj) {
		Object valueObj = null;
		String methodName = getProNameMethod(name);
		Method method = transferMethoder(obj, methodName, new Class[0]);
		if (null != method) {
			try {
				valueObj = method.invoke(obj);
				if (null == valueObj) {
					valueObj = defObj;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return valueObj;
	}

	/**
	 * 赋值对象里对应的属性值(通过set方法)
	 * 
	 * @param obj
	 * @param name
	 * @param defObj
	 *            值
	 * @return
	 */
	public static void setObjValue(Object obj, String name, Object defObj) {
		String methodName = getProSetNameMethod(name);
		try {
			Field field = getDeclaredField(obj, name);
			if (null == field)
				return;
			Class fclass = field.getType();
			Object valueobj = getValueByType(fclass, defObj);
			Class[] types = { fclass };
			Method method = transferMethoder(obj, methodName, types);
			if (null != method) {
				method.invoke(obj, valueobj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param className
	 * @param defObj
	 * @return
	 */
	public static Object getValueByType(Class cls, Object defObj) {
		Object obj = defObj;
		if (cls == null) return obj;
		String className = cls.getName();
		if (null == className)
			return obj;
		if (className.indexOf("[") == 0) {
			return obj;
		} else if (null != defObj && defObj.getClass().getName().equals(className)) {
			return obj;
		} else {
			if (className.indexOf("String") >= 0) {
				if (null == defObj) {
					obj = null;
				} else {
					obj = defObj + "";
				}
			} else if (className.indexOf("int") >= 0) {
				if (null == defObj || StringUtil.isBlank(String.valueOf(defObj))) {
					defObj = "0";
				}
				obj = Long.valueOf(String.valueOf(defObj)).intValue();
			} else if (className.indexOf("Long") >= 0 || className.indexOf("long") >= 0) {
				if (null == defObj || StringUtil.isBlank(String.valueOf(defObj))) {
					defObj = "0";
				}
				obj = Long.valueOf(String.valueOf(defObj));
			} else if (className.indexOf("Number") >= 0 || className.indexOf("number") >= 0) {
				if (null == defObj || StringUtil.isBlank(String.valueOf(defObj))) {
					defObj = "0";
				}
				obj = Long.valueOf(String.valueOf(defObj));
			} else if (className.indexOf("Double") >= 0) {
				if (null == defObj || StringUtil.isBlank(String.valueOf(defObj))) {
					defObj = "0";
				}
				obj = Double.valueOf(String.valueOf(defObj));
			} else if (className.indexOf("double") >= 0) {
				if (null == defObj || StringUtil.isBlank(String.valueOf(defObj))) {
					defObj = "0";
				}
				obj = Double.valueOf(String.valueOf(defObj));
			} else if (className.indexOf("Date") >= 0) {
				if (null != defObj && StringUtil.isNotBlank(String.valueOf(defObj))) {
					if (String.valueOf(defObj).length() > 10) {
						try {
							obj = DateUtil.parseDate(String.valueOf(defObj), DateUtil.DATETIME_FORMAT);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					} else {
						try {
							obj = DateUtil.parseDate(String.valueOf(defObj), DateUtil.DATE_FORMAT);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					if (obj == null) {
						obj = defObj;
					}
				}
			} else if (className.indexOf("Integer") >= 0) {
				if (null == defObj || StringUtil.isBlank(String.valueOf(defObj))) {
					defObj = "0";
				}
				obj = Integer.valueOf(String.valueOf(defObj));
			} else if (className.indexOf("boolean") >= 0) {
				if (null == defObj || StringUtil.isBlank(String.valueOf(defObj))) {
					defObj = "false";
				}
				if ("true".equals(String.valueOf(defObj))) {
					obj = true;
				} else {
					obj = false;
				}
			} else if (className.indexOf("Boolean") >= 0) {
				if (null == defObj || StringUtil.isBlank(String.valueOf(defObj))) {
					defObj = "false";
				}
				if ("true".equals(String.valueOf(defObj))) {
					obj = true;
				} else {
					obj = false;
				}
			}else if (cls.isEnum()) {
				obj = Enum.valueOf(cls, defObj.toString());
			}
		}
		return obj;
	}

	/**
	 * 赋值对象里对应的属性值(通过set方法) defObj固定式string,要转换
	 * 
	 * @param obj
	 * @param name
	 * @param defObj
	 *            值
	 * @return
	 */
	public static void setObjValue(Object obj, String name, String defObj) {
		String methodName = getProSetNameMethod(name);
		try {
			Field field = getDeclaredField(obj, name);
			Class fclass = field.getType();
			Class[] types = { fclass };
			Method method = transferMethoder(obj, methodName, types);
			if (null != method) {
				method.invoke(obj, getStringToType(fclass, defObj));
			}
		} catch (Exception e) {
		}
	}

	public static Object getObject(Object obj, String name, String defObj) {
		String methodName = getProSetNameMethod(name);
		try {
			Field field = getDeclaredField(obj, name);
			Class fclass = field.getType();
			Class[] types = { fclass };
			return getStringToType(fclass, defObj);
		} catch (Exception e) {
		}
		return null;
	}

	public static String getObjectHql(Object obj, String name, List<Object> paramlist, Object value) {
		String methodName = getProSetNameMethod(name);
		try {
			Field field = getDeclaredField(obj, name);
			Class fclass = field.getType();
			Class[] types = { fclass };
			return getStringToHql(fclass, name, paramlist, value);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 把string 转化成对应的类型
	 * 
	 * @param typeClass
	 * @param value
	 * @return
	 */
	public static Object getStringToType(Class typeClass, String value) {
		Object obj = null;
		if (typeClass.equals(String.class)) {
			if (null == value || StringUtil.isBlank(value)) {
				obj = "";
			} else {
				obj = String.valueOf(value);
			}
		} else if (typeClass.equals(Double.class)) {
			if (null == value || StringUtil.isBlank(value)) {
				obj = 0D;
			} else {
				obj = Double.valueOf(value);
			}
		} else if (typeClass.equals(Integer.class)) {
			if (null == value || StringUtil.isBlank(value)) {
				obj = 0;
			} else {
				obj = Integer.valueOf(value);
			}
		} else if (typeClass.equals(Date.class)) {
			if (null == value || StringUtil.isBlank(value)) {
				obj = null;
			} else {
				try {
					obj = DateUtil.parseDate(value, DateUtil.DATE_FORMAT);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		} else if (typeClass.equals(Long.class)) {
			if (null == value || StringUtil.isBlank(value)) {
				obj = 0L;
			} else {
				obj = Long.valueOf(value);
			}
		} else {
			obj = 0;
		}
		return obj;
	}

	@SuppressWarnings("deprecation")
	public static String getStringToHql(Class typeClass, String name, List<Object> paramlist, Object value) {
		String obj = null;
		if (typeClass.equals(String.class)) {
			obj = "'--'";
			paramlist.add(null == value || "".equals(value) ? "--" : value);
		} else if (typeClass.equals(Double.class)) {
			obj = "0";
			paramlist.add(null == value || "".equals(value) ? 0D : value);
		} else if (typeClass.equals(Integer.class)) {
			obj = "0";
			paramlist.add(null == value || "".equals(value) ? 0 : value);
		} else if (typeClass.equals(Date.class)) {
			obj = "to_date('1991.01.01','yyyy.mm.dd')";
			paramlist.add(null == value || "".equals(value) ? new Date("1991.01.01") : value);
		} else if (typeClass.equals(Long.class)) {
			obj = "0";
			paramlist.add(null == value || "".equals(value) ? 0L : value);
		} else {
			obj = "0";
			paramlist.add(null == value || "".equals(value) ? 0 : value);
		}
		return obj;
	}

	public static void copyAllPropertysNotNull(Object arg0, Object arg1) throws Exception {
		if (null != arg0 && null != arg1) {
			Object value = null;
			if (arg1 instanceof Map) {
				for (String key : ((Map<String, Object>) arg1).keySet()) {
					value = BeanUtil.forceGetProperty(arg1, key);
					if (value == null)
						continue;
					BeanUtil.forceSetProperty(arg0, key, value);
				}
			} else {
				Field[] field = getObjAllProperty(arg1);
				if (null != field) {
					for (int i = 0; i < field.length; i++) {
						value = BeanUtil.forceGetProperty(arg1, field[i].getName());
						if (value == null)
							continue;
						BeanUtil.forceSetProperty(arg0, field[i].getName(), value);
					}
				}
			}
		} else {
			throw new Exception("参数为空");
		}
	}
	
	private static List<Field> getFieldList(Class clazz, List<Field> fieldList) {
		Field[] fields = clazz.getDeclaredFields();
		fieldList.addAll(Arrays.asList(fields));
		Field[] pFields = clazz.getSuperclass().getDeclaredFields();
		if(pFields != null && pFields.length > 0) {
			fieldList.addAll(Arrays.asList(pFields));
			getFieldList(clazz.getSuperclass(), fieldList);
		} else {
			return fieldList;
		}
		return fieldList;
	}
	
	/**
	 * 深度将Map转成对象
	 * @param obj 对象实例
	 * @param kv 对象属性键值对
	 * @param defineClass 需要深度转换的属性类
	 * @throws Exception
	 */
	public static void copyMapToObject(Object obj, Map<String, Object> kv, Map<String, Class> defineClass, Class money)
			throws Exception {
		try {
			if (MapUtil.isEmpty(kv)) {
				return;
			}
			if(money.getName().equals(obj.getClass().getName())) {
				Method setCentMethod = obj.getClass().getMethod("setCent", long.class);
				setCentMethod.invoke(obj, kv.get("cent"));
				return;
			}
			List<Field> fieldList = new ArrayList<Field>();

			getFieldList(obj.getClass(), fieldList);

			for (Field field : fieldList) {
				if (!(field.getModifiers() == Modifier.PRIVATE || field.getModifiers() == Modifier.PROTECTED))
					continue;

				field.setAccessible(true);

				if (MapUtil.isNotEmpty(defineClass) && defineClass.containsKey(field.getName())
						&& defineClass.values().contains(field.getType())) {
					Map<String, Object> subKv = null;
					if (kv.get(field.getName()) instanceof Map) {
						subKv = (Map<String, Object>) kv.get(field.getName());
					} else {
						subKv = (Map<String, Object>) MapUtil.castMapByObject(kv.get(field.getName()));
					}
					Class clazz = defineClass.get(field.getName());
					Object subObj = clazz.newInstance();
					copyMapToObject(subObj, subKv, defineClass, money);
					kv.put(field.getName(), subObj);
				}

				Object value = kv.get(field.getName());
				if (value == null) {
					continue;
				}
				if (field.getType().getName().equals(BigDecimal.class.getName())) {
					value = new BigDecimal(String.valueOf(kv.get(field.getName())));
				} else if (field.getType().getName().equals(Currency.class.getName())) {
					value = Currency.getInstance((String) value);
				} else if (field.getType().getName().equals(Long.class.getName())) {
					value = Long.parseLong(String.valueOf(value));
				} else if (field.getType().getName().equals(Date.class.getName())) {
					value = DateUtil.parseDate(String.valueOf(value), DateUtil.DATETIME_FORMAT);
				}

				field.set(obj, value);
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	/**
	 * 深度将Map转成对象
	 * @param obj 对象实例
	 * @param kv 对象属性键值对
	 * @param defineClass 需要深度转换的属性类
	 * @param enumMap 枚举对象键值对
	 * @throws Exception
	 */
	public static void copyMapToObject(Object obj, Map<String, Object> kv, Map<String, Class> defineClass,
			Map<Class, Object> enumMap, Class money) throws Exception {
		try {
			if (MapUtil.isEmpty(kv)) {
				return;
			}
			if(money.getName().equals(obj.getClass().getName())) {
				Method setCentMethod = obj.getClass().getMethod("setCent", long.class);
				setCentMethod.invoke(obj, kv.get("cent"));
				return;
			}
			List<Field> fieldList = new ArrayList<Field>();

			getFieldList(obj.getClass(), fieldList);

			for (Field field : fieldList) {
				if (!(field.getModifiers() == Modifier.PRIVATE || field.getModifiers() == Modifier.PROTECTED))
					continue;

				field.setAccessible(true);

				if (MapUtil.isNotEmpty(defineClass) && defineClass.containsKey(field.getName())
						&& defineClass.values().contains(field.getType())) {

					Map<String, Object> subKv = null;
					if (kv.get(field.getName()) instanceof Map) {
						subKv = (Map<String, Object>) kv.get(field.getName());
					} else {
						subKv = (Map<String, Object>) MapUtil.castMapByObject(kv.get(field.getName()));
					}
					Class clazz = defineClass.get(field.getName());
					Object subObj = clazz.newInstance();
					copyMapToObject(subObj, subKv, defineClass, enumMap, money);
					kv.put(field.getName(), subObj);

				}

				Object value = kv.get(field.getName());
				if (value == null) {
					continue;
				}
				if (field.getType().getName().equals(BigDecimal.class.getName())) {
					value = new BigDecimal(String.valueOf(kv.get(field.getName())));
				} else if (field.getType().getName().equals(Currency.class.getName())) {
					value = Currency.getInstance((String) value);
				} else if (field.getType().getName().equals(Long.class.getName())) {
					value = Long.parseLong(String.valueOf(value));
				} else if (field.getType().getName().equals(Date.class.getName())) {
					value = DateUtil.parseDate(String.valueOf(value), DateUtil.DATETIME_FORMAT);
				}
				
				if(MapUtil.isNotEmpty(enumMap) && enumMap.containsKey(field.getType())) {
					Map<String, Object> enumCVMap = (Map<String, Object>) enumMap.get(field.getType());
					value = enumCVMap.get(String.valueOf(value).toUpperCase());
				}

				field.set(obj, value);
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	/**
	 * 深度将Map转成对象,支持枚举、list
	 * @param obj 对象实例
	 * @param kv 对象属性键值对
	 * @param defineClass 需要深度转换的属性类
	 * @param enumMap 枚举对象键值对
	 * @param listValMap list值对象class
	 * @throws Exception
	 */
	public static void copyMapToObject(Object obj, Map<String, Object> kv, Map<String, Class> defineClass, Map<Class, Object> enumMap,
			Map<String, Class> listValMap,Class money) throws Exception {
		try {
			if (MapUtil.isEmpty(kv)) {
				return;
			}
			if(money.getName().equals(obj.getClass().getName())) {
				Method setCentMethod = obj.getClass().getMethod("setCent", long.class);
				setCentMethod.invoke(obj, kv.get("cent"));
				return;
			}
			List<Field> fieldList = new ArrayList<Field>();

			getFieldList(obj.getClass(), fieldList);

			for (Field field : fieldList) {
				if (!(field.getModifiers() == Modifier.PRIVATE || field.getModifiers() == Modifier.PROTECTED || (0 == field.getModifiers()))) {
					continue;
				}
				field.setAccessible(true);

				if (MapUtil.isNotEmpty(defineClass) && defineClass.containsKey(field.getName())
						&& defineClass.values().contains(field.getType())) {

					Map<String, Object> subKv = null;
					if (kv.get(field.getName()) instanceof Map) {
						subKv = (Map<String, Object>) kv.get(field.getName());
					} else {
						subKv = (Map<String, Object>) MapUtil.castMapByObject(kv.get(field.getName()));
					}
					Class clazz = defineClass.get(field.getName());
					Object subObj = clazz.newInstance();
					copyMapToObject(subObj, subKv, defineClass, enumMap, listValMap,money);
					kv.put(field.getName(), subObj);
				} else if (MapUtil.isNotEmpty(listValMap) && listValMap.containsKey(field.getName())) {
					Object value = kv.get(field.getName());
					List<Map> mapList = (List<Map>) value;
					if (ListUtil.isNotEmpty(mapList)) {
						List valList = new ArrayList();
						for (int i = 0; i < mapList.size(); i++) {
							Map map = mapList.get(i);
							Object subObj = listValMap.get(field.getName()).newInstance();
							copyMapToObject(subObj, map, defineClass, enumMap, listValMap,money);
							valList.add(subObj);
						}
						kv.put(field.getName(), valList);
					}
				}

				Object value = kv.get(field.getName());
				if (value == null) {
					continue;
				}
				if (field.getType().getName().equals(BigDecimal.class.getName())) {
					value = new BigDecimal(String.valueOf(kv.get(field.getName())));
				} else if (field.getType().getName().equals(Currency.class.getName())) {
					value = Currency.getInstance((String) value);
				} else if (field.getType().getName().equals(Long.class.getName())) {
					value = Long.parseLong(String.valueOf(value));
				} else if (field.getType().getName().equals(Date.class.getName())) {
					value = DateUtil.parseDate(String.valueOf(value), DateUtil.DATETIME_FORMAT);
				}

				if (MapUtil.isNotEmpty(enumMap) && enumMap.containsKey(field.getType())) {
					Map<String, Object> enumCVMap = (Map<String, Object>) enumMap.get(field.getType());
					value = enumCVMap.get(String.valueOf(value).toUpperCase());
				}

				field.set(obj, value);
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public static void copyMapToObjectSN(Object obj, Map<String, Object> kv, Map<String, Class> defineClass, Map<Class, Object> enumMap,
			Map<String, Class[]> listValMap,Class money) throws Exception {
		try {
			if (MapUtil.isEmpty(kv)) {
				return;
			}
			if(money.getName().equals(obj.getClass().getName())) {
				Method setCentMethod = obj.getClass().getMethod("setCent", long.class);
				setCentMethod.invoke(obj, kv.get("cent"));
				return;
			}
			List<Field> fieldList = new ArrayList<Field>();

			getFieldList(obj.getClass(), fieldList);

			for (Field field : fieldList) {
				if (!(field.getModifiers() == Modifier.PRIVATE || field.getModifiers() == Modifier.PROTECTED || (0 == field.getModifiers()))) {
					continue;
				}
				field.setAccessible(true);
				
				if (MapUtil.isNotEmpty(defineClass) && defineClass.containsKey(field.getName())
						&& defineClass.values().contains(field.getType())) {

					Map<String, Object> subKv = null;
					if (kv.get(field.getName()) instanceof Map) {
						subKv = (Map<String, Object>) kv.get(field.getName());
					} else {
						subKv = (Map<String, Object>) MapUtil.castMapByObject(kv.get(field.getName()));
					}
					Class clazz = defineClass.get(field.getName());
					Object subObj = clazz.newInstance();
					copyMapToObjectSN(subObj, subKv, defineClass, enumMap, listValMap,money);
					kv.put(field.getName(), subObj);
				} else if (MapUtil.isNotEmpty(listValMap) && listValMap.containsKey(field.getName())) {
					Object value = kv.get(field.getName());
					List<Map> mapList = (List<Map>) value;
					if (ListUtil.isNotEmpty(mapList)) {
						List valList = new ArrayList();
						for (int i = 0; i < mapList.size(); i++) {
							Map map = mapList.get(i);
							Class genericsfieldType = GenericsUtil.getFieldGenericType(field);
							Class[] classes= listValMap.get(field.getName());
							Object subObj = null;
							for(Class clazz : classes) {
								if(genericsfieldType.getName().equals(clazz.getName())) {
									subObj = genericsfieldType.newInstance();
								}
							}
							copyMapToObjectSN(subObj, map, defineClass, enumMap, listValMap,money);
							valList.add(subObj);
						}
						kv.put(field.getName(), valList);
					}
				}

				Object value = kv.get(field.getName());
				if (value == null) {
					continue;
				}
				if (field.getType().getName().equals(BigDecimal.class.getName())) {
					value = new BigDecimal(String.valueOf(kv.get(field.getName())));
				} else if (field.getType().getName().equals(Currency.class.getName())) {
					value = Currency.getInstance((String) value);
				} else if (field.getType().getName().equals(Long.class.getName())) {
					value = Long.parseLong(String.valueOf(value));
				} else if (field.getType().getName().equals(Date.class.getName())) {
					value = DateUtil.parseDate(String.valueOf(value), DateUtil.DATETIME_FORMAT);
				}

				if (MapUtil.isNotEmpty(enumMap) && enumMap.containsKey(field.getType())) {
					Map<String, Object> enumCVMap = (Map<String, Object>) enumMap.get(field.getType());
					value = enumCVMap.get(String.valueOf(value).toUpperCase());
				}

				field.set(obj, value);
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
}
