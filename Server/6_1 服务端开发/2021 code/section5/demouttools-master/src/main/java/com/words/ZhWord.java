package com.words;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ZhWord implements Comparable<ZhWord> {
    private String name;//词语名
    private Set<String> pinyin = new HashSet<>();//保存拼音
    private int count;//使用计数，用于将经常使用到的词排在前面

    public ZhWord(String name, String pinyin) {
        this.name = name;
        this.pinyin.add(pinyin);
    }

    public ZhWord(String name, String pinyin1, String pinyin2) {
        this.name = name;
        this.pinyin.add(pinyin1);
        this.pinyin.add(pinyin2);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getPinyin() {
        return pinyin;
    }

    public int getCount() {
        return count;
    }

    public void addCount() {
        this.count++;
    }

    public void clearCount() {
        this.count = 0;
    }

    @Override
    public int compareTo(ZhWord other) {
        return other.count - this.count;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        ZhWord word = (ZhWord) other;
        return Objects.equals(name, word.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}