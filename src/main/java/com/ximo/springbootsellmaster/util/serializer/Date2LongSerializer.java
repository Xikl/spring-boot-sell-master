package com.ximo.springbootsellmaster.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * 将date时间改为以秒为单位的
 * Created by 朱文赵
 * 2017/9/12
 */
public class Date2LongSerializer extends JsonSerializer<Date>{

    /**
     * 重写该方法
     * @param date
     * @param jsonGenerator
     * @param serializerProvider
     * @throws IOException
     * @throws JsonProcessingException
     */
    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                                            throws IOException {
        //转化为时间戳格式
        jsonGenerator.writeNumber(date.getTime() / 1000);
        //转换为标准的日期格式 "createTime": "2017-09-12 13:44:25",
//        jsonGenerator.writeString(DateUtil.parseDateToString(date));
    }

}
