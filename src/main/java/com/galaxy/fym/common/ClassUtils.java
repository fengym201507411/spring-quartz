package com.galaxy.fym.common;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by fengyiming on 2016/7/26.
 */
public class ClassUtils {

    private final static String FILE = "file";

    private final static String CLASS_FILE_SUFFIX = ".class";

    private final static String COMMA = ".";

    private final static String SLASH = "/";

    private final static String UTF_8_ENCODING = "UTF-8";

    public static List<Class<?>> getAnnotationClassList(String packagePath,Class<? extends Annotation> annotation) throws IOException{
        List<Class<?>> classList = new ArrayList<Class<?>>();
        String dirpath = packagePath.replaceAll("\\.", SLASH);
        Enumeration<URL> enumeration = Thread.currentThread().getContextClassLoader().getResources(dirpath);
        while (enumeration.hasMoreElements()){
            URL url = enumeration.nextElement();
            String protocol = url.getProtocol();
            String filePath = URLDecoder.decode(url.getFile(),UTF_8_ENCODING);
            if(FILE.equals(protocol)){
                getClassList(classList,packagePath,filePath,true,annotation);
            }
        }
        return classList;
    }

    private static void getClassList(List<Class<?>> classList,String packageName,String packagePath,boolean isRecursice,Class<? extends Annotation> annotation){
        File[] files = fileFilterClass(packagePath);
        if(files != null && files.length > 0){
            for(File file:files){
                if(file.isFile()){
                    String className = getclassName(packageName,file.getName());
                    addClass(classList,className,annotation);
                }else{
                    packageName = packageName + COMMA + file.getName();
                    packagePath = packagePath + SLASH + file.getName();
                    getClassList(classList,packageName,packagePath,isRecursice,annotation);
                }
            }
        }
    }

    private static File[] fileFilterClass(String path){
        if(StringUtils.isEmpty(path)){
            return null;
        }else{
            return new File(path).listFiles(new FileFilter() {
                public boolean accept(File pathname) {
                    return pathname.isDirectory() || (pathname.isFile() && pathname.getName().endsWith(CLASS_FILE_SUFFIX));
                }
            });
        }
    }

    private static String getclassName(String packageName,String fileName){
        int index = fileName.lastIndexOf(COMMA);
        String className = fileName.substring(0,index);
        return packageName + COMMA + className;
    }

    private static void addClass(List<Class<?>> classList,String clasName,Class<? extends Annotation> annotation){
        if(StringUtils.isEmpty(clasName)){
            return;
        }else{
            try {
                Class<?> clazz = Class.forName(clasName);
                if(clazz.isAnnotationPresent(annotation)){
                    classList.add(clazz);
                }
            }catch (ClassNotFoundException e){
                return;
            }
        }
    }
}
