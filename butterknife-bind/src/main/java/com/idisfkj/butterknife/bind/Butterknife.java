package com.idisfkj.butterknife.bind;

import android.app.Activity;

import com.idisfkj.butterknife.annotations.utlis.ConstantUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Butterknife {

    private Butterknife() {

    }

    private static <T extends Activity> void initialization(T target, String suffix) {
        Class<?> tClass = target.getClass();
        String className = tClass.getName();
        try {
            Class<?> bindingClass = tClass.getClassLoader().loadClass(className + suffix);
            Constructor<?> constructor = bindingClass.getConstructor(tClass);
            constructor.newInstance(target);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void bind(Activity activity) {
        initialization(activity, ConstantUtils.BINDING_BUTTERKNIFE_SUFFIX);
    }
}
