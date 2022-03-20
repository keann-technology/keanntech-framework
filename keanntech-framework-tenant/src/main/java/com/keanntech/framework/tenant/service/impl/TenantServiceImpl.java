package com.keanntech.framework.tenant.service.impl;


import com.keanntech.framework.tenant.service.TenantService;
import org.springframework.stereotype.Service;

/**
 * @author miaoqingfu
 * @date 2022年03月01日 8:18 AM
 */
@Service
public class TenantServiceImpl implements TenantService {

//    final TenantMapper tenantMapper;
//    final TenantcodeMapper tenantcodeMapper;
//    final SnowflakeService snowflakeService;
//    final TransactionTemplate transactionTemplate;
//
//    @Autowired
//    public TenantServiceImpl(TenantMapper tenantMapper, TenantcodeMapper tenantcodeMapper, SnowflakeService snowflakeService, TransactionTemplate transactionTemplate) {
//        this.tenantMapper = tenantMapper;
//        this.tenantcodeMapper = tenantcodeMapper;
//        this.snowflakeService = snowflakeService;
//        this.transactionTemplate = transactionTemplate;
//    }

    @Override
    public int regTenant() {
        return -99;
//        tenant.setId(snowflakeService.getId("id").getId());
//        Tenantcode tenantcode = tenantcodeMapper.getUnusedTenantCode();
//        tenant.setTenantCode(tenantcode.getTenantCode());
//        return transactionTemplate.execute(status -> {
//            try {
//                tenantcode.setUsed(true);
//                tenantcodeMapper.updateById(tenantcode);
//                return tenantMapper.insert(tenant);
//            } catch (Exception e) {
//                status.setRollbackOnly();
//                return  -1;
//            }
//        });
    }
}
