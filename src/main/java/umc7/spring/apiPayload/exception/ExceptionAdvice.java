package umc7.spring.apiPayload.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import umc7.spring.apiPayload.ApiResponse;
import umc7.spring.apiPayload.code.ErrorReasonDto;
import umc7.spring.apiPayload.code.status.ErrorStatus;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestControllerAdvice(annotations = {RestController.class})
public class ExceptionAdvice extends ResponseEntityExceptionHandler {
    /**
     * @org.springframework.web.bind.annotation.ExceptionHandler 를 사용하면
     * 컨트롤러 내에서 특정 예외 유형에 대한 전용 처리기를 지정한다.
     * 지금 매개변수로는 ConstraintException을 받고 있기 때문에
     * 해당 validation 메서드는 ConstraintVioleationException 예외를 처리할 메서드임을 나타낸다.
     * 정리하자면 ExceptionHandler 어노테이션 덕분에 ContraintViolcationException이 발생했을 때 valudation 메소드를 호출한다.
     */
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Object> validation(ConstraintViolationException e, WebRequest request) {
        // 첫번째 메세지를 추출한다. 예외 발생시 , RunTimeException을 던짐
        String errorMessage = e.getConstraintViolations().stream()
                .map(constraintViolation -> constraintViolation.getMessage())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("ConstraintViolationException 추출 도중 에러 발생"));
        //hadleExceptionInternalConstraint 메서드를 호출해서 RespneseEntity 응답 객체를 생성하고 반환
        return handleExceptionInternalConstraint(e, ErrorStatus.valueOf(errorMessage), HttpHeaders.EMPTY,request);
    }

    /**
     * handleMethodArgumentNotValid 메서드는 ResponseEntityExceptionHandler 클래스에서 제공하는 메서드이다.
     * 주로 MethodArgumentNotValidException 예외를 처리한다. 이 예외는 주로 폼 데이터 유요성 검사시 발생하며, 데이터 바인딩이나
     * 유효성 검사에 실패했을때 호츌된다.
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        //필드 에러들을 추출하고 에러 메세지를 맵에 저장한다.
        Map<String, String> errors = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().stream()
                .forEach(fieldError -> {
                    String fieldNAme = fieldError.getField();
                    String errorMessage = Optional.ofNullable(fieldError.getDefaultMessage()).orElse("");
                    errors.merge(fieldNAme, errorMessage, (existingErrorMessage, newErrorMessage) -> existingErrorMessage + ", " + newErrorMessage);
                });
        return handleExceptionInternalArgs(ex, HttpHeaders.EMPTY, ErrorStatus.valueOf("_BAD_REQUEST"), request, errors);

    }
    /**
     * 마찬가지로 @org.springframework.web.bind.annotation.ExceptionHandler 를 사용하고 있다.
     * Exception 예외를 처리할 메서드임을 나타낸다.
     * 이 어노테이션 때문에 Exception 발생했을 때 exception 메소드를 호출한다.
     */

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Object> exception(Exception e, WebRequest request) {
        e.printStackTrace();
        //예외 발생시, 스택트레이스를 출력하고 handleExceptionInternalFalse 메서드를 호출해서 ResponseEntity 객체를 생성하고 반환
        return handleExceptionInternalFalse(e, ErrorStatus._INTERNAL_SERVER_ERROR, HttpHeaders.EMPTY, ErrorStatus._INTERNAL_SERVER_ERROR.getHttpStatus(),request, e.getMessage());
    }
    @ExceptionHandler(value = GeneralException.class)
    public ResponseEntity onThrowException(GeneralException generalException, HttpServletRequest request) {
        ErrorReasonDto errorReasonHttpStatus = generalException.getErrorReasonHttpStatus(); //ErrorReasonDTO 객체를 가져온다.
        return handleExceptionInternal(generalException,errorReasonHttpStatus,null,request); //다른 메서드들과는 다르게 super가 아닌 커스터마이징된 handleExceptionInternal을 호출
    }


    //ResponseEntityExceptionHandler 의 클래스의 handleExceptionInternal 메서드를 호출해서 ResponseEntity 생성
    //그런데 여기서 이 메서드는 handleExceptionInternal를 재정의한 것이며,커스터 마이징 한걸 볼 수 있다.
    // ErrorResonDTO 객체를 바탕으로 ApiResponse 객체를 생성하고, HttpServletRequest객체를 webRequest 객체로 변환하는 과정이 있음.
    // 그리고 부모 클래스의 habdleExceptionInternal 메서드를 호출해서 ResponseEntity를 생성하고 반환하고 있다.
    private ResponseEntity<Object> handleExceptionInternal(Exception e, ErrorReasonDto reason, HttpHeaders headers, HttpServletRequest request) {

        ApiResponse<Object> body = ApiResponse.onFailure(reason.getCode(),reason.getMessage(),null);
//        e.printStackTrace();

        WebRequest webRequest = new ServletWebRequest(request);
        return super.handleExceptionInternal(
                e,
                body,
                headers,
                reason.getHttpStatus(),
                webRequest
        );
    }

    /**
     * errorCommonStatus랑 errorPoint 사용해서 ApiResponse 객체를 생성한다. 그리고
     * ResponseEntityExceptionHandler의 handleExceptionInternal 메서드를 호출하여 ResponseEntity 객체를 생성하고 반환한다.
     */
    private ResponseEntity<Object> handleExceptionInternalFalse(Exception e, ErrorStatus errorCommonStatus,
                                                                HttpHeaders headers, HttpStatus status, WebRequest request, String errorPoint) {
        ApiResponse<Object> body = ApiResponse.onFailure(errorCommonStatus.getCode(),errorCommonStatus.getMessage(),errorPoint);
        return super.handleExceptionInternal(
                e,
                body,
                headers,
                status,
                request
        );
    }
    private ResponseEntity<Object> handleExceptionInternalArgs(Exception e, HttpHeaders headers, ErrorStatus errorCommonStatus, WebRequest request, Map<String, String> errorArgs) {
        ApiResponse<Object> body = ApiResponse.onFailure(errorCommonStatus.getCode(),errorCommonStatus.getMessage(),errorArgs);

        return super.handleExceptionInternal(
                e,
                body,
                headers,
                errorCommonStatus.getHttpStatus(),
                request
        );
    }
    private ResponseEntity<Object> handleExceptionInternalConstraint(Exception e, ErrorStatus errorCommonStatus,
                                                                     HttpHeaders headers, WebRequest request) {
        //ErrorStatus 정보를 바탕으로 ApiResponse 객체 생성
        //ApiResponse.onFailure 메서드를 사용해서 실패 응답 객체 생성.
        ApiResponse<Object> body = ApiResponse.onFailure(errorCommonStatus.getCode(), errorCommonStatus.getMessage(), null);
        //ResponseEntityExceptionHandler 의 클래스의 handleExceptionInternal 메서드를 호출해서 ResponseEntity 생성
        return super.handleExceptionInternal( //여러 종류의 예외 처리 메서드가 최종적으로 호출하는 내부 처리 메서드
                // 이 메서드는 예외 발생시 , 적절한 responseEntity 객체를 생성해서 클라이언트에게 반환한다.
                e,
                body,
                headers,
                errorCommonStatus.getHttpStatus(),
                request
        );
    }

}
