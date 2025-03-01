package cn.wolfcode.feign;

import cn.wolfcode.common.web.Result;
import cn.wolfcode.domain.PayVo;
import cn.wolfcode.domain.RefundVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient("pay-service")
public interface AlipayFeignApi {
    @PostMapping("/alipay/doPay")
    Result<String> doPay(@RequestBody PayVo vo);

    @PostMapping("/alipay/checkRSASignature")
    Result<Boolean> checkRSASignature(@RequestBody Map<String, String> params);

    @PostMapping("/alipay/refund")
    Result<Boolean> refund(@RequestBody RefundVo vo);
}