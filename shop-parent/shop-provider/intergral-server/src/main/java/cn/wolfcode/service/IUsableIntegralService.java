package cn.wolfcode.service;

import cn.wolfcode.domain.IntegralRefundVo;
import cn.wolfcode.domain.OperateIntergralVo;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface IUsableIntegralService {
    /**
     * 积分支付接口
     * @param vo 积分支付vo
     * @return 支付流水号
     */
    String doPay(OperateIntergralVo vo);

    /**
     * 积分退款
     * @param vo 积分退款vo
     * @return 是否退款成功
     */
    boolean refund(IntegralRefundVo vo);

    /**
     * 一阶段
     * TCC的try方法，执行资源预留操作
     * 先检查余额是否足够，并且冻结积分
     * @param vo      操作积分vo对象
     * @param context 事务上下文对象，该对象在try阶段传入null即可，RM会自动创建并注入该参数
     * @return 账户流水id
     */
    @TwoPhaseBusinessAction(name = "tryIncrIntegral", commitMethod = "commitIncrIntegral", rollbackMethod = "rollbackIncrIntegral")
    String tryIncrIntegral(@BusinessActionContextParameter(paramName = "integralVo") OperateIntergralVo vo, BusinessActionContext context);

    /**
     * 二阶段
     * TCC的confirm方法，执行确认资源扣除操作
     * 扣除真实积分 & 扣除冻结积分
     * 增加账户变动流水记录
     * @param context 事务上下文对象
     * @return 账户流水id
     */
    void commitIncrIntegral(BusinessActionContext context);

    /**
     * 二阶段
     * TCC的rollback方法，执行回滚try预留资源
     * 扣除try阶段冻结的金额
     * @param context 事务上下文对象
     * @return 账户流水id
     */
    void rollbackIncrIntegral(BusinessActionContext context);
}