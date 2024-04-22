package rikkei.academy.advice;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import rikkei.academy.exception.DataExistException;
import rikkei.academy.model.dto.response.DataError;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class APIControllerAdvice {
    // Bắt ngoại lệ và thông báo cho Lỗi: Thông tin gửi lên không hợp lệ (không đúng định dạng, ... )
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DataError<Map<String, String>> handleErr(MethodArgumentNotValidException e) {
        Map<String, String> map = new HashMap<>();
        for (FieldError error : e.getFieldErrors()){
            map.put(error.getField(),error.getDefaultMessage());
        }
        return new DataError<>(map, HttpStatus.BAD_REQUEST);
    }

    // Bắt ngoại lệ và thông báo cho Lỗi: Không tìm thấy Sản phẩm theo Id
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DataError<String> handleErrNotFound(NoSuchElementException e) {
        return new DataError<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Bắt ngoại lệ và thông báo cho Lỗi: Trùng tên Sản phẩm(Chia ra để hợp với: phương thức thêm mới và Update)
    @ExceptionHandler(DataExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DataError<Map<String, String>> handleErr(DataExistException e) {
        Map<String, String> map = new HashMap<>();
        map.put(e.getField(), e.getMessage());
        return new DataError<>(map, HttpStatus.BAD_REQUEST);
    }
}
