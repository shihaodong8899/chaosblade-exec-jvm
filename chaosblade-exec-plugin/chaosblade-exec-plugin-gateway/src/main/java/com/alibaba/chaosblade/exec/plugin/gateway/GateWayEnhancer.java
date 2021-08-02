package com.alibaba.chaosblade.exec.plugin.gateway;

import com.alibaba.chaosblade.exec.common.aop.BeforeEnhancer;
import com.alibaba.chaosblade.exec.common.aop.EnhancerModel;
import com.alibaba.chaosblade.exec.common.model.matcher.MatcherModel;
import com.alibaba.chaosblade.exec.common.util.JsonUtil;
import com.alibaba.chaosblade.exec.common.util.ObjectsUtil;
import com.alibaba.chaosblade.exec.common.util.ReflectUtil;
import com.alibaba.chaosblade.exec.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.Method;

/**
 * @Author wb-shd671576
 * @package: com.alibaba.chaosblade.exec.plugin.gateway
 * @Date 2021-07-29
 */
public class GateWayEnhancer extends BeforeEnhancer {

    private static final Logger LOGGER = LoggerFactory.getLogger(GateWayEnhancer.class);

    @Override
    public EnhancerModel doBeforeAdvice(ClassLoader classLoader, String className, Object object,
                                        Method method, Object[]
                                            methodArguments)
        throws Exception {
        if (methodArguments == null || object == null) {
            LOGGER.warn("The necessary parameters is null");
            return null;
        }
        Object serverWebExchange = methodArguments[0];
        // 执行被增强类的方法，获取一些需要的值
        Object request = ReflectUtil.invokeMethod(serverWebExchange, "getRequest", new Object[] {}, false);
        MatcherModel matcherModel = new MatcherModel();
        if(request != null){
            Object url = ReflectUtil.invokeMethod(request, "getURI", new Object[] {}, false);
            if(url != null){
                String path = ReflectUtil.invokeMethod(request, "getPath", new Object[] {}, false);
                matcherModel.add(GateWayConstant.GET_REQUST_PATH, path);

            }
        }
        LOGGER.debug("gateway matchers: {}", JsonUtil.writer().writeValueAsString(matcherModel));
        return new EnhancerModel(classLoader, matcherModel);
    }


}
