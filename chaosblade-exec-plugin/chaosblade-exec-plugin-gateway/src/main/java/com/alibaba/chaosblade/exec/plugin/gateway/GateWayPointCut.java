package com.alibaba.chaosblade.exec.plugin.gateway;

import com.alibaba.chaosblade.exec.common.aop.PointCut;
import com.alibaba.chaosblade.exec.common.aop.matcher.clazz.ClassMatcher;
import com.alibaba.chaosblade.exec.common.aop.matcher.clazz.NameClassMatcher;
import com.alibaba.chaosblade.exec.common.aop.matcher.clazz.OrClassMatcher;
import com.alibaba.chaosblade.exec.common.aop.matcher.method.MethodMatcher;
import com.alibaba.chaosblade.exec.common.aop.matcher.method.NameMethodMatcher;
import com.alibaba.chaosblade.exec.common.aop.matcher.method.OrMethodMatcher;


/**
 * @Author wb-shd671576
 * @package: com.alibaba.chaosblade.exec.plugin.gateway
 * @Date 2021-07-29
 */
public class GateWayPointCut implements PointCut {

    private static final String GW_REST_CLASS = "org.springframework.cloud.gateway.handler.RoutePredicateHandlerMapping";
    private static final String GW_REST_METHOD = "getHandlerInternal";


    @Override
    public ClassMatcher getClassMatcher() {
        return new NameClassMatcher(GW_REST_CLASS);
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        OrMethodMatcher orMethodMatcher = new OrMethodMatcher();
        orMethodMatcher.or(new NameMethodMatcher(GW_REST_METHOD));
        return orMethodMatcher;
    }

}
