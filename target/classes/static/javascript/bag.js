
let bagArray = []
let userprofile

// Function to save cart data to local storage
function saveCartToLocalStorage(key) {
	localStorage.setItem(key, JSON.stringify(bagArray));
}

// Function to load cart data from local storage
function loadCartFromLocalStorage(key) {
	const cartData = localStorage.getItem(key);
	console.log(cartData)
	if (cartData) {
		bagArray = JSON.parse(cartData);
		console.log(bagArray)
	}
}

// Load cart data from local storage when the page loads
window.addEventListener('load', async function() {
	userprofile = await getUserProfile()
	console.log(userprofile)

	loadCartFromLocalStorage(userprofile.username);

	document.getElementById('cart').innerText = bagArray.length


	displayCart()
	createCartBill()
	makePayment()
	document.getElementById('artist-book-confirm').disabled = Boolean(localStorage.getItem('disable'))
	//displayCart(); // Display cart items on the page
});

async function addItemToBag(item) {
	/**/
	console.log("add to bag clicked", item)
	/*Gettiong the username*/

	userprofile = await getUserProfile();
	bagArray.push(item)

	// using key 
	saveCartToLocalStorage(userprofile.username);

	document.getElementById('cart').innerText = bagArray.length
	createCartBill()
}


// function Used to remove Items From Shop list
function removeItemFromList(item) {
	console.log(item)
	fetch('http://localhost:8080/delete-item', {
		method: 'DELETE',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(item)
	})
		.then(response => {
			// Check if the response is successful
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			// Parse the JSON response
			return response.json();
		})
		.then(data => {
			// Use the fetched data
			location.reload();
			console.log(data);
		})
		.catch(error => {
			// Handle any errors that occur during the fetch
			console.error('Error:', error);
		});

}

function removeFromBag(item) {
	const elementToRemove = document.getElementById(item.p_id);
	if (elementToRemove) {
		elementToRemove.remove();
		bagArray = bagArray.filter((bagItem) => bagItem.p_id !== item.p_id);
		saveCartToLocalStorage(userprofile.username);
		document.getElementById('cart').innerText = bagArray.length;
		createCartBill()
		location.reload()
		makePayment()
	} else {
		console.error(`Element with ID ${document.getElementById(item.p_id)} not found.`);
	}
}


let cartHtml = "";

// Function to display cart items
function displayCart() {
	let cartHtml = "";
	const cartItemsElement = document.getElementById('cart-items');
	if (bagArray.length === 0) {
		cartHtml += `<div style="display:flex;justify-content:center;align-items:center; height:60%;font-size:xxx-large;">
                        <h1 style="font-size:xxx-large;"> No Items In The Cart </h1>
                    </div>`;
	} else {
		bagArray.forEach(item => {
			cartHtml += `<div class="card" style="width: 18rem; height: fit-content;" id=${item.p_id}>
                            <img src="/${item.imageUrl}" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="card-title">${item.name}</h5>
                                <h3 class="card-title">Rs. ${item.price}</h3>
                                <p class="card-text">${item.description}</p>
                                <button  style="margin-left:20%;" class="btn btn-danger ml-auto remove-from-bag" id="button-${item.p_id}">Remove From Bag</button>
                            </div>
                        </div>`;
		});
	}
	cartItemsElement.innerHTML = cartHtml;

	// Attach event listeners to remove items from bag
	bagArray.forEach((item) => {
		document.getElementById(`button-${item.p_id}`).addEventListener("click", () => removeFromBag(item));
	});
}

let cartBillHtml = "";
let totalPrice = 0;
let createCartBill = () => {
	bagArray.forEach(item => {
		/*shop.push(item)*/
		totalPrice += item.price
		cartBillHtml += ` 
            <section class="cart-items-bill">
							<div class="itemName"><b>	${item.name} : </b></div>
							<div class="itemPrice">${item.price} Rs</div>
						</section>
							<hr />`
	});

	localStorage.setItem("price", totalPrice);
	document.getElementById("card-bill").innerHTML = cartBillHtml;
	document.getElementById("total-price").innerText = `${totalPrice} Rs`
}

document.getElementById('numberOfDays').addEventListener('keyup', (e) => {
	var positiveIntegerRegex = /^[1-9]\d*$/;
	let perDayCharge = 5000;
	if (positiveIntegerRegex.test(e.key) || e.key === 'Backspace') {
		var days = Number(document.getElementById('numberOfDays').value)
		var charges = days * perDayCharge;
		document.getElementById('charges').value = charges
	} else {
		alert("Please Enter a Number")
	}
})


// Validates the from Filled by the user , ArtistBooking Form
function checkFormValidation() {
	let mark = true;
	if (document.getElementById('artistName').value.length < 6) {
		document.getElementById('form-an').innerText = 'Enter A Name With Atlest 5 Charcters'
		mark = false;
	} else {
		document.getElementById('form-an').innerText = ""
	}

	if (document.getElementById('eventDate').value === '') {
		document.getElementById('e-date').innerText = "Please Select a date"
		mark = false;
	} else {
		document.getElementById('e-date').innerText = ""
	}

	if (Number(document.getElementById('numberOfDays').value) < 1) {
		document.getElementById('form-dy').innerText = 'Enter A Valid Number Of Days '
		mark = false;
	} else {
		document.getElementById('form-dy').innerText = ""
	}
	if (document.getElementById('contactName').value.length < 5) {
		document.getElementById('form-yn').innerText = 'Enter A Name With Atlest 5 Charcters'
		mark = false;
	} else {
		document.getElementById('form-yn').innerText = ""
	}
	if (document.getElementById('contactPhone').value.length < 10) {
		document.getElementById('form-yp').innerText = 'Enter A Valid Number'
		mark = false;
	} else {
		document.getElementById('form-yp').innerText = ""
	}

	return mark;
}


// Handling payment first, of Artist Booking
document.getElementById('artist-submit').addEventListener('click', () => {
	let amount = document.getElementById('charges').value;
	let name = document.getElementById('contactName').value
	let contact = document.getElementById('contactPhone').value
	let email = document.getElementById('contactEmail').value
	console.log(amount)

	if (!checkFormValidation()) {
		console.log("hello checking again")
		return;
	} else {

		var options = {
			key: 'rzp_test_KzfyJqkNOcGlv7', // Replace with your Razorpay API key
			amount: amount + "00", // Amount in smallest currency unit (e.g., paisa for INR)
			currency: 'INR', // Currency code (e.g., INR for Indian Rupees)
			name: 'Temple Trsut Donation',
			description: 'Items Realted To Temple and GOd',
			image: null, // Logo or image URL
			handler: function(response) {
				// Handle successful payment
				alert('Payment successful: ' + response.razorpay_payment_id);
				document.getElementById('artist-book-confirm').disabled = false;
				localStorage.setItem('disable', false);
			},
			prefill: {
				name: name, // Buyer's name
				email: email, // Buyer's email
				contact: contact // Buyer's contact number
			},
			theme: {
				color: '#F37254' // Customize the checkout theme color
			}
		};

		var rzp = new Razorpay(options);
		rzp.open();
	}
})


function closeTheModal() {
	const model = document.getElementById('modal');
	model.style.display = "none"
	model.style.visibility = 'hidden'
	window.location.href = '/admin/shop';
}
function closeTheUserModal() {
	const model = document.getElementById('modal');
	model.style.display = "none"
	model.style.visibility = 'hidden'
	window.location.href = '/products';
}





