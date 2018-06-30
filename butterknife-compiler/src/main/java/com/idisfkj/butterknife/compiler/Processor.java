package com.idisfkj.butterknife.compiler;

import com.idisfkj.butterknife.annotations.BindView;
import com.idisfkj.butterknife.annotations.Keep;
import com.idisfkj.butterknife.annotations.OnClick;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

public class Processor extends AbstractProcessor {

    private Filer mFiler;
    private Messager mMessager;
    private Elements mElementUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFiler = processingEnv.getFiler();
        mMessager = processingEnv.getMessager();
        mElementUtils = processingEnv.getElementUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()) {
            //获取与annotation相匹配的TypeElement,即有注释声明的class
            Set<TypeElement> elements = getTypeElementsByAnnotationType(annotations, roundEnv.getRootElements());

            for (TypeElement typeElement : elements) {
                //包名
                String packageName = mElementUtils.getPackageOf(typeElement).getQualifiedName().toString();
                //类名
                String typeName = typeElement.getSimpleName().toString();
                //全称类名
                ClassName className = ClassName.get(packageName, typeName);
                //自动生成类全称名
                ClassName autoGenerationClassName = ClassName.get(packageName,
                        NameUtils.getAutoGeneratorTypeName(typeName));

                //构建自动生成的类
                TypeSpec.Builder typeBuilder = TypeSpec.classBuilder(autoGenerationClassName)
                        .addModifiers(Modifier.PUBLIC)
                        .addAnnotation(Keep.class);

                //添加构造方法
                typeBuilder.addMethod(MethodSpec.constructorBuilder()
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(className, NameUtils.Variable.ANDROID_ACTIVITY)
                        .addStatement("$N($N)",
                                NameUtils.Method.BIND_VIEW,
                                NameUtils.Variable.ANDROID_ACTIVITY)
                        .addStatement("$N($N)",
                                NameUtils.Method.SET_ON_CLICK_LISTENER,
                                NameUtils.Variable.ANDROID_ACTIVITY)
                        .build());

                //添加bindView成员方法
                MethodSpec.Builder bindViewBuilder = MethodSpec.methodBuilder(NameUtils.Method.BIND_VIEW)
                        .addModifiers(Modifier.PRIVATE)
                        .returns(TypeName.VOID)
                        .addParameter(className, NameUtils.Variable.ANDROID_ACTIVITY);

                //添加方法内容
                for (VariableElement variableElement : ElementFilter.fieldsIn(typeElement.getEnclosedElements())) {
                    BindView bindView = variableElement.getAnnotation(BindView.class);
                    if (bindView != null) {
                        bindViewBuilder.addStatement("$N.$N=($T)$N.findViewById($L)",
                                NameUtils.Variable.ANDROID_ACTIVITY,
                                variableElement.getSimpleName(),
                                variableElement,
                                NameUtils.Variable.ANDROID_ACTIVITY,
                                bindView.value()[0]
                        ).addStatement("$N.$N.setText($N.getString($L))",
                                NameUtils.Variable.ANDROID_ACTIVITY,
                                variableElement.getSimpleName(),
                                NameUtils.Variable.ANDROID_ACTIVITY,
                                bindView.value()[1]);
                    }
                }

                typeBuilder.addMethod(bindViewBuilder.build());

                //添加setOnClickListener成员方法
                MethodSpec.Builder setOnClickListenerBuilder = MethodSpec.methodBuilder(NameUtils.Method.SET_ON_CLICK_LISTENER)
                        .addModifiers(Modifier.PRIVATE)
                        .returns(TypeName.VOID)
                        .addParameter(className, NameUtils.Variable.ANDROID_ACTIVITY, Modifier.FINAL);

                //添加方法内容
                ClassName viewClassName = ClassName.get(NameUtils.Package.ANDROID_VIEW, NameUtils.Class.CLASS_VIEW);
                ClassName onClickListenerClassName = ClassName.get(NameUtils.Package.ANDROID_VIEW, NameUtils.Class.CLASS_VIEW, NameUtils.Class.CLASS_ON_CLICK_LISTENER);
                for (ExecutableElement executableElement : ElementFilter.methodsIn(typeElement.getEnclosedElements())) {
                    OnClick onClick = executableElement.getAnnotation(OnClick.class);
                    if (onClick != null) {
                        //构建匿名class
                        TypeSpec typeSpec = TypeSpec.anonymousClassBuilder("")
                                .addSuperinterface(onClickListenerClassName)
                                .addMethod(MethodSpec.methodBuilder(NameUtils.Method.ON_CLICK)
                                        .addModifiers(Modifier.PUBLIC)
                                        .addParameter(viewClassName, NameUtils.Class.CLASS_VIEW)
                                        .returns(TypeName.VOID)
                                        .addStatement("$N.$N($N)",
                                                NameUtils.Variable.ANDROID_ACTIVITY,
                                                executableElement.getSimpleName(),
                                                NameUtils.Class.CLASS_VIEW)
                                        .build())
                                .build();

                        setOnClickListenerBuilder.addStatement("$N.findViewById($L).setOnClickListener($L)",
                                NameUtils.Variable.ANDROID_ACTIVITY,
                                onClick.value(),
                                typeSpec);
                    }
                }

                typeBuilder.addMethod(setOnClickListenerBuilder.build());

                //写入java文件
                try {
                    JavaFile.builder(packageName, typeBuilder.build()).build().writeTo(mFiler);
                } catch (IOException e) {
                    mMessager.printMessage(Diagnostic.Kind.ERROR, e.getMessage(), typeElement);
                }
            }
        }
        return true;
    }

    private Set<TypeElement> getTypeElementsByAnnotationType(Set<? extends TypeElement> annotations, Set<? extends Element> elements) {
        Set<TypeElement> result = new HashSet<>();
        //遍历包含的 package class method
        for (Element element : elements) {
            //匹配 class or interface
            if (element instanceof TypeElement) {
                boolean found = false;
                //遍历class中包含的 filed method constructors
                for (Element subElement : element.getEnclosedElements()) {
                    //遍历element中包含的注释
                    for (AnnotationMirror annotationMirror : subElement.getAnnotationMirrors()) {
                        for (TypeElement annotation : annotations) {
                            //匹配注释
                            if (annotationMirror.getAnnotationType().asElement().equals(annotation)) {
                                result.add((TypeElement) element);
                                found = true;
                                break;
                            }
                        }
                        if (found) break;
                    }
                    if (found) break;
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return new TreeSet<>(Arrays.asList(
                BindView.class.getCanonicalName(),
                OnClick.class.getCanonicalName(),
                Keep.class.getCanonicalName())
        );
    }
}
