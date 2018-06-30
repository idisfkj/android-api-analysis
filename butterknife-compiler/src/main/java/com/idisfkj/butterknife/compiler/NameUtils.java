package com.idisfkj.butterknife.compiler;

import com.idisfkj.butterknife.annotations.utlis.ConstantUtils;

final class NameUtils {

    static String getAutoGeneratorTypeName(String typeName) {
        return typeName + ConstantUtils.BINDING_BUTTERKNIFE_SUFFIX;
    }

    static class Package{
        static final String ANDROID_VIEW = "android.view";
    }

    static class Class {
        static final String CLASS_VIEW = "View";
        static final String CLASS_ON_CLICK_LISTENER = "OnClickListener";
    }

    static class Method{
        static final String BIND_VIEW = "bindView";
        static final String SET_ON_CLICK_LISTENER = "setOnClickListener";
        static final String ON_CLICK = "onClick";
    }

    static class Variable{
        static final String ANDROID_ACTIVITY = "activity";
    }
}
