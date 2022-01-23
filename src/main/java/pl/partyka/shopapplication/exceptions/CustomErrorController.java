package pl.partyka.shopapplication.exceptions;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {

    @GetMapping
    public String handleError(HttpServletRequest request, Model model){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        switch (statusCode){
            case 404:
                model.addAttribute("pageNotFoundMessage", "Podana strona nie ozstała odnaleziona");
                break;
            case 403:
                model.addAttribute("accessDeniedMessage", "Próbujesz dostać się do zasobu do którego nie masz dostępu");
                break;
            default:
                model.addAttribute("defaultErrorMessage", "Wystąpił nieznany błąd");
        }
        return "errors";
    }
}
