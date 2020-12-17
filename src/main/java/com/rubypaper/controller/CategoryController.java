package com.rubypaper.controller;

import com.rubypaper.domain.blog.Blog;
import com.rubypaper.domain.category.Category;
import com.rubypaper.domain.user.User;
import com.rubypaper.service.BlogService;
import com.rubypaper.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/category")
@SessionAttributes({"user", "myBlogId"})
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final BlogService blogService;

    @GetMapping("/view/managing")
    public String managingView(Long categoryId, Model model) {
        User user = (User) model.getAttribute("user");

        Optional<Blog> blog = blogService.findMyBlog(user.getId());
        Blog myBlog = blog.get();
        model.addAttribute("myBlog", myBlog);
        model.addAttribute("myBlogId", myBlog.getId());

        List<Category> categoryList = categoryService.getCategories(myBlog.getId());
        model.addAttribute("categoryList", categoryList);

        if (categoryId != null) {
            for (Category category : categoryList) {
                if (category.getId().equals(categoryId)) {
                    model.addAttribute("selectedCategory", category);
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
    public String updateProcess(Category category) {
        categoryService.updateCategory(category);

        return "redirect:/category/view/managing";
    }

    @PostMapping("/insert")
    public String insertProcess(Category category, Model model) {
        Long myBlogId = (Long) model.getAttribute("myBlogId");
        categoryService.saveCategory(category, myBlogId);

        return "redirect:/category/view/managing";
    }


}
