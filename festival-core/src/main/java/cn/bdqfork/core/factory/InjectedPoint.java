package cn.bdqfork.core.factory;

import cn.bdqfork.core.util.ReflectUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author bdq
 * @since 2019/12/18
 */
public class InjectedPoint {
    private String beanName;
    private Type type;
    private boolean require;

    public InjectedPoint(Type type) {
        this(type, true);
    }

    public InjectedPoint(Type type, boolean require) {
        this("", type, require);
    }

    public InjectedPoint(String beanName, boolean require) {
        this.beanName = beanName;
        this.require = require;
    }

    public InjectedPoint(String beanName, Type type, boolean require) {
        this.beanName = beanName;
        this.type = type;
        this.require = require;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

    public boolean isRequire() {
        return require;
    }

    public Type getType() {
        return type;
    }

    public Class<?> getClassType() {
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return (Class<?>) parameterizedType.getRawType();
        } else {
            return (Class<?>) type;
        }
    }

    public Class<?> getActualType() {
        return ReflectUtils.getActualType(type);
    }
}
