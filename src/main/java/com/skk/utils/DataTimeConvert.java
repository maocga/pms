package com.skk.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataTimeConvert implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        try {
            Date parse = sdf.parse(source);
            return parse;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
