package br.com.jway.multitenancy;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;

import br.com.jway.geraesooh.model.Tenant;


@SessionScoped
public class TenantHolder {

	public static Tenant getCurrentTenant() {
		final FacesContext context = FacesContext.getCurrentInstance();
		return (Tenant) context.getExternalContext().getSessionMap()
				.get("tenant");
	}

	public static void setTenant(final Tenant tenant) {
		final FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put("tenant", tenant);
	}

	public static void cleanupTenant() {
		final FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("tenant");
	}
}