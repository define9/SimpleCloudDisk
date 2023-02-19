package cn.tomisme.domain.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class R<T> {
    private String code;
    private String msg;
    private T data;

    public static <T> R<T> success(String m, T d) {
        return new R<T>("0", m, d);
    }

    public static <T> R<T> success(T d) {
        return new R<T>("0", "", d);
    }

    public static <T> R<T> success() {
        return new R<>("0", "", null);
    }

    public static <T> R<T> error(String m) {
        return new R<>("1", m, null);
    }

    public static <T> R<T> error(String c, String m) {
        return new R<>(c, m, null);
    }

    public R<T> setData(T d) {
        data = d;
        return this;
    }
    public R<T> setMsg(String m) {
        msg = m;
        return this;
    }
    public R<T> setCode(String c) {
        code = c;
        return this;
    }
}
