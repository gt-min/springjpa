package com.min.springjpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.min.springjpa.entity.Visitor;
import com.min.springjpa.repository.VisitorRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Controller
public class VisitorController {
  
  private final VisitorRepository visitorRepository;

  @Transactional(readOnly = true)
  @GetMapping("/vlist")
  public ModelAndView vlist() {
    List<Visitor> list = visitorRepository.findAll();
    ModelAndView mav = new ModelAndView();  
    if (list.size() != 0) {
      mav.addObject("list", list);
    } else {
      mav.addObject("msg", "추출된 결과가 없어요");
    }
    mav.setViewName("visitorView");
    return mav;
  }

  @Transactional(readOnly = true)
  @GetMapping("/vsearch")
  public ModelAndView vsearch(String key) {
    List<Visitor> list = visitorRepository.findByMemoContains(key);
    ModelAndView mav = new ModelAndView();
    if (list.size() != 0) {
      mav.addObject("list", list);
    } else {
      mav.addObject("msg", "추출된 결과가 없어요");
    }
    mav.setViewName("visitorView");
    return mav;
  }

  @GetMapping(value="/vdelete")
  public ModelAndView vdelete(int id) {
    ModelAndView mav = new ModelAndView();
    try {
      visitorRepository.deleteById(id);      
      mav.addObject("list", visitorRepository.findAll());
    } catch(Exception e) {      
      mav.addObject("msg", "글 삭제에 실패했습니다.");
    }
    mav.setViewName("visitorView");
    return mav;
  }
  
  @GetMapping(value = "/one", produces = "application/json")
  public ResponseEntity<Visitor> one(int id) {
    Optional<Visitor> result = visitorRepository.findById(id);
    return ResponseEntity.ok(result.get());    
  }

  @PostMapping(value = "/vinsert")
  public String vinsert(Visitor vo, Model model) {
    try {
      visitorRepository.save(vo);      
      return "redirect:/vlist";
    } catch(Exception e) {      
      model.addAttribute("msg", "글 작성에 실패했습니다.");
    }
    return "visitorView";
  }
  
  @PostMapping(value = "/vupdate")
  @Transactional
  public String vupdate(Visitor vo, Model model) {    
    try {
      Visitor entity = visitorRepository.findById(vo.getId()).get();
      entity.setName(vo.getName());  
      entity.setMemo(vo.getMemo());
      return "redirect:/vlist";
    } catch(Exception e) {            
      model.addAttribute("msg", "글 수정에 실패했습니다.");
    }
    return "visitorView";  
  }
  
}