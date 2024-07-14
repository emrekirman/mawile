package com.emrekirman.mawile.common.translator;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

/**
 * It allows us to access messages created in Messages directly with keywords
 */
@Service
public class TranslatorService {

    final MessageSource messageSource;

    TranslatorService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String translate(String code) {
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }
}