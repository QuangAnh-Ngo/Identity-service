package com.quanh.Identity_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    /**
     * Lỗi chung nhất, được sử dụng khi một ngoại lệ không xác định xảy ra trong hệ thống.
     * <ul>
     * <li><b>Khi nào ném ra:</b> Là fallback cuối cùng khi không có handler nào khác bắt được Exception.</li>
     * <li><b>Nơi xử lý:</b> GlobalExceptionHandler.</li>
     * <li><b>Gợi ý cho Client:</b> Hiển thị một thông báo lỗi chung chung như "Đã có lỗi xảy ra, vui lòng thử lại sau" và ghi lại chi tiết lỗi để đội ngũ phát triển kiểm tra.</li>
     * </ul>
     */
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Exception", HttpStatus.INTERNAL_SERVER_ERROR),
    /**
     * Lỗi này thường dành cho mục đích kiểm tra nội bộ trong quá trình phát triển.
     * <ul>
     * <li><b>Khi nào ném ra:</b> Khi một key message trong validation không hợp lệ.</li>
     * <li><b>Nơi xử lý:</b> GlobalExceptionHandler, trong handler của MethodArgumentNotValidException.</li>
     * <li><b>Gợi ý cho Client:</b> Lỗi này không nên xuất hiện ở môi trường production. Nếu có, client chỉ nên hiển thị lỗi chung và báo cho đội ngũ phát triển.</li>
     * </ul>
     */
    INVALID_KEY(1001,"Invalid message key", HttpStatus.BAD_REQUEST),    //Để kiểm tra key trong handler code đã để đúng chưa
    /**
     * Lỗi cho biết người dùng đang cố gắng đăng ký với một username hoặc email đã tồn tại trong cơ sở dữ liệu.
     * <ul>
     * <li><b>Khi nào ném ra:</b> Trong service xử lý logic đăng ký người dùng, khi kiểm tra thấy username/email đã tồn tại. Thường được ném ra dưới dạng new AppException(ErrorCode.USER_EXISTED).</li>
     * <li><b>Nơi xử lý:</b> GlobalExceptionHandler.</li>
     * <li><b>Gợi ý cho Client:</b> Hiển thị thông báo lỗi ngay trên form đăng ký, ví dụ: "Tên đăng nhập hoặc email đã được sử dụng."</li>
     * </ul>
     */
    USER_EXISTED(1002, "User existed", HttpStatus.BAD_REQUEST),
    /**
     * Lỗi validation cho trường username, không đáp ứng yêu cầu về độ dài tối thiểu.
     * <ul>
     * <li><b>Khi nào ném ra:</b> Khi DTO request (ví dụ UserCreationRequest) có annotation validation (@Size) và dữ liệu gửi lên không hợp lệ.</li>
     * <li><b>Nơi xử lý:</b> GlobalExceptionHandler, trong handler của MethodArgumentNotValidException.</li>
     * <li><b>Gợi ý cho Client:</b> Hiển thị thông báo lỗi validation ngay bên dưới trường nhập username, ví dụ: "Tên người dùng phải có ít nhất 3 ký tự."</li>
     * </ul>
     */
    USERNAME_INVALID(1003, "User name must be at least 3 characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1004, "Password must be at least 8 characters", HttpStatus.BAD_REQUEST),
    /**
     * Lỗi cho biết người dùng được tìm kiếm không tồn tại trong hệ thống.
     * <ul>
     * <li><b>Khi nào ném ra:</b> Khi thực hiện các thao tác như lấy thông tin, cập nhật, hoặc xóa một người dùng qua ID nhưng không tìm thấy ID đó trong DB.</li>
     * <li><b>Nơi xử lý:</b> GlobalExceptionHandler (thông qua AppException).</li>
     * <li><b>Gợi ý cho Client:</b> Hiển thị trang "404 Not Found" hoặc một thông báo thân thiện "Không tìm thấy người dùng."</li>
     * </ul>
     */
    USER_NOT_EXISTED(1005, "User not existed",  HttpStatus.NOT_FOUND),
    /**
     * Lỗi xác thực, cho biết người dùng chưa đăng nhập hoặc token không hợp lệ.
     * <ul>
     * <li><b>Khi nào ném ra:</b> Khi người dùng truy cập vào một endpoint được bảo vệ mà không cung cấp token, hoặc token đã hết hạn, hoặc token có chữ ký không hợp lệ.</li>
     * <li><b>Nơi xử lý:</b> JwtAuthenticationEntryPoint (cấu hình trong SecurityConfig).</li>
     * <li><b>Gợi ý cho Client:</b> Xóa token đã lưu (nếu có) và tự động chuyển hướng người dùng về trang đăng nhập.</li>
     * </ul>
     */
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    /**
     * Lỗi phân quyền, cho biết người dùng đã đăng nhập nhưng không có quyền truy cập tài nguyên.
     * <ul>
     * <li><b>Khi nào ném ra:</b> Khi một người dùng với vai trò USER cố gắng truy cập vào một API yêu cầu vai trò ADMIN.</li>
     * <li><b>Nơi xử lý:</b> CustomAccessDeniedHandler (cấu hình trong SecurityConfig).</li>
     * <li><b>Gợi ý cho Client:</b> Hiển thị một trang/thông báo lỗi "403 Forbidden" hoặc "Bạn không có quyền truy cập vào trang này." Không chuyển hướng về trang đăng nhập vì người dùng đã đăng nhập rồi.</li>
     * </ul>
     */
    UNAUTHORIZE(1007, "Access denied", HttpStatus.FORBIDDEN),
    ;

    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }


}
