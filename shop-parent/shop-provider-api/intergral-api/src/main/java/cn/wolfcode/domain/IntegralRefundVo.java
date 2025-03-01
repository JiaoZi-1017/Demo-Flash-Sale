package cn.wolfcode.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IntegralRefundVo {
    private Long userId;
    private String orderNo;
    private Long refundAmount;
    private String refundReason;
}