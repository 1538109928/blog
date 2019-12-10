package com.jjy.blog.web.admin;

import com.jjy.blog.po.Tag;
import com.jjy.blog.po.Type;
import com.jjy.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping("/tags")
    public String tagsList(Model model,@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("page",tagService.listTag(pageable));
        return "admin/tags";
    }

    @RequestMapping("tags/input")
    public String inputPage(Model model) {
        model.addAttribute("tag",new Tag());
        return "admin/tag-input";
}

    @PostMapping("tags")
    public String input(@Valid Tag tag, BindingResult result, RedirectAttributes attributes) {

        //非空检测,这里和type采用的验证方式不同
        if (tag.getName() == null) {
            result.rejectValue("name", "nameError", "标签不能为空");
            return "admin/tag-input";
        }
        //同名检测
        if (tagService.getTagByName(tag.getName()) != null) {
            result.rejectValue("name", "nameError", "不能添加重复的标签");
            return "admin/tag-input";
        }

        Tag t = tagService.saveTag(tag);

        if(t == null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping ("tags/{id}/update")
    public String updatePage(@PathVariable Long id, Model model) {
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tag-update";
    }

    @PostMapping ("tags/{id}")
    public String update(@Valid Tag tag, @PathVariable Long id, BindingResult result, RedirectAttributes attributes) {
        // @Valid 验证变量是否符合要求，如非空等
        // 非空检测
        if (tag.getName() == null) {
            result.rejectValue("name", "nameError", "标签不能为空");
            return "admin/tag-update";
        }

        Tag t = tagService.updateTag(id, tag);

        if (t != null) {
            attributes.addFlashAttribute("message", "更新成功");
        } else {
            attributes.addFlashAttribute("message", "更新失败");
        }

        return "redirect:/admin/tags";
    }

    @GetMapping ("tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }

}
