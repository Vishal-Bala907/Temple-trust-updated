package com.temple.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.temple.Entities.BookArtist;
import com.temple.Entities.DarshanSlot;
import com.temple.Entities.DonationForm;
import com.temple.Entities.PoojaSlot;
import com.temple.Entities.Products;
import com.temple.fileService.FileService;
import com.temple.repos.ArtistBookRepo;
import com.temple.repos.BookHallRepo;
import com.temple.repos.DonationRepo;
import com.temple.repos.FileUploadRepo;
import com.temple.repos.PoojaSlotRepo;
import com.temple.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	UserService service;
	@Autowired
	PoojaSlotRepo poojaSlotRepo;
	@Autowired
	com.temple.repos.DarshanSlotRepo darshanSlotrepo;
	@Autowired
	ArtistBookRepo artistBookRepo;
	@Autowired
	DonationRepo donationRepo;
	@Autowired
	BookHallRepo bookHallRepo;

	@Autowired
	private FileService fileService;
	@Value("${project.images}")
	private String path;
	@Autowired
	FileUploadRepo repo;
	
	@GetMapping("/user-cart")
	public String userCart(Model model) {
		model.addAttribute("title","Cart Page");
		return "admin/user-cart";
	}
	@GetMapping("/about")
	public String getAbout(Model model) {
		model.addAttribute("title","about page");
		return "admin/adminAbout";
	}
	
	@GetMapping("/profile")
	public String getProfile(Model model) {
		model.addAttribute("title","Profile page");
		return "admin/admin-profile";
	}


	@GetMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("title","Shop Page");
		return "/admin/admin-shop-page";
	}

	@GetMapping("/artist")
	public String artist(BookArtist artist,Model model) {
		model.addAttribute("title","Book Artist Page");
		return "/admin/admin-Book-Artist-page";
	}

	@PostMapping("/book-artist")
	public String bookArtist(@Valid BookArtist artist, BindingResult bindingResult, Model model) {
		model.addAttribute("title", "Artist Slot Booking Page");
		if (bindingResult.hasErrors()) {
			return "/admin/admin-Book-Artist-page";
		} else {
			
			try {
				if(artistBookRepo.findByDate(artist.getDate())!= null) {
					throw new Exception("A Artist is Already Booked in this Date");
				}
			}catch(Exception e) {
				bindingResult.rejectValue("date", "artist.date", " A Artist is Already Booked in this Date");
				model.addAttribute("artist", "Artist Alredy Booked !!");
				model.addAttribute("name", "Your Money Will Be refunded back to you within 24Hours ");
				return "/admin/admin-Book-Artist-page";	
			}
			
			artistBookRepo.save(artist);
			model.addAttribute("artist", "Slot Booked For Artist Successfully !!");
			model.addAttribute("name", "Artist NAME : " + artist.getArtistName());
			model.addAttribute("email", "Organizer EMAIL : " + artist.getYourName());
			model.addAttribute("number", "Organizer Name : " + artist.getYourName());
			model.addAttribute("date", "DATE : " + artist.getDate());
			model.addAttribute("button","CLOSE");
			model.addAttribute("reciept","RECIEPT");
			artist = new BookArtist();
			return "/admin/admin-Book-Artist-page";	
		}

	}

	@GetMapping("/pooja")
	public String pooja(PoojaSlot poojaSlot,Model model) {
		model.addAttribute("title","pooja Slot page");
		return "/admin/admin-pooja-slot";
	}
	@PostMapping("/book-pooja-slot")
	public String bookPoojaSlot(@Valid PoojaSlot poojaSlot, BindingResult bindingResult,Model model) {
		model.addAttribute("title","Pooja Slot Booking Page");

		if (bindingResult.hasErrors()) {
			return "/admin/admin-pooja-slot";
		} else {

			try {
				if(poojaSlotRepo.findByDateAndTime(poojaSlot.getDate(),poojaSlot.getTime())!=null ) {
					throw new Exception("A Pooja SLot is Already Booked in That Date and Time");
				}
			}catch(Exception e) {
				model.addAttribute("pooja", "A Pooja SLot is Already Booked in That Date and Time !!");
				model.addAttribute("name", "Please Select Another Date and Time ");
				return "/admin/admin-pooja-slot";
			}
			
//			darshanSlotrepo.save(darshanSlot);
			poojaSlotRepo.save(poojaSlot);
			model.addAttribute("pooja", "Slot Booked For Pooja Successfully !!");
			model.addAttribute("name","NAME : "+poojaSlot.getName());
			model.addAttribute("email","EMAIL : "+poojaSlot.getEmail());
			model.addAttribute("number","NUMBER : "+poojaSlot.getNumber());
			model.addAttribute("date","DATE : "+poojaSlot.getDate());
			model.addAttribute("time","TIME : "+poojaSlot.getTime());
			model.addAttribute("button","CLOSE");
			model.addAttribute("reciept","RECIEPT");
			poojaSlot = new PoojaSlot();
			return "/admin/admin-pooja-slot";
		}

	}

	@GetMapping("/darshan")
	public String darshan(DarshanSlot darshanSlot,Model model) {
		model.addAttribute("title","Darshan Slot page");
		return "/admin/admin-darshan-slot";
	}
	@PostMapping("/book-darshan-slot")
	public String bookDarsanSlot(@Valid DarshanSlot darshanSlot, BindingResult bindingResult, Model model) {
		model.addAttribute("title","Darshan Slot Booking Page");
		if (bindingResult.hasErrors()) {
			return "/admin/admin-darshan-slot";
		} else {
			darshanSlotrepo.save(darshanSlot);
			model.addAttribute("darshan", "Slot Booked For Darshan Successfully !!");
			model.addAttribute("name","NAME : "+darshanSlot.getName());
			model.addAttribute("email","EMAIL : "+darshanSlot.getEmail());
			model.addAttribute("number","NUMBER : "+darshanSlot.getNumber());
			model.addAttribute("date","DATE : "+darshanSlot.getDate());
			model.addAttribute("time","TIME : "+darshanSlot.getTime());
			model.addAttribute("button","CLOSE");
			model.addAttribute("reciept","RECIEPT");
			darshanSlot = new DarshanSlot();
			return "/admin/admin-darshan-slot";
		}
		
	}

	@GetMapping("/donate")
	public String donate(DonationForm donationForm,Model model) {
		model.addAttribute("title","Donation Page");
		return "/admin/admin-donate";
	}

	@GetMapping("/additem")
	public String addItemForm(Products products,Model model) {
		model.addAttribute("title","Add Items Page");
		return "/admin/admin-add-item-page";
	}

	@PostMapping("/upload")
	public String uploadItem(@Valid Products products, BindingResult result, @RequestParam("image") MultipartFile image,
			Model model) {
		model.addAttribute("title","Add Items Page");
		if (result.hasErrors()) {
			model.addAttribute("products", products);
		}

		Products fileUpload = this.fileService.fileUpload(path, products, image);
//		System.out.println(fileUpload.getImageUrl());
		repo.save(fileUpload);
		model.addAttribute("upload", "Item Added Successfully");
		return "/admin/admin-add-item-page";

	}
	
	@GetMapping("/delete-item")
	public String deleteItem(Model model) {
		model.addAttribute("title","Delete Item Page");
		return "admin/admin-remove-items";
	}

}
