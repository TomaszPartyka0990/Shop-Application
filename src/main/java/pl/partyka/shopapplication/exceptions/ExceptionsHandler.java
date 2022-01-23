package pl.partyka.shopapplication.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public String userAlreadyExistsExceptionThrow (Exception e, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("exceptionHandlerMessage", e.getMessage());
        return "redirect:/register";
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public String categoryAlereadyExistsExceptionThrow (CategoryAlreadyExistsException e, RedirectAttributes redirectAttributes,
                                                        HttpServletRequest request){
        redirectAttributes.addFlashAttribute("exceptionHandlerMessage", e.getMessage());
        redirectAttributes.addFlashAttribute("categoryForm", e.getCategoryRequest());
        if (request.getRequestURI().equals("/categories/update")){
            return "redirect:/categories/"+e.getCategoryRequest().getCategoryId();
        } else {
            return "redirect:/categories/add";
        }
    }

    @ExceptionHandler(CategoryDoesNotExistsException.class)
    public String categoryDoesNotExistsExceptionThrow (Exception e, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("exceptionHandlerMessage", e.getMessage());
        return "redirect:/categories";
    }
}
