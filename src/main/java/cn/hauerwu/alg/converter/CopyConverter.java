package cn.hauerwu.alg.converter;

public class CopyConverter<T> implements Converter<T> {
    public T convert(T value){
        return value;
    }
}
