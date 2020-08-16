package ksy.geshi.validator;

import ksy.geshi.form.MemberForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class PasswordValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz){
        return MemberForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberForm memberForm=(MemberForm)target;
        String beanName=errors.getObjectName();
        if (beanName.equals("memberForm")) {
            if (memberForm.getPassword().equals(memberForm.getPassword2()) == false) {
                errors.rejectValue("password","NotEquals","비밀번호가 같지 않습니다.");
                errors.rejectValue("password2","NotEquals","비밀번호가 같지 않습니다.");

            }
        }
    }

}
