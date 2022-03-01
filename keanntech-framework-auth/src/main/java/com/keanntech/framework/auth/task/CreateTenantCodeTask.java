package com.keanntech.framework.auth.task;

import com.keanntech.framework.auth.domain.Tenantcode;
import com.keanntech.framework.auth.mapper.TenantcodeMapper;
import com.keanntech.framework.common.utils.RandomStringUtil;
import com.sankuai.inf.leaf.service.SnowflakeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;

/**
 * @author miaoqingfu
 * @date 2022年02月28日 4:34 PM
 */
@EnableScheduling
@Configuration
@Slf4j
public class CreateTenantCodeTask {

    final TenantcodeMapper tenantcodeMapper;
    final SnowflakeService snowflakeService;

    public CreateTenantCodeTask(TenantcodeMapper tenantcodeMapper, SnowflakeService snowflakeService) {
        this.tenantcodeMapper = tenantcodeMapper;
        this.snowflakeService = snowflakeService;
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void createTenantCode() {
        int noUsed = tenantcodeMapper.countByUsed(0);
        if (noUsed < 10) {
            List<Tenantcode> tenantCodeList = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                String code = RandomStringUtil.generateByRandom(6);
                Tenantcode tenantcode = new Tenantcode();
                tenantcode.setId(snowflakeService.getId("id").getId());
                tenantcode.setTenantCode(code);
                tenantcode.setUsed(false);
                tenantCodeList.add(tenantcode);
            }
            tenantcodeMapper.batchInsert(tenantCodeList);
        }
    }
}
