package com.example.huo.myappgit.Entity;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by huo on 08/07/16.
 */

public class Language {
    private        String         name;
    private        String         path;
    private static List<Language> mLanguages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public static List<Language> getLanguages(Context context) {
        if (mLanguages != null) return mLanguages;
        try {
            InputStream stream = context.getAssets().open("json/langs.json");
            String      langs  = IOUtils.toString(stream);
            mLanguages = new Gson().fromJson(langs, new TypeToken<List<Language>>() {
            }.getType());
            return mLanguages;
        } catch (IOException e) {
            throw new RuntimeException("小伙子 不要看一些不良信息");
        }
    }
}
