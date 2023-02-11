package cn.tomisme.config;

import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {
    /**
     * 所有的 BizException 异常处理
     * @param exception BizException
     * @return R
     */
    @ExceptionHandler(value = BizException.class)
    public SingleResponse handleException(BizException exception) {
        log.error("error: {}", exception.getMessage());
        return SingleResponse.buildFailure(exception.getErrCode(), exception.getMessage());
    }
}
