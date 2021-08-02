package com.alibaba.chaosblade.exec.plugin.gateway;

import com.alibaba.chaosblade.exec.common.aop.Enhancer;
import com.alibaba.chaosblade.exec.common.aop.Plugin;
import com.alibaba.chaosblade.exec.common.aop.PointCut;
import com.alibaba.chaosblade.exec.common.model.ModelSpec;

/**
 * @Author wb-shd671576
 * @package: com.alibaba.chaosblade.exec.plugin.gateway
 * @Date 2021-07-29
 */
public class GateWayPlugin implements Plugin {

    @Override
    public String getName() {
        return GateWayConstant.TARGET_NAME;
    }

    @Override
    public ModelSpec getModelSpec() {
        return new GateWayModelSpec();
    }

    @Override
    public PointCut getPointCut() {
        return new GateWayPointCut();
    }

    @Override
    public Enhancer getEnhancer() {
        return new GateWayEnhancer();
    }
}
