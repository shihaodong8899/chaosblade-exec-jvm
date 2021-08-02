package com.alibaba.chaosblade.exec.plugin.gateway;

import com.alibaba.chaosblade.exec.common.model.FrameworkModelSpec;
import com.alibaba.chaosblade.exec.common.model.action.ActionSpec;
import com.alibaba.chaosblade.exec.common.model.action.delay.DelayActionSpec;
import com.alibaba.chaosblade.exec.common.model.action.exception.ThrowCustomExceptionActionSpec;
import com.alibaba.chaosblade.exec.common.model.matcher.MatcherSpec;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wb-shd671576
 * @package: com.alibaba.chaosblade.exec.plugin.gateway
 * @Date 2021-07-29
 */
public class GateWayModelSpec extends FrameworkModelSpec {

    public GateWayModelSpec() {
        addActionExample();
    }

    private void addActionExample() {
        List<ActionSpec> actions = getActions();
        for (ActionSpec action : actions) {
            if (action instanceof DelayActionSpec) {
                action.setLongDesc("SpringCloud Gateway delay experiment");
                action.setExample("# Do a delay 2s experiment for SpringCloud Gateway forward operations\n"
                        + "blade create scGateway delay --requestpath /gateway/path --time 2000\n\n");
            }
            if (action instanceof ThrowCustomExceptionActionSpec) {
                action.setLongDesc("SpringCloudGateway throws customer exception experiment");
                action.setExample("# Do a throws customer exception experiment for SpringCloud Gateway operations\n" +
                        "blade create scGateway throwCustomException --exception java.lang.Exception --requestpath /gateway/path");
            }
        }
    }

    @Override
    protected List<MatcherSpec> createNewMatcherSpecs() {
        ArrayList<MatcherSpec> matcherSpecs = new ArrayList<MatcherSpec>();
        matcherSpecs.add(new GateWayMatcherSpec());
        return matcherSpecs;
    }

    @Override
    public String getTarget() {
        return GateWayConstant.TARGET_NAME;
    }

    @Override
    public String getShortDesc() {
        return "GateWay experiment!";
    }

    @Override
    public String getLongDesc() {
        return "GateWay experiment contains delay and exception.";
    }
}
