package com.keanntech.framework.tenant.task;

import com.keanntech.framework.common.utils.RandomStringUtil;
import com.keanntech.framework.entity.domain.Tenantcode;
import com.keanntech.framework.entity.mapper.tenant.TenantcodeMapper;
import com.sankuai.inf.leaf.service.SnowflakeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author miaoqingfu
 * @date 2022年02月28日 4:34 PM
 */
@EnableScheduling
@Component
@Slf4j
public class CreateTenantCodeTask {

    private TenantcodeMapper tenantcodeMapper;
    private SnowflakeService snowflakeService;

    @Autowired
    public void setTenantcodeMapper(TenantcodeMapper tenantcodeMapper) {
        this.tenantcodeMapper = tenantcodeMapper;
    }

    @Autowired
    public void setSnowflakeService(SnowflakeService snowflakeService) {
        this.snowflakeService = snowflakeService;
    }

//    @Scheduled(cron = "0 0 1 * * ?")
    @Scheduled(cron = "0 0/5 * * * ? ")
    public void createTenantCode() {
        int noUsed = tenantcodeMapper.countByUsed(0);
        System.out.println(noUsed);
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
