package utils;

import com.appspring.model.Result;

enum  NetStatus {
    Success(0),
    Error(1);

    int rawValue;
    NetStatus(Integer integer) {

        rawValue = integer;
    }
}

public class ResultUtil {

    public static Result success(Object object) {

        Result r = new Result();
        r.setCode(NetStatus.Success.rawValue);
        r.setMsg("success");
        r.setData(object);
        return r;
    }

    public static Result error(Object object) {

        Result r = new Result();
        r.setCode(NetStatus.Error.rawValue);
        r.setMsg("error");
        r.setData(object);
        return r;

    }
}
