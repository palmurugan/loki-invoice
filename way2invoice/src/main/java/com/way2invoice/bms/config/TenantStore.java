package com.way2invoice.bms.config;

/**
 * @author palmuruganc
 * <p>
 * The store to keep the tenant information based on each request
 */
public class TenantStore {

  private static final Long DEFAULT_TENANT_ID = 1L;

  private static final ThreadLocal<Long> TENANT_CONTEXT = new ThreadLocal<>();

  public static void clear() {
    TENANT_CONTEXT.remove();
  }

  public static Long getTenantId() {
    return TENANT_CONTEXT.get() == null ? DEFAULT_TENANT_ID : TENANT_CONTEXT.get();
  }

  public static void setTenantId(Long tenantId) {
    TENANT_CONTEXT.set(tenantId);
  }
}
