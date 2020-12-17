package com.rubypaper.controller;

import com.rubypaper.domain.blog.Blog;
import com.rubypaper.domain.category.Category;
import com.rubypaper.domain.user.User;
import com.rubypaper.service.BlogService;
import com.rubypaper.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/category")
@SessionAttributes({"user", "myBlog", "category", })
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final BlogService blogService;

    @GetMapping("/view/managing")
    public String managingView(Long categoryId, String actionType, String errorMessage, Model model) {
        User user = (User) model.getAttribute("user");

        Optional<Blog> blog = blogService.findMyBlog(user.getId());
        Blog myBlog = blog.get();
        model.addAttribute("myBlog", myBlog);

        List<Category> categoryList = categoryService.getCategories(myBlog.getId());
        model.addAttribute("categoryList", categoryList);
        if (actionType != null) {
            model.addAttribute("actionType", actionType);
        } else {
            model.addAttribute("actionType", "insert");
        }
        model.addAttribute("errorMessage", errorMessage);

        if (categoryId != null) {
            for (Category category : categoryList) {
                if (category.getId().equals(categoryId)) {
                    model.addAttribute("category", category);
                    break;
                }
            }
        }

        return "blogadmin_category";
    }

    @GetMapping("/delete")
    public String deleteProcess(Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return "redirect:/category/view/managing";
    }

    @PostMapping("/update")
    public String updateProcess(@Valid Category category, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            redirectAttributes.addAttribute("errorMessage", fieldError.getDefaultMessage());
            redirectAttributes.addAttribute("actionType", "update");
            return "redirect:/category/view/managing";
        }
        categoryService.updateCategory(category);

        return "redirect:/category/view/managing";
    }

    @PostMapping("/insert")
    public String insertProcess(@Valid Category category, BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,Model model) {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            redirectAttributes.addAttribute("errorMessage", fieldError.getDefaultMessage());
            redirectAttributes.addAttribute("actionType", "insert");
            return "redirect:/category/view/managing";
        }
        Blog myBlog = (Blog) model.getAttribute("myBlog");
        categoryService.saveCategory(category, myBlog.getId());

        return "redirect:/category/view/managing";
    }
}
