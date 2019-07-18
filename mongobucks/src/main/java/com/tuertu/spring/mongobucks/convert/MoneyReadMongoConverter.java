package com.tuertu.spring.mongobucks.convert;

import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;


@Slf4j
public class MoneyReadMongoConverter implements Converter<Document, Money> {
    @Override
    public Money convert(Document document) {
        Document source = (Document) document.get("money");
        log.info("source: {}", source);
        Document currency = (Document) source.get("currency");
        double price = Double.parseDouble(source.getString("amount"));

        Money money = Money.of(CurrencyUnit.of(currency.getString("code")), price);
        return money;
    }
}
