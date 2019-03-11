package com.teste.voiture.web;

import java.io.File;
import java.io.FileInputStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.teste.voiture.Entity.Voiture;
import com.teste.voiture.dao.voitureRepository;


@Controller
@RequestMapping(value="/voiture")
public class voitureController {
	@Autowired
	private voitureRepository vRepo;
	@Value("${dir.images}")
	private String dirImage;
	
	@RequestMapping(value="/index")
	public String index(Model model,@RequestParam(name="page",defaultValue="0") int p,
									@RequestParam(name="size",defaultValue="3")int s,
									@RequestParam(name="voitureNom",defaultValue="")String vNom)
	{
		@SuppressWarnings("deprecation")
		Page<Voiture> pageVoitures = vRepo.chercherVoiture("%"+vNom+"%",new PageRequest(p, s));
		model.addAttribute("voitures", pageVoitures);
		int pageCount=pageVoitures.getTotalPages();
		int[] pages = new int[pageCount];
		for (int i = 1; i < pageCount; i++) {
			 pages[i]=i;
		}
		model.addAttribute("pages",pages);
		model.addAttribute("pageCourante",p);
		model.addAttribute("voitureNom",vNom);
		return "voiture";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(Long id, String page, String n) {
		vRepo.deleteById(id); 
		String xx = " ";
		if (n == null)
		 	n=xx;
		else 
			xx=n;
		return "redirect:index?page="+page+"&voitureNom="+xx;
	}
	
	@RequestMapping(value="/formAdd",method=RequestMethod.GET)
	public String formAdd(Model model) {
		model.addAttribute("car",new Voiture());
		return "formaddcar";
	}
	
	@RequestMapping(value="/AddCar",method=RequestMethod.POST)
	public String addCar(@Valid Voiture v,@RequestParam(name="pictuer") MultipartFile photo,Model model) 
			throws Exception {
	
		if (!(photo.isEmpty())) {
			v.setPhoto(photo.getOriginalFilename());
		}
		vRepo.save(v);
		if (!(photo.isEmpty())) {
			v.setPhoto(photo.getOriginalFilename());
			photo.transferTo(new File(dirImage+v.getId()));
		}
		
		return "redirect:index";
	}
	@RequestMapping(value="/getPhoto",produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto(Long id) throws Exception{
		File f = new File(dirImage+id);
		return org.apache.commons.io.IOUtils.toByteArray(new FileInputStream(f));	
	}
	
	@RequestMapping(value="/edit")
	public String edit(Long id, Model model) {
		Voiture v = vRepo.getOne(id);
		model.addAttribute("vo",v);
		return "EditVoiture";
	}
	
	@RequestMapping(value="/UpdateCar",method=RequestMethod.POST)
	public String update(@Valid Voiture v,@RequestParam(name="pictuer") 
	MultipartFile photo, BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors())
		{
			return "/edit";
		}
		
		if (!(photo.isEmpty())) {
			v.setPhoto(photo.getOriginalFilename());
		}
		vRepo.save(v);
		if (!(photo.isEmpty())) {
			v.setPhoto(photo.getOriginalFilename());
			photo.transferTo(new File(dirImage+v.getId()));
		}
		return "redirect:index";
	}
	
}
