package com.web.ControlCliente.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    //Internacionalización
    @Bean
    public LocaleResolver localeResolver() {
        //Bean lo agregara al contexto de spring en el contenedor de inyecciones
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("es"));
        return slr;

    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        //interface que modificara dinamicamente el lenguaje
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");//param lang para especificar lengauje en las rutas
        return lci;

    }

    @Override// lo esramos sobre escribiendo
    public void addInterceptors(InterceptorRegistry registration) {
        registration.addInterceptor(localeChangeInterceptor());
    }

    @Override //paths que no pasan por controlador
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "index");
        registry.addViewController("/login");
        registry.addViewController("/errors/403").setViewName("/errors/403");
    }
}
