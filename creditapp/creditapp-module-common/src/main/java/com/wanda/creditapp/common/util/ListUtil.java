package com.wanda.creditapp.common.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;


@SuppressWarnings({ "unchecked", "rawtypes" })
public class ListUtil {

    
	public static final List EMPTY_LIST = Collections.EMPTY_LIST;
    
    public ListUtil() {
    }

    public static boolean isEmpty(Collection coll) {
    	return CollectionUtils.isEmpty(coll);
    }
    public static boolean isNotEmpty(Collection coll) {
    	return !CollectionUtils.isEmpty(coll);
    }
    
	public static List intersection(final List list1, final List list2) {
        final ArrayList result = new ArrayList();
        final Iterator iterator = list2.iterator();

        while (iterator.hasNext()) {
            final Object o = iterator.next();

            if (list1.contains(o)) {
                result.add(o);
            }
        }

        return result;
    }

	public static void addAll(List list1, List list2) {
		if (list1 == null || isEmpty(list2)) return;
		list1.addAll(list2);
	}
	
    public static List subtract(final List list1, final List list2) {
        final ArrayList result = new ArrayList(list1);
        final Iterator iterator = list2.iterator();

        while (iterator.hasNext()) {
            result.remove(iterator.next());
        }

        return result;
    }
    public static List sum(final List list1, final List list2) {
        return subtract(union(list1, list2), intersection(list1, list2));
    }
	public static List union(final List list1, final List list2) {
        final ArrayList result = new ArrayList(list1);
        result.addAll(list2);
        return result;
    }
    public static boolean isEqualList(final Collection list1, final Collection list2) {
        if (list1 == list2) {
            return true;
        }
        if (list1 == null || list2 == null || list1.size() != list2.size()) {
            return false;
        }

        Iterator it1 = list1.iterator();
        Iterator it2 = list2.iterator();
        Object obj1 = null;
        Object obj2 = null;

        while (it1.hasNext() && it2.hasNext()) {
            obj1 = it1.next();
            obj2 = it2.next();

            if (!(obj1 == null ? obj2 == null : obj1.equals(obj2))) {
                return false;
            }
        }

        return !(it1.hasNext() || it2.hasNext());
    }
    
    public static int hashCodeForList(final Collection list) {
        if (list == null) {
            return 0;
        }
        int hashCode = 1;
        Iterator it = list.iterator();
        Object obj = null;
        
        while (it.hasNext()) {
            obj = it.next();
            hashCode = 31 * hashCode + (obj == null ? 0 : obj.hashCode());
        }
        return hashCode;
    }   
    public static List retainAll(Collection collection, Collection retain) {
        List list = new ArrayList(Math.min(collection.size(), retain.size()));

        for (Iterator iter = collection.iterator(); iter.hasNext();) {
            Object obj = iter.next();
            if (retain.contains(obj)) {
                list.add(obj);
            }
        }
        return list;
    }
    public static List removeAll(Collection collection, Collection remove) {
        List list = new ArrayList();
        for (Iterator iter = collection.iterator(); iter.hasNext();) {
            Object obj = iter.next();
            if (remove.contains(obj) == false) {
                list.add(obj);
            }
        }
        return list;
    }

    /**
     * 将List<**Domain>转换为List<**fidld>
     * @param collection 数据源
     * @param clazz domain名称
     * @param fieldName 字段名称
     * @return
     */
    public static List<Object> getFieldValueList(Collection collection, Class clazz, String fieldName) {
        List<Object> objList = new ArrayList<Object>();
        String methodName = "get" + StringUtil.getFirstUpper(fieldName);
        try {
            Method m = clazz.getMethod(methodName);
            if(CollectionUtils.isNotEmpty(collection)){
                for (Object object : collection){
                    objList.add(m.invoke(object));
                }
            }
        }catch (Exception e) {
        } 
        return objList;
    }
   
}
