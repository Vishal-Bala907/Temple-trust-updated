package com.temple.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.temple.Entities.BookArtist;
import com.temple.Entities.BookHall;
import com.temple.Entities.DarshanSlot;
import com.temple.Entities.Devotee;
import com.temple.Entities.DonationForm;
import com.temple.Entities.PoojaSlot;
import com.temple.repos.ArtistBookRepo;
import com.temple.repos.BookHallRepo;
import com.temple.repos.DonationRepo;
import com.temple.repos.PoojaSlotRepo;
import com.temple.service.UserService;

import jakarta.validation.Valid;

@Controller
public class ViewController {
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

	

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("title","Temple Trusr Donations");
		return "public/index";
	}
	
	@GetMapping("/profile")
	public String getProfile() {
		return "Users/profile";
	}

	@GetMapping("/login")
	public String getLoginPage(Model model) {
		model.addAttribute("title"," Login ");
		return "public/login";
	}

	@GetMapping("/register-page")
	public String getSignupPage(Devotee devotee,Model model) {
		model.addAttribute("title","Sign-Up Page");
		return "public/signup";
	}

	@PostMapping("/login")
	public String login(Devotee devotee , Model model) {
		model.addAttribute("title","Login Page");
		return "public/login";
	}

	@PostMapping("/register")
	public String signup(@Valid Devotee devotee, BindingResult bindingResult, Model model) {
		model.addAttribute("title","Sign-Up");
		if (bindingResult.hasErrors()) {
			model.addAttribute("devotee", devotee);
		}
		try {
			service.save(devotee);
			return "public/login";
		} catch (Exception e) {
			bindingResult.rejectValue("username", "devotee.username", " The UserName is Already Exixsts");
			bindingResult.rejectValue("email", "devotee.email", " The Email is Already Exixsts");
			e.printStackTrace();
		}
		return "public/signup";
	}

	@GetMapping("/manager/login")
	public String managerLoginPage() {
		
		return "admin/admin-Book-Artist-page";
	}

	@GetMapping("/products")
	public String productsPage(Model model) {
		model.addAttribute("title","Products Page");
		return "Users/products-page";
	}

	@GetMapping("/pooja-slot")
	public String showBookPoojaSlot(PoojaSlot poojaSlot,Model model) {
		model.addAttribute("title","Pooja Slot Booking Page");
		return "Users/Book-Pooja-Slot";
	}

	@GetMapping("/darshan-slot")
	public String bookDarshanSlot(DarshanSlot darshanSlot,Model model) {
		model.addAttribute("title","Darshan Slot Booking Page");
		return "Users/Darshan-slot";
	}

	@GetMapping("/book-artist")
	public String bookArtistForm(BookArtist artist , Model model) {
		model.addAttribute("title","Artist Booking Page");
		return "Users/Book-artist";
	}

	@GetMapping("/donate")
	public String donate(DonationForm donationForm,Model model) {
		model.addAttribute("title","Donation Page");
		return "Users/Donate";
	}
	@PostMapping("/submitDonationForm")
	public String donate(@Valid DonationForm donationForm ,BindingResult bindingResult ,Model model) {
		model.addAttribute("title","Donation Page");

		if (bindingResult.hasErrors()) {
			return "Users/Donate";
		} else {
//			darshanSlotrepo.save(darshanSlot);
			donationRepo.save(donationForm);
			model.addAttribute("donate", "Donation Request Registered Successfully !!");
			model.addAttribute("name","NAME : "+donationForm.getContactName());
			model.addAttribute("email","EMAIL : "+donationForm.getContactEmail());
			model.addAttribute("number","NUMBER : "+donationForm.getContactPhone());
			model.addAttribute("button","CLOSE");
			model.addAttribute("items",donationForm.getDonationType().toUpperCase() +" : " +donationForm.getAmountOrItems());
			model.addAttribute("reciept","RECIEPT");
			donationForm = null;
					return "Users/Donate";
		}
	}

	@GetMapping("/user-cart")
	public String userCart(Model model) {
		model.addAttribute("title","Cart Page");
		return "Users/user-cart";
	}
	@GetMapping("/about")
	public String getAbout(Model model) {
		model.addAttribute("title","about page");
		return "Users/userAbout";
	}


	@GetMapping("/payment-form")
	public String paymentForm() {
		return "Users/Payment-form";
	}

	public boolean isChecked(boolean check) throws Exception {
		if (check == true) {
			return true;
		} else {
			throw new Exception("New Exception");
		}
	}

	@PostMapping("/book-pooja-slot")
	public String bookPoojaSlot(@Valid PoojaSlot poojaSlot, BindingResult bindingResult,Model model) {
		model.addAttribute("title","Pooja Slot Booking Page");

		if (bindingResult.hasErrors()) {
			return "Users/Book-Pooja-Slot";
		} else {
			
			try {
				if(poojaSlotRepo.findByDateAndTime(poojaSlot.getDate(),poojaSlot.getTime())!=null ) {
					throw new Exception("A Pooja SLot is Already Booked in That Date and Time");
				}
			}catch(Exception e) {
				model.addAttribute("pooja", "A Pooja SLot is Already Booked in That Date and Time !!");
				model.addAttribute("name", "Please Select Another Date and Time ");
				model.addAttribute("button","CLOSE");
				
				return "Users/Book-Pooja-Slot";
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
			return "Users/Book-Pooja-Slot";
		}

	}

	@PostMapping("/book-darshan-slot")
	public String bookDarsanSlot(@Valid DarshanSlot darshanSlot, BindingResult bindingResult, Model model) {
		model.addAttribute("title","Darshan Slot Booking Page");
		if (bindingResult.hasErrors()) {
			return "Users/Darshan-slot";
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
			return "Users/Darshan-slot";
		}
		
	}

	@PostMapping("/book-artist")
	public String bookArtist(@Valid BookArtist artist,BindingResult bindingResult , Model model) {
		model.addAttribute("title","Artist Slot Booking Page");
		if (bindingResult.hasErrors()) {
			return "Users/Book-artist";
		} else {
			
			try {
				if(artistBookRepo.findByDate(artist.getDate())!= null) {
					throw new Exception("A Artist is Already Booked in this Date");
				}
			}catch(Exception e) {
				bindingResult.rejectValue("date", "artist.date", " A Artist is Already Booked in this Date");
				model.addAttribute("artist", "Artist Alredy Booked !!");
				model.addAttribute("name", "Your Money Will Be refunded back to you within 24Hours ");
				return "Users/Book-artist";
			}
			
			artistBookRepo.save(artist);
			model.addAttribute("artist", "Slot Booked For Artist Successfully !!");
			model.addAttribute("name","Artist NAME : "+artist.getArtistName());
			model.addAttribute("email","Organizer EMAIL : "+artist.getYourEmail());
			model.addAttribute("number","Organizer Name : "+artist.getYourName());
			model.addAttribute("date","DATE : "+artist.getDate());
			model.addAttribute("button","CLOSE");
			model.addAttribute("reciept","RECIEPT");
			artist = new BookArtist();
			return "Users/Book-artist";
		}
		
	
	}
	
	@GetMapping("/hall-booking")
	public String showHallBookingForm(BookHall bookHall) {
		return "Users/hall-booking";
	}
	
	@PostMapping("/book-hall")
	public String bookHall(@Valid BookHall bookHall,BindingResult bindingResult , Model model) {
		model.addAttribute("title","Artist Slot Booking Page");
		if (bindingResult.hasErrors()) {
			return "Users/hall-booking";
		} else {
			try {
				if(bookHallRepo.findByDate(bookHall.getDate())!= null) {
					throw new Exception("The Hall is Already Booked in this Date");
				}
			}catch(Exception e) {
				bindingResult.rejectValue("date", "bookHall.date", " The Hall is Already Booked in this Date");
				model.addAttribute("artist", "Hall Alredy Booked !!");
				model.addAttribute("name", "Your Money Will Be refunded back to you within 24Hours ");
				return "Users/hall-booking";
			}
			
			bookHallRepo.save(bookHall);
			model.addAttribute("artist", "Slot Booked For Artist Successfully !!");
			model.addAttribute("name","Artist NAME : "+bookHall.getEventName());
			model.addAttribute("email","Organizer EMAIL : "+bookHall.getYourName());
			model.addAttribute("number","Organizer Name : "+bookHall.getYourName());
			model.addAttribute("date","DATE : "+bookHall.getDate());
			model.addAttribute("button","CLOSE");
			model.addAttribute("reciept","RECIEPT");
			bookHall = new BookHall();
			return "Users/hall-booking";
		}
		
	
	}
	
}
