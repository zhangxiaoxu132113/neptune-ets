package com.water.neptune.ets.common.dao.plus.ditc;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by admin on 2017/11/23.
 */
public class DictInitalzation {

    /**
     * 将数据库的字典数据写入xml文件
     */
    public static void DB2XML() {

    }

    /**
     * 将字典xml文件写入缓存
     */
    public static void XML2Memory() {
    }

    /**
     * 将本地的字典xml文件写入缓存
     */
    public static void LOCALXML2CACHE(Map<String, Map> dictMap) {
        try {
            SAXReader reader = new SAXReader();
            //读取文件 转换成Document
            InputStream is = DictInitalzation.class.getClassLoader().getResourceAsStream("dict/dict.xml");
            Document document = reader.read(DictInitalzation.class.getClassLoader().getResourceAsStream("dict/dict.xml"));
            Element root = document.getRootElement();
            //遍历
            listNodes(root, dictMap);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 遍历当前节点下的所有节点
     *
     * @param node
     * @param dictMap
     */
    public static void listNodes(Element node, Object dictMap) {
        String code;
        String name;
        Map<String, Map> sonMap = null;
        if ("type".equals(node.getName())) {
            sonMap = new HashMap<>();
            code = node.attribute("code").getValue();
            ((Map<String, Map>) dictMap).put(code, sonMap);
        } else if ("item".equals(node.getName())) {
            name = node.attribute("name").getValue();
            code = node.attribute("code").getValue();

            ((Map<String, String>) dictMap).put(code, name);
        }
        //如果当前节点内容不为空，则输出
        if (!(node.getTextTrim().equals(""))) {
            System.out.println(node.getName() + "：" + node.getText());
        }
        //同时迭代当前节点下面的所有子节点
        Iterator<Element> iterator = node.elementIterator();
        while (iterator.hasNext()) {
            Element e = iterator.next();
            listNodes(e, sonMap == null ? dictMap : sonMap);
        }
    }

    public static void main(String[] args) {
    }
}
