package com.min.springjpa.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.min.springjpa.entity.Meeting;
import com.min.springjpa.entity.Reply;
import com.min.springjpa.repository.MeetingRepository;
import com.min.springjpa.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Controller
public class MeetingController  {
	
  private final MeetingRepository meetingRepository;
  private final ReplyRepository replyRepository;
  
  @Transactional(readOnly = true)
  @GetMapping("/meeting")
  public ModelAndView meeting() {
    List<Meeting> list = meetingRepository.findAll();
    ModelAndView mav = new ModelAndView();  
    if (list.size() != 0) {
      mav.addObject("list", list);
    } else {
      mav.addObject("msg", "추출된 결과가 없어요");
    }
    mav.setViewName("meetingView");
    return mav;
  }
  
  @Transactional(readOnly = true)
  @GetMapping("/meeting/search")
  public ModelAndView meetingSearch(String keyword) {
    List<Meeting> list = meetingRepository.findByTitleContains(keyword);
    ModelAndView mav = new ModelAndView();  
    if (list.size() != 0) {
      mav.addObject("list", list);
      mav.addObject("button", "메인화면으로");
    } else {
      mav.addObject("msg", "추출된 결과가 없어요");
    }
    mav.setViewName("meetingView");
    return mav;
  }
  
  @Transactional(readOnly = true)
  @GetMapping("/meeting/searchname")
  public ModelAndView meetingSearchName(String name) {
    List<Meeting> list = meetingRepository.findByName(name);
    ModelAndView mav = new ModelAndView();  
    if (list.size() != 0) {
      mav.addObject("list", list);
      mav.addObject("button", "메인화면으로");
    } else {
      mav.addObject("msg", "추출된 결과가 없어요");
    }
    mav.setViewName("meetingView");
    return mav;
  }
  
  @GetMapping("/meeting/delete")
  public ModelAndView meetingDelete(int id) {     
    ModelAndView mav = new ModelAndView();
    try {
      meetingRepository.deleteById(id);
      mav.addObject("list", meetingRepository.findAll());        
    } catch(Exception e) {
      mav.addObject("msg", "삭제를 처리하는 동안 오류 발생");
    }
    mav.setViewName("meetingView");
    return mav;
  }
  
  @Transactional(readOnly = true)
  @GetMapping(value ="/meeting/one", produces = "application/json")
  public ResponseEntity<Meeting> mettingOne(int id) {
    return ResponseEntity.ok(meetingRepository.findById(id).get());
  }  
  
  @PostMapping("/meeting/insert")
  public ModelAndView meetingInsert(Meeting meeting) {
    ModelAndView mav = new ModelAndView();
    try {
      meetingRepository.save(meeting);
      mav.addObject("list", meetingRepository.findAll());        
    } catch(Exception e) {
      mav.addObject("msg", "글 작성을 처리하는 동안 오류 발생");
    }
    mav.setViewName("meetingView");
    return mav;    
  }
  
  @PostMapping("/meeting/update")
  public ModelAndView meetingUpdate(Meeting meeting) {
    ModelAndView mav = new ModelAndView();
    try {
      Meeting oldvo = meetingRepository.findById(meeting.getId()).get();
      oldvo.setName(meeting.getName());
      oldvo.setTitle(meeting.getTitle());
      oldvo.setMeetingDate(meeting.getMeetingDate());
      mav.addObject("list", meetingRepository.findAll());        
    } catch(Exception e) {
      mav.addObject("msg", "글 작성을 수정하는 동안 오류 발생");
    }
    mav.setViewName("meetingView");
    return mav;
  }  
  
  @GetMapping(value="/meeting/ireply", produces = "application/json")
  public ResponseEntity<Map<String, Boolean>> meetingIreply(String name, String content, int refid) {
    Meeting mainWriting = meetingRepository.findById(refid).get();
    Reply reply = new Reply();
    reply.setName(name);
    reply.setContent(content);    
    reply.setRefid(mainWriting);
    try {
      replyRepository.save(reply);
      return ResponseEntity.ok(Map.of("result", true));
    } catch(Exception e) {
      return ResponseEntity.ok(Map.of("result", false));
    }
  }
  
  @Transactional(readOnly = true)
  @GetMapping(value="/meeting/lreply", produces = "application/json")
  public ResponseEntity<List<Reply>> meetingLreply(int refid) {
    return ResponseEntity.ok(replyRepository.findByRefidId(refid));
  }
  
}
