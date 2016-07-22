/*
 * Copyright 2013 netfinworks.com, Inc. All rights reserved.
 */
package plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

public class ColumnRemarkPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        String remark = introspectedColumn.getRemarks();
        if(remark != null && remark.trim().length() > 0 && !"null".equals(remark)){
            field.addJavaDocLine("/**");
            field.addJavaDocLine(" * "+remark.replaceAll("\r|\n|\r\n", ""));
            field.addJavaDocLine(" */");
        }
        return super.modelFieldGenerated(field, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
    }
}
