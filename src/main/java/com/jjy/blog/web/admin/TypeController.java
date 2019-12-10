package com.jjy.blog.web.admin;

import com.jjy.blog.po.Type;
import com.jjy.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TypeController {


    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String typesList(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model) {

        model.addAttribute("page",typeService.listType(pageable));
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String inputPage(Model model) {
        model.addAttribute("type",new Type());
        return "admin/type-input";
    }

    @PostMapping("/types")
    public String input(@Valid Type type, BindingResult result, RedirectAttributes attributes) {
        //非空验证
        if (result.hasErrors()) {
            return "admin/type-input";
        }
        //同名验证
        if (typeService.getTypeByName(type.getName()) != null) {
            result.rejectValue("name", "nameError", "不能添加重复的分类");
            return "admin/type-input";
        }

        Type t = typeService.saveType(type);
        if (t == null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else{
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/update")
    public String updatePage(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getType(id));
        return "/admin/type-update";
    }

    @PostMapping("/types/{id}")
    public String update(@Valid Type type, BindingResult result, @PathVariable Long id, RedirectAttributes attributes) {

        //非空验证
        if (result.hasErrors()) {
            return "admin/type-update";
        }
        //同名验证
        if (typeService.getTypeByName(type.getName()) != null) {
            result.rejectValue("name", "nameError", "不能添加重复的分类");
            return "admin/type-update";
        }
        Type t = typeService.updateType(id, type);
        if (t == null) {
            attributes.addFlashAttribute("message", "更新失败");
        } else{
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }

}
