package com.webapp.controller;

import com.webapp.model.BookInventory;
import com.webapp.model.Sortby;
import com.webapp.repository.BookInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@RequestMapping("master/book")
public class BookInventoryController extends BaseController {
    @Autowired
    BookInventoryRepository bookInventoryRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView init(@RequestParam(value = "keyword",required = false) Optional<String> keyword, @RequestParam(value = "page",required = false) Optional<Integer> page,
                             @RequestParam(value = "size",required = false) Optional<Integer> size,
                             @RequestParam(value = "sort_by",required = false) Optional<String> sort_by,
                             @RequestParam(value = "sort_method",required = false) String sort_method){
        String key = keyword.isEmpty() ? "%%" : "%" + keyword.get()+"%";
        Integer pageint = page.map(integer -> (integer - 1)).orElse(0);
        Pageable paging;
        if (sort_method != null && sort_method.equalsIgnoreCase("DESC")){
            paging = PageRequest.of(pageint, size.orElse(10), Sort.by(sort_by.orElse("id")).descending());
        }else{
            paging = PageRequest.of(pageint, size.orElse(10),Sort.by(sort_by.orElse("id")).ascending());
        }
        Page<BookInventory> list = bookInventoryRepository.findAllByKeyword(key,paging);
        mvc = new ModelAndView("book");
        mvc.addObject("mode","list");
        mvc.addObject("list",list);
        mvc.addObject("sortby",getSortby());
        mvc.addObject("sort_by",sort_by.orElse("id"));
        mvc.addObject("sort_method",sort_method);
        mvc.addObject("keyword",keyword.orElse(null));

        int totalPages = list.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            mvc.addObject("pageNumbers", pageNumbers);
        }
        return mvc;
    }

    public ArrayList<Sortby> getSortby() {
        if (sortby == null){
            sortby = new ArrayList<>();
            sortby.add(new Sortby("id","ID"));
            sortby.add(new Sortby("title","Title"));
            sortby.add(new Sortby("publisher","Publisher"));
            sortby.add(new Sortby("author","Author"));
        }
        return sortby;
    }

    @RequestMapping(value="/add",method = RequestMethod.GET)
    public ModelAndView add() throws Exception{
        BookInventory books = new BookInventory();
        mvc.addObject("book",books);
        mvc.addObject("mode","edit");
        return mvc;
    }

    @RequestMapping(value = "/save", method =RequestMethod.POST)
    public String save(@ModelAttribute BookInventory books) throws Exception {
        bookInventoryRepository.save(books);
        return "redirect:/master/book/";
    }

    @RequestMapping("/edit")
    public ModelAndView edit(@RequestParam(name="id") Long id) throws Exception{
        Optional<BookInventory> books = bookInventoryRepository.findById(id);
        if (books.isEmpty()) throw new Exception("can not found book with id :"+id+"!");
        mvc.addObject("book",books.get());
        mvc.addObject("mode","edit");
        return mvc;
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam (name="id") Long id) {
        bookInventoryRepository.deleteById(id);
        return "redirect:/master/book/";
    }
}
