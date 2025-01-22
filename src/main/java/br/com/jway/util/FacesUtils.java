package br.com.jway.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import jakarta.faces.application.Application;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class FacesUtils {

    public static final String I18N_BUNDLE_NAME = "br.com.jway.i18n";
    public static final String I18N_BUNDLE_VAR = "i18n";

    private FacesUtils() {
    }

    public static void addMessage(String clientId, FacesMessage.Severity severity, String title, String detail) {
        getFacesContext().addMessage(clientId, new FacesMessage(severity, title, detail));
    }

    public static void addI18nMessage(String clientId, FacesMessage.Severity severity, String I18nTitle,
                                      Object[] titleArgs, String I18nDetail, Object[] detailArgs) {
        String title = formatI18nMessage(I18nTitle, titleArgs);
        String detail = formatI18nMessage(I18nDetail, detailArgs);
        addMessage(clientId, severity, title, detail);
    }

    public static void addMessage(String clientId, FacesMessage.Severity severity, String title) {
        addMessage(clientId, severity, title, null);
    }

    public static void addI18nMessage(String clientId, FacesMessage.Severity severity, String I18nTitle,
                                      Object... titleArgs) {
        String title = formatI18nMessage(I18nTitle, titleArgs);
        addMessage(clientId, severity, title);
    }

    public static void addInfo(String title, String detail) {
        addMessage(null, FacesMessage.SEVERITY_INFO, title, detail);
    }

    public static void addInfo(String title) {
        addMessage(null, FacesMessage.SEVERITY_INFO, title);
    }

    public static void addI18nInfo(String I18ntitle, Object... titleArgs) {
        addI18nMessage(null, FacesMessage.SEVERITY_INFO, I18ntitle, titleArgs);
    }

    public static void addWarn(String title, String detail) {
        addMessage(null, FacesMessage.SEVERITY_WARN, title, detail);
    }

    public static void addWarn(String title) {
        addMessage(null, FacesMessage.SEVERITY_WARN, title);
    }

    public static void addI18nWarn(String I18ntitle, Object... titleArgs) {
        addI18nMessage(null, FacesMessage.SEVERITY_WARN, I18ntitle, titleArgs);
    }

    public static void addError(String title, String detail) {
        addMessage(null, FacesMessage.SEVERITY_ERROR, title, detail);
    }

    public static void addError(String title) {
        addMessage(null, FacesMessage.SEVERITY_ERROR, title);
    }

    public static void addI18nError(String I18ntitle, Object... titleArgs) {
        addI18nMessage(null, FacesMessage.SEVERITY_ERROR, I18ntitle, titleArgs);
    }

    public static void addFatalError(String title, String detail) {
        addMessage(null, FacesMessage.SEVERITY_FATAL, title, detail);
    }

    public static void addFatalError(String title) {
        addMessage(null, FacesMessage.SEVERITY_FATAL, title);
    }

    public static void addI18nFatalError(String I18ntitle, Object... titleArgs) {
        addI18nMessage(null, FacesMessage.SEVERITY_FATAL, I18ntitle, titleArgs);
    }

    public static FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    public static ExternalContext getExternalContext() {
        return getFacesContext().getExternalContext();
    }

    public static HttpSession getSession() {
        return (HttpSession) getExternalContext().getSession(true);
    }

    public static HttpServletRequest getHttpServletRequest() {
        return (HttpServletRequest) getExternalContext().getRequest();
    }

    public static ServletContext getServletContext() {
        return (ServletContext) getExternalContext().getContext();
    }

    public static Locale getUserLocale() {
        return getFacesContext().getViewRoot().getLocale();
    }

    public static void updateUserLocale(String locale) {
       /* Locale resolvedLocale = LocaleUtils.toLocale(locale);
        setUserLocale(resolvedLocale);*/
    }

    public static void setUserLocale(Locale locale) {
        getFacesContext().getViewRoot().setLocale(locale);
    }

    public static String formatMessage(String message, Object... arguments) {
        return MessageFormat.format(message, arguments);
    }

    public static ResourceBundle getResourceBundle() {
        FacesContext context = getFacesContext();
        Application application = context.getApplication();
        return application.getResourceBundle(context, I18N_BUNDLE_VAR);
    }

    public static String formatI18nMessage(String I18nMessage, Object... arguments) {
        ResourceBundle bundle = getResourceBundle();
        return formatMessage(bundle.getString(I18nMessage), arguments);
    }

    public static UIComponent getUIComponent(String fullComponentId) {
        return getFacesContext().getViewRoot().findComponent(fullComponentId);
    }

    public static <T> T getBeanFromClass(FacesContext context, Class<T> beanClass) {
        Application application = context.getApplication();

        String beanName = resolveManagedBeanName(beanClass);
        if (beanName.isEmpty()) {
            beanName = toCamelCase(beanClass.getSimpleName());
        }

        return application.evaluateExpressionGet(context, "#{" + beanName + "}", beanClass);
    }

    public static <T> T getBeanFromClass(Class<T> beanClass) {
        FacesContext context = getFacesContext();
        return getBeanFromClass(context, beanClass);
    }

    private static String resolveManagedBeanName(Class<?> beanClass) {
        // Tenta buscar a anotação @ApplicationScoped
        Named named = beanClass.getAnnotation(Named.class);

        if (named != null) {
            return named.value();
        } else {
            throw new IllegalArgumentException(beanClass.getName() + " is not a bean.");
        }
    }

    public static String toCamelCase(String text) {
        String[] parts = text.split("_");

        StringBuilder builder = new StringBuilder();
        builder.append(parts[0].substring(0, 1).toLowerCase());
        builder.append(parts[0].substring(1));

        for (int i = 1; i < parts.length; i++) {
            builder.append(parts[i].substring(0, 1).toUpperCase());
            builder.append(parts[i].substring(1).toLowerCase());
        }

        return builder.toString();
    }
}
