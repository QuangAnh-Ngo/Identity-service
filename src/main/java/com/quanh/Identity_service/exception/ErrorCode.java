package com.quanh.Identity_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    /**
     * Lỗi chung nhất, được sử dụng khi một ngoại lệ không xác định xảy ra trong hệ thống.
     * <ul>
     * <li><b>Khi nào ném ra:</b> Là fallback cuối cùng khi không có handler nào khác bắt được Exception. Ví dụ: Lỗi NullPointerException không mong muốn.</li>
     * <li><b>Nơi xử lý:</b> GlobalExceptionHandler.</li>
     * <li><b>Gợi ý cho Client:</b> Hiển thị một thông báo lỗi chung chung như "Đã có lỗi xảy ra, vui lòng thử lại sau."</li>
     * </ul>
     */
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Exception", HttpStatus.INTERNAL_SERVER_ERROR),

    /**
     * Lỗi này dành cho mục đích kiểm tra nội bộ trong quá trình phát triển.
     * <ul>
     * <li><b>Khi nào ném ra:</b> Khi một key message trong validation không hợp lệ.</li>
     * <li><b>Nơi xử lý:</b> GlobalExceptionHandler, trong handler của MethodArgumentNotValidException.</li>
     * <li><b>Gợi ý cho Client:</b> Lỗi này không nên xuất hiện ở môi trường production.</li>
     * </ul>
     */
    INVALID_KEY(1001,"Invalid message key", HttpStatus.BAD_REQUEST),

    /**
     * Lỗi cho biết người dùng đang cố gắng đăng ký với một username đã tồn tại.
     * <ul>
     * <li><b>Khi nào ném ra:</b> Trong `UserService.createUser` khi kiểm tra thấy username đã có trong database.</li>
     * <li><b>Nơi xử lý:</b> GlobalExceptionHandler (bắt AppException).</li>
     * <li><b>Gợi ý cho Client:</b> Hiển thị thông báo lỗi ngay trên form đăng ký: "Tên đăng nhập đã được sử dụng."</li>
     * </ul>
     */
    USER_EXISTED(1002, "User existed", HttpStatus.BAD_REQUEST),

    /**
     * Lỗi validation cho trường username, không đáp ứng yêu cầu (ví dụ: độ dài tối thiểu).
     * <ul>
     * <li><b>Khi nào ném ra:</b> Khi DTO request (ví dụ UserCreationRequest) có annotation validation (@Size) và dữ liệu gửi lên không hợp lệ.</li>
     * <li><b>Nơi xử lý:</b> GlobalExceptionHandler.</li>
     * <li><b>Gợi ý cho Client:</b> Hiển thị thông báo lỗi validation ngay bên dưới trường nhập username.</li>
     * </ul>
     */
    USERNAME_INVALID(1003, "User name must be at least 3 characters", HttpStatus.BAD_REQUEST),

    /**
     * Lỗi validation cho trường password, không đáp ứng yêu cầu (ví dụ: độ dài tối thiểu).
     * <ul>
     * <li><b>Khi nào ném ra:</b> Tương tự như USERNAME_INVALID, nhưng áp dụng cho trường password.</li>
     * <li><b>Nơi xử lý:</b> GlobalExceptionHandler.</li>
     * <li><b>Gợi ý cho Client:</b> Hiển thị yêu cầu về mật khẩu ngay bên dưới trường nhập liệu.</li>
     * </ul>
     */
    PASSWORD_INVALID(1004, "Password must be at least 8 characters", HttpStatus.BAD_REQUEST),

    /**
     * Lỗi cho biết người dùng được tìm kiếm không tồn tại trong hệ thống.
     * <ul>
     * <li><b>Khi nào ném ra:</b>
     * <li>1. Trong `AuthenticationService.authenticate` khi đăng nhập với username không tồn tại.</li>
     * <li>2. Trong `UserService.getUser` và `getMyInfo` khi không tìm thấy user.</li>
     * </li>
     * <li><b>Nơi xử lý:</b> GlobalExceptionHandler (bắt AppException).</li>
     * <li><b>Gợi ý cho Client:</b> Hiển thị thông báo "Tên đăng nhập hoặc mật khẩu không chính xác" (cho trường hợp đăng nhập) hoặc "Không tìm thấy người dùng."</li>
     * </ul>
     */
    USER_NOT_EXISTED(1005, "User not existed",  HttpStatus.NOT_FOUND),

    /**
     * Lỗi xác thực, có 2 trường hợp chính.
     * <ul>
     * <li><b>Khi nào ném ra:</b>
     * <li>1. Trong `AuthenticationService.authenticate` khi người dùng nhập sai mật khẩu.</li>
     * <li>2. Khi người dùng truy cập endpoint được bảo vệ mà không cung cấp token, token hết hạn, hoặc token không hợp lệ.</li>
     * </li>
     * <li><b>Nơi xử lý:</b>
     * <li>1. GlobalExceptionHandler (bắt AppException từ service).</li>
     * <li>2. JwtAuthenticationEntryPoint (bắt lỗi từ tầng Security Filter).</li>
     * </li>
     * <li><b>Gợi ý cho Client:</b>
     * <li>1. Hiển thị "Tên đăng nhập hoặc mật khẩu không chính xác."</li>
     * <li>2. Xóa token đã lưu và tự động chuyển hướng người dùng về trang đăng nhập.</li>
     * </li>
     * </ul>
     */
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),

    /**
     * Lỗi phân quyền, cho biết người dùng đã đăng nhập nhưng không có quyền truy cập tài nguyên.
     * <ul>
     * <li><b>Khi nào ném ra:</b> Khi một người dùng truy cập vào API được bảo vệ bởi `@PreAuthorize` hoặc `@PostAuthorize` mà không có đủ quyền hạn yêu cầu.</li>
     * <li><b>Nơi xử lý:</b> CustomAccessDeniedHandler (được cấu hình trong SecurityConfig để xử lý AccessDeniedException).</li>
     * <li><b>Gợi ý cho Client:</b> Hiển thị trang/thông báo lỗi "403 Forbidden" hoặc "Bạn không có quyền truy cập."</li>
     * </ul>
     */
    UNAUTHORIZE(1007, "Access denied", HttpStatus.FORBIDDEN),
    DOB_INVALID(1008, "User must be over 18", HttpStatus.BAD_REQUEST),

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