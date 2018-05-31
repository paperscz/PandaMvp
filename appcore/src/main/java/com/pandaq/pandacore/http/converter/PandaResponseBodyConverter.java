package com.pandaq.pandacore.http.converter;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.pandaq.pandacore.http.entity.ApiData;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by huxinyu on 2018/5/31.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :响应实体解析类
 */
public class PandaResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private Gson gson;
    private TypeAdapter<T> typeAdapter;

    public PandaResponseBodyConverter(Gson gson, TypeAdapter<T> typeAdapter) {
        this.gson = gson;
        this.typeAdapter = typeAdapter;
    }

    @Override
    public T convert(@NonNull ResponseBody value) throws IOException {
        String response = value.string();
        // 如是加密信息可在此处解密后再解析
        ApiData<T> apiData = gson.fromJson(response, new TypeToken<ApiData<T>>() {
        }.getType());
        /* 如是按约定格式返回数据 apiData 中的 code 是必须的。
         * 因此可以用 code 是否存在来判断数据是否合法
         */
        if (apiData.getCode() == null) {
            try {
                return typeAdapter.fromJson(response);
            } finally {
                value.close();
            }
        } else {
            try {
                // 获取解析数据
                String data = new Gson().toJson(apiData.getData());
                if (!apiData.isSuccess()) {
                    throw new ApiException(apiData.getCode(), apiData.getMsg(), data);
                }
                return typeAdapter.fromJson(data);
            } finally {
                value.close();
            }
        }
    }
}
