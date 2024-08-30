package cn.wolfcode.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountLogMapperTest {
    @Autowired
    private AccountLogMapper accountLogMapper;

    @Test
    public void test() {
        accountLogMapper.changeStatus("3", 2);
    }
}